package com.zml.dao.proxy;

import com.zml.dao.StudentDao;
import com.zml.dao.impl.StudentDaoImpl;
import com.zml.model.Student;

public class StudentDaoProxy extends BaseDaoProxy implements StudentDao{

	public StudentDaoProxy() {
		super(StudentDaoImpl.class);
	}
	
	public boolean findDao(String cardID) {
		boolean result = false;
		result = ((StudentDaoImpl) dao).findDao(cardID);
		connPool.returnConnection(conn);
		return result;
	}
	
	public Student _findDao(String cardID) {
		Student result = null;
		result = ((StudentDaoImpl) dao)._findDao(cardID);
		connPool.returnConnection(conn);
		return result;
	}

	@Override
	public boolean doCreate(Student obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Student findDao(Student obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
