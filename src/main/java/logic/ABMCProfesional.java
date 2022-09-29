package logic;

import java.util.LinkedList;

import data.DataProfesional;
import entities.Paciente;
import entities.Profesional;

public class ABMCProfesional {

	private DataProfesional dataProf= null;
	
	public ABMCProfesional() {
		dataProf = new DataProfesional();
	}
	
	public void alta(Profesional profAlta) {
		
		LinkedList<Profesional> profesionalesActuales = new LinkedList<Profesional>(dataProf.getAll()); 
		
		for (Profesional p: profesionalesActuales) {
			if (p.getDocumento().getTipo() == profAlta.getDocumento().getTipo() && p.getDocumento().getNro() == profAlta.getDocumento().getNro()) {
				throw new IllegalArgumentException("Profesional repetido.");
			}		
		}
		dataProf.add(profAlta);
		
	}


	public void baja(Profesional profBaja) {
		
		LinkedList<Profesional> profesionalesActuales = new LinkedList<Profesional>(dataProf.getAll()); 
		boolean existe = false;
		for (Profesional p: profesionalesActuales) {
			if (p.getLegajo() == profBaja.getLegajo()) {
				existe = true;
				break;
			}		
		}
		if (existe) {
			dataProf.delete(profBaja);
		}
		else {
			throw new IllegalArgumentException("El profesional no existe.");
		}
	}

	
	public void modificacion(Profesional profModif) {
		
		LinkedList<Profesional> profesionalesActuales = new LinkedList<Profesional>(dataProf.getAll()); 
		boolean existe = false;
		for (Profesional p: profesionalesActuales) {
			if (p.getLegajo() == profModif.getLegajo()) {
				existe = true;
				break;
			}		
		}
		if (existe) {
			dataProf.update(profModif);
		}
		else {
			throw new IllegalArgumentException("El profesional no existe.");
		}
	}
	
	
	public LinkedList<Profesional> consultaTodos(){
		return dataProf.getAll();
	}


	public Profesional consultaUno(Profesional profConsulta) {
		return dataProf.getByLegajo(profConsulta);
	}
}
