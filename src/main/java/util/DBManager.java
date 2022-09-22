package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {
	public static Connection getConnection() throws NamingException, SQLException {

		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		// jdbc/myoracle이란 이름을 객체를 찾아서 DataSource가 받는다.
		DataSource ds = (DataSource) envContext.lookup("jdbc/teamdb");

		return ds.getConnection();
	}

	// select을 수행한 후 리소스 해제를 위한 메소드
	public static void close(PreparedStatement pstmt, ResultSet rs) {
	      try {
	         // 역순으로
	         if(rs != null)
	            rs.close();
	         if(pstmt != null)
	            pstmt.close();
	      } catch(Exception e) { }
	   }


	// DML(insert, update, delete)을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
