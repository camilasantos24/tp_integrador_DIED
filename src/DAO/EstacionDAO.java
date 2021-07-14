package DAO;

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

}
