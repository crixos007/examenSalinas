package com.caraveo.crud.dto;

public record ClienteRec(String id, String nombre, String email, int edad, TipoCliente tipoCliente) {

	public enum TipoCliente {
		REGULAR,
		VIP
	}
}