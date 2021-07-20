package Entidades;

public class LineaTransporte {
	
	private int id;
	private String nombre;
	private String color;
	private int estado; // 1-Activa 0-No activa
	private int alta_baja; // 1-alta 0-baja
	private Trayecto trayecto;
	
	public LineaTransporte() {
	
	}
	
	public LineaTransporte(int id, String nombre, String color, int estado, Trayecto trayecto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.color = color;
		this.estado = estado;
		this.trayecto = trayecto;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Trayecto getTrayecto() {
		return trayecto;
	}

	public void setTrayecto(Trayecto trayecto) {
		this.trayecto = trayecto;
	}

	public int getAlta_baja() {
		return alta_baja;
	}

	public void setAlta_baja(int alta_baja) {
		this.alta_baja = alta_baja;
	}
	
	

}
