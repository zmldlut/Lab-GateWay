package com.zml.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zml.dao.PM2_5Dao;
import com.zml.model.PM2_5;

public class PM2_5DaoImpl extends BaseDaoImpl implements PM2_5Dao{

	public PM2_5DaoImpl() {
		
	}
	
	public PM2_5DaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(PM2_5 pm) {
		boolean result = false;
		String sql = "insert into pm2_5 (node_id, pm_value, acq_time) values (?, ?, ?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, pm.getNode_id());
			this.pstmt.setInt(2, pm.getPm_value());
			this.pstmt.setTimestamp(3, new java.sql.Timestamp(pm.getAcq_time().getTime()));
			
			if(this.pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public PM2_5 findDao(PM2_5 pm) {
		// TODO Auto-generated method stub
		return null;
	}
}
