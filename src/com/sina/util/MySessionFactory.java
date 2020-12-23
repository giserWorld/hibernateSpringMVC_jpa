package com.sina.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//获取SessionFactory工具类
final public class MySessionFactory {
	private static SessionFactory sessionFactory;//定义属性
	//构造方法
	private MySessionFactory(){
		
	}
	//static块
	static{
		//通过配置获取sessionFactory对象(只运行一次)
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		sessionFactory=config.buildSessionFactory();
	}
	//获取SessionFactory对象
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
