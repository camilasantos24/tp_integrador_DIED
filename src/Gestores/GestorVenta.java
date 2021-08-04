package Gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import DAO.VentaDAO;
import DTO.BoletoDTO;
import DTO.UsuarioDTO;
import Entidades.Boleto;
import Entidades.Estacion;
import Entidades.Usuario;
import Grafo.Arista;
import Grafo.Grafo;
import Grafo.Vertice;

public class GestorVenta {

	public static float menor_peso (Grafo g, Estacion origen, Estacion destino,  int index) {
			
		List<Object[]> visitar = new ArrayList();
		List<Object[]> pesoMin = new ArrayList();
		
		List<String> visitado = new ArrayList();
		
		//Inicializamos pesoMin
		Object[] e = {origen.getNombre(), 0}; 
		pesoMin.add(0, e);
		
		for (int i=0; i<g.getVertices().size(); i++) {
			if (!g.getVertices().get(i).toString().equals(origen.getNombre())) {
				Object[] e1 = {g.getNodo(g.getVertices().get(i).toString()).getValue(), null};	
				pesoMin.add(e1);
			}
		}
		
		//Inicializamos visitar
		
		Object[] e_visitar = {origen.getNombre(), 0};
		visitar.add(e_visitar);
		
		while (visitar.isEmpty() == false) {
			// 1- Tomamos el primer elemento de visitar
			// 2- Buscamos sus adyacentes 
			// 3- Agregamos ady y (Peso de arista anterior + peso de la arista a visitar) (Si no están en visitados)
			// 4- Actualizar Valor de PesoMin
			// 5- Quitar de tabla visitar
			// 6- Agregar a "Visitados"
			// 7- Vuelve al paso 1
		
		Object[] primer_elemento = visitar.get(0);
		
		List<Vertice> ady = new ArrayList();
		ady = g.getNeighbourhood(primer_elemento[0]);
		
		for (int i =0; i<ady.size(); i++) {
			if (!visitado.contains(ady.get(i))) {
				float peso_arista = 0; 
				
				switch (index) {
				case 1: 					
					peso_arista = g.findAristas(primer_elemento[0], ady.get(i)).getDuracion();
				break;
				case 2:
					peso_arista = g.findAristas(primer_elemento[0], ady.get(i)).getDistancia();
				break;
				case 3: 
					peso_arista = g.findAristas(primer_elemento[0], ady.get(i)).getCosto();
				break;
				}
			
				float peso_arista_fin = peso_arista + Float.parseFloat(visitar.get(0)[1].toString());
				

				

				
				if(existe_en_visitar(visitar, g.getNodo(ady.get(i)).getValue().toString())== false) {
					Object[] nuevo_ady = {ady.get(i), peso_arista_fin};
					visitar.add(nuevo_ady);
				}else {
					int indice1 =buscar_indice(visitar, g.getNodo(ady.get(i)).getValue().toString());
					if((float)visitar.get(indice1)[1] > peso_arista_fin) {
					visitar.get(indice1)[1] = peso_arista_fin;
					}
				}
				
				
				
				}
			}
		//paso 4, 5, 6:
		
		int indice=buscar_indice(pesoMin, primer_elemento[0].toString());
		pesoMin.get(indice)[1] = primer_elemento[1];
		visitado.add(primer_elemento[0].toString());
		visitar.remove(0);
		
		}
		
		for (int i = 0; i < pesoMin.size(); i++) {
			System.out.println("Estacion:" + pesoMin.get(i)[0] + " Peso: " + pesoMin.get(i)[1]  + "\n");
		}
		int indice3= buscar_indice(pesoMin, destino.getNombre());
		return Float.parseFloat(pesoMin.get(indice3)[1].toString());
	} 
	
		
	public static int buscar_indice (List<Object[]> v, Object ady) {
		boolean encontrado = false;
		int i =0;
		int index =0 ;
		while(i<v.size() && encontrado==false) {
			if(v.get(i)[0].equals(ady)) {
				index = i;
				encontrado = true;
			}
			i++;
		}
		return index;
	}
	
	public static boolean existe_en_visitar (List<Object[]> v, String estacion) {
		boolean existe = false; 
		int i =0; 
		
		while (i<v.size() && existe == false) {
			if(v.get(i)[0].equals(estacion)) {
				existe=true;
			}
			i++;
		}
		
		return existe;
	}
	
	
	public static List<Vertice> get_camino_de_menor_peso (Grafo g, int index, Estacion o, Estacion d){
		float peso_min = menor_peso(g, o, d, index);
		List<Vertice> camino_result = new ArrayList();

		List<List<Vertice>> caminos = g.paths(o.getNombre(), d.getNombre());
		boolean encontrado= false;
		int i =0;

		while (i<caminos.size() && encontrado ==false) {
			if(get_peso_camino(g, caminos.get(i), index) == peso_min) {
				encontrado= true;
				camino_result = caminos.get(i);
			}
			i++;
		}
		
		return camino_result;
		
	}
	
