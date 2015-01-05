package com.zml.dao.proxy;

import com.zml.dao.DoorDao;
import com.zml.dao.impl.DoorDaoImpl;
import com.zml.model.DoorRecord;

public class DoorDaoProxy extends BaseDaoProxy implements DoorDao{
	
	public DoorDaoProxy() {
		super(DoorDaoImpl.class);
	}
	
	@Override
	public boolean doCreate(DoorRecord record) {
		boolean result = false;
		result = ((DoorDaoImpl) dao).doCreate(record);
		connPool.returnConnection(this.conn);
		return result;
	}

	@Override
	public DoorRecord findDao(DoorRecord obj) {
		return null;
	}
}
