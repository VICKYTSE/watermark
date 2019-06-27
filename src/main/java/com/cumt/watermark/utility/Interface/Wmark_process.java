package com.cumt.watermark.utility.Interface;

import java.io.File;
import java.sql.Connection;

//水印处理部分
public interface Wmark_process {
    public boolean add_watermark(int[] watermark,Connection connect);      //嵌入水印序列，结果存入数据库B表
    public int [] pikup_watermark();                                    //从数据库B表读取全部数据，提取水印序列
    public int [] pikup_partofwatermark(double percent);                //选取自定义比例的元组，进行水印提取
    public int [] restore_watermark(int []W_B);                         //水印序列还原为图片二值序列
    public File image(int []W_B,String path);                           //图片二值序列还原为图片
}
