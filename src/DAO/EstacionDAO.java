package DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
	
	public List<Estacion> get_estaciones_de_alta() throws Exception{
		try {
			String query = "SELECT * FROM \"tpDied\".\"Estacion\" WHERE alta_baja = 1;";
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
	
	public Estacion get_estacion_origen_by_tramo(int id_tramo) throws Exception{
		try {
			String query = "SELECT e.* FROM \"tpDied\".\"Estacion\" e, \"tpDied\".\"Tramo\" tmo " +
		                    "WHERE tmo.id_tramo = " + id_tramo + 
		                    " AND tmo.id_estacion_origen = e.id_estacion ;";
			ArrayList<Estacion> estaciones = (ArrayList<Estacion>)((Object)Conexion.consultar(query, Estacion.class));
			return estaciones.get(0);
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	public Estacion get_estacion_destino_by_tramo(int id_tramo) throws Exception{
		try {
			String query = "SELECT e.* FROM \"tpDied\".\"Estacion\" e, \"tpDied\".\"Tramo\" tmo " +
		                    "WHERE tmo.id_tramo = " + id_tramo + 
		                    " AND tmo.id_estacion_destino = e.id_estacion ;";
			ArrayList<Estacion> estaciones = (ArrayList<Estacion>)((Object)Conexion.consultar(query, Estacion.class));
			return estaciones.get(0);
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
		//	String query3="SELECT est.* FROM  \"tpDied\".\"Estacion\" est ORDER BY id_estacion;";	ORDENA LA TABLA PERO NO LO PUEDO INCLUIR
		Connection con = Conexion.conectarBD();

		try {
			con.setAutoCommit(false);
			
			con.createStatement().executeUpdate(query);
			
			con.commit();
			
			} 
		
		catch (Exception e) {
			try {
				//deshace todos los cambios realizados en los datos
				con.rollback();
				} catch (SQLException ex1) {
					System.err.println( "No se pudo deshacer" + ex1.getMessage() );    
					}
			} 
		
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void createEstacion(Estacion est) {
		int id= est.getId_estacion();
		String nombre= est.getNombre();
		LocalTime hs_apertura= est.getHs_apertura();
		LocalTime hs_cierre= est.getHs_cierre();
		int estado= est.getEstado();
		int alta_baja=est.getAlta_baja();
		
		String query= null;
		
		query="INSERT INTO \"tpDied\".\"Estacion\" (id_estacion, nombre, hs_apertura, hs_cierre, estado, alta_baja) VALUES ("+id+", '"+nombre+"', '"+hs_apertura+"', '"+hs_cierre+"', "+estado+", "+alta_baja+");";
		
		
		Connection con = Conexion.conectarBD();

		try {
			con.setAutoCommit(false);
			
			con.createStatement().executeUpdate(query);
			
			con.commit();
			
			} 
		catch (Exception e) {
			try {
				//deshace todos los cambios realizados en los datos
				con.rollback();
				} catch (SQLException ex1) {
					System.err.println( "No se pudo deshacer" + ex1.getMessage() );    
					}
			} 
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void createMantenimiento(Mantenimiento mant) {
		int id_est= mant.getEstacion().getId_estacion();
		LocalDate fecha_ini= mant.getFecha_inicio();
		LocalDate fecha_fin= mant.getFecha_fin();
		String obs= mant.getObservacion();
		
		String query= null;
		
		query = "INSERT INTO \"tpDied\".\"Mantenimiento\" (id_estacion, fecha_inicio, fecha_fin, observaciones) VALUES ("+id_est+", '"+fecha_ini+"', "+fecha_fin+", '"+obs+"');";
		
		Connection con = Conexion.conectarBD();

		try {
			con.setAutoCommit(false);
			
			con.createStatement().executeUpdate(query);
			
			con.commit();
			
			} 
		catch (Exception e) {
			try {
				//deshace todos los cambios realizados en los datos
				con.rollback();
				} catch (SQLException ex1) {
					System.err.println( "No se pudo deshacer" + ex1.getMessage() );    
					}
			} 
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void finalizeMantenimiento(String query) throws SQLException {
			Conexion.ejecutar(query);
	}
	
	
}
