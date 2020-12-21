package org.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.model.PropriedadeConexao;

public class DatabasePool {
	
	private static Map<Integer, Connection> connectionMap = 
		new HashMap<Integer, Connection>();

	private static void createConnection(final PropriedadeConexao cp) 
	throws SQLException, ClassNotFoundException {
		try {
			Class.forName(cp.getDriver());
			final Connection connection = 
				DriverManager.getConnection(cp.getUrl(), 
						cp.getUser(), cp.getPassword());
			connectionMap.put(cp.getId(), connection);
		} catch (final SQLException e) {
			throw new SQLException("Error trying to get connection to" +
					" Id: "+cp.getId()+
					" Driver: "+cp.getDriver()+
					" Url: "+cp.getUrl()+
					". Error: "+e.getMessage(), e.getSQLState());
		}
	}

	private static boolean isCreated(final PropriedadeConexao cp) {
		if (connectionMap.containsKey(cp.getId())) {
			return true;
		}
		return false;
	}

		
	public static void create(final PropriedadeConexao cp) 
	throws SQLException, ClassNotFoundException {
		if (!isCreated(cp)) {
			createConnection(cp);
		} else {
			throw new SQLException("You are trying to create a database connection " +
					"which already exists to" +
					" Id: "+cp.getId()+
					" Driver: "+cp.getDriver()+
					" Url: "+cp.getUrl()+
					". Please, check if the jod id is unique", "");
		}
	}

	public static Connection getConnection(final PropriedadeConexao cp) 
	throws SQLException {
		int id = cp.getId();
		if (connectionMap.containsKey(id)) 
			return connectionMap.get(id);
		return null;
	}
	
	public static void close(final PropriedadeConexao cp) 
	throws SQLException, ClassNotFoundException {
		int id = cp.getId();
		if (connectionMap.containsKey(id)) {
			connectionMap.get(id).close();
			connectionMap.remove(id);
		}	
	}

	public static void commit(final PropriedadeConexao cp) 
	throws SQLException, ClassNotFoundException {
		int id = cp.getId();
		if (connectionMap.containsKey(id)) 
			connectionMap.get(id).commit();
	}
	
	public static void rollback(final PropriedadeConexao cps) 
	throws SQLException, ClassNotFoundException {
		int id = cps.getId();
		if (connectionMap.containsKey(id)) 
			connectionMap.get(id).rollback();
	}

	public static void setAutoCommit(final PropriedadeConexao cp, 
			final boolean autoCommit) 
	throws SQLException, ClassNotFoundException {
		int id = cp.getId();
		if (connectionMap.containsKey(id)) 
			connectionMap.get(id).setAutoCommit(autoCommit);
	}

}
