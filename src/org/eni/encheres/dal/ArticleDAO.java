package org.eni.encheres.dal;

import java.util.List;

import org.eni.encheres.bo.Article;

public interface ArticleDAO extends DAO<Article>{
	
	public List<Article> selectByCategorie(int id) throws DALException;
	public List<Article> selectByMotsClefs(int id) throws DALException;
	public List<Article> selectByStatus(int id) throws DALException;
	
}
