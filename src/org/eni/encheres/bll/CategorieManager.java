package org.eni.encheres.bll;

import java.util.List;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.DALException;
import org.eni.encheres.dal.DAO;
import org.eni.encheres.dal.DAOFactory;

public class CategorieManager {
	private DAO<Categorie> categorieDAO;
	private static CategorieManager instance;
	
	public CategorieManager() throws BLLException{
		super();
		categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public static CategorieManager getInstance() throws BLLException {
		if(instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	public void addCategorie(Categorie categorie) throws BLLException{
		try {
            validerCategorie(categorie);
            categorieDAO.insert(categorie);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de l'ajout d'une nouvelle catégorie", exc);
        }
	}
	
	public void removeCategorie(int index) throws BLLException{
		try {
			categorieDAO.delete(index);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la suppression de la catégorie.", exc);
        }
	}
	
	public void updateCategorie(Categorie categorie) throws BLLException{
		try {
			validerCategorie(categorie);
			categorieDAO.update(categorie);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la mise à jour d'une catégorie", exc);
        }
	}
	
	public Categorie getCategorie(int index) throws BLLException{
		try {
            return categorieDAO.selectByID(index);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération de la catégorie par identifiant", exc);
        }
	}
	
	public List<Categorie> getAllCategories() throws BLLException{
		try {
            return categorieDAO.selectAll();
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération des catégories", exc);
        }
	}
	
	public void validerCategorie(Categorie categorie) throws BLLException{
		if (categorie == null) { 
			throw new BLLException("Categorie invalide"); 
		}
		
        if (categorie.getLibelle() == null){
        	throw new BLLException("Libelle invalide"); 
        }
	}
}
