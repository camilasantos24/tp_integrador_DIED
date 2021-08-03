package Entidades;

import java.util.ArrayList;
import java.util.List;

import DAO.EstacionDAO;
import DAO.TrayectoDAO;

public class Trayecto {
	
	private int id;
	private List<Estacion> estaciones=null; 
	private int id_estacion_origen;
	private int id_estacion_destino;
	
	private List<Tramo> tramos; 
	
	//Contructores
	
	public Trayecto(int id) {
		super();
		this.id = id;
		
	}
	
	
	public Trayecto (String obj) {
		String[] atributos= obj.split("\t");
		
		this.id= Integer.parseInt(atributos[0]);
		this.id_estacion_origen= Integer.parseInt(atributos[1]);
		this.id_estacion_destino= Integer.parseInt(atributos[2]);
	}
	
	//Getters y Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Tramo> getTramos() throws Exception {
		if(this.tramos != null) {
			return this.tramos;
		}else {
			this.tramos=TrayectoDAO.getInstance().get_tramos_by_trayecto(id);
			return this.tramos;
		}
		 
	}


	public int getId_estacion_origen() {
		return id_estacion_origen;
	}


	public void setId_estacion_origen(int id_estacion_origen) {
		this.id_estacion_origen = id_estacion_origen;
	}


	public List<Estacion> getEstaciones() throws Exception {
		if (this.estaciones == null) {
			
				this.estaciones= new ArrayList<Estacion>();
			
				this.estaciones.add(EstacionDAO.getInstance().get_estacion_by_id(id_estacion_origen));
				this.estaciones.addAll(TrayectoDAO.getInstance().get_estaciones_by_trayecto(id));
				
				return estaciones;
			
		}else {
			
		return estaciones;
		}
	}

	public void setEstaciones(List<Estacion> estaciones) {
		this.estaciones = estaciones;
	}


	public int getId_estacion_destino() {
		return id_estacion_destino;
	}


	public void setId_estacion_destino(int id_estacion_destino) {
		this.id_estacion_destino = id_estacion_destino;
	}
	
	
	public int get_flujo_max() throws Exception {
		List<Tramo> tramos = this.getTramos();
		int min = tramos.get(0).getCant_max_pasajeros();
		for (int j=0; j<tramos.size(); j++) {
			if(tramos.get(j).getCant_max_pasajeros()<min) {
				min =tramos.get(j).getCant_max_pasajeros();
			}
		}
		
		return min;
	}
	
	public float get_costo () throws Exception {
		float costo =0;
		
		List<Tramo> tramos = this.getTramos();
		
		for(int i=0; i<tramos.size(); i++) {
			costo += tramos.get(i).getCosto();
		}
		
		return costo;
	}
	
	public int get_distancia () throws Exception {
		int dis =0;
		
		List<Tramo> tramos = this.getTramos();
		
		for(int i=0; i<tramos.size(); i++) {
			dis += tramos.get(i).getDistancia_km();
		}
		
		return dis;
	}
	
	public int get_duracion () throws Exception {
		int dur =0;
		
		List<Tramo> tramos = this.getTramos();
		
		for(int i=0; i<tramos.size(); i++) {
			dur += tramos.get(i).getDuracion();
		}
		
		return dur;
	}
	

}
