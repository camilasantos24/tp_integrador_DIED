package Entidades;

public class Tramo {
	
	private int id;
	private Estacion estacion_origen;
	private Estacion estacion_destino;
	private int distancia_km;
	private int duracion;
	private int cant_max_pasajeros;
	private int estado;
	private float costo;
	
	public Tramo(int id, Estacion estacion_origen, Estacion estacion_destino, int distancia_km, int duracion,
			int cant_max_pasajeros, int estado, float costo) {
		super();
		this.id = id;
		this.estacion_origen = estacion_origen;
		this.estacion_destino = estacion_destino;
		this.distancia_km = distancia_km;
		this.duracion = duracion;
		this.cant_max_pasajeros = cant_max_pasajeros;
		this.estado = estado;
		this.costo = costo;
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

	public int getDistancia_km() {
		return distancia_km;
	}

	public void setDistancia_km(int distancia_km) {
		this.distancia_km = distancia_km;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getCant_max_pasajeros() {
		return cant_max_pasajeros;
	}

	public void setCant_max_pasajeros(int cant_max_pasajeros) {
		this.cant_max_pasajeros = cant_max_pasajeros;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	
	

}
