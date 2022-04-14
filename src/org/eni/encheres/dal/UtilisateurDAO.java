package org.eni.encheres.dal;

import org.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur>{
	public Utilisateur selectByLogin(String login, String motDePasse) throws DALException;
}
