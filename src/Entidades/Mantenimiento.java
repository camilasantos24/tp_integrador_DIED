package Entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Mantenimiento {

	private long id_mantenimiento;
	private LocalDate fecha_inicio;
	private LocalDate fecha_fin;
	private String observacion;
	//private Estacion estacion;
	
	
 // ------------------Constructores------------------------
	
	public Mantenimiento() {
	}
	
	public Mantenimiento(long id_mantenimiento, LocalDate fecha_inicio, LocalDate fecha_fin, String observacion) {
		super();
		this.id_mantenimiento = id_mantenimiento;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.observacion = observacion;
	}

	public Mantenimiento (String obj) {
		String[] atributos= obj.split("\t");
		
		this.id_mantenimiento= Long.parseLong(atributos[0]);
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.fecha_inicio= LocalDate.parse(atributos[2], formato);
		this.fecha_fin=LocalDate.parse(atributos[3], formato);
		this.observacion= atributos[4];
	}

	
   //---------------Getters and Setters------------------------

	public long getId_mantenimiento() {
		return id_mantenimiento;
	}

	public void setId_mantenimiento(long id_mantenimiento) {
		this.id_mantenimiento = id_mantenimiento;
	}

	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public LocalDate getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(LocalDate fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
