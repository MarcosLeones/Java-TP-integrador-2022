package logic;

import java.time.LocalDate;
import java.util.ArrayList;

import data.DataPersona;
import entities.ObraSocial;
import entities.Persona;

public class ABMCPersona {

	private DataPersona dataPer = new DataPersona();
	
	public void alta(Persona personaAlta) throws Exception {		
		
		//Verifica que la persona no esté repetida
		ArrayList<Persona> personasActuales = new ArrayList<Persona>(dataPer.getAll()); 
		for (Persona p: personasActuales) {
			if (p.getDocumento().getTipo().equals(personaAlta.getDocumento().getTipo()) 
					&& p.getDocumento().getNro() == personaAlta.getDocumento().getNro()
					&& p.getRol() == personaAlta.getRol()) {
				throw new Exception("Persona repetida.");
			}		
		}
		
		//Verifica que no haya campos fundamentales vacíos
		if (personaAlta.getNombre().equals("") || personaAlta.getNombre() == null
			|| personaAlta.getApellido().equals("") || personaAlta.getApellido() == null
			|| personaAlta.getRol().equals("") || personaAlta.getRol() == null
			|| personaAlta.getDocumento().getTipo().equals("") || personaAlta.getDocumento().getTipo() == null
			|| personaAlta.getDocumento().getNro() == 0
			|| personaAlta.getRol().equals("") || personaAlta.getRol() == null
			|| personaAlta.getEmail().equals("") || personaAlta.getEmail() == null
			|| personaAlta.getPassword().equals("") || personaAlta.getPassword() == null) 
		{
			throw new Exception("Hay datos incompletos.");
		}
		
		//Verifica que no haya otros datos incompletos si es paciente
		if (personaAlta.getRol().equals("paciente") && 
			(	personaAlta.getDomicilio().equals("") || personaAlta.getDomicilio() == null
				|| personaAlta.getTelefono().equals("") || personaAlta.getRol() == null
				|| personaAlta.getSexo().equals("") || personaAlta.getSexo() == null
				|| personaAlta.getFechaNacimiento() == null)	)
		{
			throw new Exception("Hay datos incompletos.");
		}
		
		//Verifica que si el documento es del tipo libreta esta coincida con el sexo de la persona
		if (personaAlta.getDocumento().getTipo().equals("libretaEnrrolamiento") && !personaAlta.getSexo().equals("m")) {
			throw new Exception("Sólo puede tener libreta de enrrolamiento si es masculino.");
		}
		if (personaAlta.getDocumento().getTipo().equals("libretaCivica") && !personaAlta.getSexo().equals("f")) {
			throw new Exception("Sólo puede tener libreta cívica si es femenino.");
		}
		
		//verifica que la persona sea mayor de edad
		LocalDate fechaMayorEdad = LocalDate.now().minusYears(18);
		if (personaAlta.getFechaNacimiento().isAfter(fechaMayorEdad)) {
			throw new Exception("Debe ser mayor de edad para registrarse.");
		}
		
		
		dataPer.add(personaAlta);	
	}
	
	
	public ArrayList<Persona> consultaTodos() {
		return dataPer.getAll();
	}

	public Persona consultaUno(Persona p) {
		return dataPer.getByLegajo(p.getLegajo());
	}

	public void baja(Persona personaBaja) {
		dataPer.delete(personaBaja);
	}
	
	public void modificacion(Persona personaModif) throws Exception {
		//Verifica que no haya campos fundamentales vacíos
		if (personaModif.getNombre().equals("") || personaModif.getNombre() == null
			|| personaModif.getApellido().equals("") || personaModif.getApellido() == null
			|| personaModif.getRol().equals("") || personaModif.getRol() == null
			|| personaModif.getDocumento().getTipo().equals("") || personaModif.getDocumento().getTipo() == null
			|| personaModif.getDocumento().getNro() == 0
			|| personaModif.getRol().equals("") || personaModif.getRol() == null
			|| personaModif.getEmail().equals("") || personaModif.getEmail() == null
			|| personaModif.getPassword().equals("") || personaModif.getPassword() == null) 
		{
			throw new Exception("Hay datos incompletos.");
		}
		
		//Verifica que no haya otros datos incompletos si es paciente
		if (personaModif.getRol().equals("paciente") && 
			(	personaModif.getDomicilio().equals("") || personaModif.getDomicilio() == null
				|| personaModif.getTelefono().equals("") || personaModif.getRol() == null
				|| personaModif.getSexo().equals("") || personaModif.getSexo() == null
				|| personaModif.getFechaNacimiento() == null)	)
		{
			throw new Exception("Hay datos incompletos.");
		}
		
		//Verifica que si el documento es del tipo libreta esta coincida con el sexo de la persona
		if (personaModif.getDocumento().getTipo().equals("libretaEnrrolamiento") && !personaModif.getSexo().equals("m")) {
			throw new Exception("Sólo puede tener libreta de enrrolamiento si es masculino.");
		}
		if (personaModif.getDocumento().getTipo().equals("libretaCivica") && !personaModif.getSexo().equals("f")) {
			throw new Exception("Sólo puede tener libreta cívica si es femenino.");
		}
		
		//verifica que la persona sea mayor de edad
		LocalDate fechaMayorEdad = LocalDate.now().minusYears(18);
		if (personaModif.getFechaNacimiento().isAfter(fechaMayorEdad)) {
			throw new Exception("Debe ser mayor de edad para registrarse.");
		}
		
		
		//Verifica que la persona exista
		ArrayList<Persona> personasActuales = new ArrayList<Persona>(dataPer.getAll());
		boolean existe = false;
		for (Persona p: personasActuales) {
			if (personaModif.getLegajo() == p.getLegajo()) {
				existe = true;
				break;
			}		
		}
		if (!existe) {
			throw new Exception("La persona que quiere modificar no está registrada.");
		}
		
		dataPer.update(personaModif);				
	}

}
