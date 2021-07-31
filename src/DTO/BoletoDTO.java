package DTO;

import java.util.List;

import Grafo.Grafo;
import Grafo.Vertice;

public class BoletoDTO {
	
	private Grafo grafo;
	private List<Vertice> caminos;
	private float costo;
	private float distancia;
	private float duracion;
	
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

}
