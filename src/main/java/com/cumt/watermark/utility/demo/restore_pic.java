package com.cumt.watermark.utility.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class restore_pic {
	public static void main(String args[]) {
		try {
		      Class.forName("com.mysql.jdbc.Driver");     
		      System.out.println("Success loading Mysql Driver!");
		    } catch (Exception e) {
		    	  System.out.print("Error loading Mysql Driver!");
		    	  e.printStackTrace(); }
		try {		   
		      Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/traffic","root","mysqlpassword");
		      System.out.println("Success connect Mysql server!");
		      
		      int[] W_B=new int[1024];
		      Statement stmt = connect.createStatement();
		      ResultSet rs_B = stmt.executeQuery("select * from order_20161110_B");
		      pickup_Water.init_group_b();
		      pickup_Water.addChild_B(rs_B);		      
		      pickup_Water.pickup_Watermark();
		      W_B=pickup_Water.getWeight_B();
		      Watermark_proc.init_Logistic(0.3,4);
		      Watermark_proc.proLogistic();
		      double [] lg=new double [1024];
		      lg=Watermark_proc.getLogistic();
		      for(int i=0;i<1024;i++)
		      {
		    	 if(W_B[i]==1&&lg[i]==0)
		    		 W_B[i]=1;
		    	 else if(W_B[i]==1&&lg[i]==1)
		    		 W_B[i]=0;
		    	 else if(W_B[i]==0)
		    		 W_B[i]=(int)lg[i];
		      }
		     
		      pixelMaker.maker(W_B, "C:\\image\\cumt2.bmp");
		      
		      System.out.print("��ȡ�ɹ�!");
		      connect.close();
		      }catch (Exception e) {
		    	  System.out.print("Error connect Mysql server!");
		    	  e.printStackTrace(); }
	}

}
