package Entidades;

import DAO.TrayectoDAO;

public class LineaTransporte {
	
	private int id;
	private String nombre;
	private String color;
	private int estado; // 1-Activa 0-No activa
	private int alta_baja; // 1-alta 0-baja
	private Trayecto trayecto;
	
	public LineaTransporte() {
	
	}
	
	public LineaTransporte(int id, String nombre, String color, int estado, Trayecto trayecto, int alta_baja) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.color = color;
		this.estado = estado;
		this.trayecto = trayecto;
		this.alta_baja = alta_baja;
	}
	
	public LineaTransporte(String obj) {
		String[] atributos= obj.split("\t");
		
		this.id = Integer.parseInt(atributos[0]);
		this.nombre = atributos[1];
		this.color = atributos[2];
		this.estado = Integer.parseInt(atributos[3]);
		
		if(atributos[4].compareTo("null")!=0) {
		Trayecto trayecto= new Trayecto(Integer.parseInt(atributos[4]));
		this.trayecto=trayecto;
		}
		
		this.alta_baja = Integer.parseInt(atributos[5]);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Trayecto getTrayecto() {
		
		try {
			if(this.trayecto!=null) {
				this.trayecto.setId_estacion_destino(TrayectoDAO.getInstance().get_trayecto_by_id(this.trayecto.getId()).getId_estacion_destino());	
				this.trayecto.setId_estacion_origen(TrayectoDAO.getInstance().get_trayecto_by_id(this.trayecto.getId()).getId_estacion_origen());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return trayecto;
	}

	public void setTrayecto(Trayecto trayecto) {
		this.trayecto = trayecto;
	}

	public int getAlta_baja() {
		return alta_baja;
	}

	public void setAlta_baja(int alta_baja) {
		this.alta_baja = alta_baja;
	}
	
	

}
