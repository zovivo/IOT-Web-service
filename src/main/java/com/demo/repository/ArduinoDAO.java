package com.demo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Arduino;
import com.demo.entity.ArduinoSearch;

@Repository
@Transactional
public class ArduinoDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public ArduinoDAO() {
	}
	
	public List<Arduino> getByTime(long fromTime, long toTime){
		String queryStr = " From Arduino a WHERE a.createtime >= :fromTime AND a.createtime < :toTime";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Arduino> query = session.createQuery(queryStr, Arduino.class);
		query.setParameter("fromTime", fromTime);
		query.setParameter("toTime", toTime);
		return query.getResultList();
	}
	
	public List<Arduino> getLive(int amount, int arduinoId, int sensorId){
		String queryStr = "Select * FROM Arduino a where a.arduinoid = :arduinoId and a.sensorid = :sensorId ORDER BY createtime DESC LIMIT :amount ";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Arduino> query = session.createNativeQuery(queryStr, Arduino.class);
		query.setParameter("amount", amount);
		query.setParameter("arduinoId", arduinoId);
		query.setParameter("sensorId", sensorId);
		return query.getResultList();
	}
	
	public Arduino insert(Arduino arduino) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(arduino);
		return arduino;
	}
	
	public List<Arduino> search(ArduinoSearch search){
		String queryStr = buildQuerySearch(search);
		Map<String, Object> params = buildParamsSearch(search);
		Session session = this.sessionFactory.getCurrentSession();
		Query<Arduino> query = session.createQuery(queryStr, Arduino.class);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		if (search.isPageable()) {
			return query.setFirstResult(search.getPage() * search.getPageSize())
                    .setMaxResults(search.getPageSize())
                    .getResultList();
		}
		return query.getResultList();
	}
	
	private String buildQuerySearch(ArduinoSearch search) {
        String query = " FROM Arduino a WHERE 1=1 ";
        if (search.getArduinoId() > 0) {
            query += " AND a.arduinoInfo.id = :arduinoId ";
        }
        if (search.getSensorId() > 0) {
            query += " AND a.sensorInfo.id = :sensorId ";
        }
        if (search.getFromTime() > 0) {
            query += " AND a.createtime >= :fromTime ";
        }
        if (search.getToTime() > 0) {
            query += " AND a.createtime < :toTime ";
        }        
        query +=" ORDER BY a.createtime DESC";
        return query;
    }

    private Map<String, Object> buildParamsSearch(ArduinoSearch search) {
        Map<String, Object> params = new HashMap<>();
        if (search.getArduinoId() > 0) {
        	params.put("arduinoId", search.getArduinoId());
        }
        if (search.getSensorId() > 0) {
        	params.put("sensorId", search.getSensorId());

        }
        if (search.getFromTime() > 0) {
        	params.put("fromTime", search.getFromTime());
        }
        if (search.getToTime() > 0) {
        	params.put("toTime", search.getToTime());
        }
        return params;
    }

}
