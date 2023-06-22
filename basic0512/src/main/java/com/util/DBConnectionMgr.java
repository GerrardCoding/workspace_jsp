package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DBConnectionMgr {
    Logger logger = Logger.getLogger(DBConnectionMgr.class);
    //각 제조사가 제공하는 드라이버 클래스를 로딩해야 한다 - ojdbc6.jar, ojdbc8.jar
    private final String driver = "oracle.jdbc.driver.OracleDriver";
    //사용자 계정이 필요하다
    private final String _user = "scott";
    //비번이 필요하다
    private final String _passwd = "tiger";
    //물리적으로 떨어져 있는 서버의 정보(리소스)
    private final String url = "jdbc:oracle:thin:@localhost:1521:orcl11";//xe는 SID값이다
    //객체 생성하는 메소드 구현 - 싱글톤 패턴으로 구현해봄 - 하나로 여러 사용자가 공유한다
    
    public static DBConnectionMgr getInstance() {//static붙은 메소드는 인스턴스화 없이 호출가능하다
       DBConnectionMgr dbMgr = null;
       //아 이래서 객체생성을 메소드로 구현하나 보다
       //메소드 안에서는 if문을 사용할 수 있으니까
       if(dbMgr == null) {//null인지 체크하고 null이면 인스턴스화 해주고 아니면 가지고 있는 걸 사용하기
          dbMgr = new DBConnectionMgr();
       }
       return dbMgr;
    }
    //물리적으로 떨어져 있는 오라클 서버와 연결통로를 만들어줌
    public Connection getConnection() {
       Connection con = null;
       try {
          Class.forName(driver);//드라이버 클래스를 로딩한다 - ojdbc6.jar가 없으면(못찾으면) ClassNotFoundException이 발생한다.
          con = DriverManager.getConnection(url, _user, _passwd);
       } catch (Exception e) {
          // TODO: handle exception
          logger.info("=======================================" + e.toString());
          e.printStackTrace();
       }

       return con;
    }

    public static void freeConnection(Connection con, Statement stmt, ResultSet rs) {
       if (rs != null) {
          try {
             rs.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }
       if (stmt != null) {
          try {
             stmt.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }
       if (con != null) {
          try {
             con.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }
    }

    public static void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
       if (rs != null) {
          try {
             rs.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }
       if (pstmt != null) {
          try {
             pstmt.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }
       if (con != null) {
          try {
             con.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }
    }

    public static void freeConnection(Connection con, Statement stmt) {
       if (stmt != null) {
          try {
             stmt.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }
       if (con != null) {
          try {
             con.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }
    }

    public static void freeConnection(Connection con, PreparedStatement pstmt) {
       if (pstmt != null) {
          try {
             pstmt.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }
       if (con != null) {
          try {
             con.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }
    }
    
    public static void main(String[] args) {
    	//int i = Integer.parseInt("10");
    	DBConnectionMgr dbmgr = DBConnectionMgr.getInstance();
		Connection con = dbmgr.getConnection();
		System.out.println(con);
	}
}

	
	

