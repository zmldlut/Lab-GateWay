package com.zml.dao.proxy;

import com.zml.dao.LampDao;
import com.zml.dao.impl.LampDaoImpl;
import com.zml.model.LampRecord;

public class LampDaoProxy extends BaseDaoProxy implements LampDao{
	
	public LampDaoProxy() {
		super(LampDaoImpl.class);
	}

	@Override
	public boolean doCreate(LampRecord lamp) {
		boolean result = false;
		result = ((LampDaoImpl) dao).doCreate(lamp);
		connPool.returnConnection(this.conn);
		return result;
	}

	@Override
	public LampRecord findDao(LampRecord obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
