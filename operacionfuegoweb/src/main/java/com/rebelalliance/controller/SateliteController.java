package com.rebelalliance.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rebelalliance.exceptions.RebelAllianceException;
import com.rebelalliance.model.Greeting;
import com.rebelalliance.model.SateliteWrap;
import com.rebelalliance.service.SateliteService;

@RestController
public class SateliteController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	SateliteService sateliteService;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@RequestMapping(value = "/topsecret", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity  topSecret(@RequestBody SateliteWrap satelites) {
		
		try {
            return ResponseEntity.status(HttpStatus.OK).body(sateliteService.getInformation(satelites));
        }catch (RebelAllianceException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
		
	
	}

}
