package com.sina.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*************************Hibernate工具类***********************
 *executeQuery():执行hql查询
 *executeQueryByPage():执行hql查询(带分页)
 *save():统一的保存
 *executeUpdate():执行更新(删除)操作
 **/
final public class HibernateUtil {
	private static SessionFactory sessionFactory=null;
	private static ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
	static{
		//通过配置获取sessionFactory对象(只运行一次),默认获取hibernate.cfg.xml文件
		Configuration config=new Configuration().configure();
		sessionFactory=config.buildSessionFactory();
	}
	
	/******************执行更新(删除)操作******************
	 * @param hql
	 * @param parameters
	 * @return
	 */
	public static void executeUpdate(String hql,String[] parameters){
		Session session=null;
		Transaction ts=null;
		List list=null;
		try {
			session=HibernateUtil.getCurrentSession();
			ts=session.beginTransaction();
			Query query=session.createQuery(hql);
			if(parameters!=null&&parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i,parameters[i]);
				}
			}
			query.executeUpdate();
			ts.commit();
		} 
		catch (Exception e) {
			if(ts!=null){
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}
		finally{
			if(session!=null){
				session.close();
			}
		}
	}//e
	
	
	//统一的添加方法
	public static void save(Object obj){
		Session session=null;
		Transaction ts=null;
		try {
			session=HibernateUtil.getCurrentSession();
			ts=session.beginTransaction();
			session.save(obj);
			ts.commit();
		} 
		catch (Exception e) {
			if(ts!=null){
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}
		finally{
			if(session!=null){
				session.close();
			}
		}
	}//e
	
	
	/***********执行hql统一查询方法(带分页)***********
	 * @param hql
	 * @param parameters
	 * @param pagesize
	 * @param nowPage
	 * @return
	 */
	public static List executeQueryByPage(String hql,String[] parameters,int pagesize,int nowPage){
		Session session=null;
		Transaction ts=null;
		List list=null;
		try {
			session=HibernateUtil.getCurrentSession();
			ts=session.beginTransaction();
			Query query=session.createQuery(hql);
			if(parameters!=null&&parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i,parameters[i]);
				}
			}
			int startIndex=(nowPage-1)*pagesize;
			//分页设置
			query.setFirstResult(startIndex).setMaxResults(pagesize);
			list=query.list();
			ts.commit();
		} 
		catch (Exception e) {
			if(ts!=null){
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}
		finally{
			if(session!=null){
				session.close();
			}
		}
		return list;
	}//e
	
	/******************执行hql统一查询方法******************
	 * @param hql
	 * @param parameters
	 * @return
	 */
	public static List executeQuery(String hql,String[] parameters){
		Session session=null;
		Transaction ts=null;
		List list=null;
		try {
			session=HibernateUtil.getCurrentSession();
			ts=session.beginTransaction();
			Query query=session.createQuery(hql);
			if(parameters!=null&&parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i,parameters[i]);
				}
			}
			list=query.list();
			ts.commit();
		} 
		catch (Exception e) {
			if(ts!=null){
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}
		finally{
			if(session!=null){
				session.close();
			}
		}
		return list;
	}//e
	
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	public static Session getCurrentSession(){
		Session session=threadLocal.get();
		if(session==null){
			session=sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}
}
