package com.caraveo.crud.service;

import java.util.List;

import com.caraveo.crud.model.Cliente;

public interface ClienteService {
	public Cliente altaCliente(Cliente in)throws Exception;
	public List<Cliente> getCliente()throws Exception;
	public Cliente actualizarCliente(Cliente in)throws Exception;
	public int deleteCliente(String id) throws Exception;
	public Cliente getCliente(String id)throws Exception;
}