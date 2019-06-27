package com.cumt.watermark.utility.demo;
import java.io.File;
public class pixelMaker {	

	public static File maker(int[] pixel, String path)
	{
		byte[] pixel_buffer=new byte[130];
		int current=128;
		int count=0;
		while(current>0)
		{
			current=current-8;
			for(int i=0;i<64;i++)
			{
				if(pixel[count]==1)
					pixel_buffer[current+i/8] |=0x01 << (7-(i%8));
				count++;
			}
		}
		ImageMaker image=new ImageMaker();
		image.setfileSize(192);
		image.setWidth(64);
		image.setHeight(16);
		image.setSizeImage(130);
		File f=image.print(path,pixel_buffer);
		return f;
	}

}

