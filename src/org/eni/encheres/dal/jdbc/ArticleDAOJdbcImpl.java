package org.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.Article;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.ArticleDAO;
import org.eni.encheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO{
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	
	@Override
	public void insert(Article article) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, article.getDateDebutEncheres());
			pstmt.setDate(4, article.getDateFinEncheres());
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getNoUtilisateur());
			pstmt.setInt(7, article.getNoCategorie());
			int rows = pstmt.executeUpdate();
            if (rows != 0) {
                ResultSet resultSet = pstmt.getGeneratedKeys();
                if (resultSet.next()) { article.setNoArticle(resultSet.getInt(1)); }
            }
			pstmt.close();
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'insertion d'un article", e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
		
	}

	@Override
	public List<Article> selectAll() throws DALException {
		List<Article> listeArticles = new ArrayList<Article>();
		
		Article article = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM ARTICLES_VENDUS");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				article = new Article(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("etat_vente"), rs.getInt("no_categorie"));
				listeArticles.add(article);
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection des articles", e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
		return listeArticles;
	}

	@Override
	public Article selectByID(int id) throws DALException {	
		Article article = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				article = new Article(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("etat_vente"), rs.getInt("no_categorie"));
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection des articles", e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
		return article;
	}

	@Override
	public void update(Article article) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("UPDATE ARTICLES_VENDUS SET prix_vente = ?, etat_vente = ? WHERE no_article = ?");
			pstmt.setInt(1, article.getPrixVente());
			pstmt.setString(2, article.getEtatVente());
			pstmt.setInt(3, article.getNoArticle());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de a mise à jour d'un article", e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
		
	}

	@Override
	public void delete(int id) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			String articleDelete = "DELETE * FROM ARTICLES_VENDUS WHERE no_article=?";
			pstmt = connection.prepareStatement(articleDelete);
			pstmt.setInt(1, id);
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la supression d'un article", e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Article> selectByCategorie(Categorie categorie) throws DALException {
		List<Article> listeArticles = new ArrayList<Article>();
		
		Article article = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ?");
			pstmt.setInt(1, categorie.getNoCategorie());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				article = new Article(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("etat_vente"), rs.getInt("no_categorie"));
				listeArticles.add(article);
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection des articles", e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
		return listeArticles;
	}

	@Override
	public List<Article> selectByMotsClefs(String motCle) throws DALException {
		List<Article> listeArticles = new ArrayList<Article>();
		
		Article article = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE no_categorie LIKE %?%");
			pstmt.setString(1, motCle);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				article = new Article(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("etat_vente"), rs.getInt("no_categorie"));
				listeArticles.add(article);
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection des articles", e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
		return listeArticles;
	}

	@Override
	public List<Article> selectByStatus(String status) throws DALException {
		List<Article> listeArticles = new ArrayList<Article>();
		
		Article article = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE etat_vente = ?");
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				article = new Article(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("etat_vente"), rs.getInt("no_categorie"));
				listeArticles.add(article);
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection des articles", e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
		return listeArticles;
	}

}
