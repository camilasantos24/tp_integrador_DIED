package Entidades;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ConsultaGenerica {

	 public Map<String, String> resultados;
	    
	    public ConsultaGenerica(String rta){
	        String[] rtas = rta.split("\t");
	        resultados = new LinkedHashMap<String, String>();
	        for(String str: rtas){
	            resultados.put((str.split("--")[0]).trim(), (str.split("--")[1]).trim());

	        	// resultados.put((str.split("-")[0]).trim(), (str.split("-")[1]).trim());
	        }
	    }
	    
	    public Map<String, String> getResultados(){
	        return resultados;
	    }
	    
	    public Set<String> getParametros(){
	        return this.resultados.keySet();
	    }
	    
	    public String getValor(String campo){
	        if(this.resultados.containsKey(campo)){
	            return this.resultados.get(campo);
	        }
	        else{
	            return null;
	        }
	    }
	    
	    public String toString(){
	        String resultado = "";
	        int i = 1;
	        for(String key : this.getParametros()){
	            resultado += key + ": " + this.getValor(key);
	            if(i<this.resultados.size()){
	                resultado += " | ";
	            }
	            i++;
	        }
	        return resultado;
	    }

}
