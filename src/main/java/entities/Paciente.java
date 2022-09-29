package entities;
import java.sql.Date;

public class Paciente extends Persona{
	
	private int telefono;
	private String domicilio;
	private Date fechaNacimiento;
	private String sexo;
	
	
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	
	public String toString() {
		return this.getLegajo() + " - " +  this.getApellido() + ", " + this.getNombre() + " - " + this.getDocumento().getTipo() + " " + this.getDocumento().getNro();
	}
	
}
