package com.cumt.watermark.utility.demo;
import java.util.Arrays;

import javax.imageio.stream.FileImageInputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class Watermark_proc {
	private static double k;
	private static int mio;
	private static double[] logistic;      //��ֵ��������
	private static int[] I;      //ͼƬһά����
	private static int[] W;      //��Ƕ���ˮӡ����

	//��ʼ��
	public static void init_Logistic(double KK,int m){
		k=KK;
		mio=m;
		logistic = new double[1024];
	}
	
	//��ֵ��������
	public static void proLogistic() {
		logistic[0]=k;
		for(int i=1;i<1024;i++) {
			logistic[i]=logistic[i-1]*mio*(1-logistic[i-1]);
		}
		for(int i=0;i<1024;i++) {
			if(logistic[i]>=0.5)
				logistic[i]=1;
			else
				logistic[i]=0;
		}
	}
	
	//���ض�ֵ��������
	public static double[] getLogistic() {
		return logistic;
	}
	
	
	public static byte[] image2byte(String path){
	    byte[] data = null;
	    FileImageInputStream input = null;
	    try {
	      input = new FileImageInputStream(new File(path));
	      ByteArrayOutputStream output = new ByteArrayOutputStream();
	      byte[] buf = new byte[1024];
	      int numBytesRead = 0;
	      while ((numBytesRead = input.read(buf)) != -1) {
	      output.write(buf, 0, numBytesRead);
	      }
	      data = output.toByteArray();
	      output.close();
	      input.close();
	    }
	    catch (FileNotFoundException ex1) {
	      ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	      ex1.printStackTrace();
	    }
	    return data;
	  }
	 
    
	public static void standard_I(String path)
    {
    	byte []bb=null;      
		bb=image2byte(path);
		I=new int[1024];
		int count=0;
		int j=190; //������Ϣ�����һ���ֽ�
		while(j>62)
		{
			j=j-8;
			for(int k=j; k<j+8;k++)
			{
				byte b=bb[k];
				for(int i=0;i<8;i++)
				{
					int n=(int) b>>(7-i) & 0x01;
					I[count]=n;
					count++;
				}
			}
		}
    }
	
	//����һά����I
	public static int[] getI()
	{
		return I;
	}
	
	//��λ��򣬵õ���Ƕ��ˮӡ����
	public static void Weight(int[] I,double[] logistic)
	{
		W=new int[1024];
		for(int i=0;i<1024;i++)
		{
			W[i]=I[i]^(int)logistic[i];
		}
	}
	
	public static int[] getW()
	{
		return W;
	}
	
}
