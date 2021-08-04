package DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Entidades.ConsultaGenerica;
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
				e.printStackTrace();
			}
		}
	}
	
	public void finalizeMantenimiento(String query) throws SQLException {
			Conexion.ejecutar(query);
	}
	
	public List<Object[]> get_page_rank () throws Exception{
		String query = "SELECT e.* , COUNT(*) FROM \"tpDied\".\"Estacion\" e, \"tpDied\".\"Tramo\" tr WHERE tr.id_estacion_destino = e.id_estacion AND e.alta_baja = 1 GROUP BY e.id_estacion;";
		List<ConsultaGenerica> ls1;
		
		ls1 = (List<ConsultaGenerica>)(Object)Conexion.consultar(query, ConsultaGenerica.class);
		
		List<Object[]> resultado = new ArrayList();
		for (int i =0; i<ls1.size(); i++) {
			Estacion e = new Estacion ();
			e.setId_estacion(Integer.parseInt(ls1.get(i).getValor("id_estacion")));
			e.setNombre(ls1.get(i).getValor("nombre"));
			e.setHs_apertura(LocalTime.parse(ls1.get(i).getValor("hs_apertura")));
			e.setHs_cierre(LocalTime.parse(ls1.get(i).getValor("hs_cierre")));
			e.setEstado(Integer.parseInt(ls1.get(i).getValor("estado")));
			e.setAlta_baja(Integer.parseInt(ls1.get(i).getValor("alta_baja")));
			
			int count = Integer.parseInt(ls1.get(i).getValor("count"));
			
			Object[] r = {e, count};
			resultado.add(r);
		}
		return resultado;
		
	}
	
	public List<Object[]> get_estaciones_con_mantenimiento () throws Exception{
		String query = "SELECT DISTINCT e.*, mnt.fecha_inicio " + 
				"FROM \"tpDied\".\"Estacion\" e, \"tpDied\".\"Mantenimiento\" mnt " + 
				"WHERE mnt.id_estacion = e.id_estacion " + 
				"ORDER BY mnt.fecha_inicio ASC ;";
		
		List<ConsultaGenerica> ls1 = (List<ConsultaGenerica>)(Object)Conexion.consultar(query, ConsultaGenerica.class);

		List<Object[]> resultado = new ArrayList();
		LocalDate fecha_mant;
		for (int i=0; i<ls1.size(); i++) {
			Estacion e = new Estacion();
			e.setId_estacion(Integer.parseInt(ls1.get(i).getValor("id_estacion")));
			e.setNombre(ls1.get(i).getValor("nombre"));
			e.setHs_apertura(LocalTime.parse(ls1.get(i).getValor("hs_apertura")));
			e.setHs_cierre(LocalTime.parse(ls1.get(i).getValor("hs_cierre")));
			e.setEstado(Integer.parseInt(ls1.get(i).getValor("estado")));
			e.setAlta_baja(Integer.parseInt(ls1.get(i).getValor("alta_baja")));
			
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			fecha_mant= LocalDate.parse(ls1.get(i).getValor("fecha_inicio"), formato);
			
			Object[] r = {e, fecha_mant};
			resultado.add(r);
		}

		
		return resultado;
	}
	
	public List<Object[]> get_estaciones_sin_mantenimiento () throws Exception{
		String query = "SELECT e.* " + 
				"FROM \"tpDied\".\"Estacion\" e, \"tpDied\".\"Mantenimiento\" mnt " + 
				"WHERE e.id_estacion NOT IN ( SELECT mt2.id_estacion FROM \"tpDied\".\"Mantenimiento\" mt2)" + 
				"GROUP BY e.id_estacion;";
		ArrayList<Estacion> estaciones= (ArrayList<Estacion>)((Object)Conexion.consultar(query, Estacion.class));

		List<Object[]> result = new ArrayList();
		for(int i=0; i<estaciones.size(); i++) {
			Object[] r = {estaciones.get(i), null};
			result.add(r);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
}
