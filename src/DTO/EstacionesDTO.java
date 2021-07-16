package DTO;

import java.time.LocalTime;

public class EstacionesDTO {
	
	private int id;
	private String nombre;
	private LocalTime hs_apertura;
	private LocalTime hs_cierre;
	private int estado;
	private int alta_baja;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalTime getHs_apertura() {
		return hs_apertura;
	}
	public void setHs_apertura(LocalTime hs_apertura) {
		this.hs_apertura = hs_apertura;
	}
	public LocalTime getHs_cierre() {
		return hs_cierre;
	}
	public void setHs_cierre(LocalTime hs_cierre) {
		this.hs_cierre = hs_cierre;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getAlta_baja() {
		return alta_baja;
	}
	public void setAlta_baja(int alta_baja) {
		this.alta_baja = alta_baja;
	}
	
	

}
