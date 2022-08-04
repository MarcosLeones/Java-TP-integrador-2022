package entities;

import java.util.LinkedList;

public class Persona {
	
	private int legajo;
	private Documento documento;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private LinkedList<ObraSocial> obrasSociales;
	
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Documento getDocumento() {
		return this.documento;
	}
	
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public void setObrasSociales(LinkedList<ObraSocial> obrasSociales) {
		this.obrasSociales = obrasSociales;
	}
	
	public void addObraSocial(ObraSocial obra) {
		this.obrasSociales.add(obra);
	}
	
	public LinkedList<ObraSocial> getObrasSociales(){
		return this.obrasSociales;
	}
	
	public void removeObraSocial(ObraSocial obra) {
		for (ObraSocial o : obrasSociales) {
			if (o.getId()== obra.getId()) {
				obrasSociales.remove(o);
				break;
			}
		}
	}

}
