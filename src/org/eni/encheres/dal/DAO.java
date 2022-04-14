package org.eni.encheres.dal;

import java.util.List;

public interface DAO<T> {
	public void insert(T data) throws DALException;
	public List<T> selectAll() throws DALException;
	public T selectByID(int id) throws DALException;
	public void update(T data) throws DALException;
	public void delete(int id) throws DALException;
}
