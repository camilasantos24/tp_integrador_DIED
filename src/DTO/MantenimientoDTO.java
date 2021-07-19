package DTO;

import java.time.LocalDate;

public class MantenimientoDTO {
	
	private int id_mant;
	private int id_estacion;
	private LocalDate fecha_inicio;
	private LocalDate fecha_fin;
	private  String observ;
	
	public int getId_mant() {
		return id_mant;
	}
	public void setId_mant(int id_mant) {
		this.id_mant = id_mant;
	}
	public int getId_estacion() {
		return id_estacion;
	}
	public void setId_estacion(int id_estacion) {
		this.id_estacion = id_estacion;
	}
	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public LocalDate getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(LocalDate fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getObserv() {
		return observ;
	}
	public void setObserv(String observ) {
		this.observ = observ;
	}
	
	

}
