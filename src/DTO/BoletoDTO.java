package DTO;

import java.time.LocalDate;
import java.util.List;

import Grafo.Grafo;
import Grafo.Vertice;

public class BoletoDTO {
	
	private Grafo grafo;
	private List<Vertice> caminos;
	private Vertice inicio;
	private Vertice fin;
	private float costo;
	private float distancia;
	private float duracion;
	private LocalDate fechaVenta;
	
	public Grafo getGrafo() {
		return grafo;
	}
	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}
	public List<Vertice> getCaminos() {
		return caminos;
	}
	public void setCaminos(List<Vertice> caminos) {
		this.caminos = caminos;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public float getDistancia() {
		return distancia;
	}
	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	public float getDuracion() {
		return duracion;
	}
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}
	public LocalDate getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public Vertice getInicio() {
		return inicio;
	}
	public void setInicio(Vertice inicio) {
		this.inicio = inicio;
	}
	public Vertice getFin() {
		return fin;
	}
	public void setFin(Vertice fin) {
		this.fin = fin;
	}
	

}
