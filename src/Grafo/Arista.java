package Grafo;

import java.util.ArrayList;
import java.util.List;

import Entidades.LineaTransporte;

public class Arista<T> {
	
	private Vertice<T> origin;
	private Vertice<T> end;
	private int distancia;
	private int duracion;
	private float costo;
	private List<String> lineaTransp = new ArrayList();
	
	public Arista() {
		
	}
	
	public Arista(Vertice<T> origin, Vertice<T> end, int distancia, int duracion, float costo, List<String> lineaTransp) {
		super();
		this.origin = origin;
		this.end = end;
		this.distancia = distancia;
		this.duracion = duracion;
		this.costo = costo;
		for(int i=0; i<lineaTransp.size(); i++) {
			this.lineaTransp.add(lineaTransp.get(i));
		}
	}

	public Vertice<T> getOrigin() {
		return origin;
	}

	public void setOrigin(Vertice<T> origin) {
		this.origin = origin;
	}

	public Vertice<T> getEnd() {
		return end;
	}

	public void setEnd(Vertice<T> end) {
		this.end = end;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public List<String> getLineaTransp() {
		return lineaTransp;
	}

	public void setLineaTransp(List<String> lineaTransp) {
		this.lineaTransp = lineaTransp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(costo);
		result = prime * result + distancia;
		result = prime * result + duracion;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((lineaTransp == null) ? 0 : lineaTransp.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arista other = (Arista) obj;
		if (Float.floatToIntBits(costo) != Float.floatToIntBits(other.costo))
			return false;
		if (distancia != other.distancia)
			return false;
		if (duracion != other.duracion)
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (lineaTransp == null) {
			if (other.lineaTransp != null)
				return false;
		} else if (!lineaTransp.equals(other.lineaTransp))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		return true;
	}

	

		
}
