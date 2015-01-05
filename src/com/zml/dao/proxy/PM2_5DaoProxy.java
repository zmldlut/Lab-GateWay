package com.zml.dao.proxy;

import com.zml.dao.PM2_5Dao;
import com.zml.dao.impl.PM2_5DaoImpl;
import com.zml.model.PM2_5;

public class PM2_5DaoProxy extends BaseDaoProxy implements PM2_5Dao{
	
	public PM2_5DaoProxy() {
		super(PM2_5DaoImpl.class);
	}

	@Override
	public boolean doCreate(PM2_5 pm) {
		boolean result = false;
		result = ((PM2_5DaoImpl) dao).doCreate(pm);
		connPool.returnConnection(this.conn);
		return result;
	}

	@Override
	public PM2_5 findDao(PM2_5 obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
