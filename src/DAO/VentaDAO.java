package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entidades.Boleto;
import Entidades.Estacion;
import Entidades.Usuario;

public class VentaDAO {
	
private static VentaDAO _INSTANCE;
	
	public static VentaDAO getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE= new VentaDAO();
		}
		return _INSTANCE;
	}
	
	//-------------------------------------------------------------------------------------------
		// CONSULTAS 
	
	public void createBoleto(Boleto boleto, Usuario usuario) {
		String nombre= usuario.getNombre();
		float costo= boleto.getCosto();
		int estacion_origen= boleto.getEstacion_origen().getId_estacion();
		int estacion_destino= boleto.getEstacion_destino().getId_estacion();
		LocalDate fecha= boleto.getFechaVenta();
		List<Integer> camino= boleto.getCamino();
		
		String query= null;
		
		query= "INSERT INTO \"tpDied\".\"Boleto\" (fecha_venta, costo, id_estacion_origen, id_estacion_destino, id_usuario, camino) VALUES ('"+fecha+"', "+costo+", "+estacion_origen+", "+estacion_destino+", (SELECT usu.id_usuario FROM \"tpDied\".\"Usuario\" usu WHERE usu.nombre='"+nombre+"' ),'"+camino+"');";
		
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
	
	public void createUsuario(Usuario usuario) {
		String nombre= usuario.getNombre();
		String correo= usuario.getCorreo();
		
		String query= null;
		
		query= "INSERT INTO \"tpDied\".\"Usuario\" (nombre, correo) VALUES ('"+nombre+"', '"+correo+"');";
		
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
	
	public int getIDBoleto(String query)throws Exception{
		try {
			ArrayList<Boleto> idBoleto= (ArrayList<Boleto>)((Object)Conexion.consultar(query, Boleto.class));
			if(idBoleto.size() !=0) {
				return idBoleto.get(0).getNro_boleto();
				} else {return -1;}
			}
			catch(Exception ex) {
				throw ex;
			}
	}
	
	public Usuario getUsuarioByID(int id) throws Exception {
		try {
			String query = "SELECT * FROM \"tpDied\".\"Usuario\" WHERE id_usuario = " + id+ " ;";
			ArrayList<Usuario> usuario = (ArrayList<Usuario>)((Object)Conexion.consultar(query, Usuario.class));
			if(usuario.size() !=0) {
			return usuario.get(0);
			} else {return null;}
		}
		catch(Exception ex) {
			throw ex;
		}
	}
}
