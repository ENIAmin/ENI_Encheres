package org.eni.encheres.bll;

import org.eni.encheres.bo.Article;
import org.eni.encheres.dal.DAO;
import org.eni.encheres.dal.DAOFactory;

public class ArticleManager {
	private DAO<Article> articleDAO;
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
}
