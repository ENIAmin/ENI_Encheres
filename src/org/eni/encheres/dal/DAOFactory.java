package org.eni.encheres.dal;

import org.eni.encheres.dal.jdbc.ArticleDAOJdbcImpl;
import org.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static UtilisateurDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}


}

