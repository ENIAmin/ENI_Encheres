package org.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.DALException;
import org.eni.encheres.dal.DAO;

public class CategorieDAOJdbcImpl implements DAO<Categorie> {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	
	@Override
	public void insert(Categorie categorie) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("INSERT INTO CATEGORIES (libelle) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, categorie.getLibelle());
			int rows = pstmt.executeUpdate();
            if (rows != 0) {
                ResultSet resultSet = pstmt.getGeneratedKeys();
                if (resultSet.next()) { categorie.setNoCategorie(resultSet.getInt(1)); }
            }
			pstmt.close();
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'insertion d'une catégorie", e);
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
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		
		Categorie categorie = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM CATEGORIES");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				listeCategories.add(categorie);
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection des catégories", e);
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
		return listeCategories;
	}

	@Override
	public Categorie selectByID(int id) throws DALException {
		
		Categorie categorie = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM CATEGORIES WHERE no_categorie = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection de la catégorie", e);
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
		return categorie;
	}

	@Override
	public void update(Categorie categorie) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?");
			pstmt.setString(1, categorie.getLibelle());
			pstmt.setInt(2, categorie.getNoCategorie());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la mise à jour d'une catégorie", e);
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
			String articleDelete = "DELETE * FROM CATEGORIES WHERE no_categorie= ?";
			pstmt = connection.prepareStatement(articleDelete);
			pstmt.setInt(1, id);
			
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la supression d'une catégorie", e);
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

}
