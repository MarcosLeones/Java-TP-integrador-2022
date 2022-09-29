package entities;

import java.util.LinkedList;

public class Profesional  extends Persona{

	
	private LinkedList<Especialidad> especialidades;
	
	
	public void addEspecialidad(Especialidad esp) {
		this.especialidades.add(esp);
	}
	
	public LinkedList<Especialidad> getEspecialidades() {
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
	
	public void setEspecialidades(LinkedList<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	
}
