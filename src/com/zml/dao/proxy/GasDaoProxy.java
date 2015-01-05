package com.zml.dao.proxy;

import com.zml.dao.GasDao;
import com.zml.dao.impl.GasDaoImpl;
import com.zml.model.Gas;

public class GasDaoProxy extends BaseDaoProxy implements GasDao{
	
	public GasDaoProxy() {
		super(GasDaoImpl.class);
	}

	@Override
	public boolean doCreate(Gas gas) {
		boolean result = false;
		result = ((GasDaoImpl) dao).doCreate(gas);
		connPool.returnConnection(this.conn);
		return result;
	}

	@Override
	public Gas findDao(Gas obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
