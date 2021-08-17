package Gestores;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import DAO.EstacionDAO;
import DTO.EstacionesDTO;
import DTO.MantenimientoDTO;
import Entidades.Estacion;
import Entidades.LineaTransporte;
import Entidades.Mantenimiento;
import Entidades.Tramo;
import Entidades.Trayecto;
import Grafo.Arista;
import Grafo.Grafo;
import Grafo.Vertice;

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
	
	public static List<Estacion> obtenerTodasLasEstaciones() throws Exception{
		String query= null;
		query= "SELECT * FROM \"tpDied\".\"Estacion\" WHERE alta_baja=1;";
		
		List<Estacion> estaciones=EstacionDAO.getInstance().get_estacion_by_filtros(query);
		
		return estaciones;
	}

	


	public static List<Object[]> get_page_rank() throws Exception{
		List<Object[]> pg = EstacionDAO.getInstance().get_page_rank();
		
		Collections.sort(pg, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] p1, Object[] p2) {
				// Comparamos p2 con p1 y no al reves como antes
				return ((Integer)p2[1]).compareTo((Integer)p1[1]);
			}
		});
		
		return pg;
	}
	
	public static List<Object[]> get_lista_proximo_mantenimiento () throws Exception{
		List<Object[]> estaciones_mant = EstacionDAO.getInstance().get_estaciones_sin_mantenimiento();
		estaciones_mant.addAll(EstacionDAO.getInstance().get_estaciones_con_mantenimiento());
		return estaciones_mant;
	}
	
	
	public static Grafo armarGrafo() throws Exception {
		List<Trayecto>trayectos=GestorTrayecto.get_all_trayectos();	
		Grafo grafo=null;

		try {	
			for (int i = 0; i < trayectos.size(); i++) {	// Por cada trayecto buscamos sus tramos, estaciones activas y lineas tambien activas.
				
				List<Tramo>listaTramos=trayectos.get(i).getTramos();
				List<Estacion>listaEstaciones=trayectos.get(i).getEstaciones();
				List<LineaTransporte>listaLineas=GestorLineaTransporte.obtenerLineasPorTrayecto(trayectos.get(i).getId());
				List<String>listaLineas_nombres= obtenerNombres(listaLineas);
				
				if (grafo==null) {					// Si no existe grafo lo crea. Si existe compara si los nodos existen, si no existen tampoco los agrega y conecta.
				grafo=generarGrafo(listaTramos, listaEstaciones, listaLineas_nombres);
				
				}else {
					for(int j=0; j<listaTramos.size(); j++) {
						
						if(!existeNodo(grafo, listaTramos.get(j).getEstacion_origen())) {	
							grafo.addNodo(listaTramos.get(j).getEstacion_origen());
						}
						if(!existeNodo(grafo, listaTramos.get(j).getEstacion_destino())) {
							grafo.addNodo(listaTramos.get(j).getEstacion_destino());
						}
						
						if(!grafo.validar_conexion_estaciones(listaTramos.get(j).getEstacion_origen(), listaTramos.get(j).getEstacion_destino())) {
						grafo.conectar_estacion(listaTramos.get(j).getEstacion_origen(), listaTramos.get(j).getEstacion_destino(), listaTramos.get(j).getDistancia_km(), listaTramos.get(j).getDuracion(), listaTramos.get(j).getCosto(), listaLineas_nombres);
						}
						
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
			return grafo;
		}	
	
	public static boolean existeNodo(Grafo grafo, Estacion e) {
		if(grafo.getNodo_estacion(e) != null) {
		if((((Estacion)grafo.getNodo_estacion(e).getValue())).equals(e)) {
			return true;
			}else {
				return false;
			}
		}else {return false;}
	}
	
	public static Grafo generarGrafo(List<Tramo> tramos, List<Estacion> estaciones, List<String> lineas) {
		List<Tramo> tramos2=tramos;
		Grafo<Estacion> grafo1 = new Grafo<Estacion>();
		
		for (int i = 0; i < estaciones.size(); i++) {
			grafo1.addNodo(estaciones.get(i));
		}
		
		for (int i = 0; i < tramos2.size(); i++) {
			grafo1.conectar_estacion(tramos2.get(i).getEstacion_origen(), tramos2.get(i).getEstacion_destino(),tramos2.get(i).getDistancia_km(), tramos2.get(i).getDuracion(), tramos2.get(i).getCosto(), lineas);
		}
		
		return grafo1;
	}
	
	public static List<String> obtenerNombres(List<LineaTransporte> lineas){
		List<String> nombresLineas= new ArrayList();;
		
		for (int i=0; i<lineas.size(); i++) {
			nombresLineas.add(lineas.get(i).getNombre());
		}
		
		return nombresLineas;
	}
	
	public static List<Estacion> calcular_page_rank_error(Grafo g, float error) {
		g.inicializar_page_rank();
		boolean diferencia_error;
		int iteracion = 1;
		HashMap< String, Float> hm = new HashMap<String, Float>();
		List<Estacion> nodos_pr= new ArrayList();
		do {
			diferencia_error = false;
			for (int j = 0; j < g.getVertices().size(); j++) {
				Estacion n = (Estacion) ((Vertice) g.getVertices().get(j)).getValue();
				hm.put(n.getNombre(), n.getPage_rank());
			}
			
			 nodos_pr = g.get_page_rank();
			// Setear pagerank a nodos

			for (int i = 0; i < nodos_pr.size(); i++) {
				float pr_it_anterior= hm.get(nodos_pr.get(i).getNombre());
				
				
					if ((nodos_pr.get(i).getPage_rank() - pr_it_anterior) >= error) {
						diferencia_error = true;
						
					}
				
				
				}
			
			System.out.println("ITERACION: "+ iteracion);
			for (int i = 0; i < nodos_pr.size(); i++) {
				System.out.println(nodos_pr.get(i).getNombre() + " : " + nodos_pr.get(i).getPage_rank());
			}
			g.setear_page_rank(nodos_pr);
			iteracion ++;

		} while (diferencia_error);
		
		return nodos_pr;
	}
	
	

}