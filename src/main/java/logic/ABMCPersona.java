package logic;

import java.time.LocalDate;
import java.util.ArrayList;

import data.DataPersona;
import entities.ObraSocial;
import entities.Persona;

public class ABMCPersona {

	private DataPersona dataPer = new DataPersona();
	
	public void alta(Persona personaAlta) throws IllegalArgumentException {
		
		ArrayList<Persona> personasActuales = new ArrayList<Persona>(dataPer.getAll()); 
		
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
			throw new IllegalArgumentException("Hay datos incompletos.");
		}
		
		//Verifica que no haya otros datos incompletos si es paciente
		if (personaAlta.getRol().equals("paciente") && 
			(	personaAlta.getDomicilio().equals("") || personaAlta.getDomicilio() == null
				|| personaAlta.getTelefono().equals("") || personaAlta.getRol() == null
				|| personaAlta.getSexo().equals("") || personaAlta.getSexo() == null
				|| personaAlta.getFechaNacimiento() == null)	)
		{
			throw new IllegalArgumentException("Hay datos incompletos.");
		}
		
		
		//Verifica que la persona no esté repetida
		for (Persona p: personasActuales) {
			if (p.getDocumento().getTipo().equals(personaAlta.getDocumento().getTipo()) 
					&& p.getDocumento().getNro() == personaAlta.getDocumento().getNro()
					&& p.getRol() == personaAlta.getRol()) {
				throw new IllegalArgumentException("Persona repetida.");
			}		
		}
		
		//verifica que la persona sea mayor de edad
		LocalDate fechaMayorEdad = LocalDate.now().minusYears(18);
		if (personaAlta.getFechaNacimiento().isAfter(fechaMayorEdad)) {
			throw new IllegalArgumentException("Debe ser mayor de edad para registrarse.");
		}
		
		
		dataPer.add(personaAlta);	
	}
	
	
	
}
