package logic;

import data.DataTurno;
import entities.Persona;
import entities.Turno;
import java.util.ArrayList;

public class CancelarTurno {

	public ArrayList<Turno> getReservas(Persona paciente) {
		DataTurno dataTurno = new DataTurno();
		return dataTurno.getReservas(paciente);
	}
	
	public void registrarCancelacion(Turno turno) {
		DataTurno dataTurno = new DataTurno();
		Persona p = new Persona();
		turno.setPaciente(p);
		turno.setEstado("disponible");
		dataTurno.updateTurno(turno);
	}
}
