package com.caraveo.crud.service;

import java.util.List;

import com.caraveo.crud.model.Prestamo;

public interface PrestamoService {
	public Prestamo altaPrestamo(Prestamo in)throws Exception;
	public List<Prestamo> getPrestamo()throws Exception;
	public Prestamo actualizarPrestamo(Prestamo in)throws Exception;
	public int deletePrestamo(String id) throws Exception;
	public Prestamo getPrestamo(String id)throws Exception;
}