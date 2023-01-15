package logic;

import data.DataPersona;
import entities.*;

public class Login {

	public Persona validate(Persona per) {
		Persona p = null;
		DataPersona dataPer = new DataPersona();
		p = dataPer.getByEmailPassword(per);
		return p;
	}
	
	
}
