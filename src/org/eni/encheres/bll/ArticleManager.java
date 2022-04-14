package org.eni.encheres.bll;

import java.util.List;

import org.eni.encheres.bo.Article;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.ArticleDAO;
import org.eni.encheres.dal.DALException;
import org.eni.encheres.dal.DAOFactory;

public class ArticleManager {
	private ArticleDAO articleDAO;
	private static ArticleManager instance;
	
	public ArticleManager() throws BLLException{
		super();
		articleDAO = DAOFactory.getArticleDAO();
	}
	
	public static ArticleManager getInstance() throws BLLException {
		if(instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	public void addArticle(Article article) throws BLLException{
		try {
            validerArticle(article);
            articleDAO.insert(article);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de l'ajout d'un nouvel article", exc);
        }
	}
	
	public void removeArticle(int index) throws BLLException{
		try {
			articleDAO.delete(index);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la suppression de l'article.", exc);
        }
	}
	
	public void updateArticle(Article article) throws BLLException{
		try {
            validerArticle(article);
            articleDAO.update(article);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la mise à jour d'un article", exc);
        }
	}
	
	public Article getArticle(int index) throws BLLException{
		try {
            return articleDAO.selectByID(index);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération de l'article par identifiant", exc);
        }
	}
	
	public List<Article> getArticleByCategorie(Categorie categorie) throws BLLException{
		try {
            return articleDAO.selectByCategorie(categorie);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération de l'article par catégorie", exc);
        }
	}
	
	public List<Article> getArticleByMotClefs(String motCle) throws BLLException{
		try {
            return articleDAO.selectByMotsClefs(motCle);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération de l'article par mot clé", exc);
        }
	}
	
	public List<Article> getArticleByStatus(String status) throws BLLException{
		try {
            return articleDAO.selectByStatus(status);
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération de l'article par statut", exc);
        }
	}
	
	public List<Article> getAllArticles() throws BLLException{
		try {
            return articleDAO.selectAll();
        } catch (DALException exc) {
            throw new BLLException("Erreur lors de la récupération de tous les articles", exc);
        }
	}
	
	public void validerArticle(Article article) throws BLLException{
		if (article == null) { 
			throw new BLLException("Article invalide"); 
		}
		
        if (article.getNomArticle() == null){
        	throw new BLLException("Nom invalide"); 
        }
        if (article.getDescription() == null){
        	throw new BLLException("Description invalide"); 
        }
        if (article.getMiseAPrix() <= 0){
        	throw new BLLException("Mise à prix invalide"); 
        }
        if (article.getDateDebutEncheres() == null){
        	throw new BLLException("Date de début d'enchère invalide"); 
        }
        if (article.getDateFinEncheres() == null){
        	throw new BLLException("Date de fin d'enchère invalide"); 
        }
	}
}
