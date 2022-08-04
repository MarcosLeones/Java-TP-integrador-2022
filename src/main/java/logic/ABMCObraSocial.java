package logic;

import java.util.LinkedList;

import data.DataObraSocial;
import entities.ObraSocial;

public class ABMCObraSocial {

	private DataObraSocial dataObSoc = new DataObraSocial();
	
	public void alta(ObraSocial obraAlta) 
	{
		LinkedList<ObraSocial> obrasActuales = dataObSoc.getAll();
		
		for (ObraSocial o: obrasActuales) {
			if (o.getNombre()== obraAlta.getNombre()) {
				throw new IllegalArgumentException("Obra Social repetida");
			}
		}
		dataObSoc.add(obraAlta);
	}
}
