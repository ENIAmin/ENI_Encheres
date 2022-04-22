package org.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.Enchere;
import org.eni.encheres.dal.DALException;
import org.eni.encheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO{
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	
	@Override
	public void insert(Enchere enchere) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere) VALUES (?,?,?,?)");
			pstmt.setInt(1, enchere.getNoUtilisateur());
			pstmt.setInt(2, enchere.getNoArticle());
			pstmt.setTimestamp(3, Timestamp.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchere());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'insertion d'une enchère", e);
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
	public List<Enchere> selectAll() throws DALException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		Enchere enchere = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM ENCHERES");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"));
				listeEncheres.add(enchere);
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection des enchères", e);
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
		return listeEncheres;
	}

	@Override
	public Enchere selectByID(int id) throws DALException {
		
		Enchere enchere = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM ENCHERES WHERE no_article = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"));
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection de l'enchère", e);
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
		return enchere;
	}

	@Override
	public void update(Enchere enchere) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("UPDATE ENCHERES SET date_enchere = ?, montant_enchere = ? WHERE no_article = ? AND no_utilisateur = ?");
			pstmt.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(2, enchere.getMontantEnchere());
			pstmt.setInt(3, enchere.getNoArticle());
			pstmt.setInt(4, enchere.getNoUtilisateur());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la mise à jour d'une enchère", e);
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
	public void delete(int idUtilisateur, int idArticle) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			String articleDelete = "DELETE * FROM ENCHERE WHERE no_article= ? AND no_utilisateur = ?";
			pstmt = connection.prepareStatement(articleDelete);
			pstmt.setInt(1, idUtilisateur);
			pstmt.setInt(2, idArticle);
			
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la supression d'une enchère", e);
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
	public List<Enchere> selectByUtilisateur(int id) throws DALException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		Enchere enchere = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM ENCHERES WHERE no_utilisateur = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"));
				listeEncheres.add(enchere);
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection des enchères", e);
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
		return listeEncheres;
	}

	@Override
	public void delete(int id) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			String articleDelete = "DELETE * FROM ENCHERES WHERE no_article= ?";
			pstmt = connection.prepareStatement(articleDelete);
			pstmt.setInt(1, id);
			
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la supression d'une enchère", e);
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
	public Enchere selectByArticle(int id) throws DALException {
		
		Enchere enchere = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM ENCHERES WHERE no_article = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"));
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection de l'enchère", e);
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
		return enchere;
	}
}
