package com.ci.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ci.bean.CustomerInfor;
import com.ci.util.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * CustomerInfor entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ci.bean.CustomerInfor
 * @author MyEclipse Persistence Tools
 */
public class CustomerInforDAO extends BaseHibernateDAO {

	
	public void saveCI(CustomerInfor aCI){
		Session session=HibernateSessionFactory.getSession();  
        Transaction transaction=session.beginTransaction();  
        //System.out.println(aCI.getQqNum());
        String sql = "INSERT INTO customer_infor(qq_num,NAME,file_name) VALUES(?,?,?)";
        Query query = session.createSQLQuery(sql);
        query.setString(0, aCI.getQqNum());
        query.setString(1, aCI.getName());
        query.setString(2, aCI.getFileName());
        try {
        	query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unique");
		}
        
        transaction.commit();  
        session.close();  
	}
	
	public void insertCP(CustomerInfor aCp){
		Session session=HibernateSessionFactory.getSession();  
        Transaction transaction=session.beginTransaction();  
        //System.out.println(aCI.getQqNum());
        String sql = "UPDATE customer_infor SET phone=? WHERE qq_num=?";
        Query query = session.createSQLQuery(sql);
        query.setString(0, aCp.getPhone());
        query.setString(1, aCp.getQqNum());
        try {
        	query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unique");
		}
        
        transaction.commit();  
        session.close();  
	} 
	
	public void saveCC(CustomerInfor aCC){
		Session session=HibernateSessionFactory.getSession();  
        Transaction transaction=session.beginTransaction();  
        //System.out.println(aCI.getQqNum());
        String sql = "UPDATE customer_infor SET compAndName=? WHERE qq_num=?";
        Query query = session.createSQLQuery(sql);
        query.setString(0, aCC.getCompAndName());
        query.setString(1, aCC.getQqNum());
        try {
        	query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unique");
		}
        
        transaction.commit();  
        session.close();  
	}
	
	public int getCIId(String aQqNum, String aFileName){
		int iId=0;
		Session session=HibernateSessionFactory.getSession();
        Transaction transaction=session.beginTransaction();  
        //System.out.println(aCI.getQqNum());
        String hql = "SELECT id FROM CustomerInfor WHERE qq_num=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, aQqNum);
        iId=(int) query.uniqueResult();
        //query.setString(0, aCpMd5);     
        transaction.commit();  
        session.close();  
        return iId;
	}
	
	public String isRepeat(CustomerInfor aCC, String aType){
		String sValue="";
		Session session=HibernateSessionFactory.getSession();
        Transaction transaction=session.beginTransaction();  
        //System.out.println(aCI.getQqNum());
        String hql = "SELECT "+aType+" FROM CustomerInfor WHERE qq_num=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, aCC.getQqNum());
        sValue=(String) query.uniqueResult();
        //query.setString(0, aCpMd5);     
        transaction.commit();  
        session.close();  
		return sValue;
	}
	
}