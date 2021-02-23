package com.jiang.base;

import java.util.List;

public interface IBaseService<T> {
	int deleteByPrimaryKey(Long id);
	
	int insert(T t);
	
	int insertSelective(T t);
	
	T selectByPrimaryKey(Long id);
	
	int updateByPrimaryKeySelective(T t);
	
	int updateByPrimaryKey(T t);

	List<T> list();
}
