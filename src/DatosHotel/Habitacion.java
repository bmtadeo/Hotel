package DatosHotel;

import java.util.*;

public class Habitacion {
	private int dia;
	private int mes;
	private int anyo;
	private int numeroHabitacion;
	private int diasEstancia;
	//true para fumadores, false para no fumadores
	private boolean fumadores= false;
	//Libre-false, Ocupada-true
	private boolean ocupada= false;
	private Cliente cliente;
	private List<Habitacion>habitaciones;
	

	/**
	 * Constructor con parametros de Habitacion
	 * @param dia
	 * @param mes
	 * @param anyo
	 * @param numeroHabitacion
	 * @param diasEstancia
	 * @param fumadores
	 * @param cliente
	 * @param ocupada
	 */
	public Habitacion(int dia, int mes, int anyo, int numeroHabitacion, int diasEstancia,  Cliente cliente, boolean ocupada, boolean fumadores){
		this.dia=dia;
		this.mes=mes;
		this.anyo=anyo;
		this.numeroHabitacion=numeroHabitacion;
		this.diasEstancia=diasEstancia;
		this.cliente=cliente;
		this.ocupada=ocupada;
		this.fumadores=fumadores;
		
	}
	public String traduccion(){
		String traduccion;
		
	    if(isFumadores()==true){
	    	traduccion="habitacion para fumadores";
	    }else{
	    	traduccion="habitacion para no fumadores";
	    }
		return traduccion;
	    }
	public String traduccion1(){
		String traduccion;
		
	    if(isOcupada()==true){
	    	traduccion="ocupada";
	    }else{
	    	traduccion="habitacion no ocupada";
	    }
		return traduccion;
	    }
	public Cliente getCliente(){
		return cliente;
	}
	public void setCliente(Cliente cliente){
		this.cliente=cliente;
	}
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(int numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public boolean isFumadores() {
		return fumadores;
	}

	public void setFumadores(boolean fumadores) {
		this.fumadores = fumadores;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public int getDiasEstancia() {
		return diasEstancia;
	}

	public void setDiasEstancia(int diasEstancia) {
		this.diasEstancia = diasEstancia;
	}

	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	
}
