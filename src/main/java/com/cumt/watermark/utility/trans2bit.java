package com.cumt.watermark.utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

public class trans2bit {
//    public static void  main(String[] arg) throws IOException {
//        BufferedImage sourceImg = ImageIO.read(new File("c:\\aa.bmp"));
//        int h = sourceImg.getHeight();
//        int w = sourceImg.getWidth();
//        int[] pixels = new int[w * h]; // 定义一数组，用来存储图片的象素
//        int gray;
//        PixelGrabber pg = new PixelGrabber(sourceImg, 0, 0, w, h, pixels, 0, w);
//        try {
//            pg.grabPixels(); // 读取像素值
//        } catch (InterruptedException e) {
//            System.err.println("处理被异常中断！请重试！");
//        }
//        for (int j = 0; j < h; j++) // 扫描列 {
//            for (int i = 0; i < w; i++) // 扫描行
//            { // 由红，绿，蓝值得到灰度值
//                gray = (int) (((pixels[w * j + i] >> 16) & 0xff) * 0.8);
//                gray += (int) (((pixels[w * j + i] >> 8) & 0xff) * 0.1);
//                gray += (int) (((pixels[w * j + i]) & 0xff) * 0.1);
//                pixels[w * j + i] = (255 << 24) | (gray << 16) | (gray << 8)
//                        | gray;
//            }
//
//    MemoryImageSource s= new MemoryImageSource(w,h,pixels,0,w);
//    Image img =Toolkit.getDefaultToolkit().createImage(s);
//        BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);
//        buf.createGraphics().drawImage(img, 0, 0, null);
//ImageIO.write(buf, "BMP", new File("d:\\111.bmp"));
//    }

}
