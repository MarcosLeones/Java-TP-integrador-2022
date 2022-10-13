package logic;

import java.util.ArrayList;
import data.DataObraSocial;
import entities.ObraSocial;


public class ABMCObraSocial {

	private DataObraSocial dataObSoc = new DataObraSocial();
	
	public void alta(ObraSocial obraAlta) 
	{
		//Valida que el nombre no esté vacío
		if (obraAlta.getNombre().equals("") || obraAlta.getNombre() == null) {
			throw new IllegalArgumentException("El nombre no puede estar vacío");
		}
		
		//Valida que no esté repetida
		ArrayList<ObraSocial> obrasActuales = dataObSoc.getAll();
		for (ObraSocial o: obrasActuales) {
			if (o.getNombre().equals(obraAlta.getNombre())) {
				throw new IllegalArgumentException("Obra Social repetida");
			}
		}
		dataObSoc.add(obraAlta);
	}
	
	public void baja(ObraSocial obraBaja)  throws IllegalArgumentException {
		
		ArrayList<ObraSocial> obrasActuales = new ArrayList<ObraSocial>(dataObSoc.getAll()); 
		
		boolean existe = false;
		for (ObraSocial os: obrasActuales) {
			if (os.getId() == obraBaja.getId()) {
				existe = true;
				break;
			}		
		}
		if (existe) {
			dataObSoc.delete(obraBaja);
		}
		else {
			throw new IllegalArgumentException("La obra social no existe.");
		}
	}
	
	public void modificacion(ObraSocial obraModif) {
		ArrayList<ObraSocial> obrasActuales = new ArrayList<ObraSocial>(dataObSoc.getAll()); 
		boolean existe = false;
		for (ObraSocial os: obrasActuales) {
			if (os.getId() == obraModif.getId()) {
				existe = true;
				break;
			}		
		}
		if (existe) {
			dataObSoc.update(obraModif);
		}
		else {
			throw new IllegalArgumentException("La obra social no existe.");
		}
	}

	public ArrayList<ObraSocial> consultaTodos(){
		return dataObSoc.getAll();
	}
	
	public ObraSocial consultaUno(ObraSocial obraConsulta) {
		return dataObSoc.getById(obraConsulta);
	}
	
}
