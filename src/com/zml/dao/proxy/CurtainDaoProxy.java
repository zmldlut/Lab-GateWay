package com.zml.dao.proxy;

import com.zml.dao.CurtainDao;
import com.zml.dao.impl.CurtainDaoImpl;
import com.zml.model.CurtainRecord;

public class CurtainDaoProxy extends BaseDaoProxy implements CurtainDao{
	
	public CurtainDaoProxy() {
		super(CurtainDaoImpl.class);
	}

	@Override
	public boolean doCreate(CurtainRecord curtain) {
		boolean result = false;
		result = ((CurtainDaoImpl) dao).doCreate(curtain);
		connPool.returnConnection(this.conn);
		return result;
	}

	@Override
	public CurtainRecord findDao(CurtainRecord obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
