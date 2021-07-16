package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Entidades.Estacion;
import Entidades.Mantenimiento;

public class EstacionDAO {

	private static EstacionDAO _INSTANCE;
	
	public static EstacionDAO getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE= new EstacionDAO();
		}
		return _INSTANCE;
	}
	
//-------------------------------------------------------------------------------------------
	// CONSULTAS 
	
	public List<Estacion> get_all_estaciones() throws Exception{
		try {
			String query = "SELECT * FROM \"tpDied\".\"Estacion\" ;";
			ArrayList<Estacion> estaciones = (ArrayList<Estacion>)((Object)Conexion.consultar(query, Estacion.class));
			return estaciones;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	public Estacion get_estacion_by_id(long id_estacion) throws Exception{
		try {
			String query = "SELECT * FROM \"tpDied\".\"Estacion\" WHERE id_estacion = " + id_estacion + " ;";
			ArrayList<Estacion> estaciones = (ArrayList<Estacion>)((Object)Conexion.consultar(query, Estacion.class));
			if(estaciones.size() !=0) {
			return estaciones.get(0);
			} else {return null;}
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	public List<Mantenimiento> get_mantenimientos_by_estacion(long id_estacion) throws Exception{
		try {
			String query = "SELECT * FROM \"tpDied\".\"Mantenimiento\" WHERE id_estacion = " + id_estacion + " ;" ;
			ArrayList<Mantenimiento> mantenimientos = (ArrayList<Mantenimiento>)((Object)Conexion.consultar(query, Mantenimiento.class));
			return mantenimientos;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	public List<Estacion> get_estacion_origen_destino_by_tramo(int id_tramo) throws Exception{
		try {
			String query = "SELECT e.id_estacion, e.nombre, e.hs_apertura, e.hs_cierre, e.estado FROM \"tpDied\".\"Estacion\" e, \"tpDied\".\"Tramo\" tmo " +
		                    "WHERE tmo.id_tramo = " + id_tramo + 
		                    " AND tmo.id_estacion_origen = e.id_estacion " +
		                    "AND tmo.id_estacion_destino = e.id_estacion ;";
			ArrayList<Estacion> estaciones = (ArrayList<Estacion>)((Object)Conexion.consultar(query, Estacion.class));
			return estaciones;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	public List<Estacion> get_estacion_by_filtros(String query) throws Exception{
		try {
		ArrayList<Estacion> estaciones= (ArrayList<Estacion>)((Object)Conexion.consultar(query, Estacion.class));
			return estaciones;
		}
		catch(Exception ex) {
			throw ex;
		}
	}

	public void updateEstacion(String query) {
		String query2= query;
	//	String query3="SELECT est.* FROM  \"tpDied\".\"Estacion\" est ORDER BY id_estacion;";
		try {
			Conexion.ejecutar(query2);
			/*Conexion.ejecutar(query3);		NO PUEDO REORDENAR LA TABLA */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
