package com.caraveo.crud.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.caraveo.crud.model.Cliente;
import com.caraveo.crud.model.Prestamo;
import com.caraveo.crud.service.ClienteService;
import com.caraveo.crud.service.PrestamoService;

@CrossOrigin(origins = "*")
@RestController()
public class InteresController {
	private static final Logger LOG = LogManager.getLogger(InteresController.class.getName());
	
	@Autowired
	PrestamoService prestamoService;
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/pago/{idPrestamo}")
	public ResponseEntity<Map<String, Object>> getPago(@PathVariable("idPrestamo") String idPrestamo) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Map<String, Object> out = new HashMap<>();
		    	
		//LOGICA DEL SERVICIO
		try {	
			try {				
				Prestamo prestamo = prestamoService.getPrestamo(idPrestamo);
				Cliente cliente = clienteService.getCliente(prestamo.getClienteId());
				
				if(cliente.getTipoCliente().equals("VIP")) {
					out.put("tasa de interes", "5%");
					out.put("total a pagar", prestamo.getMonto() * 1.05);
				}else {
					out.put("tasa de interes", "10%");
					out.put("total a pagar", prestamo.getMonto() * 1.10);
				}
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Map<String, Object>>(out,HttpStatus.CREATED);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Map<String, Object>>(out,headers,exception.getStatusCode());
		}
	}
}