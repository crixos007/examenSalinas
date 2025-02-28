package com.caraveo.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caraveo.crud.model.Cliente;
import com.caraveo.crud.repository.ClienteRepository;
import com.caraveo.crud.util.ClienteMapper;

@Service("ClienteService")
public class ClienteImpl implements ClienteService{
	@Autowired
	ClienteRepository repository;
	
	@Autowired
    private ClienteMapper clienteMapper;
	
	@Override
	public Cliente altaCliente(Cliente in) throws Exception {
		try {
			return repository.save(in);
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}		
	}
	
	@Override
	public List<Cliente> getCliente() throws Exception {
		try {
			return repository.findAll();
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
	}
	
	@Override
	public Cliente actualizarCliente(Cliente in) throws Exception {
		try {
			Cliente clienteExistente = repository.findById(in.getId()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
			clienteMapper.actualizarCliente(clienteExistente, in);
			return repository.save(clienteExistente);
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}		
	}
	
	@Override
	public int deleteCliente(String id) throws Exception {
		try {
			Optional<Cliente> out = repository.findById(id);
			if(out.isPresent()) {
				repository.deleteById(out.get().getIdRecord());
				return 1;
			}
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
		return 0;
	}
	
	@Override
	public Cliente getCliente(String id) throws Exception {
		try {
			Cliente clienteExistente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
			return clienteExistente;
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}		
	}
}