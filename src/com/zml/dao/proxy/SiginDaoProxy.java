package com.zml.dao.proxy;

import com.zml.dao.SiginDao;
import com.zml.dao.impl.SiginDaoImpl;
import com.zml.model.SgRecord;

public class SiginDaoProxy extends BaseDaoProxy implements SiginDao{

	public SiginDaoProxy() {
		super(SiginDaoImpl.class);
	}
	
	@Override
	public boolean doCreate(SgRecord record) {
		boolean result = false;
		result = ((SiginDaoImpl) dao).doCreate(record);
		connPool.returnConnection(this.conn);
		return result;
	}

	@Override
	public SgRecord findDao(SgRecord obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
