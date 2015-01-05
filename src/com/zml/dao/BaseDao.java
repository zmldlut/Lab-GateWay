package com.zml.dao;

public interface BaseDao<T> {	
	//基本数据库操作方法
	public boolean doCreate(T obj);//保存数据
	public T findDao(T obj);//修改数据
}
