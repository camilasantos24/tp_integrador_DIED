package Grafo;

public class Vertice<T> {
	
	private T value;
	
	public Vertice(){	}
	 
	public Vertice(T v){
		this.value = v;
	}
	
	public void setValue(T v){
		this.value = v;
	}
	
	public T getValue(){
		return this.value;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

}
