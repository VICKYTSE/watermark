package com.cumt.watermark.service.impl;

import com.cumt.watermark.dao.MarkRecordDao;
import com.cumt.watermark.entity.MarkRecord;
import com.cumt.watermark.service.MarkRecordService;
import com.cumt.watermark.utility.DrawPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MarkRecordServiceImpl implements MarkRecordService {
    @Autowired
    private MarkRecordDao markRecordDao;
    @Override
    public List<MarkRecord> searchByFirmSign(String firmSign) throws FileNotFoundException {
        List<MarkRecord> markRecord = markRecordDao.searchByFirmSign(firmSign);
        if(markRecord.get(0).getFirmSign()!="" && !markRecord.isEmpty()){
            File file = DrawPic.createImage("/08163279/watermark/tracePic.bmp",markRecord.get(0).getFirmSign());
        }
        return markRecordDao.searchByFirmSign(firmSign);
    }

    @Override
    public boolean insertMarkRecord(String dataName, String firmName, String firmSign) {
        MarkRecord markRecord = new MarkRecord();
        markRecord.setDataName(dataName);
        markRecord.setExecuteTime(getStringDate());
        markRecord.setFirmName(firmName);
        markRecord.setFirmSign(firmSign);
        System.out.println(markRecord.getFirmSign());
        return markRecordDao.insertMarkRecord(markRecord);
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
