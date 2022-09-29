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
	
	public void baja(ObraSocial obraBaja)  throws IllegalArgumentException {
		
		LinkedList<ObraSocial> obrasActuales = new LinkedList<ObraSocial>(dataObSoc.getAll()); 
		
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
		LinkedList<ObraSocial> obrasActuales = new LinkedList<ObraSocial>(dataObSoc.getAll()); 
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

	public LinkedList<ObraSocial> consultaTodos(){
		return dataObSoc.getAll();
	}
	
	public ObraSocial consultaUno(ObraSocial obraConsulta) {
		return dataObSoc.getById(obraConsulta);
	}
	
}
