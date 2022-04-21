package org.eni.encheres.bll;

import java.util.List;

import org.eni.encheres.bo.Enchere;
import org.eni.encheres.dal.DALException;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	private EnchereDAO enchereDAO;
	private static EnchereManager instance;
	
	public EnchereManager() throws BLLException{
		super();
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public static EnchereManager getInstance() throws BLLException {
		if(instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public void addEnchere(Enchere enchere) throws BLLException{
		try {
            validerEnchere(enchere);
            enchereDAO.insert(enchere);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de l'ajout d'une nouvelle enchère", exc);
        }
	}
	
	public void removeEnchere(int index) throws BLLException{
		try {
			enchereDAO.delete(index);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la suppression d'une enchère.", exc);
        }
	}
	
	public void updateEnchere(Enchere enchere) throws BLLException{
		try {
			validerEnchere(enchere);
			enchereDAO.update(enchere);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la mise à jour d'une enchère", exc);
        }
	}
	
	public Enchere getEnchere(int index) throws BLLException{
		try {
            return enchereDAO.selectByID(index);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération d'une enchère par identifiant", exc);
        }
	}
	
	public List<Enchere> getAllEncheres() throws BLLException{
		try {
            return enchereDAO.selectAll();
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération des enchères", exc);
        }
	}
	
	public Enchere getEnchereByArticle(int id) throws BLLException{
		try {
            return enchereDAO.selectByArticle(id);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération de l'enchère", exc);
        }
	}
	
	public void validerEnchere(Enchere enchere) throws BLLException{
		if (enchere == null) { 
			throw new BLLException("Enchère invalide"); 
		}
		
        if (enchere.getDateEnchere() == null){
        	throw new BLLException("Date d'enchère invalide"); 
        }
        if (enchere.getMontantEnchere() <= 0){
        	throw new BLLException("Montant d'enchère invalide"); 
        }
	}
}
