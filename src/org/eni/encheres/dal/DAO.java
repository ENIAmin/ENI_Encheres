package org.eni.encheres.dal;

public interface DAO<T> {
	public void insert(T data) throws DALException;
	public void selectAll() throws DALException;
	public void selectByID(int id) throws DALException;
	public void update(T data) throws DALException;
	public void delete(int id) throws DALException;
}
