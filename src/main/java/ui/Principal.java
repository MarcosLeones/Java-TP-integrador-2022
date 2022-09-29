package ui;

import logic.*;
import entities.*;
import data.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		/*
		ABMCPaciente abmc = new ABMCPaciente();
		Paciente pac = new Paciente();
		pac.setApellido("perez");
		pac.setNombre("Juan");
		Documento doc = new Documento();
		doc.setNro(12345678);
		doc.setTipo("dni");
		pac.setDocumento(doc);
		pac.setEmail("jp@gmail.com");
		pac.setPassword("jperez");
		LinkedList<ObraSocial> os = new LinkedList<ObraSocial>();
		pac.setObrasSociales(os);
		
		//abmc.alta(pac);
		
		LinkedList<Paciente> lista = abmc.consultaTodos();
		
		for(Paciente p: lista) {
			System.out.println(p.toString());
		}

		pac = abmc.consultaUnoPorDocumento(pac);
		
		System.out.println("por doc " + pac.toString());
		
		pac.setLegajo(12);
		
		pac = abmc.consultaUno(pac);
		
		System.out.println("por legajo " + pac.toString());
		
		//abmc.baja(pac);
		*/
		
		/*
		ObraSocial os = new ObraSocial();
		os.setNombre("prueba1");
		ABMCObraSocial abmc = new ABMCObraSocial();
		//abmc.alta(os);
		LinkedList<ObraSocial> obras = abmc.consultaTodos();
		for (ObraSocial o: obras) {
			System.out.println(o + " " + o.getId() + " " + o.getNombre());
		}
		
		ObraSocial os2 = new ObraSocial();
		os2.setId(3);
		os2 = abmc.consultaUno(os2);
		System.out.println(os2);
		abmc.baja(os2);
		*/
		
		
	}

}
