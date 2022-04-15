package org.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DALException;
import org.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	
	private Connection connection = null;
	private PreparedStatement pstmt = null;

	@Override
	
	//---------Insértion des données de l'utilisateur dans la base de données
	
	public void insert(Utilisateur utilisateur) throws DALException {
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("INSERT INTO UTILISATEURS (pseudo,nom,prénom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.getAdministrateur());
			int rows = pstmt.executeUpdate();
            if (rows != 0) {
                ResultSet resultSet = pstmt.getGeneratedKeys();
                if (resultSet.next()) { utilisateur.setNoUtilisateur(resultSet.getInt(1)); }
            }
			pstmt.close();
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'insertion d'une liste", e);
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
	
	//Supression d'un utilisateur
	
	@Override
	public void delete(int id) throws DALException {

		try {
			connection = JDBCTools.getConnection();
			String userDelete = "DELETE * FROM UTILISATEUR WHERE no_utilisateur=?";
			pstmt = connection.prepareStatement(userDelete);
			pstmt.setInt(1, id);
			
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la supression d'un utilisateur", e);
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
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		
		Utilisateur utilisateur = null;
		
		try {
			connection = JDBCTools.getConnection();
			
			pstmt = connection.prepareStatement("SELECT * FROM Utilisateurs ORDER BY no_utilisateur");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
					listeUtilisateurs.add(utilisateur);
			}
			
		}catch (SQLException e){
			throw new DALException("Erreur lors de la sélection des utilisateurs", e);
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
		return listeUtilisateurs;
	}

	@Override
	public Utilisateur selectByID(int id) throws DALException {
		
		Utilisateur utilisateur = null;

		try {
			connection = JDBCTools.getConnection();
			pstmt = connection.prepareStatement("SELECT * FROM  UTILISATEUR WHERE no_utilisateur=?");
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
			
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la selection d'un utilisateur", e);
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
		
		return utilisateur;
	}

	@Override
	public void update(Utilisateur utilisateur) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur selectByLogin(String login, String motDePasse) throws DALException {


		Utilisateur utilisateur = null;

		try {
			connection = JDBCTools.getConnection();
			pstmt = connection.prepareStatement("SELECT * FROM  UTILISATEUR WHERE no_utilisateur=?");
			// pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
			
			
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la selection d'un utilisateur", e);
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
		
		return utilisateur;
}
}
