package com.jiang.base;

import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	public abstract IBaseDao<T> getBaseDao();
	
	public int deleteByPrimaryKey(Long id) {
		return getBaseDao().deleteByPrimaryKey(id);
	}

	public int insert(T t) {
		return getBaseDao().insert(t);
	}

	public int insertSelective(T t) {
		return getBaseDao().insertSelective(t);
	}

	public T selectByPrimaryKey(Long id) {
		return getBaseDao().selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(T t) {
		return getBaseDao().updateByPrimaryKeySelective(t);
	}

	public int updateByPrimaryKey(T t) {
		return getBaseDao().updateByPrimaryKey(t);
	}

	public List<T> list(){
		return getBaseDao().list();
	}
}
