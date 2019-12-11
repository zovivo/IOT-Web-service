package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.Arduino;
import com.demo.entity.ArduinoInfo;
import com.demo.entity.ArduinoInput;
import com.demo.entity.ArduinoSearch;
import com.demo.entity.SensorInfo;
import com.demo.service.ArduinoService;

import ch.qos.logback.core.status.Status;

@Controller
@RequestMapping("/arduino")
public class ArduinoController {
	
	private static int STATUS = 0;
	
	@Autowired
	private ArduinoService arduinoService;
	
	// Get Status
	@RequestMapping(value="/status",method = RequestMethod.GET)
	@ResponseBody
	public int getStatus() {
		return STATUS;
	}

	// Get Arduino Info
	@RequestMapping(value="/arduino-info",method = RequestMethod.GET)
	@ResponseBody
	public ArduinoInfo getArduinoInfo(@RequestParam(name="id")int id) {
		return arduinoService.getAdruinoInfo(id);
	}

	// Get Sensor Info
	@RequestMapping(value="/sensor-info",method = RequestMethod.GET)
	@ResponseBody
	public SensorInfo getSensorInfo(@RequestParam(name="id")int id) {
		return arduinoService.getSensorInfo(id);
	}
	
	// Change status
	@RequestMapping(value="/change-status",method = RequestMethod.POST)
	@ResponseBody
	public int changeStatus(@RequestParam("status")int status) {
		STATUS = status;
		return STATUS;
	}
	
	// @RequestMapping(value="/all-log",method = RequestMethod.GET)
	// @ResponseBody
	// public List<Arduino> getAllArduinoLogs() {
	// 	return arduinoService.getAll();
	// }
	
	@RequestMapping(value="/log",method = RequestMethod.GET)
	@ResponseBody
	public List<Arduino> getByTime(@RequestParam(name = "date") long time) {
		return arduinoService.getByTime(time, time + 24*3600*1000l);
	}
	
	// @RequestMapping(value="/all",method = RequestMethod.GET)
	// @ResponseBody
	// public List<Arduino> searchAll(@RequestParam(name = "page") int page,@RequestParam(name = "pageSize") int pageSize) {
	// 	return arduinoService.searchAll(page, pageSize).getContent();
	// }
	
	// Search Log
	@RequestMapping(value="/search",method = RequestMethod.POST)
	@ResponseBody
	public List<Arduino> search(@RequestBody ArduinoSearch search) {
		return arduinoService.search(search);
	}
	
	// Get Live
	@RequestMapping(value="/live",method = RequestMethod.GET)
	@ResponseBody
	public List<Arduino> getLive(@RequestParam(name = "amount") int amount,@RequestParam(name = "arduinoId") int arduinoId,@RequestParam(name = "sensorId") int sensorId) {
		return arduinoService.getLive(amount,arduinoId,sensorId);
	}
	
	// Insert Log
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	@ResponseBody
	public int createArduinoLog(@RequestBody ArduinoInput input) {
		arduinoService.createLog(input);
		return STATUS;
	}

}
