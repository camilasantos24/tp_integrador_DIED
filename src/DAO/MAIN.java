package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Entidades.Estacion;

public class MAIN {

	public MAIN() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		//Conexion c = new Conexion();
		//c.conectarBD(); 
		
		try {
			List<Estacion> es = TrayectoDAO.getInstance().get_estaciones_by_trayecto(1);
			for (int i=0; i<es.size(); i++) {
			System.out.println(es.get(i).getNombre() + ", hs_ap = " + es.get(i).getHs_apertura().getHour() + ":"+  es.get(i).getHs_apertura().getMinute());
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*String f1= "01/11/2020";
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date fecha= formato.parse(f1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

	}

}
