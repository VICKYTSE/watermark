package com.cumt.watermark.utility;
import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.*;

public class DrawPic {

//    public static void main(String[] args) {
//
//        createImage("Google");
//    }

    public static File createImage(String path,String s) throws FileNotFoundException {
        BufferedImage bi = new BufferedImage(64, 16, BufferedImage.TYPE_BYTE_BINARY);

        //final File file = new File("C:\\Users\\tsevc\\Documents\\workspace\\watermark\\target\\classes\\static\\img\\javaPic.bmp");
        File file = new File(path);
        //final File file = new File("/bin/javaPic.bmp");
        try {
            if(file.exists()) {
                file.delete();
                file.createNewFile();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        if(writeImage(bi,"bmp",file,s)){
            System.out.println("绘图成功233");
            System.out.println(s);
            return file;
        }
        writeImage(bi,"bmp",file,s);
        return file;
    }

    /** 通过指定参数写一个图片  */
    public static boolean writeImage(BufferedImage bi, String picType, File file, String s) {

        Graphics g = bi.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,64,16);
        g.setColor(Color.BLACK);
        if(s.length()==6){
            g.setFont(new Font("宋体",Font.PLAIN,10));
            g.drawString(s,2,12);
        }
        else if(s.length()==5){
            g.setFont(new Font("宋体",Font.PLAIN,12));
            g.drawString(s,2,12);
        }

        else if(s.length()==4){
            g.setFont(new Font("宋体",Font.PLAIN,14));
            g.drawString(s,3,13);
        }
        else {
            g.drawString(s,3,12);
        }
        g.dispose();
        boolean val = false;

        try {
            val = ImageIO.write(bi, picType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(bi.getColorModel().getPixelSize());
        return val;
    }

}
