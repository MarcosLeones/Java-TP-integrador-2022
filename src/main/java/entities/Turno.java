package entities;

import java.time.*;

public class Turno {

	private int id;
	private LocalDate fecha;
	private LocalTime hora;
	private String estado;
	private Persona paciente;
	private Persona profesional;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Persona getPaciente() {
		return paciente;
	}
	public void setPaciente(Persona paciente) {
		this.paciente = paciente;
	}
	public Persona getProfesional() {
		return profesional;
	}
	public void setProfesional(Persona profesional) {
		this.profesional = profesional;
	}
	
	
}