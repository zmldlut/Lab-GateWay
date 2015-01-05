package com.zml.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zml.dao.HumidityDao;
import com.zml.model.Humidity;

public class HumidityDaoImpl extends BaseDaoImpl implements HumidityDao{

	public HumidityDaoImpl() {
		
	}
	
	public HumidityDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Humidity humidity) {
		boolean result = false;
		String sql = "insert into humidity (node_id, humidity_value, acq_time) values (?, ?, ?)";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, humidity.getNode_id());
			this.pstmt.setInt(2, Integer.parseInt(humidity.getHumidity_value()));
			this.pstmt.setTimestamp(3, new java.sql.Timestamp(humidity.getAcq_time().getTime()));
			
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
	public Humidity findDao(Humidity obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
