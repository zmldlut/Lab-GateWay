package com.zml.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zml.dao.CurtainDao;
import com.zml.model.CurtainRecord;

public class CurtainDaoImpl extends BaseDaoImpl implements CurtainDao{

	public CurtainDaoImpl() {
		
	}
	
	public CurtainDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(CurtainRecord curtain) {
		boolean result = false;
		String sql = "insert into curtain_record (stdnum, operate_time, node_id, operate) values (?, ?, ?, ?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, curtain.getStdNum());
			this.pstmt.setTimestamp(2, new java.sql.Timestamp(curtain.getOperate_date().getTime()));
			this.pstmt.setInt(3, curtain.getNode_id());
			this.pstmt.setInt(4, curtain.getOperate());
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
	public CurtainRecord findDao(CurtainRecord obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
