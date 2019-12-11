package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = {"http://localhost:8080/demo-aws","http://springbooteb1-env.rzpga2pgvr.us-east-1.elasticbeanstalk.com/"})
public class TestController {
	
	@RequestMapping(value="", method =RequestMethod.GET)
	@ResponseBody
	public String test() {
		return "Connect Success !";
	}

}
