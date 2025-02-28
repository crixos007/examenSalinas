package com.caraveo.crud.util;

import org.mapstruct.BeanMapping;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.caraveo.crud.model.Prestamo;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actualizarPrestamo(@MappingTarget Prestamo prestamoExistente, Prestamo nuevosDatos);
	
	// Ignorar valores numéricos si son 0
	@Condition
    default boolean isNotZero(int value) {
        return value != 0;
    }
}