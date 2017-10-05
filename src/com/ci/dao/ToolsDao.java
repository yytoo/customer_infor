package com.ci.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ci.util.HibernateSessionFactory;

public class ToolsDao {
	public List<String> getCityName(){
		List<String> cityList = new ArrayList<String>();
		Session session=HibernateSessionFactory.getSession();
        Transaction transaction=session.beginTransaction();  
        //System.out.println(aCI.getQqNum());
        String hql = "SELECT sd.distName FROM SysDist sd";
        Query query = session.createQuery(hql);
        cityList= query.list(); 
        transaction.commit();  
        session.close();  
        return cityList;
	}
	
	public List<String> getFactoryShortName(){
		List<String> factoryList = new ArrayList<String>();
		Session session=HibernateSessionFactory.getSession();
        Transaction transaction=session.beginTransaction();  
        //System.out.println(aCI.getQqNum());
        String hql = "SELECT f.shortName FROM Factory f";
        Query query = session.createQuery(hql);
        factoryList= query.list(); 
        transaction.commit();  
        session.close();  
        return factoryList;
	}
}
