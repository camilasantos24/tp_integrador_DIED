package Gestores;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import DAO.EstacionDAO;
import DTO.EstacionesDTO;
import DTO.MantenimientoDTO;
import Entidades.Estacion;
import Entidades.Mantenimiento;

public class GestorEstacion {
	
	public static List<Estacion> get_estaciones_de_alta() throws Exception{
		return EstacionDAO.getInstance().get_estaciones_de_alta();
	}
	
	public static List<Estacion> obtenerEstaciones (EstacionesDTO obtEst) throws Exception {
		
		int id= obtEst.getId();
		String nombre= obtEst.getNombre();
		LocalTime hs_apertura= obtEst.getHs_apertura();
		LocalTime hs_cierre= obtEst.getHs_cierre();
		int estado= obtEst.getEstado();
		
		String query= null;
		
		// Solo valida el estado
		if ((id==-1) && (nombre.length()==0) && (hs_apertura==null) && (hs_cierre==null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" ";
		}
		// Validamos ID
		else if ((id!=-1) && (nombre.length()==0) && (hs_apertura==null) && (hs_cierre==null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.id_estacion="+id+" ";
		}
		// Validamos nombre
		else if ((id==-1) && (nombre.length()!=0) && (hs_apertura==null) && (hs_cierre==null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.nombre='"+nombre+"' ";
		}
		// Validamos hs_apertura
		else if ((id==-1) && (nombre.length()==0) && (hs_apertura!=null) && (hs_cierre==null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.hs_apertura='"+hs_apertura+"' ";
		}
		// Validamos hs_cierre
		else if ((id==-1) && (nombre.length()==0) && (hs_apertura==null) && (hs_cierre!=null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.hs_cierre='"+hs_cierre+"' ";
		}
		// Validamos ID y nombre
		else if ((id!=-1) && (nombre.length()!=0) && (hs_apertura==null) && (hs_cierre==null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.id_estacion="+id+" AND est.nombre='"+nombre+"' ";
		}
		// Validamos nombre y hs_apertura
		else if ((id==-1) && (nombre.length()!=0) && (hs_apertura!=null) && (hs_cierre==null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.hs_apertura='"+hs_apertura+"' AND est.nombre='"+nombre+"' ";
		}
		// Validamos hs_apertura y hs_cierre
		else if ((id==-1) && (nombre.length()==0) && (hs_apertura!=null) && (hs_cierre!=null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.hs_apertura='"+hs_apertura+"' AND est.hs_cierre='"+hs_cierre+"' ";
		}
		// Validamos id y hs_cierre
		else if ((id!=-1) && (nombre.length()==0) && (hs_apertura==null) && (hs_cierre!=null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.id_estacion="+id+" AND est.hs_cierre='"+hs_cierre+"' ";
		}
		// Validamos id y hs_apertura
		else if ((id!=-1) && (nombre.length()==0) && (hs_apertura!=null) && (hs_cierre==null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.id_estacion="+id+" AND est.hs_apertura='"+hs_apertura+"' ";
		}
		// Validamos id, nombre y hs_apertura
		else if ((id!=-1) && (nombre.length()!=0) && (hs_apertura!=null) && (hs_cierre==null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.id_estacion="+id+" AND est.nombre='"+nombre+"' AND est.hs_apertura='"+hs_apertura+"' ";
		}
		// Validamos nombre, hs_apertura y hs_cierre
		else if ((id==-1) && (nombre.length()!=0) && (hs_apertura!=null) && (hs_cierre!=null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.hs_cierre='"+hs_cierre+"' AND est.nombre='"+nombre+"' AND est.hs_apertura='"+hs_apertura+"' ";
		}
		// Validamos id, hs_apertura y hs_cierre
		else if ((id!=-1) && (nombre.length()==0) && (hs_apertura!=null) && (hs_cierre!=null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.hs_cierre='"+hs_cierre+"' AND est.id_estacion="+id+" AND est.hs_apertura='"+hs_apertura+"' ";
		}
		// Validamos id, nombre y hs_cierre
		else if ((id!=-1) && (nombre.length()!=0) && (hs_apertura==null) && (hs_cierre!=null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.hs_cierre='"+hs_cierre+"' AND est.id_estacion="+id+" AND est.nombre='"+nombre+"' ";
		}
		// Validamos TODOS juntos
		else if ((id!=-1) && (nombre.length()!=0) && (hs_apertura!=null) && (hs_cierre!=null)) {
			query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.hs_cierre='"+hs_cierre+"' AND est.id_estacion="+id+" AND est.nombre='"+nombre+"' AND est.hs_apertura='"+hs_apertura+"' ";
		}
		
		// Valida que todas las estaciones que devuelva esten dadas de alta
		query = query + " AND alta_baja=1 ;";
		
		List<Estacion> estaciones=EstacionDAO.getInstance().get_estacion_by_filtros(query);
		
		return estaciones;
	}
	
	public static void actualizarEstacion(EstacionesDTO estDTO) {
		int id= estDTO.getId();
		String nombre= estDTO.getNombre();
		LocalTime hs_apertura= estDTO.getHs_apertura();
		LocalTime hs_cierre= estDTO.getHs_cierre();
		int estado= estDTO.getEstado();
		int alta_baja=estDTO.getAlta_baja();
		
		String query= null;
		
		query= "UPDATE \"tpDied\".\"Estacion\" SET id_estacion="+id+", nombre='"+nombre+"', hs_apertura='"+hs_apertura+"', hs_cierre='"+hs_cierre+"', estado="+estado+", alta_baja="+alta_baja+" WHERE id_estacion="+id+";";
		
		EstacionDAO.getInstance().updateEstacion(query);
	}
	
	public static Estacion obtenerEstacionPorID(int id) throws Exception{
		try {
			Estacion idExistente= EstacionDAO.getInstance().get_estacion_by_id(id);
			return idExistente;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void crearEstacion(EstacionesDTO estDTO) {
		
		Estacion estacion= new Estacion();
		List<Mantenimiento> mantenimientos = new ArrayList();
		
		estacion.setAlta_baja(estDTO.getAlta_baja());
		estacion.setEstado(estDTO.getEstado());
		estacion.setHs_apertura(estDTO.getHs_apertura());
		estacion.setHs_cierre(estDTO.getHs_cierre());
		estacion.setId_estacion(estDTO.getId());
		estacion.setNombre(estDTO.getNombre());
		estacion.setMantenimientos(mantenimientos);
		
		EstacionDAO.getInstance().createEstacion(estacion);
	}
	
	public static void crearMantenimiento(MantenimientoDTO mantDTO) throws Exception {
		Mantenimiento mantenimiento = new Mantenimiento();
		
		mantenimiento.setFecha_fin(mantDTO.getFecha_fin());
		mantenimiento.setFecha_inicio(mantDTO.getFecha_inicio());
		mantenimiento.setObservacion(mantDTO.getObserv());
		mantenimiento.setEstacion(obtenerEstacionPorID(mantDTO.getId_estacion()));
		
		EstacionDAO.getInstance().createMantenimiento(mantenimiento);
	}
	
	public static void finalizarMantenimiento(MantenimientoDTO mantDTO) throws SQLException {
		String query= null;
		
		query= "UPDATE \"tpDied\".\"Mantenimiento\" SET fecha_fin='"+mantDTO.getFecha_fin()+"', observaciones='"+mantDTO.getObserv()+"' WHERE id_estacion="+mantDTO.getId_estacion()+" AND fecha_fin is null;";
		EstacionDAO.getInstance().finalizeMantenimiento(query);
	}
	
	public static String obtenerEstadoTxt(int estado) {
		String estadoTxt;
		
		if(estado==0) {
			estadoTxt="En mantenimiento";
		}else {
			estadoTxt="Operativa";
		}
		
		return estadoTxt;
	}
	
	public static int obtenerEstadoInt(String estado) {
		int estadoInt=0;
		
		if(estado=="En mantenimiento") {
			estadoInt=0;
		}
		if(estado=="Operativa") {
			estadoInt=1;
		}
		
		return estadoInt;
	}
	
	public static List<Estacion> obtenerEstacionesBoleto() throws Exception{
		LocalTime hs_actual=LocalTime.now();
		int estado= 1;
		int alta_baja=1;
		
		String query= null;

		query= "SELECT est.* FROM \"tpDied\".\"Estacion\" est WHERE est.estado="+estado+" AND est.alta_baja="+alta_baja+" AND est.hs_apertura<'"+hs_actual+"' AND est.hs_cierre>'"+hs_actual+"' ;";
				
		List<Estacion> estaciones=EstacionDAO.getInstance().get_estacion_by_filtros(query);
				
		return estaciones;
	}

}