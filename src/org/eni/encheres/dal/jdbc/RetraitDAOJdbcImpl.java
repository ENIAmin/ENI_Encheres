package org.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.Retrait;
import org.eni.encheres.dal.DALException;
import org.eni.encheres.dal.DAO;

public class RetraitDAOJdbcImpl implements DAO<Retrait>{
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	
	@Override
	public void insert(Retrait retrait) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("INSERT INTO RETRAITS (no_article,rue,code_postal,ville) VALUES (?,?,?,?)");
			pstmt.setInt(1, retrait.getNoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'insertion d'un retrait", e);
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
	public List<Retrait> selectAll() throws DALException {
		List<Retrait> listeRetraits = new ArrayList<Retrait>();
		
		Retrait retrait = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM RETRAITS");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				retrait = new Retrait(rs.getString("ville"), rs.getString("rue"), rs.getString("code_postal"), rs.getInt("no_article"));
				listeRetraits.add(retrait);
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection des retraits", e);
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
		return listeRetraits;
	}

	@Override
	public Retrait selectByID(int id) throws DALException {
		
		Retrait retrait = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM RETRAITS WHERE no_article = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				retrait = new Retrait(rs.getString("ville"), rs.getString("rue"), rs.getString("code_postal"), rs.getInt("no_article"));
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection du retrait", e);
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
		return retrait;
	}

	@Override
	public void update(Retrait retrait) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?");
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());
			pstmt.setInt(4, retrait.getNoArticle());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la mise à jour d'un article", e);
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
			String articleDelete = "DELETE * FROM RETRIATS WHERE no_article=?";
			pstmt = connection.prepareStatement(articleDelete);
			pstmt.setInt(1, id);
			
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la supression d'un retrait", e);
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
