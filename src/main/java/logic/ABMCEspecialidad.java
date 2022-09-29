package logic;

import java.util.LinkedList;
import data.DataEspecialidad;
import entities.Especialidad;


public class ABMCEspecialidad {

	private DataEspecialidad dataEsp = null;
	
	public ABMCEspecialidad() {
		dataEsp = new DataEspecialidad();
	}
	
	public void alta(Especialidad espAlta) {
		LinkedList<Especialidad> especialidadesActuales = dataEsp.getAll();
		
		for (Especialidad e: especialidadesActuales) {
			if (e.getNombre()== espAlta.getNombre()) {
				throw new IllegalArgumentException("Especialidad repetida");
			}
		}
		dataEsp.add(espAlta);
	}
	
	public void baja(Especialidad espBaja) {
		LinkedList<Especialidad> especialidadesActuales = new LinkedList<Especialidad>(dataEsp.getAll()); 
		
		boolean existe = false;
		for (Especialidad e: especialidadesActuales) {
			if (e.getId() == espBaja.getId()) {
				existe = true;
				break;
			}		
		}
		if (existe) {
			dataEsp.delete(espBaja);
		}
		else {
			throw new IllegalArgumentException("La especialidad no existe.");
		}
	}
	
	public void modificacion(Especialidad espModif) {
		LinkedList<Especialidad> especialidadesActuales = new LinkedList<Especialidad>(dataEsp.getAll()); 
		boolean existe = false;
		for (Especialidad e: especialidadesActuales) {
			if (e.getId() == espModif.getId()) {
				existe = true;
				break;
			}		
		}
		if (existe) {
			dataEsp.update(espModif);
		}
		else {
			throw new IllegalArgumentException("La especialidad no existe.");
		}
	}
	
	public Especialidad consultaUno(Especialidad esp) {
		return dataEsp.getById(esp);
	}
	
	public LinkedList<Especialidad> consultaTodos(){
		return dataEsp.getAll();
	}
}
