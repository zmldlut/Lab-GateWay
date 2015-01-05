package com.zml.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zml.dao.DoorDao;
import com.zml.model.DoorRecord;

public class DoorDaoImpl extends BaseDaoImpl implements DoorDao{

	public DoorDaoImpl() {
		
	}
	
	public DoorDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(DoorRecord record) {
		boolean result = false;
		String sql = "insert into open_record (stdnum, open_time, node_id) values (?, ?, ?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, record.getStdNum());
			this.pstmt.setTimestamp(2, new java.sql.Timestamp(record.getOpen_date().getTime()));
			this.pstmt.setInt(3, record.getNode_id());
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
	public DoorRecord findDao(DoorRecord obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
