package logic;

import data.DataTurno;
import entities.Persona;
import entities.Turno;

import java.time.LocalDate;
import java.util.ArrayList;

public class CancelarTurno {

	public ArrayList<Turno> getReservasCancelables(Persona paciente) {
		DataTurno dataTurno = new DataTurno();
		ArrayList<Turno> turnos = dataTurno.getReservas(paciente);
		//Solo se pueden cancelar las reservas con al menos un día de anticipación.
		ArrayList<Turno> turnosCancelables = new ArrayList<Turno>();
		for(Turno t: turnos) {
			if (t.getFecha().isAfter(LocalDate.now().plusDays(1))) {
				turnosCancelables.add(t);
			}
		}
		return turnosCancelables;
	}
	
	public void registrarCancelacion(Turno t) {
		DataTurno dataTurno = new DataTurno();
		Turno turno = dataTurno.getById(t);
		Persona p = new Persona();
		turno.setPaciente(p);
		turno.setEstado("disponible");
		dataTurno.updateTurno(turno);
	}
}
