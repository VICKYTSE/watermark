package com.cumt.watermark.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;

public interface UtilService {
    File createImage(String path, String s) throws FileNotFoundException;
    int[] getBSnum(String path);
    File image(int[] W_B, String path);
    int[] getMarkSeq(int [] I);
    int[] restoreMarkSeq(int [] W);
    boolean addMark(int[] watermark);
    int[] pickMark();
    int[] pickMarkByPart(double percent);
}
