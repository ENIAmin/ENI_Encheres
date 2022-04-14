package org.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCTools {
	
	public static Connection getConnection() throws SQLException{
		InitialContext annuaire = null;
		try {
			annuaire = new InitialContext();
		} catch (NamingException ne) {
			// TODO Auto-generated catch block
			ne.printStackTrace();
		}
		
		String racineNomJNDI = "java:comp/env/";
		String nomPool = "PoolEncheres";
		DataSource pool = null;
		
		try {
			pool = (DataSource)annuaire.lookup(racineNomJNDI+nomPool);
		} catch (NamingException ne) {
			// TODO Auto-generated catch block
			ne.printStackTrace();
		}
		Connection cnx = pool.getConnection();
		return cnx;
	}

}
