package Gestores;

import java.util.List;

import DAO.LineaTransporteDAO;
import DTO.LineaTransporteDTO;
import Entidades.LineaTransporte;
import Entidades.Trayecto;

public class GestorLineaTransporte {
	
	public static List<LineaTransporte> obtenerLineas(LineaTransporteDTO lTransp) throws Exception{
		
		String nombre= lTransp.getNombre();
		String color= lTransp.getColor();
		int estado= lTransp.getEstado();
		
		String query=null;
		
		// Valida solamente el estado
		if(nombre.length()==0 && color.length()==0) {
			query="SELECT lt.* FROM \"tpDied\".\"Linea_Transporte\" lt WHERE lt.estado="+estado+"";
		}
		// Valida estado y nombre
		else if(nombre.length()!=0 && color.length()==0){
			query="SELECT lt.* FROM \"tpDied\".\"Linea_Transporte\" lt WHERE lt.estado="+estado+" AND lt.nombre='"+nombre+"'";
		}
		// Valida estado y color
		else if(nombre.length()==0 && color.length()!=0) {
			query="SELECT lt.* FROM \"tpDied\".\"Linea_Transporte\" lt WHERE lt.estado="+estado+" AND lt.color='"+color+"'";
		}
		// Valida nombre color y estado
		else if(nombre.length()!=0 && color.length()!=0) {
			query="SELECT lt.* FROM \"tpDied\".\"Linea_Transporte\" lt WHERE lt.estado="+estado+" AND lt.nombre='"+nombre+"' AND lt.color='"+color+"'";
		}
		
		// Valida que todas las lineas esten dadas de alta
		query= query+" AND lt.alta_baja=1;";
		
		List<LineaTransporte> lineaTransp = LineaTransporteDAO.getInstance().get_lineas_by_filtros(query);
		
		return lineaTransp;
		
	}
	
	public static void crearLineaTransp(LineaTransporteDTO lTransp) {
		LineaTransporte lineaTransporte = new LineaTransporte();
		
		lineaTransporte.setAlta_baja(lTransp.getAlta_baja());
		lineaTransporte.setColor(lTransp.getColor());
		lineaTransporte.setEstado(lTransp.getEstado());
		lineaTransporte.setNombre(lTransp.getNombre());
		
		LineaTransporteDAO.getInstance().createLineaTransp(lineaTransporte);
	}
	
	public static void actualizarLineaTransp(LineaTransporteDTO lTransp) {
		int id=lTransp.getId();
		String nombre=lTransp.getNombre();
		String color=lTransp.getColor();
		int estado=lTransp.getEstado();
		int alta_baja=lTransp.getAlta_baja();
		
		String query=null;
		
		query="UPDATE \"tpDied\".\"Linea_Transporte\" SET nombre='"+nombre+"', color='"+color+"', estado="+estado+", alta_baja="+alta_baja+" WHERE id_linea="+id+";";
		
		LineaTransporteDAO.getInstance().updateLineaTransp(query);
	}
	
	public static LineaTransporte obtenerLineaPorNombreColor(String nombre, String color)  throws Exception{
		
		try {
			LineaTransporte lineaExistente = LineaTransporteDAO.getInstance().get_LineaTransporte_by_nombre_color(nombre, color);
			return lineaExistente;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public static String obtenerTrayectoTxt(Trayecto trayecto) {
		String nombreTrayecto;
		
		if(trayecto == null) {
			nombreTrayecto="Sin trayectos";
		}else {
			nombreTrayecto=Long.toString(trayecto.getId());
		}
		
		return nombreTrayecto;
	}
	
	public static long obtenerTrayectoLong(String trayecto) {
		long trayectoLong=0;
		
		if(trayecto=="Sin trayectos") {
			trayectoLong=-1;
		}
		if(trayecto.startsWith("Trayecto")) {
			trayectoLong=Long.parseLong(trayecto);
		}
		
		return trayectoLong;
	}
	
	public static String obtenerEstadoTxt(int estado) {
		String estadoTxt;
		
		if(estado == 0) {
			estadoTxt="No activa";
		}else {
			estadoTxt="Activa";
		}
		
		return estadoTxt;
	}
	
	public static int obtenerEstadoInt(String estado) {
		int estadoInt=0;
		
		if(estado=="No activa") {
			estadoInt=0;
		}
		if(estado=="Activa") {
			estadoInt=1;
		}
		
		return estadoInt;
	}
}
