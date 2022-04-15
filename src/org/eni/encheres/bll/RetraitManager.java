package org.eni.encheres.bll;

import java.util.List;

import org.eni.encheres.bo.Retrait;
import org.eni.encheres.dal.DALException;
import org.eni.encheres.dal.DAO;
import org.eni.encheres.dal.DAOFactory;

public class RetraitManager {
	private DAO<Retrait> retraitDAO;
	private static RetraitManager instance;
	
	public RetraitManager() throws BLLException{
		super();
		retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public static RetraitManager getInstance() throws BLLException {
		if(instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}
	
	public void addRetrait(Retrait retrait) throws BLLException{
		try {
            validerRetrait(retrait);
            retraitDAO.insert(retrait);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de l'ajout d'un nouveau retrait", exc);
        }
	}
	
	public void removeRetrait(int index) throws BLLException{
		try {
			retraitDAO.delete(index);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la suppression d'un retrait.", exc);
        }
	}
	
	public void updateRetrait(Retrait retrait) throws BLLException{
		try {
			validerRetrait(retrait);
			retraitDAO.update(retrait);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la mise à jour d'un retrait", exc);
        }
	}
	
	public Retrait getRetrait(int index) throws BLLException{
		try {
            return retraitDAO.selectByID(index);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération d'un retrait par identifiant", exc);
        }
	}
	
	public List<Retrait> getAllRetraits() throws BLLException{
		try {
            return retraitDAO.selectAll();
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération des retraits", exc);
        }
	}
	
	public void validerRetrait(Retrait retrait) throws BLLException{
		if (retrait == null) { 
			throw new BLLException("Retrait invalide"); 
		}
		
        if (retrait.getRue() == null){
        	throw new BLLException("Rue invalide"); 
        }
        if (retrait.getCodePostal() == null){
        	throw new BLLException("Code Postal invalide"); 
        }
        if (retrait.getVille() == null){
        	throw new BLLException("Ville invalide"); 
        }
	}
}
