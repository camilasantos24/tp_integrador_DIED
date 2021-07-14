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
		
		/*try {
			List<Estacion> es = EstacionDAO.getInstance().get_all_estaciones();
			System.out.println(es.get(0).getNombre() + ", hs_ap = " + es.get(0).getHs_apertura().getHour() + ":"+  es.get(0).getHs_apertura().getMinute());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		String f1= "01/11/2020";
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date fecha= formato.parse(f1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
