package com.cumt.watermark.utility.demo;

import com.cumt.watermark.entity.Order;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class add_Watermark {
	private static List<List<Order>> group = new ArrayList<>();
	private static int[] Weight;
	private static int size=1024;

	public static List<List<Order>> getGroup()
	{
		return group;
	}

	public static void init_group()
	{
		for(int i=0;i<1025;i++)
			group.add(new ArrayList<>());
		Weight = new int[1024];
	}

	public static void setWeight(int[] w)
	{
		Weight = w;
	}

	//基于二次哈希的分组方法
	public static void addChild(ResultSet rs)
	{
		try
		{
			while(rs.next())
			{
				//一次哈希
				String encrypt = md5_key.encrypt(rs.getString("orderId"), "15");
				int a = Integer.parseInt(encrypt.substring(31,32), 16);

				Order stu = new Order();
				stu.setOrderId(rs.getString(1));
				stu.setStartTime(Long.parseLong(rs.getString(2)));
				stu.setEndTime(Long.parseLong(rs.getString(3)));
				stu.setStartLongitude(Double.parseDouble(rs.getString(4)));
				stu.setEndLongitude(Double.parseDouble(rs.getString(5)));
				stu.setStartLatitude(Double.parseDouble(rs.getString(6)));
				stu.setEndLatitude(Double.parseDouble(rs.getString(7)));
				stu.setH1(encrypt);

				//依据h1的奇偶划分，保留h1为偶数的元组
				if(a%2==0)
				{
					//二次哈希,并根据h2进行分组
					String new_encrypt = md5_key.encrypt(encrypt, "15");
					int b = Integer.parseInt(new_encrypt.substring(29,32),16);
					group.get(b%(size)).add(stu);
				}
				else
				{
					group.get(size).add(stu);
				}
			}
			rs.close();
		}catch(Exception e)
		{
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}

	//加水印
	public static void add_Watermark()
	{
		//每组依次加水印
		for(int i=0;i<size;i++)
		{
			int count=0;
			while(group.get(i).size()-count>0)
			{
				//获取元组信息
				Order stu = group.get(i).get(count);

				long type = stu.getStartTime();
				//可嵌入条件判断
				double n = Math.log(type * 0.1)/Math.log(2);
				if(n>0)
				{
					//根据水印控制因子计算嵌入位数
					int k = Integer.parseInt(stu.getH1().substring(29,32),16)%1;
					long fix = stu.getStartTime();
					//具体嵌入规则
					if(Weight[i]==1)
					{
						if(fix%2==0)
							fix=fix+1;
					}
					else
					{
						if(fix%10==9)
							fix=fix-1;
						else if(fix%2==1)
							fix=fix+1;
					}
					//更新score值
					group.get(i).get(count).setStartTime(fix);
				}
				count++;
			}
		}
	}
	
	

}
