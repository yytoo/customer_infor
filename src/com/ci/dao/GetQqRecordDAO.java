package com.ci.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ci.bean.CustomerAddr;
import com.ci.bean.CustomerMark;
import com.ci.bean.QqRecord;
import com.ci.util.HibernateSessionFactory;

public class GetQqRecordDAO {
	public List<List> getMarkList(){
		QqRecord qr = new QqRecord();
		List<CustomerMark> cmList = new ArrayList<CustomerMark>();
		List<CustomerAddr> caList = new ArrayList<CustomerAddr>();
		List<List> list = new ArrayList<List>();
		Session session=HibernateSessionFactory.getSession();
        String hql = "FROM QqRecord";
        Query query = session.createQuery(hql);
     	Iterator<QqRecord> iterator=query.iterate();
     	while(iterator.hasNext()){
    		CustomerMark cm = new CustomerMark();     //必须将初始化放在while中，否则每次修改cm的时候会将cmList中的cm改掉
    		CustomerAddr ca = new CustomerAddr();
 			int cmFlag=1;
 			int caFlag=1;
     		qr=iterator.next();
     		
     		cm.setMark(qr.getMark());
     		if(qr.getMarkId()!=null){
     			cm.setMarkId(qr.getMarkId());
     		}else{
     			cm.setMarkId(0);
     		}    		
     		cm.setFactory(qr.getFactory());
     		if(qr.getFactoryId()!=null){
     			cm.setFactoryId(qr.getFactoryId());
     		}else{
     			cm.setFactoryId(0);
     		}    		
     		cm.setQqNum(qr.getQq());
     		cm.setMarkCount(1);
     		
     		ca.setQqNum(qr.getQq());     		
     		if(qr.getAddress()!=null){
     			ca.setAddr(qr.getAddress());
     		}else{
     			ca.setAddr("0");
     		}
     		ca.setCityId(qr.getCityId());
     		ca.setAddrCount(1);
 		
     		if(cmList!=null && cmList.size()!=0){
	     		for(int i=0; i<cmList.size(); i++){	 
	     			if((cm.getQqNum()).equals(cmList.get(i).getQqNum()) && (cm.getMark()).equals(cmList.get(i).getMark()) && (cm.getFactory()).equals(cmList.get(i).getFactory())){
	     				int cmCount = cmList.get(i).getMarkCount()+1;
	     				cmList.get(i).setMarkCount(cmCount);
	     				cmFlag=0;     				
	     				break;
	     			}
	     		}
	     		
     		}
     		if(cmFlag==1){
     			cmList.add(cm);
     		}
     		
     		if(caList!=null && caList.size()!=0){
     			for(int j=0; j<caList.size(); j++){
     				if((ca.getAddr()).equals(caList.get(j).getAddr()) && (ca.getQqNum()).equals(caList.get(j).getQqNum())){
     					int caCount = caList.get(j).getAddrCount()+1;
     					caList.get(j).setAddrCount(caCount);
     					caFlag=0;
     					break;
     				}
     			}
     		}
     		if(caFlag==1){
     			caList.add(ca);
     		}
     	} 
     	
        session.close(); 
        list.add(cmList);
        list.add(caList);
        return list;
	}
	
	public void insertMark(List<CustomerMark> aCmList){
		Session session=HibernateSessionFactory.getSession();
	    Transaction transaction=session.beginTransaction();  
		for(int i=0; i<aCmList.size(); i++){
			int iId=isRepeatMark(aCmList.get(i), session);
			if(iId==0){
				saveMark(aCmList.get(i), session);
			}else{
				updateCountMark(iId, aCmList.get(i).getMarkCount(), session);
			}
		}
		transaction.commit();  
	    session.close(); 
	}
	
	
	public int isRepeatMark(CustomerMark aCm, Session session){
		int iId=0;
        String hql = "SELECT id FROM CustomerMark WHERE qqNum=? AND mark=? AND factory=?";
        Query query = session.createQuery(hql);
        query.setString(0, aCm.getQqNum());
        query.setString(1, aCm.getMark());
        query.setString(2, aCm.getFactory());
        if(query.uniqueResult()!=null){
        	 iId=(int) query.uniqueResult(); 
        }         
        return iId;
	}
	
	public void saveMark(CustomerMark aCm, Session session){
        String sql = "INSERT INTO customer_mark(qq_num, mark, mark_id, factory, factory_id, mark_count) VALUES(?,?,?,?,?,?)";
        Query query = session.createSQLQuery(sql);
        query.setString(0, aCm.getQqNum());
        query.setString(1, aCm.getMark());
        query.setInteger(2, aCm.getMarkId());
        query.setString(3, aCm.getFactory());
        query.setInteger(4, aCm.getFactoryId());      
        query.setInteger(5, aCm.getMarkCount());
       	query.executeUpdate(); 
	}
	
	
	public void updateCountMark(int aId, int aCount, Session session){
        String sql = "UPDATE customer_mark SET mark_count=? WHERE id=?";
        Query query = session.createSQLQuery(sql);
        query.setInteger(0, aCount);
        query.setInteger(1, aId);
    	query.executeUpdate();     
	}
	
	public void insertAddr(List<CustomerAddr> aCaList){
		Session session=HibernateSessionFactory.getSession();
	    Transaction transaction=session.beginTransaction();  
		for(int i=0; i<aCaList.size(); i++){
			int iId=isRepeatAddr(aCaList.get(i), session);
			if(iId==0){
				saveAddr(aCaList.get(i), session);
			}else{
				updateCountAddr(iId, aCaList.get(i).getAddrCount(), session);
			}
		}
		transaction.commit();  
	    session.close(); 
	}
	
	
	public int isRepeatAddr(CustomerAddr aCa, Session session){
		int iId=0;
        String hql = "SELECT id FROM CustomerAddr WHERE qqNum=? AND addr=?";
        Query query = session.createQuery(hql);
        query.setString(0, aCa.getQqNum());
        query.setString(1, aCa.getAddr());
        if(query.uniqueResult()!=null){
        	 iId=(int) query.uniqueResult(); 
        }         
        return iId;
	}
	
	public void saveAddr(CustomerAddr aCa, Session session){
        String sql = "INSERT INTO customer_addr(qq_num, addr, city_id, addr_count) VALUES(?,?,?,?)";
        Query query = session.createSQLQuery(sql);
        query.setString(0, aCa.getQqNum());
        query.setString(1, aCa.getAddr());
        query.setString(2, aCa.getCityId());
        query.setInteger(3, aCa.getAddrCount());
       	query.executeUpdate(); 
	}
	
	
	public void updateCountAddr(int aId, int aCount, Session session){
        String sql = "UPDATE customer_addr SET addr_count=? WHERE id=?";
        Query query = session.createSQLQuery(sql);
        query.setInteger(0, aCount);
        query.setInteger(1, aId);
    	query.executeUpdate();     
	}

}
