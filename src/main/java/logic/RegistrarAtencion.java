package logic;

import entities.*;

import java.util.ArrayList;

import data.DataTurno;

public class RegistrarAtencion {

	public ArrayList<Turno> getTurnosDelDia(Persona profesional) {
		DataTurno dt = new DataTurno();
		return dt.getByProfesionalHoy(profesional);
	}
	
	public ArrayList<Turno> getHistoriasClinica(Persona paciente) {
		DataTurno dt = new DataTurno();
		return dt.getReservas(paciente);
	}
	
	public void registrarHistoriaClinica(Turno turno) {
		DataTurno dt = new DataTurno();
		dt.updateTurno(turno);
	}
	
}
