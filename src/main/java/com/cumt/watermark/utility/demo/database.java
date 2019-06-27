package com.cumt.watermark.utility.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class database {
	public static Connection getConnect()
	{
		Connection connect = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/traffic?rewriteBatchedStatements=true&serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true","root","mysqlpassword");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}

	public static boolean close(Connection connection) {//关闭连接对象的方法
		boolean s=false;
		try {
			if (connection != null) {
				connection.close();
				s=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static ResultSet search()
	{
		Connection connect = getConnect();
		ResultSet rs = null;
		try {
			Statement stmt = connect.createStatement();
			rs = stmt.executeQuery("select * from order_20161110");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
//	public static void main(String args[])
//	{
//		//加载MYSQL JDBC驱动类
//		try {
//		      Class.forName("com.mysql.jdbc.Driver");
//		      System.out.println("Success loading Mysql Driver!");
//		    } catch (Exception e) {
//		    	  System.out.print("Error loading Mysql Driver!");
//		    	  e.printStackTrace(); }
//		//连接数据库
//		try {
//		      Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/traffic","root","mysqlpassword");
//		      System.out.println("Success connect Mysql server!");
//
//		      //查询order_20161110
//		      Statement stmt = connect.createStatement();
//		      ResultSet rs = stmt.executeQuery("select * from order_20161110");
//
//		      //更新order_20161110_B
//		      String sqlUpdateB = "update order_20161110_B set startTime=? where orderId=? ";
//		      String sqlInsertB = "insert into order_20161110_B(orderId, startTime, endTime, startLongitude,startLatitude,endLongitude,endLatitude) values(?, ?, ?, ?,?,?,?)";
//		      PreparedStatement stmt1 = connect.prepareStatement(sqlUpdateB);
//		      List<List<Order>>stu_list=new ArrayList<>();
//
//
//		      int[] W=new int[1024];
//		      double[] log=new double[1024];
//
//		      //水印预处理
//		      Watermark_proc.standard_I("C:\\image\\github.bmp");    //图片信息
//		      Watermark_proc.init_Logistic(0.3, 4);
//		      Watermark_proc.proLogistic();
//		      log=Watermark_proc.getLogistic();     //二值混沌序列
//		      Watermark_proc.Weight(Watermark_proc.getI(), log);
//		      W=Watermark_proc.getW();              //待嵌入的水印序列
//
//		      //加水印
//		      add_Watermark.init_group();
//		      add_Watermark.setWeight(W);
//		      add_Watermark.addChild(rs);
//		      rs.close();
//		      add_Watermark.add_Watermark();
//		      stu_list = add_Watermark.getGroup();
//		      System.out.println("加水印完成！");
//
//		      //加水印后的数据存入B表
//		      for(int i=0;i<1025;i++)
//		      {
//		    	  while(!stu_list.get(i).isEmpty())
//		    	  {
//		    		  Order stu=stu_list.get(i).remove(0)  ;
//		    		  stmt1.setString(2, stu.getOrderId());
//		    		  stmt1.setLong(1, stu.getStartTime());
//		    		  stmt1.executeUpdate();
//		    	  }
//		      }
//		      System.out.println("加水印后的数据存储于B表完成！");
//		      connect.close();
//
//		    }catch (Exception e) {
//		    	  System.out.print("Error connect Mysql server!");
//		    	  e.printStackTrace(); }
//
//	}

}