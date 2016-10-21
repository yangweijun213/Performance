package com.lrm.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

import com.lrm.data.DimTransaction;
import com.lrm.data.FctTestResult;

import sun.reflect.generics.tree.Tree;

import com.lrm.data.DimTestRun;
import com.lrm.biz.*;

public class DBHelper {
	
	static private long st;
	static private long et;
	static private int runId;
	static private int transactionId;
	
	public static void saveDimTestRun(long startTime, long endTime) {
		
		st = startTime;
		et= endTime;
		//DimRun
		DimTestRun dimTestRun = new DimTestRun();
		dimTestRun.setStartTime(DateTimeUtil.convertLongToTimestamp(startTime));
		dimTestRun.setEndTime(DateTimeUtil.convertLongToTimestamp(endTime));
		
		// 建立session
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		// 开始事务
		Transaction transaction = session.beginTransaction();
		//保存dimTestRun
		session.save(dimTestRun);
		runId = dimTestRun.getRunId();
		// 提交事务
		transaction.commit();
		// 关闭session
		session.close();		
	}
	
	public static void updateDimTestRun(long endTime2) {	        
        // 建立session
        Session session = new Configuration().configure().buildSessionFactory()
                .openSession();
        // 开始事务
        Transaction transaction = session.beginTransaction();
        DimTestRun dimTestRun = (DimTestRun) session.get(DimTestRun.class, runId);
        dimTestRun.setEndTime(DateTimeUtil.convertLongToTimestamp(endTime2));
        session.update(dimTestRun);
        session.getTransaction().commit();
		// 关闭session
		session.close();	
        	
	}
	
	public static void saveDimTransaction(String name) {
		
		// 声明DimTransaction对象，并赋值
		DimTransaction dimTransaction = new DimTransaction();
		dimTransaction.setTransactionName(name);
				
		// 建立session
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		// 开始事务
		Transaction transaction = session.beginTransaction();
		// 保存dimTransaction
		session.save(dimTransaction);
		transactionId = dimTransaction.getTransactionId();	
		// 提交事务
		transaction.commit();
		// 关闭session
		session.close();
	}
	
	public static void saveFctTestResult (long userActimeTime, long domReadyTime) {
		
		// 声明FctTestResult对象
		FctTestResult fctTestResult = new FctTestResult();
		fctTestResult.setUserActionTime(DateTimeUtil.convertLongToTimestamp(userActimeTime));
		fctTestResult.setDomReadyTime(DateTimeUtil.convertLongToTimestamp(domReadyTime));
		fctTestResult.setTransactionId(transactionId);
		fctTestResult.setRunId(runId);
		
		// 建立session
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		// 开始事务
		Transaction transaction = session.beginTransaction();
		// 保存fctTestResult
		session.save(fctTestResult);
		// 提交事务
		transaction.commit();
		// 关闭session
		session.close();
	}
	
	public static void  getFctTestResult () {
		FctTestResult fctTestResult = new FctTestResult();
				
		// 建立session
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		// 开始事务
		Transaction transaction = session.beginTransaction();
		//使用 createQuery(hql)
		Query query=session.createQuery("from DimTransaction");
		List  list = query.list(); 
		// 提交事务
		transaction.commit();
		// 关闭session
		session.close();
		
		System.out.println(list.get(1));
		
//		for(int i=0;i<list.size();i++) {
//			Map map =(Map)list.get(i);
//			System.out.println(map.get("transaction_id"));
//		}
	
	}
	
/*
	public static void save(String name,long userActimeTime, long domReadyTime, long startTime, long endTime) {

		// 声明DimTransaction对象，并赋值
		DimTransaction dimTransaction = new DimTransaction();
		dimTransaction.setTransactionName(name);
		
		//DimRun
		DimTestRun dimTestRun = new DimTestRun();
		dimTestRun.setStartTime(DateTimeUtil.convertLongToTimestamp(startTime));
		dimTestRun.setEndTime(DateTimeUtil.convertLongToTimestamp(endTime));
		
		// 声明FctTestResult对象
		FctTestResult fctTestResult = new FctTestResult();
		fctTestResult.setUserActionTime(DateTimeUtil.convertLongToTimestamp(userActimeTime));
		fctTestResult.setDomReadyTime(DateTimeUtil.convertLongToTimestamp(domReadyTime));

		// 将fctTestResult添加到dimTransaction
		dimTransaction.getFctTestResults().add(fctTestResult);
		dimTestRun.getFctTestResults().add(fctTestResult);

		// 建立session
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		// 开始事务
		Transaction transaction = session.beginTransaction();
		// 保存dimTransaction
		session.save(dimTransaction);
		// 保存fctTestResult
		session.save(fctTestResult);
		//保存dimTestRun
		session.save(dimTestRun);
		// 提交事务
		transaction.commit();
		// 关闭session
		session.close();
	}

*/
	

}
