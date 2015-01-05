package com.zml.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zml.dao.SiginDao;
import com.zml.model.SgRecord;

public class SiginDaoImpl extends BaseDaoImpl implements SiginDao{

	public SiginDaoImpl() {
		
	}
	
	public SiginDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(SgRecord record) {
		boolean result = false;
//		String sql = "insert into sg_record (`stdnum`, `arrival_time`, `departure_time`, `node_id`) values (?, ?, ?, ?)";
		String sql = "insert into sg_record (stdnum, arrival_time, departure_time, node_id) values (?, ?, ?, ?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, record.getStdNum());
			this.pstmt.setTimestamp(2, new java.sql.Timestamp(record.getSgDate().getTime()));
			this.pstmt.setTimestamp(3, new java.sql.Timestamp(record.getSgDate().getTime()));
			this.pstmt.setInt(4, record.getNode_id());
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
	public SgRecord findDao(SgRecord obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
