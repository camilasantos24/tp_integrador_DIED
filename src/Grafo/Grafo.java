package Grafo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Entidades.Estacion;
import Entidades.LineaTransporte;

public class Grafo<T> {
	
	private List<Arista<T>> aristas;
	private List<Vertice<T>> vertices;
	
	
	
	public Grafo(){
		this.aristas = new ArrayList<Arista<T>>();
		this.vertices = new ArrayList<Vertice<T>>();
	}
	
	
	

	public List<Arista<T>> getAristas() {
		return aristas;
	}


	public void setAristas(List<Arista<T>> aristas) {
		this.aristas = aristas;
	}


	public List<Vertice<T>> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertice<T>> vertices) {
		this.vertices = vertices;
	}




	public Grafo<T> addNodo(T nodo){
		 this.addNodo(new Vertice<T>(nodo));
		 return this;
	}

	private void addNodo(Vertice<T> nodo){
		this.vertices.add(nodo);
	}
	
	public Grafo<T> conectar(T n1,T n2, int distancia, int duracion, float costo, List<String> lineaTransp){
		this.conectar(getNodo(n1), getNodo(n2), distancia, duracion, costo, lineaTransp);
		return this;
	}
	
	public Grafo<T> conectar_estacion(T n1,T n2, int distancia, int duracion, float costo, List<String> lineaTransp){
		this.conectar(getNodo_estacion(n1), getNodo_estacion(n2), distancia, duracion, costo, lineaTransp);
		return this;
	}
	
	private void conectar(Vertice<T> nodo1,Vertice<T> nodo2,int distancia, int duracion, float costo, List<String> lineaTransp){
		this.aristas.add(new Arista<T>(nodo1,nodo2, distancia, duracion, costo, lineaTransp));
	}
	
	public Vertice<T> getNodo(T valor){
		
		
		if(this.vertices.indexOf(new Vertice<T>(valor)) != -1) {
		Vertice<T> v = this.vertices.get(this.vertices.indexOf(new Vertice<T>(valor)));
		return v;
		}else {
			return null;
		}
		
		
	}
	
	public Vertice<T> getNodo_estacion(T valor){
		
		
		if(get_indice(valor) != -1) {
		Vertice<T> v = this.vertices.get(get_indice(valor));
		return v;
		}else {
			return null;
		}
	}
	
	public int get_indice(T valor) {
		int i=0;
		boolean b = false;
		while(i<this.vertices.size() && b==false) {
			if(((Estacion)valor).equals((Estacion)this.vertices.get(i).getValue())){
				b=true;
			}else {
				i++;
			}
		}
		
		if(b==false) {
			return -1;
		}else {
		return i;
		}
	}
	
	public void printAristas(){
		System.out.println(this.aristas.toString());
	}
	
    public Arista<T> findAristas(T v1, T v2){
    	return this.findAristas(new Vertice<T>(v1), new Vertice<T>(v2));
    }
    
    protected Arista<T> findAristas(Vertice<T> v1, Vertice<T> v2){
    	for(Arista<T> unaArista : this.aristas) {
    		
    		if(unaArista.getOrigin().equals(v1) && unaArista.getEnd().equals(v2)) return unaArista;
    	}
    	return null;
    }
    
    protected Arista<T> findAristas_estacion(Estacion v1, Estacion v2){
    	for(Arista<T> unaArista : this.aristas) {
    		
    		if(((Estacion)unaArista.getOrigin().getValue()).equals(v1) && ((Estacion)unaArista.getEnd().getValue()).equals(v2)) return unaArista;
    	}
    	return null;
    }
    
    
    public List<List<Vertice<T>>> paths(T v1,T v2){
    	return this.paths(new Vertice(v1), new Vertice(v2));
    }
    
    public List<List<Vertice<T>>> paths(Vertice<T> v1,Vertice<T> v2){
    	List<List<Vertice<T>>>salida = new ArrayList<List<Vertice<T>>>();
     	List<Vertice<T>> visitados= new ArrayList<Vertice<T>>();
    	visitados.add(v1);
    	findPathAux(v1, v2, visitados, salida);
    	return salida;
    }

    private void findPathAux(Vertice<T> v1,Vertice<T> v2, List<Vertice<T>> visitados, List<List<Vertice<T>>> todosLosCaminos) {
    	List<Vertice<T>> adyacentes = this.getNeighbourhood(v1);
    	List<Vertice<T>> copiaVisitados = null;
    	
    	for(Vertice<T> ad: adyacentes) {
    		copiaVisitados=visitados.stream().collect(Collectors.toList());
			copiaVisitados.add(ad);
    		if (ad.equals(v2)) {
    			todosLosCaminos.add(new ArrayList<Vertice<T>>(copiaVisitados));
    		}else {
    			if(!visitados.contains(ad)) {
    				this.findPathAux(ad, v2, copiaVisitados, todosLosCaminos);
    			}
    		}
    	}
    }
    
    public List<T> getNeighbourhood(T valor){ 
    	Vertice<T> unNodo = this.getNodo(valor);
		List<T> salida = new ArrayList<T>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getOrigin().equals(unNodo)){
				salida.add(enlace.getEnd().getValue());
			}
		}
		return salida;
	}
	

	private List<Vertice<T>> getNeighbourhood(Vertice<T> unNodo){ 
		List<Vertice<T>> salida = new ArrayList<Vertice<T>>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getOrigin().equals(unNodo)){
				salida.add(enlace.getEnd());
			}
		}
		return salida;
	}


	public boolean validar_conexion_vertices(T nodo_origen, T nodo_destino) {
		
		if(this.findAristas(nodo_origen, nodo_destino) != null) {
			return true;
		}else {return false;}
			
	}
	
	public boolean validar_conexion_estaciones(T nodo_origen, T nodo_destino) {
		
		if(this.findAristas_estacion((Estacion)nodo_origen, (Estacion)nodo_destino) != null) {
			return true;
		}else {return false;}
			
	}
	
	
	
	public List<Estacion> get_page_rank () {
		
		
		List<Estacion> nodos = new ArrayList();
		
		for (int i =0; i<this.getVertices().size(); i++) {
			Estacion n = new Estacion(); 
			n=(Estacion) this.getVertices().get(i).getValue();
			float pr = this.calcular_page_rank(n);
			n.setPage_rank(pr);
			nodos.add(n);
		}
		
		return nodos;
		
	}
	
	public float calcular_page_rank (Estacion n) {
		List<Estacion> nodos_entrantes = new ArrayList();
		
		nodos_entrantes = this.get_nodos_entrantes(n);
		
		float p_r = (float) 0.5;
		float suma =0 ;
		
		if(nodos_entrantes.size()>0) {
		for (int i=0; i<nodos_entrantes.size(); i++) {
			int cant_ady =this.getNeighbourhood((T) nodos_entrantes.get(i)).size();
			
			if(cant_ady>0) {
				suma += nodos_entrantes.get(i).getPage_rank()/cant_ady;
	
			}else {
				suma += nodos_entrantes.get(i).getPage_rank();
			}
		}
		
		p_r = (float) (0.5 + (0.5 * suma));
		}
		return p_r;
		
		
	}
	
	public List<Estacion> get_nodos_entrantes (Estacion n) {
		Vertice<T> v = this.getNodo((T) n);
		List<Estacion> nodos = new ArrayList();
		
		for (int i=0; i<this.getAristas().size(); i++) {
			Arista<T> a = this.getAristas().get(i); 
			if (a.getEnd().equals(v)) {
				Estacion n2 = (Estacion) this.getAristas().get(i).getOrigin().getValue();
				nodos.add(n2);
			}
		}
		
		return nodos;
	}
	
	public void inicializar_page_rank () {
		List<Vertice<T>> vertices = new ArrayList();
		for (int i=0; i<this.vertices.size(); i++) {
			Estacion n = (Estacion) this.getVertices().get(i).getValue();
			n.setPage_rank(1);
			
			Vertice v = new Vertice (n); 
			vertices.add(v);
		}
		
		this.vertices.clear();
		this.setVertices(vertices);
	}
	
	public void setear_page_rank (List<Estacion> nodos) {
		List<Vertice<T>> vertices = new ArrayList();
		for (int i=0; i<this.vertices.size(); i++) {
			
			Vertice v = new Vertice (nodos.get(i)); 
			vertices.add(v);
		}
		
		this.vertices.clear();
		this.setVertices(vertices);
	}


}
