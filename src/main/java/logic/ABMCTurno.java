package logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

import data.DataPersona;
import data.DataTurno;
import entities.Persona;
import entities.Turno;

public class ABMCTurno {

	
	//Parámetros para la creación de los turnos
	final int diasPorAdelantado = 3; 
	final int duracionTurno = 30; //Expresado en minutos
	final LocalTime horaInicio = LocalTime.of(8,0); 
	final LocalTime horaFin = LocalTime.of(17,0);
	
	public void crearTurnos() {
		//Este método crea los turnos pero NO los deja disponibles para ser reservados.
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		LocalDate fechaObjetivo = LocalDate.now().plusDays(diasPorAdelantado);
		DataTurno dt = new DataTurno();
		DataPersona dp = new DataPersona();
		ArrayList<Persona> profesionales = dp.getByRol("profesional");
		
		for (Persona p : profesionales) {
			LocalDate fecha = dt.getFechaUltimoTurno(p);
			//Revisa de no crear turnos anteriores al día actual
			if (fecha.isBefore(LocalDate.now()))
				fecha = LocalDate.now();
				
			while (fecha.isBefore(fechaObjetivo)) {
				fecha = fecha.plusDays(1);			
				
				//Revisa de no poner turnos los fines de semana
				if (fecha.get(ChronoField.DAY_OF_WEEK) == 6 || fecha.get(ChronoField.DAY_OF_WEEK) == 7) 
					continue;
				LocalTime hora = horaInicio;
				
				while(hora.isBefore(horaFin)) {
					Turno t = new Turno();
					t.setProfesional(p);
					t.setEstado("creado");
					t.setFecha(fecha);
					t.setHora(hora);
					turnos.add(t);
					hora = hora.plusMinutes(duracionTurno);				
				}				
			}
		}	
		if (turnos.size() > 0)
			dt.add(turnos);
	}
	
	
	
	
	public ArrayList<Turno> getTurnosCreados(Persona profesional){
		DataTurno dt = new DataTurno();
		return dt.getTurnosCreados(profesional, LocalDate.now());
	}
	
	public void registrarTurnosDisponibles(ArrayList<Turno> turnos) {
		DataTurno dt = new DataTurno();
		dt.updateDisponible(turnos);
		
	}
	
	
	public ArrayList<Turno> mostrarReservados(Persona paciente){
		DataTurno dt = new DataTurno();
		return dt.getReservas(paciente);
	}
	
	public Turno consultaUno(Turno t) {
		DataTurno dt = new DataTurno();
		return dt.getById(t);
	}
	
	public void actualizarTurno(Turno t) {
		DataTurno dt = new DataTurno();
		dt.updateTurno(t);
	}
}
