package com.zml.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zml.dao.StudentDao;
import com.zml.model.Student;

public class StudentDaoImpl extends BaseDaoImpl implements StudentDao{

	public StudentDaoImpl() {
		
	}
	
	public StudentDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Student obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean findDao(String cardID) {
		boolean result = false;
		String sql = "select * from student where cardID = ?";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, cardID);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
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
	
	public Student _findDao(String cardID) {
		Student result = null;
		String sql = "select stdnum from student where cardID = ?";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, cardID);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
				result = new Student();
				result.setStdNum(rs.getString(1));
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
	public Student findDao(Student obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
