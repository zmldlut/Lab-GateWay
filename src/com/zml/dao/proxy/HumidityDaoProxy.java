package com.zml.dao.proxy;

import com.zml.dao.HumidityDao;
import com.zml.dao.impl.HumidityDaoImpl;
import com.zml.model.Humidity;

public class HumidityDaoProxy extends BaseDaoProxy implements HumidityDao{
	
	public HumidityDaoProxy() {
		super(HumidityDaoImpl.class);
	}

	@Override
	public boolean doCreate(Humidity humidity) {
		boolean result = false;
		result = ((HumidityDaoImpl) dao).doCreate(humidity);
		connPool.returnConnection(this.conn);
		return result;
	}

	@Override
	public Humidity findDao(Humidity obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
