//package com.demo.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.demo.entity.Arduino;
//
////@Repository
//public interface ArduinoRepository extends JpaRepository<Arduino, Integer> {
//	
//	@Query("SELECT a FROM Arduino a WHERE a.createtime >= :fromTime AND a.createtime < :toTime  ")
//	public List<Arduino> getByTime(@Param("fromTime") long fromTime, @Param("toTime") long toTime);
//	
//	@Query(value="SELECT * FROM arduino ORDER BY createtime DESC LIMIT :amount ", nativeQuery =true)
//	public List<Arduino> getByAmount(@Param("amount") int amount);
//	
//	
//	@Query(value="SELECT * FROM arduino ORDER BY createtime DESC LIMIT :amount ", nativeQuery =true)
//	public List<Arduino> getBy(@Param("amount") int amount);
//	
//}
