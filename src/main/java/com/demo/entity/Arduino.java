package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Arduino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long createtime;
	private int status;
	@ManyToOne()
    @JoinColumn(name = "arduinoid", referencedColumnName = "id", nullable = false)
	private ArduinoInfo arduinoInfo;
	@ManyToOne()
    @JoinColumn(name = "sensorid", referencedColumnName = "id", nullable = false)
	private SensorInfo sensorInfo;
	private float value;
	
	public Arduino() {
		super();
	}
	public Arduino(ArduinoInput input) {
		this.value = input.getValue();
		this.status = input.getStatus();
		this.createtime = System.currentTimeMillis();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArduinoInfo getArduinoInfo() {
		return arduinoInfo;
	}
	public void setArduinoInfo(ArduinoInfo arduinoInfo) {
		this.arduinoInfo = arduinoInfo;
	}
	public SensorInfo getSensorInfo() {
		return sensorInfo;
	}
	public void setSensorInfo(SensorInfo sensorInfo) {
		this.sensorInfo = sensorInfo;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
}
