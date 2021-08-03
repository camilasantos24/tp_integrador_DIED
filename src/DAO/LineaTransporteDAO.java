package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Estacion;
import Entidades.LineaTransporte;

public class LineaTransporteDAO {
	
	private static LineaTransporteDAO _INSTANCE;
	
	public static LineaTransporteDAO getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE= new LineaTransporteDAO();
		}
		return _INSTANCE;
	}
	
	//-------------------------------------------------------------------------------------------
		// CONSULTAS 
	
	public List<LineaTransporte> get_lineas_by_filtros(String query) throws Exception{
		
		try {
			ArrayList<LineaTransporte> lTransp= (ArrayList<LineaTransporte>)((Object)Conexion.consultar(query, LineaTransporte.class));
				return lTransp;
			}
			catch(Exception ex) {
				throw ex;
			}
	}
	
	public void createLineaTransp(LineaTransporte lTransp) {
		String nombre= lTransp.getNombre();
		String color = lTransp.getColor();
		int estado = lTransp.getEstado();
		int alta_baja = lTransp.getAlta_baja();
		
		String query= null;
		
		query= "INSERT INTO \"tpDied\".\"Linea_Transporte\" (nombre, color, estado, alta_baja) VALUES ('"+nombre+"', '"+color+"', "+estado+", "+alta_baja+");";
		
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
	
	public void updateLineaTransp(String query) {
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
	
	public LineaTransporte get_LineaTransporte_by_nombre_color(String nombre, String color) throws Exception {
		try {
		String query= "SELECT * FROM \"tpDied\".\"Linea_Transporte\" WHERE nombre = '"+nombre+"' AND color= '"+color+"' AND alta_baja=1 ;";
		ArrayList<LineaTransporte> lineaTransporte = (ArrayList<LineaTransporte>)((Object)Conexion.consultar(query, LineaTransporte.class));
		if(lineaTransporte.size() !=0) {
		return lineaTransporte.get(0);
		} else {return null;}
		}
		catch(Exception ex) {
			throw ex;
		}
		}
	
	public List<LineaTransporte> get_linea_by_trayecto(int id) throws Exception{
		try {
		String query= "SELECT * FROM \"tpDied\".\"Linea_Transporte\" WHERE id_trayecto = "+id+" AND alta_baja=1 AND estado=1;";
		ArrayList<LineaTransporte> lineaTransporte = (ArrayList<LineaTransporte>)((Object)Conexion.consultar(query, LineaTransporte.class));
		return lineaTransporte;
		}
		catch(Exception ex) {
			throw ex;
		}
	}

}
