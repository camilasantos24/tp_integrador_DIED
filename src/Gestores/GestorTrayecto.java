package Gestores;

import java.sql.SQLException;
import java.util.List;

import DAO.TrayectoDAO;
import DTO.TramoDTO;
import Entidades.Estacion;
import Entidades.Tramo;
import Entidades.Trayecto;

public class GestorTrayecto {

	public static boolean validar_tramo(TramoDTO t) throws Exception {
		boolean valido =true;
		
		Estacion o= GestorEstacion.obtenerEstacionPorID(t.getCod_origen());
		Estacion d= GestorEstacion.obtenerEstacionPorID(t.getCod_destino());
		
		if(o.getAlta_baja() == 0 || d.getAlta_baja() ==0 || o==null || d==null ) {
			valido = false;
		}
		
		return valido;
	}
	
	public static  List<Trayecto> get_all_trayectos() throws Exception{
		return TrayectoDAO.getInstance().get_all_trayectos();
	}

	
	public static boolean alta_trayecto (List<TramoDTO> tramos, int id_linea) throws SQLException {
		return TrayectoDAO.getInstance().alta_trayecto(tramos, id_linea);
	}
	
	public static List<Tramo> get_all_tramos() throws Exception{
		return TrayectoDAO.getInstance().get_all_tramos();
	}
	
	public static List<Tramo> get_tramos_by_origen(int id_o) throws Exception{
		return TrayectoDAO.getInstance().get_tramos_by_origen(id_o);
	}
	
	public static List<Trayecto> obtener_trayectos_origen_destino(int id_o, int id_d) throws Exception{
		return TrayectoDAO.getInstance().get_trayectos_by_origen_destino(id_o, id_d);
	}
	
	public static Trayecto get_trayecto_by_id(int id) throws Exception{
		return TrayectoDAO.getInstance().get_trayecto_by_id(id);
	}




}
