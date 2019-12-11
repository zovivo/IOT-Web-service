package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.demo.entity.Arduino;
import com.demo.entity.ArduinoInfo;
import com.demo.entity.ArduinoInput;
import com.demo.entity.ArduinoSearch;
import com.demo.entity.SensorInfo;
import com.demo.repository.ArduinoDAO;
import com.demo.repository.ArduinoInforRepository;
import com.demo.repository.SensorInfoRepository;

@Service
public class ArduinoService {
	
	@Autowired
	private ArduinoDAO arduinoDAO;
	@Autowired
	private ArduinoInforRepository arduinoInforRepository;
	@Autowired
	private SensorInfoRepository sensorInfoRepository;
	
	public Page<Arduino> searchAll(int page, int pageSize){
		PageRequest pageRequest = new PageRequest(page, pageSize);
		Page<Arduino> output = null;
 		return output;
	}
	
	public List<Arduino> search(ArduinoSearch search){
		return arduinoDAO.search(search);
	}
	
	public List<Arduino> getAll(){
		return new ArrayList<Arduino>();
	}
	
	public List<Arduino> getByTime(long fromTime, long toTime){
		return arduinoDAO.getByTime(fromTime, toTime);
	}
	
	public List<Arduino> getLive(int amount, int arduinoId, int sensorId){
		return arduinoDAO.getLive(amount, arduinoId, sensorId);
	}
	
	@Async
	public void createLog(ArduinoInput input) {
		Arduino arduino = new Arduino(input);
		ArduinoInfo arduinoInfo = arduinoInforRepository.getById(input.getArduinoId());
		SensorInfo sensorInfo = sensorInfoRepository.getById(input.getSensorId());
		arduino.setArduinoInfo(arduinoInfo);
		arduino.setSensorInfo(sensorInfo);
		arduino= arduinoDAO.insert(arduino);
		return ;
	}
	
	public ArduinoInfo getAdruinoInfo(int id) {
		return arduinoInforRepository.getById(id);
	}
	
	public SensorInfo getSensorInfo(int id) {
		return sensorInfoRepository.getById(id);
	}
	
//	@Scheduled(fixedDelay=5000L)
	public void randomImport1() {
		long fromTime =System.currentTimeMillis();
//		long fromTime =1575565200000l;
		Random rand = new Random(); 
//		while(fromTime <= 1575651600000l) {
			System.out.println("Random new Log !!!");
			int rand_int1 = rand.nextInt(50);
			Arduino arduino = new Arduino();
			arduino.setCreatetime(fromTime);
			arduino.setStatus(0);
			arduino.setValue(rand_int1);
			arduino.setArduinoInfo(getAdruinoInfo(1));
			arduino.setSensorInfo(getSensorInfo(1));
			arduinoDAO.insert(arduino);
			System.out.println("Id : "+arduino.getId()+" Value: "+arduino.getValue());
			fromTime+= 10*60*1000l;	
//		}
		System.out.println("Finish !!!");
	}
	
//	@Scheduled(fixedDelay=5000L)
	public void randomImport2() {
		long fromTime =System.currentTimeMillis();
//		long fromTime =1575565200000l;
		Random rand = new Random(); 
//		while(fromTime <= 1575651600000l) {
			System.out.println("Random new Log !!!");
			int rand_int1 = rand.nextInt(50);
			Arduino arduino = new Arduino();
			arduino.setCreatetime(fromTime);
			arduino.setStatus(0);
			arduino.setValue(rand_int1);
			arduino.setArduinoInfo(getAdruinoInfo(1));
			arduino.setSensorInfo(getSensorInfo(2));
			arduinoDAO.insert(arduino);
			System.out.println("Id : "+arduino.getId()+" Value: "+arduino.getValue());
			fromTime+= 10*60*1000l;	
//		}
		System.out.println("Finish !!!");
	}
	
//	@Scheduled(fixedDelay=5000L)
	public void randomImport3() {
		long fromTime =System.currentTimeMillis();
//		long fromTime =1575565200000l;
		Random rand = new Random(); 
//		while(fromTime <= 1575651600000l) {
			System.out.println("Random new Log !!!");
			int rand_int1 = rand.nextInt(50);
			Arduino arduino = new Arduino();
			arduino.setCreatetime(fromTime);
			arduino.setStatus(0);
			arduino.setValue(rand_int1);
			arduino.setArduinoInfo(getAdruinoInfo(1));
			arduino.setSensorInfo(getSensorInfo(1));
			arduinoDAO.insert(arduino);
			System.out.println("Id : "+arduino.getId()+" Value: "+arduino.getValue());
			fromTime+= 10*60*1000l;	
//		}
		System.out.println("Finish !!!");
	}
}
