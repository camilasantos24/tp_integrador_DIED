package Entidades;

import java.time.LocalDate;
import java.util.List;

public class Boleto {
	
	private int nro_boleto;
	private LocalDate fechaVenta;
	private float costo;
	private Estacion estacion_origen;
	private Estacion estacion_destino;
	private Usuario usuario;
	private List<Estacion> camino;
	
	public Boleto() {
		
	}
	
	public Boleto(int nro_boleto, LocalDate fechaVenta, float costo, Estacion estacion_origen, Estacion estacion_destino, Usuario usuario, List<Estacion> camino) {
		super();
		this.nro_boleto = nro_boleto;
		this.fechaVenta = fechaVenta;
		this.costo = costo;
		this.estacion_origen = estacion_origen;
		this.estacion_destino = estacion_destino;
		this.usuario = usuario;
		this.camino=camino;
	}

	public int getNro_boleto() {
		return nro_boleto;
	}

	public void setNro_boleto(int nro_boleto) {
		this.nro_boleto = nro_boleto;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Estacion> getCamino() {
		return camino;
	}

	public void setCamino(List<Estacion> camino) {
		this.camino = camino;
	}
	
	

}
