package com.sina.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("baseDAO")  
@SuppressWarnings("all")  
public class BaseDAOImpl<T> implements BaseDAO<T> {  
  
    private SessionFactory sessionFactory;  
  
    public SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
  
    @Autowired  
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }  
  
    private Session getCurrentSession() {  
        return sessionFactory.getCurrentSession();  
    }  
  
    public Serializable save(T o) {  
        return this.getCurrentSession().save(o);  
    }  
  
    public void delete(T o) {  
        this.getCurrentSession().delete(o);  
    }  
  
    public void update(T o) {  
    	System.out.println("执行更新");
    	//this.getCurrentSession().flush();
    	
        this.getCurrentSession().update(o);  
    }  
    
  
    public void saveOrUpdate(T o) {  
    	
        this.getCurrentSession().saveOrUpdate(o);  
    }  
  
    public List<T> find(String hql) {  
        return this.getCurrentSession().createQuery(hql).list();
    }
  
    public List<T> findBySQL(String sql){
		return this.getCurrentSession().createSQLQuery(sql).list();
    	
    }
    
    
   	@Override
   	public T getBySQL(String sql) {
   		// TODO Auto-generated method stub
   		return (T) this.getCurrentSession().createSQLQuery(sql).list().get(0);
   	}
   	
   	@Override
   	public T getBySQL1(String sql) {
   		System.out.println("session:"+this.getCurrentSession());
   		List<T> list = this.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
   		if(list == null || list.size() <=0 ){
   			return null;
   		}
   		
   		return (T)list.get(0);
   	}
   	
    public List<T> findBySQL(String sql, Object[] param){
        Query q = this.getCurrentSession().createSQLQuery(sql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        } 
		return q.list();
    	
    }
    public List<T> findBySQL(String sql, Object[] param, Integer page, Integer rows) {  
        if (page == null || page < 1) {  
            page = 1;  
        }  
        if (rows == null || rows < 1) {  
            rows = 10;  
        }  
        Query q = this.getCurrentSession().createSQLQuery(sql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        }  
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();  
    }  
    public List<T> findBySQL(String sql,Integer page, Integer rows) {  
        if (page == null || page < 1) {  
            page = 1;  
        }  
        if (rows == null || rows < 1) {  
            rows = 10;  
        }  
        Query q = this.getCurrentSession().createSQLQuery(sql);   
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();  
    } 
    
    public List<T> find1(String sql){
		return this.getCurrentSession().createSQLQuery(sql).list();
    	
    }
   
    
    public List<T> find(String hql, Object[] param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        }  
        return q.list();  
    }  
  
    public List<T> find(String hql, List<Object> param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.size() > 0) {  
            for (int i = 0; i < param.size(); i++) {  
                q.setParameter(i, param.get(i));  
            }  
        }  
        return q.list();  
    }  
    public List<T> find(String hql,Integer page, Integer rows) {  
        if (page == null || page < 1) {  
            page = 1;  
        }  
        if (rows == null || rows < 1) {  
            rows = 10;  
        }  
        Query q = this.getCurrentSession().createQuery(hql);  
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();  
    }
  
    public List<T> find(String hql, Object[] param, Integer page, Integer rows) {  
        if (page == null || page < 1) {  
            page = 1;  
        }  
        if (rows == null || rows < 1) {  
            rows = 10;  
        }  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        }  
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();  
    }  
  
    public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {  
        if (page == null || page < 1) {  
            page = 1;  
        }  
        if (rows == null || rows < 1) {  
            rows = 10;  
        }  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.size() > 0) {  
            for (int i = 0; i < param.size(); i++) {  
                q.setParameter(i, param.get(i));  
            }  
        }  
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();  
    }  
  
    public T get(Class<T> c, Serializable id) {  
        return (T) this.getCurrentSession().get(c, id);  
    }  
  
    public T get(String hql, Object[] param) {  
        List<T> l = this.find(hql, param);  
        if (l != null && l.size() > 0) {  
            return l.get(0);  
        } else {  
            return null;  
        }  
    }  
  
    public T get(String hql, List<Object> param) {  
        List<T> l = this.find(hql, param);  
        if (l != null && l.size() > 0) {  
            return l.get(0);  
        } else {  
            return null;  
        }  
    }  
  
    public Long count(String hql) {  
        return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();  
    }  
  
    public BigDecimal countBySQL(String sql) {  
        return (BigDecimal) this.getCurrentSession().createSQLQuery(sql).uniqueResult();  
    } 
    
    public BigInteger countBySQL1(String sql) {  
        return (BigInteger) this.getCurrentSession().createSQLQuery(sql).uniqueResult();  
    } 
    
    public Long count(String hql, Object[] param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        }  
        return (Long) q.uniqueResult();  
    }  
  
    public Long count(String hql, List<Object> param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.size() > 0) {  
            for (int i = 0; i < param.size(); i++) {  
                q.setParameter(i, param.get(i));  
            }  
        }  
        return (Long) q.uniqueResult();  
    }  
  
    public Integer executeHql(String hql) {  
        return this.getCurrentSession().createQuery(hql).executeUpdate();  
    }  
  
    public Integer executeHql(String hql, Object[] param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        }  
        return q.executeUpdate();  
    }  
  
    public Integer executeHql(String hql, List<Object> param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.size() > 0) {  
            for (int i = 0; i < param.size(); i++) {  
                q.setParameter(i, param.get(i));  
            }  
        }  
        return q.executeUpdate();  
    }

	@Override
	public void merge(T o) {
		  this.getCurrentSession().merge(o);  
	}

	@Override
	public void executeSql(String sql) {
		this.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public List<T> find1(String sql , Class bclass) {//XxxxbBean.class
		// TODO Auto-generated method stub
		return this.getCurrentSession().createSQLQuery(sql).addEntity(bclass).list();
	}

	/**
	 * 
	 * 自己添加的
	 */
	public List<T> findBySQL1(String sql){
		List<Map<String,Object>> list = this.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return (List<T>) list;
    }

	@Override
	public T findBySQL2(String sql) {
		return (T)this.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list().get(0);
	}

	@Override
	public List<T> findBySQL3(String sql) {
		// TODO Auto-generated method stub
		return this.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}


}  