package com.zml.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zml.dao.TemperatureDao;
import com.zml.model.Temperature;

public class TemperatureDaoImpl extends BaseDaoImpl implements TemperatureDao{

	public TemperatureDaoImpl() {
		
	}
	
	public TemperatureDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Temperature temp) {
		boolean result = false;
		String sql = "insert into temperature (node_id, temperature_value, acq_time) values (?, ?, ?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, temp.getNode_id());
			this.pstmt.setInt(2, Integer.parseInt(temp.getTemperature_value()));
			this.pstmt.setTimestamp(3, new java.sql.Timestamp(temp.getAcq_time().getTime()));
			
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
	public Temperature findDao(Temperature obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
