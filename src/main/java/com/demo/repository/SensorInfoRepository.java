package com.demo.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.SensorInfo;

@Repository
@Transactional
public class SensorInfoRepository {

	@Autowired
    private SessionFactory sessionFactory;
	
	public SensorInfo getById(int id){
		String queryStr = " From SensorInfo a WHERE a.id = :id ";
		Session session = this.sessionFactory.getCurrentSession();
		Query<SensorInfo> query = session.createQuery(queryStr, SensorInfo.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
}
