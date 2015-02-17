package Model;

import java.sql.*;

public class Application {
	protected static Connection db;

	public static void init() throws SQLException {
		db = DriverManager.getConnection("jdbc:sqlite:phonebook.db");

	}

	protected static ResultSet find(int id, String tableName){
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("SELECT * from %s WHERE id = '%d' ;", tableName, id);
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	protected static boolean save(String tableName, String values){
		Statement stmt= null;
		
		try {
			stmt  = db.createStatement();
			stmt.execute("begin;");
			String sql = String.format("INSERT INTO %s VALUES %s;", tableName, values);
			stmt.execute(sql);
			stmt.execute("commit;");
			return true;
		} catch (SQLException e) {
			if(stmt != null){
				try {
					stmt.execute("rollback");
				} catch (SQLException e1) {
					System.err.println(e.getMessage());
					return false;
				}
			}
			System.err.println(e.getMessage());
			return false;
		}
	}
	//columnNames = name, surname, ili npr. *
	protected static ResultSet all(String tableName, String columnNames){
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("SELECT %s FROM %s;", columnNames, tableName);
			return stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
