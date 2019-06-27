package com.cumt.watermark.utility.demo;
import com.cumt.watermark.utility.impl.DBconnectImpl;
import com.cumt.watermark.utility.impl.PretreatmentImpl;
import com.cumt.watermark.utility.impl.Wmark_processImpl;

import java.sql.Connection;
public class test {

	public static void main(String[] args)
	{
		//水印预处理过程
		PretreatmentImpl a1= new PretreatmentImpl();
		int[] I=a1.pic_proc("C:\\Users\\tsevc\\Documents\\workspace\\watermarkweb\\img\\javaPic.bmp");     //图片二值序列
		int [] W=a1.jiami(I);      //水印序列
		System.out.println("水印预处理完成");
		System.out.println("\n"+"图片二值序列");
		for(int i=0;i<1024;i++)
		{
			if(i%64==0)
				System.out.println();
			System.out.print(I[i]+" ");
		}
		System.out.println("\n"+"水印序列");
		for(int i=0;i<1024;i++)
		{
			if(i%64==0)
				System.out.println();
			System.out.print(W[i]+" ");
		}

		//连接数据库部分
		DBconnectImpl db=new DBconnectImpl();
		Connection cn=db.connect();
		System.out.println("\n"+"数据库连接完成");

		//数据处理部分
		Wmark_processImpl aw=new Wmark_processImpl();
		aw.add_watermark(W, cn);     //加水印
		System.out.println("水印添加成功");

		//提取水印
		int []pw=aw.pikup_partofwatermark(0.9);
		if(pw==null)
			System.out.println("B表为空！提取失败");
		else
		{
			int []rw=aw.restore_watermark(pw);     //还原的图片序列
			System.out.println("图片信息提取成功");
			aw.image(rw, "D://ddd.bmp");   //还原的原始图片
			System.out.println("图片还原完成");
		}
	}
}

