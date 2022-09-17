package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class ABMCPaciente {
	/*
	private DataPaciente dataPac = new DataPaciente();
	
	
	public void alta(Paciente pacienteAlta) throws IllegalArgumentException {
		
		LinkedList<Paciente> pacientesActuales = new LinkedList<Paciente>(dataPac.getAll()); 
		
		for (Paciente p: pacientesActuales) {
			if (p.getDocumento().getTipo().equals(pacienteAlta.getDocumento().getTipo()) && p.getDocumento().getNro() == pacienteAlta.getDocumento().getNro()) {
				throw new IllegalArgumentException("Paciente repetido.");
			}		
		}
		dataPac.add(pacienteAlta);	
	}
	
	public void baja(Paciente pacienteBaja) throws IllegalArgumentException {
		LinkedList<Paciente> pacientesActuales = new LinkedList<Paciente>(dataPac.getAll()); 
		boolean existe = false;
		for (Paciente p: pacientesActuales) {
			if (p.getLegajo() == pacienteBaja.getLegajo()) {
				existe = true;
				break;
			}		
		}
		if (existe) {
			dataPac.delete(pacienteBaja);
		}
		else {
			throw new IllegalArgumentException("El paceinte no existe.");
		}
		
	}
	
	public void modificacion(Paciente pacienteModif) {
		LinkedList<Paciente> pacientesActuales = new LinkedList<Paciente>(dataPac.getAll()); 
		boolean existe = false;
		for (Paciente p: pacientesActuales) {
			if (p.getLegajo() == pacienteModif.getLegajo()) {
				existe = true;
				break;
			}		
		}
		if (existe) {
			dataPac.update(pacienteModif);
		}
		else {
			throw new IllegalArgumentException("El paceinte no existe.");
		}
	}
	
	public LinkedList<Paciente> consultaTodos(){
		return dataPac.getAll();
	}
	
	public Paciente consultaUno(Paciente pacienteConsulta) {
		return dataPac.getByLegajo(pacienteConsulta);
	}
	
	public Paciente consultaUnoPorDocumento(Paciente pacienteConsulta) {
		return dataPac.getByDocumento(pacienteConsulta);
	}*/
}
