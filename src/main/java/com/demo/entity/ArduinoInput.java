package com.demo.entity;

public class ArduinoInput {
	
	private int status;
	private int arduinoid;
	private int sensorid;
	private float value;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArduinoInput() {
	}

	public int getArduinoId() {
		return arduinoid;
	}

	public void setArduinoId(int arduinoId) {
		this.arduinoid = arduinoId;
	}

	public int getSensorId() {
		return sensorid;
	}

	public void setSensorId(int sensorId) {
		this.sensorid = sensorId;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

}
