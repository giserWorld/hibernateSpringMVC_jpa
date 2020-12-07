package com.sina.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

//泛型类,基础数据访问层Dao接口
public interface BaseDAO<T> {  
  
    public Serializable save(T o);  
  
    public void delete(T o);  
  
    public void update(T o);  
    /**合并*/
    public void merge(T o);
  
    public void saveOrUpdate(T o);  
  
    public List<T> find(String hql);
    
    public List<T> findBySQL(String sql);
    
    public T getBySQL(String sql);
    public T getBySQL1(String sql);
    
    public List<T> find1(String sql);
    
    public List<T> find1(String sql , Class bclass);
  
    public List<T> find(String hql, Object[] param);  
  
    public List<T> find(String hql, List<Object> param);  
    
    public List<T> find(String hql,Integer page, Integer rows);
   
    public List<T> find(String hql, Object[] param, Integer page, Integer rows);  
  
    public List<T> find(String hql, List<Object> param, Integer page, Integer rows);  
  
    public T get(Class<T> c, Serializable id);  
   
    public T get(String hql, Object[] param);  
  
    public T get(String hql, List<Object> param);  
   
    public Long count(String hql);  
   
    public Long count(String hql, Object[] param);  
  
    public Long count(String hql, List<Object> param);  
  
    public Integer executeHql(String hql);  
   
    public Integer executeHql(String hql, Object[] param);  
  
    public Integer executeHql(String hql, List<Object> param);  
    
    public void executeSql(String sql);

	public BigDecimal countBySQL(String sql);
	
	public BigInteger countBySQL1(String sql);
	
	public List<T> findBySQL(String sql, Object[] param);

	public List<T> findBySQL(String sql, Object[] param, Integer page, Integer rows);
	public List<T> findBySQL(String sql, Integer page, Integer rows);

	//自添加
	public List<T> findBySQL1(String sql);

	public T findBySQL2(String sql);

	public List<T> findBySQL3(String sql);
    
}