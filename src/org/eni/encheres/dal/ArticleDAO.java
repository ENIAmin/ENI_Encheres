package org.eni.encheres.dal;

public interface ArticleDAO extends DAO{
	
	public void selectByCategorie(int id) throws DALException;
	public void selectByMotsClefs(int id) throws DALException;
	public void selectByStatus(int id) throws DALException;
	
}
