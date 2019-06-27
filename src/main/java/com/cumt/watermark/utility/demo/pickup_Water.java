package com.cumt.watermark.utility.demo;

import com.cumt.watermark.entity.Order;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class pickup_Water {
	private static List<List<Order>> group_b = new ArrayList<>();
	private static int[] Weight_B;    //提取后的水印序列
	private static int size=1024;          //分组数+1

	public static void init_group_b()
	{
		group_b = new ArrayList<>();
		for(int i=0;i<1025;i++)
			group_b.add(new ArrayList<>());
		Weight_B = new int[1024];
	}

	public static List<List<Order>> getGroup_B()
	{
		return group_b;
	}


	public static void addpartofChild_B(ResultSet rs_B,double percent)
	{
		int cnt=0;
		Random rand = new Random();       //抽取元组数
		Double select=10106*percent;
		int s = select.intValue();
		int start=rand.nextInt(10106-s+1);      //起始(随机)
		int end=start+s; 		              //结束
		try {
			while(rs_B.next())
			{
				if(cnt>=start&&cnt<end)
				{
					String encrypt = md5_key.encrypt(rs_B.getString("orderId"), "15");
					int a = Integer.parseInt(encrypt.substring(31,32), 16);

					Order stu = new Order();
					stu.setOrderId(rs_B.getString(1));
					stu.setStartTime(Long.parseLong(rs_B.getString(2)));
					stu.setEndTime(Long.parseLong(rs_B.getString(3)));
					stu.setStartLongitude(Double.parseDouble(rs_B.getString(4)));
					stu.setEndLongitude(Double.parseDouble(rs_B.getString(5)));
					stu.setStartLatitude(Double.parseDouble(rs_B.getString(6)));
					stu.setEndLatitude(Double.parseDouble(rs_B.getString(7)));
					stu.setH1(encrypt);

					//依据h1的奇偶划分，保留h1为偶数的元组
					if(a%2==0)
					{
						//二次哈希,并根据h2进行分组
						String new_encrypt = md5_key.encrypt(encrypt, "15");
						int b = Integer.parseInt(new_encrypt.substring(29,32),16);
						group_b.get(b%(size)).add(stu);
					}
					else
					{
						group_b.get(size).add(stu);
					}
				}
				if(cnt>=end)
					break;
				cnt++;
			}
			rs_B.close();
		}catch(Exception e)
		{
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}
	public static void addChild_B(ResultSet rs_B)
	{
		try
		{
			while(rs_B.next())
			{
				//一次哈希
				String encrypt = md5_key.encrypt(rs_B.getString("orderId"), "15");
				int a = Integer.parseInt(encrypt.substring(31,32), 16);

				Order stu = new Order();
				stu.setOrderId(rs_B.getString(1));
				stu.setStartTime(Long.parseLong(rs_B.getString(2)));
				stu.setEndTime(Long.parseLong(rs_B.getString(3)));
				stu.setStartLongitude(Double.parseDouble(rs_B.getString(4)));
				stu.setEndLongitude(Double.parseDouble(rs_B.getString(5)));
				stu.setStartLatitude(Double.parseDouble(rs_B.getString(6)));
				stu.setEndLatitude(Double.parseDouble(rs_B.getString(7)));
				stu.setH1(encrypt);

				//依据h1的奇偶划分，保留h1为偶数的元组
				if(a%2==0)
				{
					//二次哈希,并根据h2进行分组
					String new_encrypt = md5_key.encrypt(encrypt, "15");
					int b = Integer.parseInt(new_encrypt.substring(29,32),16);
					group_b.get(b%(size)).add(stu);
				}
				else
				{
					group_b.get(size).add(stu);
				}
			}
			rs_B.close();
		}catch(Exception e)
		{
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}

	//提取水印序列
	public static boolean pickup_Watermark() {
		int c0=0;
		int c1=0;
		int i=0;
		for(i=0;i<size;i++)
		{
			if(!group_b.get(i).isEmpty())
				break;
		}
		if(i==size)
			return false;
		for(i=0;i<size;i++)
		{
			c0=0;
			c1=0;
			int count=0;
			while(group_b.get(i).size()-count>0)
			{
				Order stu = group_b.get(i).get(count);
				long type = stu.getStartTime();
				double n = Math.log(type * 0.1)/Math.log(2);
				if(n>0)
				{
					int k = Integer.parseInt(stu.getH1().substring(29,32),16)%1;
					long fix = stu.getStartTime();
					if(fix%2==0)
					{
						c0++;
					}
					else
						c1++;
				}
				count++;
			}
			if(c0>c1)
				Weight_B[i]=0;
			else
				Weight_B[i]=1;
		}
		return true;
	}

	//返回提取的水印序列
	public static int[] getWeight_B()
	{
		return Weight_B;
	}

}
