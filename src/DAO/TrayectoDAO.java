package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.TramoDTO;
import Entidades.Estacion;
import Entidades.Tramo;
import Entidades.Trayecto;

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
			String query = "SELECT e.* FROM \"tpDied\". \"Estacion\" e, \"tpDied\".\"Tramo\" tmo " +   
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
	
	public Trayecto get_trayecto_by_id(int id) throws Exception{
		try {
		String query="SELECT * FROM \"tpDied\".\"Trayecto\" WHERE id_trayecto="+id+";";
		ArrayList<Trayecto> trayecto = (ArrayList<Trayecto>)((Object)Conexion.consultar(query, Trayecto.class));
		if(trayecto.size() !=0) {
			return trayecto.get(0);
			} else {return null;}
		}
		catch(Exception ex) {
			throw ex;
		}
		
	}
	
	public List<Trayecto> get_all_trayectos() throws Exception{
		try {
		String query="SELECT * FROM \"tpDied\".\"Trayecto\";";
		ArrayList<Trayecto> trayecto = (ArrayList<Trayecto>)((Object)Conexion.consultar(query, Trayecto.class));
		return trayecto;
			
		}
		catch(Exception ex) {
			throw ex;
		}
		
	}
	
	
	public List<Tramo> get_all_tramos() throws Exception{
		try {
		String query="SELECT * FROM \"tpDied\".\"Tramo\" ;";
		ArrayList<Tramo> tramos = (ArrayList<Tramo>)((Object)Conexion.consultar(query, Tramo.class));
			return tramos;
			
		}
		catch(Exception ex) {
			throw ex;
		}
		
	}
	
	public List<Tramo> get_tramos_by_origen(int id_o) throws Exception{
		try {
		String query="SELECT * FROM \"tpDied\".\"Tramo\" WHERE id_estacion_origen = " + id_o + " ;";
		ArrayList<Tramo> tramos = (ArrayList<Tramo>)((Object)Conexion.consultar(query, Tramo.class));
			return tramos;
			
		}
		catch(Exception ex) {
			throw ex;
		}
		
	}
	
	
	public boolean alta_trayecto (List<TramoDTO> tramos, int id_linea) throws SQLException {
		PreparedStatement query1=null ;
		PreparedStatement query2=null ;
		PreparedStatement query3=null ;
		PreparedStatement query4=null ;

		
		Connection con= Conexion.conectarBD();
		try {
			con.setAutoCommit(false);
			
			int id_origen = tramos.get(0).getCod_origen();
			int id_destino = tramos.get(tramos.size()-1).getCod_destino();
			
			query1 = con.prepareStatement("INSERT INTO \"tpDied\".\"Trayecto\" (id_estacion_origen, id_estacion_destino) VALUES (" + id_origen + ", " + id_destino + ");", Statement.RETURN_GENERATED_KEYS);
			query1.execute();
			
			Long trayectoID=null;
			ResultSet rs=null;
			rs= query1.getGeneratedKeys();
			if(rs.next()) {
				trayectoID=rs.getLong(1);
			}
			
			for (int i=0; i<tramos.size(); i++) {
				TramoDTO t = tramos.get(i);
				query2= con.prepareStatement("INSERT INTO \"tpDied\".\"Tramo\" (id_estacion_origen, id_estacion_destino, distancia_km, duracion, cantidad_max_pasajeros, estado, costo) VALUES (" + t.getCod_origen() + ", " + t.getCod_destino() + ", " + t.getDistancia() + ", " + t.getDuracion() + ", " + t.getCant_pas() + ", 1," + t.getCosto() + " );", Statement.RETURN_GENERATED_KEYS);
				query2.execute();
				
				Long tramoID=null;
				ResultSet rs2=null;
				rs2= query2.getGeneratedKeys();
				if(rs2.next()) {
					tramoID=rs2.getLong(1);
				}
				
				query3 = con.prepareStatement("INSERT INTO \"tpDied\".\"Tramo_Trayecto\" (id_tramo, id_trayecto) VALUES (" + tramoID + ", " + trayectoID + " );", Statement.RETURN_GENERATED_KEYS);
				query3.execute();
			}
			 // agrego trayecto a la linea 
			query4 = con.prepareStatement("UPDATE \"tpDied\".\"Linea_Transporte\" SET id_trayecto= " + trayectoID + " WHERE id_linea="+id_linea+";", Statement.RETURN_GENERATED_KEYS);
			query4.execute();
			
			con.commit();
			return true;	
			
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			return false;
		}

	}
	
	/*public List<Tramo> get_tramos_by_origen_destino(int id_o, int id_d) throws Exception{
		try {
			String query=	"SELECT tram.*" + 
							"FROM \"tpDied\".\"Trayecto\" tray" + 
							"	JOIN \"tpDied\".\"Tramo_Trayecto\" trtr ON (tray.id_trayecto=trtr.id_trayecto)" + 
							"	JOIN \"tpDied\".\"Tramo\" tram ON (trtr.id_tramo=tram.id_tramo)" + 
							"WHERE tray.id_estacion_origen="+id_o+" AND tray.id_estacion_destino="+id_d+";";
			ArrayList<Tramo> tramos = (ArrayList<Tramo>)((Object)Conexion.consultar(query, Tramo.class));
				return tramos;
				
			}
			catch(Exception ex) {
				throw ex;
			}
	}*/
	
	public List<Trayecto> get_trayectos_by_origen_destino(int id_o, int id_d) throws Exception {
		try {
			String query=	"SELECT * FROM \"tpDied\".\"Trayecto\" WHERE id_estacion_origen="+id_o+" AND id_estacion_destino="+id_d+";";
			ArrayList<Trayecto> trayectos = (ArrayList<Trayecto>)((Object)Conexion.consultar(query, Trayecto.class));
			return trayectos;
			}
			catch(Exception ex) {
				throw ex;
			}
	}
	

}
