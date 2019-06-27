package com.cumt.watermark.dao;

import com.cumt.watermark.entity.MarkRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarkRecordDao {
    List<MarkRecord> searchByFirmSign(@Param("firmSign")String firmSign);
    boolean insertMarkRecord(MarkRecord markRecord);
}
