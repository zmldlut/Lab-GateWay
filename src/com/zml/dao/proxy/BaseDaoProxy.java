package com.zml.dao.proxy;

import java.sql.Connection;
import java.sql.SQLException;

import com.zml.dao.impl.BaseDaoImpl;
import com.zml.db.ConnectionPool;

public class BaseDaoProxy {
	protected ConnectionPool connPool = null;
	protected Connection conn = null;
	protected BaseDaoImpl dao = null;
	
	BaseDaoProxy() {
		this.connPool = ConnectionPool.getInstance();
		try {
			this.conn = connPool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	BaseDaoProxy(Class<? extends BaseDaoImpl> daoClass) {
		this.connPool = ConnectionPool.getInstance();
		try {
			this.conn = connPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			dao = daoClass.newInstance();
			dao.setConn(conn);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
