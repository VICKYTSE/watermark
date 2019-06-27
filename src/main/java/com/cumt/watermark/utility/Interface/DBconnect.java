package com.cumt.watermark.utility.Interface;
import java.sql.Connection;

//���ݿ����Ӳ���
public interface DBconnect {
	public Connection connect();        //�������ݿ�
	public void close();                //�ر����ݿ�
}
