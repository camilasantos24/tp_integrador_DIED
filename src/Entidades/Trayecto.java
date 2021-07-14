package Entidades;

import java.util.List;

public class Trayecto {
	
	private long id;
	private Estacion estacion_origen;
	private Estacion estacion_destino;
	private List<Estacion> estaciones; 
	
	public Trayecto(long id, Estacion estacion_origen, Estacion estacion_destino) {
		super();
		this.id = id;
		this.estacion_origen = estacion_origen;
		this.estacion_destino = estacion_destino;
	}
	
	
	public Trayecto (String obj) {
		String[] atributos= obj.split("\t");
		
		this.id= Long.parseLong(atributos[0]);
		
		
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Estacion getEstacion_origen() {
		return estacion_origen;
	}

	public void setEstacion_origen(Estacion estacion_origen) {
		this.estacion_origen = estacion_origen;
	}

	public Estacion getEstacion_destino() {
		return estacion_destino;
	}

	public void setEstacion_destino(Estacion estacion_destino) {
		this.estacion_destino = estacion_destino;
	}

	public List<Estacion> getEstaciones() {
		return estaciones;
	}

	public void setEstaciones(List<Estacion> estaciones) {
		this.estaciones = estaciones;
	}
	
	
	
	

}
