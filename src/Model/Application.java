package model;

import java.sql.*;
/**
 * Spoj izmedju aplikacije i baze
 * @author Sanela
 *
 */
public class Application {
	protected static Connection db;

	/**
	 * Metoda koja pokusava da uspostavi konekciju sa bazom
	 * @throws SQLException - ako konekcija nije uspostavljena
	 */
	public static void init(String databaseName) throws SQLException{
		db = DriverManager.getConnection("jdbc:sqlite:" + databaseName + ".db");
	}
	
	/**
	 * Tries to find a single result
	 * using the id column
	 * @param id if the model
	 * @param tableName name of the table to search in
	 * @return a ResultSet collection
	 */
	
	/**
	 * Metoda koja treba da vrati podatke iz tabele za trazeni id 
	 * @param id - id broj unesenog korisnika
	 * @param tableName - ime tabele
	 * @return - izvrsenje querya koji smo mu poslali (ResultSet)
	 */
	protected static ResultSet find(int id, String tableName){
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("SELECT * FROM %s WHERE id = '%d' ;",
					tableName, id);
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
		
	}
	/**
	 * Metoda koja nam vraca true or false ako su podaci spaseni 
	 * u bazu podatak, koje smo unijeli za odredjenog korisnika
	 * @param tableName - ime tabele u koju cemo spasiti
	 * @param values - vrijednosti koje ce da spasi
	 * @return vraca true ako je spaseno, a folse ako nije spaseno
	 */
	protected static int save(String tableName, String values){
		Statement stmt = null;
		try {
			stmt = db.createStatement();
			String sql = String.format("INSERT INTO %s VALUES %s ;", tableName, values);
			stmt.execute("begin;");
			stmt.execute(sql);
			stmt.execute("commit;");
			
			sql = String.format("SELECT max(id) as last_id FROM %s", tableName);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			if( stmt != null){
				try {
					stmt.execute("rollback;");
				} catch (SQLException e1) {
					System.err.println(e1.getMessage());
					return -1;
				}
			}
			System.err.println(e.getMessage());
			return -1;
		}
	}
	/**
	 * Vraca ResultSet 
	 * @param tableName - ime tabele iz koje uzimamo podatke
	 * @param columnNames - zavisi od onog sto saljemo, moze biti name, surname ili *
	 * @return
	 */
	
	
	
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
