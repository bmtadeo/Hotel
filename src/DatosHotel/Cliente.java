package DatosHotel;

import java.util.*;

public class Cliente {
	private String Nombre;
	private String Apellidos;
	private String DNI;
	private long VISA;
	private  static boolean fumador;//true-fuma, false-no fuma
	private List<Cliente>clientes;
	
	public Cliente(String nomb, String ape, String dni,long visa, boolean fumador) {
		this.Nombre=nomb;
		this.Apellidos=ape;
		this.DNI=dni;
		this.VISA=visa;
		this.fumador=fumador;
	}
	public String traduccion(){
	String traduccion;
	
    if(getFumador()==true){
    	traduccion="es fumador";
    }else{
    	traduccion="no es fumador";
    }
	return traduccion;
    }

	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public static Boolean getFumador() {
		return fumador;
	}
	public static void setFumador(Boolean fumador) {
		fumador = fumador;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	public long getVISA() {
		return VISA;
	}


	public void setVISA(long vISA) {
		VISA = vISA;
	}



	}



	

