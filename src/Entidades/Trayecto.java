package Entidades;

public class Trayecto {
	
	private int id;
	private Estacion estacion_origen;
	private Estacion estacion_destino;
	
	public Trayecto(int id, Estacion estacion_origen, Estacion estacion_destino) {
		super();
		this.id = id;
		this.estacion_origen = estacion_origen;
		this.estacion_destino = estacion_destino;
	}

	public int getId() {
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
	
	

}
