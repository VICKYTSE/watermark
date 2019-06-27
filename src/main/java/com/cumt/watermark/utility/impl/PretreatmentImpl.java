package com.cumt.watermark.utility.impl;
import com.cumt.watermark.utility.Interface.Pretreatment;
import com.cumt.watermark.utility.demo.Watermark_proc;

public class PretreatmentImpl implements Pretreatment {
	
		public int[] pic_proc (String path)
		{
			Watermark_proc.standard_I(path);
			int[] I=Watermark_proc.getI();
			for(int i = 0; i <I.length;i++){
				if(I[i] == 1){
					I[i] = 0;
				}
				else{
					I[i] = 1;
				}
			}
			return I;
		}
		public int[] jiami(int[] a)
		{
			Watermark_proc.init_Logistic(0.3, 4);
			Watermark_proc.proLogistic();
			Watermark_proc.Weight(a, Watermark_proc.getLogistic());
			return Watermark_proc.getW();
		}	

}
