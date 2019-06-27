package com.cumt.watermark.utility.impl;
import com.cumt.watermark.utility.Interface.DBconnect;
import com.cumt.watermark.utility.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBconnectImpl implements DBconnect {
	public Connection connect()
	{
		return database.getConnect();
	}
	public void close()
	{
		database.close(database.getConnect());
	}

}
