package com.demo.repository;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.ArduinoInfo;

@Repository
@Transactional
public class ArduinoInforRepository {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public ArduinoInfo getById(int id){
		String queryStr = " From ArduinoInfo a WHERE a.id = :id ";
		Session session = this.sessionFactory.getCurrentSession();
		Query<ArduinoInfo> query = session.createQuery(queryStr, ArduinoInfo.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

}
