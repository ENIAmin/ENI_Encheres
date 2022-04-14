package org.eni.encheres.bll;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DALException;
import org.eni.encheres.dal.DAO;
import org.eni.encheres.dal.DAOFactory;


public class UtilisateurManager {
	private DAO<Utilisateur> utilisateurDAO;
	private static UtilisateurManager instance;
	
	public UtilisateurManager() throws BLLException{
		super();
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public static UtilisateurManager getInstance() throws BLLException {
		if(instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	public void addUtilisateur(Utilisateur utilisateur) throws BLLException{
		try {
            validerUtilisateur(utilisateur);
            utilisateurDAO.insert(utilisateur);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de l'ajout d'un nouvel utilisateur", exc);
        }
	}
	
	public void removeUtilisateur(int index) throws BLLException{
		try {
			utilisateurDAO.delete(index);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la suppression de l'utilisateur.", exc);
        }
	}
	
	public void updateUtilisateur(Utilisateur utilisateur) throws BLLException{
		try {
            validerUtilisateur(utilisateur);
            utilisateurDAO.update(utilisateur);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de l'ajout d'un nouvel utilisateur", exc);
        }
	}
	
	public Utilisateur getUtilisateur(int index) throws BLLException{
		try {
            return utilisateurDAO.selectByID(index);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération de l'utilisateur par identifiant", exc);
        }
	}
	
	public void validerUtilisateur(Utilisateur utilisateur) throws BLLException{
		if (utilisateur == null) { 
			throw new BLLException("Utilisateur invalide"); 
		}
		
        if (utilisateur.getNom() == null){
        	throw new BLLException("Nom invalide"); 
        }
        if (utilisateur.getPrenom() == null){
        	throw new BLLException("Prenom invalide"); 
        }
        if (utilisateur.getPseudo() == null){
        	throw new BLLException("Pseudo invalide"); 
        }
        if (utilisateur.getEmail() == null){
        	throw new BLLException("Email invalide"); 
        }
        if (utilisateur.getTelephone() == null){
        	throw new BLLException("Téléhpone invalide"); 
        }
        if (utilisateur.getRue() == null){
        	throw new BLLException("Rue invalide"); 
        }
        if (utilisateur.getCodePostal() == null){
        	throw new BLLException("Code Postal invalide"); 
        }
        if (utilisateur.getVille() == null){
        	throw new BLLException("Ville invalide"); 
        }
        if (utilisateur.getMotDePasse() == null){
        	throw new BLLException("Mot De Passe invalide"); 
        }
	}
}
