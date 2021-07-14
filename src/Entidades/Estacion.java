package Entidades;

import java.time.LocalTime;

public class Estacion {

	private long id_estacion;
	private String nombre;
	private LocalTime hs_apertura;
	private LocalTime hs_cierre;
	private long estado; //1- Activa ; 0-Mantenimiento
	
	
	public Estacion() {
	}
	
	

	public Estacion(long id_estacion, String nombre, LocalTime hs_apertura, LocalTime hs_cierre, long estado) {
		super();
		this.id_estacion = id_estacion;
		this.nombre = nombre;
		this.hs_apertura = hs_apertura;
		this.hs_cierre = hs_cierre;
		this.estado = estado;
	}


	public Estacion(String obj) {
		String[] atributos= obj.split("\t");
		
		this.id_estacion= Long.parseLong(atributos[0]);
		this.nombre= atributos[1];
		this.hs_apertura= LocalTime.parse(atributos[2]);
		this.hs_cierre=LocalTime.parse(atributos[3]);
		this.estado=Long.parseLong(atributos[4]);
	}
	
	//getters and setters 

	public long getId_estacion() {
		return id_estacion;
	}

	public void setId_estacion(long id_estacion) {
		this.id_estacion = id_estacion;
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

	public long getEstado() {
		return estado;
	}

	public void setEstado(long estado) {
		this.estado = estado;
	}
	
	
}
