package logic;

import java.time.LocalDate;
import java.util.ArrayList;

import data.DataEspecialidad;
import data.DataObraSocial;
import data.DataPersona;
import data.DataTurno;
import entities.Especialidad;
import entities.ObraSocial;
import entities.Persona;
import entities.Turno;

public class ReservarTurno {
	
	public ArrayList<ObraSocial> getObrasSociales(Persona pacienteActual) {
		DataObraSocial dataOS = new DataObraSocial();
		return dataOS.getByPersona(pacienteActual);
	}
	
	public ArrayList<Especialidad> getEspecialidades(){
		DataEspecialidad dataEsp = new DataEspecialidad();
		return dataEsp.getAll();
	}
	
	public ArrayList<Persona> getProfesionales(Especialidad esp, ObraSocial os){
		DataPersona dataPer = new DataPersona();
		return dataPer.getProfesionales(esp, os);
	}
	
	public ArrayList<Turno> getTurnosDisponibles(Persona profesional) {
		DataTurno dataTur = new DataTurno();
		return dataTur.getTurnosDisponibles(profesional, LocalDate.now().plusDays(1));
	}
	
	public void registrarReserva(Turno turno, Persona pacienteActual) {
		DataTurno dataTur = new DataTurno();
		turno.setPaciente(pacienteActual);
		turno.setEstado("reservado");
		dataTur.updateTurno(turno);
	}
	
	
	
}
