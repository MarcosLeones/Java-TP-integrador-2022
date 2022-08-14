package logic;

import data.DataPaciente;
import data.DataProfesional;
import entities.Persona;

public class Login {

	public Persona validate(Persona per) {
		Persona p = null;
		DataPaciente dPac = new DataPaciente();
		DataProfesional dPro = new DataProfesional();
		
		p = dPac.getByEmailYPass(per);
		if (p.getEmail() == per.getEmail() && p.getPassword() == per.getPassword()) {
			return p;
		}
		
		p = dPro.getByEmailYPass(per);
		if (p.getEmail() == per.getEmail() && p.getPassword() == per.getPassword()) {
			return p;
		}
		
		p = null;
		return p;
	}
	
	
}
