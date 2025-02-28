package com.caraveo.crud.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.caraveo.crud.model.Prestamo;
import com.caraveo.crud.service.PrestamoService;

@CrossOrigin(origins = "*")
@RestController()
public class PrestamoController {
	private static final Logger LOG = LogManager.getLogger(PrestamoController.class.getName());
	
	@Autowired
	PrestamoService service;
	
	@PostMapping("/prestamo")
	public ResponseEntity<Prestamo> altaPrestamo(@RequestBody Prestamo in) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Prestamo out = new Prestamo();
    	in.setDateRecorded(new Date());
		    	
		//LOGICA DEL SERVICIO
		try {	
			try {				
				out = service.altaPrestamo(in);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Prestamo>(out,HttpStatus.CREATED);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Prestamo>(out,headers,exception.getStatusCode());
		}
	}
	
	@GetMapping("/prestamo")
	public ResponseEntity<List<Prestamo>> prestamo() {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	List<Prestamo> out = new ArrayList<Prestamo>();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.getPrestamo();
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<List<Prestamo>>(out,HttpStatus.OK);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<List<Prestamo>>(out,headers,exception.getStatusCode());
		}
	}
	
	@PutMapping("/prestamo")
	public ResponseEntity<Prestamo> updatePrestamo(@RequestBody Prestamo in) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Prestamo out = new Prestamo();
    	    	
		//LOGICA DEL SERVICIO
		try {
			try {	
				out = service.actualizarPrestamo(in);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Prestamo>(out,HttpStatus.CREATED);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Prestamo>(out,headers,exception.getStatusCode());
		}
	}
	
	@DeleteMapping("/prestamo/{id}")
	public ResponseEntity<Prestamo> deleteCliente(@PathVariable("id") String id) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	int out;
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.deletePrestamo(id);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Prestamo>(new Prestamo(),out==1?HttpStatus.NO_CONTENT:HttpStatus.NOT_FOUND);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Prestamo>(new Prestamo(),headers,exception.getStatusCode());
		}
	}
}