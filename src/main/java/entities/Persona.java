package entities;
import java.time.LocalDate;
import java.util.ArrayList;


public class Persona {
	
	private int legajo;
	private Documento documento;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String telefono;
	private String domicilio;
	private LocalDate fechaNacimiento;
	private String sexo;
	private String rol;
	private ArrayList<ObraSocial> obrasSociales;
	private ArrayList<Especialidad> especialidades;
	
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
	
	public void setObrasSociales(ArrayList<ObraSocial> obrasSociales) {
		this.obrasSociales = obrasSociales;
	}
	
	public void addObraSocial(ObraSocial obra) {
		this.obrasSociales.add(obra);
	}
	
	public ArrayList<ObraSocial> getObrasSociales(){
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
	
	public void setEspecialidades(ArrayList<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	public void addEspecialidad(Especialidad esp) {
		this.especialidades.add(esp);
	}
	
	public ArrayList<Especialidad> getEspecialidades() {
		return this.especialidades;
	}
	
	public void removeEspecialidad(Especialidad esp) {
		for (Especialidad e: especialidades) {
			if (e.getId() == esp.getId()) {
				especialidades.remove(e);
				break;
			}
		}
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getRol() {
		return this.rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
