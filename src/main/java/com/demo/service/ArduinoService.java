package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	final static String urlInfluxDB = "https://us-west-2-1.aws.cloud2.influxdata.com/api/v2/write?org=c021011a737d335e&bucket=my-bucket";
	final static String authorInfluxDB = "Token ak5mrOgELZ6XR7yQyF0Hc6BrGszJ3JciaUwBJJnkFlMK76ZTdMKNIxOKdH_fBOl25qZ5huoxeozKjuh_-xIzOg==";
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
		arduino.setCreatetime(System.currentTimeMillis());
		arduino= arduinoDAO.insert(arduino);
		insertLogToInfluxDB(arduino);
		return ;
	}
	
	public ArduinoInfo getAdruinoInfo(int id) {
		return arduinoInforRepository.getById(id);
	}
	
	public SensorInfo getSensorInfo(int id) {
		return sensorInfoRepository.getById(id);
	}
	
	public void insertLogToInfluxDB(Arduino arduino) {
		float value = arduino.getValue();
		String sensorName = arduino.getSensorInfo().getName();
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authorInfluxDB);
		String body = "arduino_1,sensor="+sensorName+" value="+value;
		HttpEntity<Object> request = new HttpEntity<>(body,headers);
		template.postForEntity(urlInfluxDB, request, Object.class);
	}
//	@Scheduled(fixedDelay = 1000000000000000l)
	public void randomImport1() {
		long fromTime =1575563400000l;
		Random rand = new Random(); 
		while(fromTime <= System.currentTimeMillis()) {
			System.out.println("Random new Log !!!");
			int rand_int1 = rand.nextInt(50);
			Arduino arduino = new Arduino();
			arduino.setCreatetime(fromTime);
			arduino.setStatus(0);
			arduino.setValue(rand_int1);
			arduino.setArduinoInfo(getAdruinoInfo(1));
			arduino.setSensorInfo(getSensorInfo(3));
			arduinoDAO.insert(arduino);
//			insertLogToInfluxDB(arduino);
			System.out.println("Id : "+arduino.getId()+" Value: "+arduino.getValue());
			fromTime+= 10*60*1000l;	
		}
		System.out.println("Finish !!!");
	}
//	@Scheduled(fixedDelay = 1000000000000000l)
	public void randomImport2() {
		long fromTime =1575563400000l;
		Random rand = new Random(); 
		while(fromTime <=  System.currentTimeMillis()) {
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
		}
		System.out.println("Finish !!!");
	}
//	@Scheduled(fixedDelay = 1000000000000000l)
	public void randomImport3() {
		long fromTime =1575563400000l;
		Random rand = new Random(); 
		while(fromTime <= System.currentTimeMillis()) {
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
		}
		System.out.println("Finish !!!");
	}
}
