package com.cumt.watermark.service.impl;

import com.cumt.watermark.service.UtilService;
import com.cumt.watermark.utility.DrawPic;
import com.cumt.watermark.utility.Interface.DBconnect;
import com.cumt.watermark.utility.Interface.Pretreatment;
import com.cumt.watermark.utility.Interface.Wmark_process;
import com.cumt.watermark.utility.impl.DBconnectImpl;
import com.cumt.watermark.utility.impl.PretreatmentImpl;
import com.cumt.watermark.utility.impl.Wmark_processImpl;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;

@Service
public class UtilServiceImpl implements UtilService {

    @Override
    public File createImage(String path, String s) throws FileNotFoundException {
        return DrawPic.createImage(path,s);
    }

    @Override
    public int [] getBSnum(String path) {
        Pretreatment p = new PretreatmentImpl();
        return p.pic_proc(path);
    }

    @Override
    public File image(int[] W_B, String path) {
        Wmark_process wp = new Wmark_processImpl();
        return wp.image(W_B,path);
    }

    @Override
    public int[] getMarkSeq(int[] I) {
        Pretreatment p = new PretreatmentImpl();
        return p.jiami(I);
    }

    @Override
    public int[] restoreMarkSeq(int[] W) {
        Wmark_process wp = new Wmark_processImpl();
        return wp.restore_watermark(W);
    }

    @Override
    public boolean addMark(int[] watermark) {
        DBconnect db=new DBconnectImpl();
        Connection cn=db.connect();
        Wmark_process wp = new Wmark_processImpl();
        return  wp.add_watermark(watermark,cn);

    }

    @Override
    public int[] pickMark() {
        Wmark_process wp = new Wmark_processImpl();
        return wp.pikup_watermark();
    }

    @Override
    public int[] pickMarkByPart(double percent) {
        Wmark_process wp = new Wmark_processImpl();
        return wp.pikup_partofwatermark(percent);
    }
}
