package Gestores;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import Entidades.Estacion;
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
			if(v.get(i)[0] == ady) {
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
		case 1: 
			for(int i=1; i<camino.size(); i++) {
				peso += g.findAristas(camino.get(i-1), camino.get(i)).getDuracion();
			}
		break;
		case 2:
			for(int i=1; i<camino.size(); i++) {
				System.out.println(camino.get(i-1) + " " + camino.get(i));
				Arista a = g.findAristas(g.getNodo(camino.get(i-1)).getValue().toString(), g.getNodo(camino.get(i)).getValue().toString());
				float dis =a.getDistancia();
				peso += dis ;
				//TODO: ARREGLAR !!
			}
		break;
		case 3:
			for(int i=1; i<camino.size(); i++) {
				peso += g.findAristas(camino.get(i-1), camino.get(i)).getCosto();
			}
		break;
		}
		
		return peso;
	}

}
