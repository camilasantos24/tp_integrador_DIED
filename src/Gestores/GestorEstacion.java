package Gestores;

import java.time.LocalTime;
import java.util.List;

import DAO.EstacionDAO;
import DTO.EstacionesDTO;
import Entidades.Estacion;

public class GestorEstacion {
	
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

}
