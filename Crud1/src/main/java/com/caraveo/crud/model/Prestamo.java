package com.caraveo.crud.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecord;

    @Temporal(TemporalType.DATE)
    private Date dateRecorded;
    
    private String id;
    private String clienteId;
    private double monto;
    private LocalDate fecha;
    
    @Enumerated(EnumType.STRING)  // Mapear el enum como un string en la base de datos
    private Estado estado;
        
    /**
	 * Constructor sin argumentos
	 */
	public Prestamo() {
	}

	/**
	 * @param dateRecorded
	 * @param id
	 * @param monto
	 * @param fecha
	 * @param estado
	 * @param clienteId
	 */
	public Prestamo(Date dateRecorded, String id, double monto, LocalDate fecha, Estado estado, String clienteId) {
		super();
		this.dateRecorded = dateRecorded;
		this.id = id;
		this.monto = monto;
		this.fecha = fecha;
		this.estado = estado;
		this.clienteId = clienteId;
	}

	/**
	 * @param idRecord
	 * @param id
	 * @param monto
	 * @param fecha
	 * @param estado
	 * @param clienteId
	 */
	public Prestamo(Integer idRecord, String id, double monto, LocalDate fecha, Estado estado, String clienteId) {
		super();
		this.idRecord = idRecord;
		this.id = id;
		this.monto = monto;
		this.fecha = fecha;
		this.estado = estado;
		this.clienteId = clienteId;
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
	 * @return the monto
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}

	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the clienteId
	 */
	public String getClienteId() {
		return clienteId;
	}

	/**
	 * @param clienteId the clienteId to set
	 */
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public enum Estado {
    	PENDIENTE,
    	PAGADO
    }
}
