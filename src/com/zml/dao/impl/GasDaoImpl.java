package com.zml.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zml.dao.GasDao;
import com.zml.model.Gas;

public class GasDaoImpl extends BaseDaoImpl implements GasDao{

	public GasDaoImpl() {
		
	}
	
	public GasDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Gas gas) {
		boolean result = false;
		String sql = "insert into gas (node_id, gas_value, acq_time) values (?, ?, ?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, gas.getNode_id());
			this.pstmt.setInt(2, gas.getGas_value());
			this.pstmt.setTimestamp(3, new java.sql.Timestamp(gas.getAcq_time().getTime()));
			
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
	public Gas findDao(Gas obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
