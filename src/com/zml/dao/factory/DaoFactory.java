package com.zml.dao.factory;

import com.zml.dao.proxy.BaseDaoProxy;

public class DaoFactory {
	
	public static BaseDaoProxy getDaoInstance(Class<? extends BaseDaoProxy> daoClass) throws Exception {
		return daoClass.newInstance();
	}
	
//	public static StudentDaoProxy getStudentDaoInstance() throws Exception{
//		return new StudentDaoProxy() ;
//	}
//	
//	public static DoorDaoProxy getDoorDaoInstance() throws Exception{
//		return new DoorDaoProxy() ;
//	}
//	
//	public static SiginDaoProxy getSiginDaoInstance() throws Exception{
//		return new SiginDaoProxy() ;
//	}
//	
//	public static TemperatureDaoProxy getTemperatureDaoInstance() throws Exception{
//		return new TemperatureDaoProxy() ;
//	}
//	
//	public static HumidityDaoProxy getHumidityDaoInstance() throws Exception{
//		return new HumidityDaoProxy() ;
//	}
}
