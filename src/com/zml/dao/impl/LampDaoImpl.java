package com.zml.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zml.dao.LampDao;
import com.zml.model.LampRecord;

public class LampDaoImpl extends BaseDaoImpl implements LampDao{

	public LampDaoImpl() {
		
	}
	
	public LampDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(LampRecord lamp) {
		boolean result = false;
		String sql = "insert into lamp_record (stdnum, operate_time, node_id, operate) values (?, ?, ?, ?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, lamp.getStdNum());
			this.pstmt.setTimestamp(2, new java.sql.Timestamp(lamp.getOperate_date().getTime()));
			this.pstmt.setInt(3, lamp.getNode_id());
			this.pstmt.setInt(4, lamp.getOperate());
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
	public LampRecord findDao(LampRecord obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
