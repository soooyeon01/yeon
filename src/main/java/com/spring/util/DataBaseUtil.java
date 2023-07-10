package com.spring.util;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.java.servlet.vo.MembersVO;

import java.sql.DriverManager;
import java.sql.ResultSet;

//public class DataBaseUtil {
//   //DriverManager : driver down-> web-inf -> lib-> ojdbc8.jar copy
//         //Connection
//         //Statement , PrepareStatement
//         //ResultSet
//   
//   private static Connection connection;
//   static {
//      try {
//         Connection conn=connectionDB();   
//      }catch(ClassNotFoundException  e) {
//         e.printStackTrace();
//      }catch(SQLException e) {
//         e.printStackTrace();
//      }
//   }
//   
//   public static Connection getConnection() throws ClassNotFoundException, SQLException {
//      
//      if(connection==null || connection.isClosed()) {
//         connection = connectionDB();
//      }
//      return connection;
//   }
//   
//   public static void destroyConnection() {
//      if(connection!=null) {
//         try {
//            connection.close();
//            connection=null;
//         }catch(SQLException e) {
//            e.printStackTrace();
//         }
//      }
//   }
//   
//   public static Connection connectionDB()throws ClassNotFoundException, SQLException {
//      Class.forName("oracle.jdbc.driver.OracleDriver");
//      //String url="jdbc:oracle:thin:@192.168.0.108:1521:XE";
//    String url="jdbc:oracle:thin:@localhost:1521:XE";
//      String id="c##abd";
//      String pwd="abd";
//      return DriverManager.getConnection(url,id,pwd);
//   }
//   
//   public static void main(String[] args)throws ClassNotFoundException, SQLException{
//         String email="saomi@saomi.com";
//         String pwd="saomi";
//         String sql="SELECT COUNT(1) FROM members\n"
//               + "WHERE email='"+email+"'\n"
//                     + "AND pwd = '"+ pwd +"'\n";
//         sql="SELECT nickname, email, name, phone from members";
//         
//         
//         try(
//               Connection conn=connectionDB();   
//               Statement stmt = conn.createStatement();
//               ResultSet rs=stmt.executeQuery(sql);
//               ){
//         
// 
//         List<MembersVO> memberList=new ArrayList<>();
//         while(rs.next()) {
//            MembersVO vo = new MembersVO();
//            
//            
////            vo.setM_no(rs.getInt(1));
////            vo.setName(rs.getString(2));
////            vo.setJumin(rs.getString(3));
////            vo.setId(rs.getString(4));
//
//            memberList.add(vo);
//
////            memberList.add(vo);
//
//
//
////            memberList.add(vo);
//
//
//         }
//         System.out.println(memberList);
//      
//         /*
//         int cnt = rs.getInt(1);
//         
//         
//         if(cnt==1) {
//            System.out.println("로그인 성공");
//         }else {
//            System.out.println("로그인 실패");
//         }
//         System.out.println(sql);
//         */
//      }catch(Exception e) {
//         e.printStackTrace();
//      }
//      
//   }
//}