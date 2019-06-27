package com.cumt.watermark.utility.demo;
import java.io.File;
import java.io.FileOutputStream;

public class ImageMaker {

	private byte[] bm = new byte[]{0x42,0x4D};
    private byte[] fileSize=new byte[4];
    private byte[] Reserverd =new byte[]{0x00,0x00,0x00,0x00};
    private byte[] bfOffBits=new byte[]{62,0,0,0};
    private byte[] infoSize=new byte[]{40,0,0,0};
    private byte[] Width=new byte[4];
    private byte[] Height=new byte[4];
    private byte[] plane=new byte[]{1,0};
    private byte[] biBitCount= new byte[]{1,0};
    private byte[] biCompression=new byte[]{0,0,0,0};
    private byte[] SizeImage=new byte[4];
    private byte[] biXPelsPerMeter=new byte[]{0x12,0x0B,0,0};
    private byte[] biYPelsPerMeter=new byte[]{0x12,0x0B,0,0};
    private byte[] biClrUsed=new byte[]{0,0,0,0};
    private byte[] biClrImportant=new byte[]{0,0,0,0};
    private byte[] ClrTable=new byte[]{-1,-1,-1,0,0,0,0,0};
    
    public void setfileSize(int fSize)
    {
    	if( fSize > 255 )
    	{
    		fileSize[0]=(byte)(fSize>>8 & 0xff);
    		fileSize[1]=(byte)(fSize & 0xff);
    		fileSize[2]=0;
    		fileSize[3]=0;
    	}
    	else
    	{
    		fileSize[0]=(byte)(fSize & 0xff);
    		fileSize[1]=0;
    		fileSize[2]=0;
    		fileSize[3]=0;
    	}
    }
    
    public void setWidth(int width)
    {
    	if( width > 255 )
    	{
    		Width[0]=(byte)(width>>8 & 0xff);
    		Width[1]=(byte)(width & 0xff);
    		Width[2]=0;
    		Width[3]=0;
    	}
    	else
    	{
    		Width[0]=(byte)(width & 0xff);
    		Width[1]=0;
    		Width[2]=0;
    		Width[3]=0;
    	}
    }
    
    public void setHeight(int height)
    {
    	if( height > 255 )
    	{
    		Height[0]=(byte)(height>>8 & 0xff);
    		Height[1]=(byte)(height & 0xff);
    		Height[2]=0;
    		Height[3]=0;
    	}
    	else
    	{
    		Height[0]=(byte)(height & 0xff);
    		Height[1]=0;
    		Height[2]=0;
    		Height[3]=0;
    	}
    }
    
    public void setSizeImage(int SImage)
    {
    	if( SImage > 255 )
    	{
    		SizeImage[0]=(byte)(SImage>>8 & 0xff);
    		SizeImage[1]=(byte)(SImage & 0xff);
    		SizeImage[2]=0;
    		SizeImage[3]=0;
    	}
    	else
    	{
    		SizeImage[0]=(byte)(SImage & 0xff);
    		SizeImage[1]=0;
    		SizeImage[2]=0;
    		SizeImage[3]=0;
    	}
    }
    
    public File print (String path,byte[] pixels)
    {
    	File file=new File(path);
    	try
    	{   		
    		if(!file.exists())
    			file.createNewFile();
    		FileOutputStream FOS_init=new FileOutputStream(file);
    		FOS_init.close();
    		FileOutputStream FOS=new FileOutputStream(file,true);
    		FOS.write(bm);
    		FOS.write(fileSize);
    		FOS.write(Reserverd);
    		FOS.write(bfOffBits);
    		FOS.write(infoSize);
    		FOS.write(Width);
    		FOS.write(Height);
    		FOS.write(plane);
    		FOS.write(biBitCount);
    		FOS.write(biCompression);
    		FOS.write(SizeImage);
    		FOS.write(biXPelsPerMeter);
    		FOS.write(biYPelsPerMeter);
    		FOS.write(biClrUsed);
    		FOS.write(biClrImportant);
    		FOS.write(ClrTable);
    		FOS.write(pixels);
    		FOS.close();
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("error");
    	}
    	return file;
    }
	
}
