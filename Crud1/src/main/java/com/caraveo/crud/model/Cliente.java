package com.caraveo.crud.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecord;

    @Temporal(TemporalType.DATE)
    private Date dateRecorded;

    private String id;
    private String nombre;
    private String email;
    private int edad;
    private String tipoCliente;
    private boolean descuento;
    
    /**
     * Constructor sin argumentos
     */
    public Cliente() {
    }

    /**
	 * @param dateRecorded
	 * @param id
	 * @param nombre
	 * @param email
	 * @param edad
	 * @param tipoCliente
	 */
	public Cliente(Date dateRecorded, String id, String nombre, String email, int edad, String tipoCliente) {
		this.dateRecorded = dateRecorded;
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @param idRecord
	 * @param dateRecorded
	 * @param id
	 * @param nombre
	 * @param email
	 * @param edad
	 * @param tipoCliente
	 */
	public Cliente(Integer idRecord, Date dateRecorded, String id, String nombre, String email, int edad, String tipoCliente) {
		this.idRecord = idRecord;
		this.dateRecorded = dateRecorded;
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return the idRecord
	 */
	public Integer getIdRecord() {
		return idRecord;
	}

	/**
	 * @param idRecord the idRecord to set
	 */
	public void setIdRecord(Integer idRecord) {
		this.idRecord = idRecord;
	}

	/**
	 * @return the dateRecorded
	 */
	public Date getDateRecorded() {
		return dateRecorded;
	}

	/**
	 * @param dateRecorded the dateRecorded to set
	 */
	public void setDateRecorded(Date dateRecorded) {
		this.dateRecorded = dateRecorded;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return the descuento
	 */
	public boolean isDescuento() {
		return descuento;
	}

	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(boolean descuento) {
		this.descuento = descuento;
	}
}