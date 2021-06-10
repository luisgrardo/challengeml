package com.rebelalliance.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rebelalliance.model.SateliteWrap;

@RestController
public class GreetingController {


	
	
	
	@RequestMapping(value = "/topsecret", method = RequestMethod.POST)
	@ResponseBody
	public void topSecret( @RequestBody SateliteWrap satelites) {
		System.out.println("yei");
	}
	
}

