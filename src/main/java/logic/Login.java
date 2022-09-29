package logic;

import data.DataPaciente;
import data.DataProfesional;
import entities.*;

public class Login {

	public Persona validate(Persona per) {
		Persona p = null;
		DataPaciente dPac = new DataPaciente();
		DataProfesional dPro = new DataProfesional();
		
		p = dPac.getByEmailYPass(per);
		if (p.getEmail().equals(per.getEmail()) && p.getPassword().equals(per.getPassword())) {
			return (Paciente)p;
		}
		
		p = dPro.getByEmailYPass(per);
		if (p.getEmail().equals(per.getEmail()) && p.getPassword().equals(per.getPassword())) {
			return (Profesional)p;
		}
		
		p = null;
		return (Persona)p;
	}
	
	
}
