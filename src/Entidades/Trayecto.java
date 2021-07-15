package Entidades;

import java.util.ArrayList;
import java.util.List;

import DAO.EstacionDAO;
import DAO.TrayectoDAO;

public class Trayecto {
	
	private long id;
	private List<Estacion> estaciones; 
	
	
	private int id_estacion_origen;
	
	//Contructores
	
	public Trayecto(long id) {
		super();
		this.id = id;
		
	}
	
	
	public Trayecto (String obj) {
		String[] atributos= obj.split("\t");
		
		this.id= Long.parseLong(atributos[0]);
		this.id_estacion_origen= Integer.parseInt(atributos[1]);
		/*try {
			this.estaciones = getEstaciones(EstacionDAO.getInstance().get_estacion_by_id(Long.parseLong(atributos[1])));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
	}
	
	//Getters y Setters

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Tramo> getTramos() throws Exception {
		return TrayectoDAO.getInstance().get_tramos_by_trayecto(id);
	}


	public int getId_estacion_origen() {
		return id_estacion_origen;
	}


	public void setId_estacion_origen(int id_estacion_origen) {
		this.id_estacion_origen = id_estacion_origen;
	}


	public List<Estacion> getEstaciones() throws Exception {
		if (this.estaciones == null) {
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
	
	
	
	

}
