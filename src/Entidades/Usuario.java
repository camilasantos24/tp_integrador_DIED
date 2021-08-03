package Entidades;

public class Usuario {
	
	private int id;
	private String nombre;
	private String correo;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String nombre, String correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
	}
	
	public Usuario(String obj) {
		String[] atributos= obj.split("\t");
		
		this.id=Integer.parseInt(atributos[0]);
		this.nombre=atributos[1];
		this.correo=atributos[2];
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

}
