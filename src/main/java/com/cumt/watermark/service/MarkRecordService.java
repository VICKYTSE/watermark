package com.cumt.watermark.service;

import com.cumt.watermark.entity.MarkRecord;

import java.io.FileNotFoundException;
import java.util.List;

public interface MarkRecordService {
    List<MarkRecord> searchByFirmSign(String firmSign) throws FileNotFoundException;
    boolean insertMarkRecord(String dataName,String firmName,String firmSign);
}
