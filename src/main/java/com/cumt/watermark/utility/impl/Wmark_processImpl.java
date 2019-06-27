package com.cumt.watermark.utility.impl;
import java.sql.Connection;

import com.cumt.watermark.entity.Order;
import com.cumt.watermark.utility.Interface.Wmark_process;
import com.cumt.watermark.utility.demo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Wmark_processImpl implements Wmark_process{
	public boolean add_watermark(int[] watermark,Connection connect)
	{
		try {

			List<List<Order>>stu_list=new ArrayList<>();
			ResultSet rs = database.search();
			add_Watermark.init_group();
			add_Watermark.setWeight(watermark);
			add_Watermark.addChild(rs);         //分组
			rs.close();
			add_Watermark.add_Watermark();      //加水印
			stu_list = add_Watermark.getGroup();

			//清空B表
			String sqlDeleteB ="truncate table order_20161110_B";
			PreparedStatement stmt1 = connect.prepareStatement(sqlDeleteB);
			stmt1.executeUpdate();
			System.out.println("清空B表完成！");

			//插入B表
			connect.setAutoCommit(false);
			String sqlInsertB = "insert into order_20161110_B(orderId, startTime,endTime,startLongitude,startLatitude,endLongitude,endLatitude) values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt2 = connect.prepareStatement(sqlInsertB);
			for(int i=0;i<1025;i++)
			{
				while(!stu_list.get(i).isEmpty())
				{
					Order stu=stu_list.get(i).remove(0);
					stmt2.setString(1,stu.getOrderId());
					stmt2.setLong(2, stu.getStartTime());
					stmt2.setLong(3, stu.getEndTime());
					stmt2.setDouble(4,stu.getStartLongitude());
					stmt2.setDouble(5,stu.getStartLatitude());
					stmt2.setDouble(6,stu.getEndLongitude());
					stmt2.setDouble(7,stu.getEndLatitude());
					stmt2.addBatch();
				}
			}
			stmt2.executeBatch();
			connect.commit();
			stmt2.clearBatch();

			connect.setAutoCommit(true);
			connect.close();
			System.out.println("插入数据完成！");
			return true;

		}catch (Exception e) {
			e.printStackTrace();
			return false;}

	}

	//用完整数据进行水印提取
	public int [] pikup_watermark() {
		int[] W_B=new int[1024];
		try {
			Statement stmt = database.getConnect().createStatement();
			ResultSet rs_B = stmt.executeQuery("select * from order_20161110_B order by orderID ASC");
			if(!rs_B.next())
				return null;
			rs_B.first();
			pickup_Water.init_group_b();
			pickup_Water.addChild_B(rs_B);
			pickup_Water.pickup_Watermark();
			W_B=pickup_Water.getWeight_B();
		}catch (Exception e) {
			e.printStackTrace(); }
		return W_B;
	}

	//用部分数据进行水印提取
	public int [] pikup_partofwatermark(double percent) {
		int[] W_B=new int[1024];
		try {
			Statement stmt = database.getConnect().createStatement();
			ResultSet rs_B = stmt.executeQuery("select * from order_20161110_B order by orderID ASC");
			if(!rs_B.next())
				return null;
			rs_B.first();
			pickup_Water.init_group_b();
			pickup_Water.addpartofChild_B(rs_B,percent);	        //选取部分元组进行分组
			pickup_Water.pickup_Watermark();
			W_B=pickup_Water.getWeight_B();
		}catch (Exception e) {
			e.printStackTrace(); }
		return W_B;

	}

	public int [] restore_watermark(int []W_B) {
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
		return W_B;
	}

	public File image(int []W_B,String path) {
		return pixelMaker.maker(W_B, path);
	}
}
