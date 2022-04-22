package org.eni.encheres.dal;

import org.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur>{
	public Utilisateur selectByLogin(String login, String motDePasse) throws DALException;
	public Utilisateur selectByPseudo(String pseudo) throws DALException;
	public void updateCredit(int id, int montant) throws DALException;
}
