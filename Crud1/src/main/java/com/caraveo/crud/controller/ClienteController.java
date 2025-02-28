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

import com.caraveo.crud.dto.ClienteRec;
import com.caraveo.crud.model.Cliente;
import com.caraveo.crud.service.ClienteService;

@CrossOrigin(origins = "*")
@RestController()
public class ClienteController {
	private static final Logger LOG = LogManager.getLogger(ClienteController.class.getName());
	
	@Autowired
	ClienteService service;
	
	@PostMapping("/cliente")
	public ResponseEntity<Cliente> altaCliente(@RequestBody ClienteRec inRec) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Cliente out = new Cliente();
    	Cliente in = new Cliente(new Date(), inRec.id(), inRec.nombre(), inRec.email(), inRec.edad(), inRec.tipoCliente().toString());
		    	
		//LOGICA DEL SERVICIO
		try {	
			try {		
				switch (inRec.tipoCliente()) {
		            case VIP -> in.setDescuento(true);
		            case REGULAR -> in.setDescuento(false);
		        }				
				out = service.altaCliente(in);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Cliente>(out,HttpStatus.CREATED);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Cliente>(out,headers,exception.getStatusCode());
		}
	}
	
	@GetMapping("/cliente")
	public ResponseEntity<List<Cliente>> cliente() {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	List<Cliente> out = new ArrayList<Cliente>();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.getCliente();
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<List<Cliente>>(out,HttpStatus.OK);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<List<Cliente>>(out,headers,exception.getStatusCode());
		}
	}
	
	@PutMapping("/cliente")
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente in) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Cliente out = new Cliente();
    	    	
		//LOGICA DEL SERVICIO
		try {
			try {
					switch (in.getTipoCliente()) {
		            case "VIP" -> in.setDescuento(true);
		            case "REGULAR" -> in.setDescuento(false);
		        }	
				out = service.actualizarCliente(in);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Cliente>(out,HttpStatus.CREATED);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Cliente>(out,headers,exception.getStatusCode());
		}
	}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable("id") String id) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	int out;
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.deleteCliente(id);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Cliente>(new Cliente(),out==1?HttpStatus.NO_CONTENT:HttpStatus.NOT_FOUND);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Cliente>(new Cliente(),headers,exception.getStatusCode());
		}
	}
}