package com.springlab.test;

import java.sql.Connection;

import com.springlab.common.JDBCUtil;

public class ConnectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn =null;
		conn = JDBCUtil.getConnection();
	}

}
