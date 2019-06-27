package com.cumt.watermark.utility.Interface;

//ˮӡԤ������
public interface Pretreatment {
	public int[] pic_proc(String path);       //输入二值图片返回图片对应的图片二值序列(暂时是一行输出，不过可以控制成64*16的格式)
	public int[] jiami(int[] a);               //输入图片二值序列返回待嵌入的水印序列
}
