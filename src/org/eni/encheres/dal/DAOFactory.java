package org.eni.encheres.dal;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.dal.jdbc.ArticleDAOJdbcImpl;
import org.eni.encheres.dal.jdbc.CategorieDAOJdbcImpl;
import org.eni.encheres.dal.jdbc.EnchereDAOJdbcImpl;
import org.eni.encheres.dal.jdbc.RetraitDAOJdbcImpl;
import org.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}

	public static DAO<Categorie> getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}

	public static DAO<Retrait> getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}

	public static DAO<Enchere> getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}


}

