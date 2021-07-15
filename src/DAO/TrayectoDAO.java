package DAO;

import java.util.ArrayList;
import java.util.List;

import Entidades.Estacion;
import Entidades.Tramo;

public class TrayectoDAO {

private static TrayectoDAO _INSTANCE;
	
	public static TrayectoDAO getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE= new TrayectoDAO();
		}
		return _INSTANCE;
	}
	
//-----------------------------------------------------------------------------------
	
	public List<Estacion> get_estaciones_by_trayecto(long id_trayecto) throws Exception{
		//trae todas las estaciones de un trayecto MENOS la origen
		try {
			String query = "SELECT e.id_estacion, e.nombre, e.hs_apertura, e.hs_cierre, e.estado FROM \"tpDied\". \"Estacion\" e, \"tpDied\".\"Tramo\" tmo " +   
					"WHERE e.id_estacion = ANY(SELECT tmo.id_estacion_destino "+
							  "FROM \"tpDied\".\"Tramo_Trayecto\" tt "+ 
							  "WHERE tmo.id_tramo = tt.id_tramo "+
							  "AND  tt.id_trayecto = " + id_trayecto + " ORDER BY tt.id_tramo ASC) ORDER BY tmo.id_tramo ;" ;
			
			
			ArrayList<Estacion> tramos = (ArrayList<Estacion>)((Object)Conexion.consultar(query, Estacion.class));
			return tramos;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	public List<Tramo> get_tramos_by_trayecto(long id_trayecto) throws Exception{
		//trae todas las estaciones de un trayecto MENOS la origen
		try {
			String query = "SELECT tmo.id_tramo, tmo.id_estacion_origen, tmo.id_estacion_destino, tmo.distancia_km, tmo.duracion, tmo.cantidad_max_pasajeros, tmo.estado, tmo.costo " + 
					"FROM \"tpDied\".\"Tramo\" tmo, \"tpDied\". \"Tramo_Trayecto\" tt "+ 
					"WHERE tt.id_trayecto = " + id_trayecto + 
					" AND tt.id_tramo = tmo.id_tramo ORDER BY tt.id_tramo;";
			
			ArrayList<Tramo> tramos = (ArrayList<Tramo>)((Object)Conexion.consultar(query, Tramo.class));
			return tramos;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	

}
