package Grafo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public Grafo<T> conectar(T n1,T n2){
		this.conectar(getNodo(n1), getNodo(n2), -1, -1, -1);
		return this;
	}

	public Grafo<T> conectar(T n1,T n2, int distancia, int duracion, float costo){
		this.conectar(getNodo(n1), getNodo(n2), distancia, duracion, costo);
		return this;
	}

	private void conectar(Vertice<T> nodo1,Vertice<T> nodo2,int distancia, int duracion, float costo){
		this.aristas.add(new Arista<T>(nodo1,nodo2, distancia, duracion, costo));
	}
	
	public Vertice<T> getNodo(T valor){
		return this.vertices.get(this.vertices.indexOf(new Vertice<T>(valor)));
	}
	
	public void printAristas(){
		System.out.println(this.aristas.toString());
	}
	
    protected Arista<T> findAristas(T v1, T v2){
    	return this.findAristas(new Vertice<T>(v1), new Vertice<T>(v2));
    }
    
    protected Arista<T> findAristas(Vertice<T> v1, Vertice<T> v2){
    	for(Arista<T> unaArista : this.aristas) {
    		
    		if(unaArista.getOrigin().equals(v1) && unaArista.getEnd().equals(v2)) return unaArista;
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



}
