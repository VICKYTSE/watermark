package com.cumt.watermark.dao;

import com.cumt.watermark.entity.MarkRecord;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarkRecordDaoTest {
    @Autowired
    private MarkRecordDao markRecordDao;

    @Ignore
    @Test
    public void searchByFirmSign() {

    }

    @Test
    public void insertMarkRecord() {
    }
}