	public static float get_peso_camino (Grafo g, List<Vertice> camino, int index) {
		float peso =0;
		switch(index) {
		case 1: 	//MAS RAPIDO
			for(int i=1; i<camino.size(); i++) {
				peso += g.findAristas(camino.get(i-1).toString(), camino.get(i).toString()).getDuracion();
			}
		break;
		case 2:		//MENOR DISTANCIA
			for(int i=1; i<camino.size(); i++) {
				Arista a = g.findAristas(camino.get(i-1).toString(), camino.get(i).toString());
				float dis =a.getDistancia();
				peso += dis ;
			}
		break;
		case 3:		//MAS BARATO
			for(int i=1; i<camino.size(); i++) {
				peso += g.findAristas(camino.get(i-1).toString(), camino.get(i).toString()).getCosto();
			}
		break;
		}

		return peso;
	}
	
	public static void cargar_compra(BoletoDTO bolDTO, UsuarioDTO usuDTO) {
		Usuario usuario= new Usuario();
		Boleto boleto= new Boleto();
		
		List<Estacion> estaciones;
		try {
			estaciones = GestorEstacion.obtenerTodasLasEstaciones();		//Traigo todas las estaciones para no hacer muchas conexiones
			List<Object> listaEstaciones=obtenerEstaciones(estaciones,bolDTO);
			
			Estacion est_destino= (Estacion) listaEstaciones.get(1);
			Estacion est_origen=(Estacion) listaEstaciones.get(0);
			List<Integer> camino=(List<Integer>) listaEstaciones.get(2);
			
			usuario.setCorreo(usuDTO.getCorreo());
			usuario.setNombre(usuDTO.getNombre());
			boleto.setCosto(bolDTO.getCosto());
			boleto.setEstacion_destino(est_destino);
			boleto.setEstacion_origen(est_origen);
			boleto.setFechaVenta(bolDTO.getFechaVenta());
			boleto.setCamino(camino);
			
			VentaDAO.getInstance().createUsuario(usuario);
			VentaDAO.getInstance().createBoleto(boleto,usuario);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}
	
	public static List<Object> obtenerEstaciones(List<Estacion> estaciones, BoletoDTO bolDTO) {
		List listaEstaciones= new ArrayList();
		
		Estacion origen = null;
		Estacion destino = null;
		List<Integer> camino = new ArrayList();
		
		int i=0;
		boolean encontrado1= false;
		boolean encontrado2= false;
		
		while(i<estaciones.size() && (encontrado1==false || encontrado2==false)) {
			
			if(estaciones.get(i).getNombre().equals(bolDTO.getInicio().toString())) {
				origen=estaciones.get(i);
				encontrado1=true;
			}
			if(estaciones.get(i).getNombre().equals(bolDTO.getFin().toString())) {
				destino=estaciones.get(i);
				encontrado2=true;
			}
			i++;
		}
		
		boolean encontrado3=false;
		int j=0;
		int k=0;
		
		while(j<bolDTO.getCaminos().size()) {
			encontrado3=false;
			k=0;
			while(k<estaciones.size() && encontrado3==false) {
				if(bolDTO.getCaminos().get(j).toString().equals(estaciones.get(k).getNombre())) {
					camino.add(estaciones.get(k).getId_estacion());
					encontrado3=true;
				}
				k++;
			}
			j++;
		}
		
		listaEstaciones.add(0, origen);
		listaEstaciones.add(1, destino);
		listaEstaciones.add(2, camino);
		
		return listaEstaciones;
	}
	
	public static int obtenerNumeroBoleto(BoletoDTO bolDTO) throws Exception {
		String query= null;
		List<Estacion> estaciones;
		LocalDate fecha=bolDTO.getFechaVenta();
		float costo= bolDTO.getCosto();
		int id_est_origen;
		int id_est_destino;
		int id_boleto;
		

			estaciones = GestorEstacion.obtenerTodasLasEstaciones();		//Traigo todas las estaciones para no hacer muchas conexiones
			List<Object> listaEstaciones=obtenerEstaciones(estaciones,bolDTO);
			
			Estacion est_destino= (Estacion) listaEstaciones.get(1);
			id_est_destino=est_destino.getId_estacion();
			Estacion est_origen=(Estacion) listaEstaciones.get(0);
			id_est_origen=est_origen.getId_estacion();
			List<Integer> camino=(List<Integer>) listaEstaciones.get(2);
			
			query="SELECT * FROM \"tpDied\".\"Boleto\" WHERE fecha_venta='"+fecha+"' AND costo="+costo+" AND id_estacion_origen="+id_est_origen+" AND id_estacion_destino="+id_est_destino+" AND camino='"+camino.toString()+"';";
			id_boleto=VentaDAO.getInstance().getIDBoleto(query);
			
			return id_boleto;
	}

}
