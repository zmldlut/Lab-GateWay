package com.zml.dao.proxy;

import com.zml.dao.LightDao;
import com.zml.dao.impl.LightDaoImpl;
import com.zml.model.Light;

public class LightDaoProxy extends BaseDaoProxy implements LightDao{
	
	public LightDaoProxy() {
		super(LightDaoImpl.class);
	}

	@Override
	public boolean doCreate(Light light) {
		boolean result = false;
		result = ((LightDaoImpl) dao).doCreate(light);
		connPool.returnConnection(this.conn);
		return result;
	}

	@Override
	public Light findDao(Light obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
