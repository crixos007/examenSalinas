package com.caraveo.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caraveo.crud.model.Prestamo;
import com.caraveo.crud.repository.PrestamoRepository;
import com.caraveo.crud.util.PrestamoMapper;

@Service("PrestamoService")
public class PrestamoImpl implements PrestamoService{
	@Autowired
	PrestamoRepository repository;
	
	@Autowired
    private PrestamoMapper prestamoMapper;
	
	@Override
	public Prestamo altaPrestamo(Prestamo in) throws Exception {
		try {
			return repository.save(in);
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}		
	}
	
	@Override
	public List<Prestamo> getPrestamo() throws Exception {
		try {
			return repository.findAll();
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
	}
	
	@Override
	public Prestamo actualizarPrestamo(Prestamo in) throws Exception {
		try {
			Prestamo prestamoExistente = repository.findById(in.getId()).orElseThrow(() -> new RuntimeException("Prestamo no encontrado"));
			prestamoMapper.actualizarPrestamo(prestamoExistente, in);
			return repository.save(prestamoExistente);
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}		
	}
	
	@Override
	public int deletePrestamo(String id) throws Exception {
		try {
			Optional<Prestamo> out = repository.findById(id);
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
	public Prestamo getPrestamo(String id) throws Exception {
		try {
			Prestamo prestamoExistente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
			return prestamoExistente;
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}		
	}
}