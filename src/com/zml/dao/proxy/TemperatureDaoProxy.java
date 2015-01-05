package com.zml.dao.proxy;

import com.zml.dao.TemperatureDao;
import com.zml.dao.impl.TemperatureDaoImpl;
import com.zml.model.Temperature;

public class TemperatureDaoProxy extends BaseDaoProxy implements TemperatureDao{
	
	public TemperatureDaoProxy() {
		super(TemperatureDaoImpl.class);
	}

	@Override
	public boolean doCreate(Temperature temperature) {
		boolean result = false;
		result = ((TemperatureDaoImpl) dao).doCreate(temperature);
		connPool.returnConnection(this.conn);
		return result;
	}

	@Override
	public Temperature findDao(Temperature obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
