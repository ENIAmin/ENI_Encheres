package org.eni.encheres.dal;

import java.util.List;

import org.eni.encheres.bo.Enchere;

public interface EnchereDAO extends DAO<Enchere>{
	public List<Enchere> selectByUtilisateur(int id) throws DALException;
	public void delete(int idUtilisateur, int idArticle) throws DALException;
}
