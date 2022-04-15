package org.eni.encheres.dal;

import java.util.List;

import org.eni.encheres.bo.Article;
import org.eni.encheres.bo.Categorie;

public interface ArticleDAO extends DAO<Article>{
	
	public List<Article> selectByCategorie(Categorie categorie) throws DALException;
	public List<Article> selectByMotsClefs(String motCle) throws DALException;
	public List<Article> selectByStatus(String status) throws DALException;
	
}
