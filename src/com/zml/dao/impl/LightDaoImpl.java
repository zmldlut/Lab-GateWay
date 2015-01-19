package com.zml.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zml.dao.LightDao;
import com.zml.model.Light;

public class LightDaoImpl extends BaseDaoImpl implements LightDao{

	public LightDaoImpl() {
		
	}
	
	public LightDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Light light) {
		boolean result = false;
		String sql = "insert into light (node_id, light_value, acq_time) values (?, ?, ?)";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, light.getNode_id());
			this.pstmt.setInt(2, Integer.parseInt(light.getLight_value()));
			this.pstmt.setTimestamp(3, new java.sql.Timestamp(light.getAcq_time().getTime()));
			
			if(this.pstmt.executeUpdate() > 0) {
				result = true;
			}
			System.out.println(sql);
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
	public Light findDao(Light obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
