package com.rebelalliance.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
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

/**
 * @author Luis Gerardo Espinosa Sampayo
 * Clase representa el controlador Satelite, 
 *
 *
 */
@RestController
public class SateliteController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Ocurrio un Error , verifique la ruta o el payload(JSON)";
    }

	
	/**
	 * SateliteService 
	 * 
	 */
	@Autowired
	SateliteService sateliteService;

	
	/**
	 * Servicio Rest para operacion topsecret 
	 * @param satelites
	 * @return regresa la entidad WrapInformation 
	 */
	@RequestMapping(value = "/topsecret", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity topSecret(@RequestBody SateliteWrap satelites) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(sateliteService.getInformation(satelites));
		} catch (RebelAllianceException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

	}

}
