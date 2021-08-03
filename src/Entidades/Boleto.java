package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.EstacionDAO;
import DAO.VentaDAO;

public class Boleto {
	
	private int nro_boleto;
	private LocalDate fechaVenta;
	private float costo;
	private Estacion estacion_origen;
	private Estacion estacion_destino;
	private Usuario usuario;
	private List<Integer> camino;
	
	public Boleto() {
		
	}
	
	public Boleto(int nro_boleto, LocalDate fechaVenta, float costo, Estacion estacion_origen, Estacion estacion_destino, Usuario usuario, List<Integer> camino) {
		super();
		this.nro_boleto = nro_boleto;
		this.fechaVenta = fechaVenta;
		this.costo = costo;
		this.estacion_origen = estacion_origen;
		this.estacion_destino = estacion_destino;
		this.usuario = usuario;
		this.camino=camino;
	}
	
	public Boleto(String obj) throws NumberFormatException, Exception {
		String[] atributos= obj.split("\t");
		
		String[] estaciones= atributos[6].split(",|\\[|\\]|\\s+");
		
		List<Integer> cam= new ArrayList();
		
		this.nro_boleto=Integer.parseInt(atributos[0]);
		this.fechaVenta=LocalDate.parse(atributos[1]);
		this.costo=Float.parseFloat(atributos[2]);
		this.estacion_origen=EstacionDAO.getInstance().get_estacion_by_id(Integer.parseInt(atributos[3]));
		this.estacion_destino=EstacionDAO.getInstance().get_estacion_by_id(Integer.parseInt(atributos[4]));
		this.usuario=VentaDAO.getInstance().getUsuarioByID(Integer.parseInt(atributos[5]));
		
		for (int i = 1; i < estaciones.length; i=i+2) {
			cam.add(Integer.parseInt(estaciones[i]));
		}

		this.camino=cam;
		
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

	public List<Integer> getCamino() {
		return camino;
	}

	public void setCamino(List<Integer> camino) {
		this.camino = camino;
	}
	
	

}
