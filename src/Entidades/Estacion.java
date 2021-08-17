package Entidades;

import java.time.LocalTime;
import java.util.List;

import DAO.EstacionDAO;

public class Estacion {

	private int id_estacion;
	private String nombre;
	private LocalTime hs_apertura;
	private LocalTime hs_cierre;
	private int estado; //1- Activa ; 0-Mantenimiento
	private int alta_baja; // 1-Dada de alta ; 0-Dada de baja
	private List<Mantenimiento> mantenimientos;
	private float page_rank;
	public Estacion() {
	}
	
	

	public Estacion(int id_estacion, String nombre, LocalTime hs_apertura, LocalTime hs_cierre, int estado, int alta_baja) {
		super();
		this.id_estacion = id_estacion;
		this.nombre = nombre;
		this.hs_apertura = hs_apertura;
		this.hs_cierre = hs_cierre;
		this.estado = estado;
		this.alta_baja=alta_baja;
	}


	public Estacion(String obj) {
		String[] atributos= obj.split("\t");
		
		this.id_estacion= Integer.parseInt(atributos[0]);
		this.nombre= atributos[1];
		this.hs_apertura= LocalTime.parse(atributos[2]);
		this.hs_cierre=LocalTime.parse(atributos[3]);
		this.estado=Integer.parseInt(atributos[4]);
		this.alta_baja=Integer.parseInt(atributos[5]);
	}
	
	//getters and setters 

	public int getId_estacion() {
		return id_estacion;
	}

	public float getPage_rank() {
		return page_rank;
	}



	public void setPage_rank(float page_rank) {
		this.page_rank = page_rank;
	}



	public void setId_estacion(int id_estacion) {
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



	public List<Mantenimiento> getMantenimientos() throws Exception {
		
	if(this.mantenimientos == null) {
		return EstacionDAO.getInstance().get_mantenimientos_by_estacion(this.id_estacion);
	}else {
		return this.mantenimientos;
	}
		
	}



	public void setMantenimientos(List<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}
	
	public boolean equals(Estacion e) {
		if (this.getNombre().equals(e.getNombre()) && this.id_estacion == e.getId_estacion() && this.getEstado() == e.getEstado()) {
			return true;
		}else {return false;}
	}
	
	
	
	
}
