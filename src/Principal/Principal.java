package Principal;

import DatosHotel.*;
import java.util.Scanner;
import java.io.*;
import java.util.Iterator;
import java.util.ListIterator;

import DatosClienteCorrectos.*;
import java.util.LinkedList;
public class Principal {
	
    static Scanner entrada=  new Scanner(System.in);
    
    /**
     * Metodo principal, donde se puede elegir entre las siguientes opciones del programa
     * @param args
     */
	public static void main(String[]args){
		LinkedList<Habitacion>todasHabitaciones= new LinkedList<Habitacion>();
		LinkedList<Cliente>todosClientes= new LinkedList<Cliente>();
		LinkedList<Reservas>reservas= new LinkedList<Reservas>();
		Habitacion habitacion=null;
		Cliente cliente=null;
		System.out.println("Bienvenido al programa de reservas del Hotel 5 Estrellas, seleccione la opcion que desea realizar: ");
		System.out.println("---------------------------------------------------------------------------------------------------");
		int opcion = leerMenuHotel();
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				cliente= anadirCliente(todosClientes);
				break;
			case 2:
				reservarHabitacion();
				break;
			case 3:
				leerReservas();
				break;
			case 4:
				leerClientes();
				break;
			case 5:
				hacerFactura(todasHabitaciones);
				break;
			case 0:
				entrada.close();
				break;
			default:
					System.out.println("Opcion no valida, ingresa otra opcion");
					leerMenuHotel();
			}
			opcion = leerMenuHotel();
		}
		
		entrada.close();
    }
	
	/**
	 * Leer menu del programa del hotel
	 * @return opcion del menu
	 */
	public static int leerMenuHotel() {
		int opcion = 0;
		final String MENU = "\n\n1.- Añadir cliente al Hotel\n"
				+ "2.- Reservar Habitacion\n"
				+ "3.- Leer Reservas del Hotel\n"
				+ "4.- Leer Clientes del Hotel\n"
				+ "5.- Hacer Factura del Cliente saliente\n"
				+ "0.- Salir\n"
				+ "Introduce la opcion deseada:";
		System.out.println(MENU);
		try {
			opcion = entrada.nextInt();
		} catch (Exception e) {
			opcion = -1;
		}
		while (opcion < 0 || opcion > 7) {
			System.out.println("Opcion no valida\n\n");
			System.out.println(MENU);
			try {
				opcion = entrada.nextInt();
			} catch (Exception e) {
				opcion = -1;
			}
		}
		return opcion;

	}
	
	/**
	 * Metodo para añadir un cliente a la lista de clientes del hotel
	 * @param todosClientes
	 * @return cliente
	 */
	public static Cliente anadirCliente(LinkedList<Cliente>todosClientes){
		Iterator<Cliente>itr= todosClientes.iterator();
		System.out.println("Añade un cliente al Hotel");
		System.out.println("-------------------------");
		System.out.println("Nombre, Apellido, DNI, VISA y si el cliente es fumador o no (true / false)");
		Cliente cliente= new Cliente(entrada.next(), entrada.next(), entrada.next(), entrada.nextLong(), entrada.nextBoolean());
		System.out.println("¿Son los datos correctos?");
		System.out.println(cliente.getNombre() +" "+ cliente.getApellidos() +" "+ cliente.getDNI() +" "+ cliente.getVISA()+ " " + cliente.traduccion());
		System.out.println("Escribe: Si / No"); 
		String respuesta1;
        respuesta1 = entrada.next(); 
        if(respuesta1.equals("Si")) {
            todosClientes.addFirst(cliente);
            escribirClientes(todosClientes, "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/clientes.txt");
            leerClientes();
        }else if(respuesta1.equals("No")){
        	anadirCliente(todosClientes);
        }
		System.out.println();
		System.out.println ("¿Añadir nuevo cliente?");
		System.out.println("Escribe: Si / No"); 
        String respuesta2;
        respuesta2 = entrada.next(); 
        if(respuesta2.equals("Si")) {
            anadirCliente(todosClientes);
        }else if(respuesta2.equals("No")){
        	System.out.println();
        }
        return cliente;
	}
	
	/**
	 * Metodo para escribir clientes, en un fichro de texto
	 * @param todosClientes
	 * @param Fich
	 */
	public static void escribirClientes(LinkedList<Cliente>todosClientes,String Fich){
		Iterator<Cliente>itr= todosClientes.iterator();
		String ruta= "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/clientes.txt";
		File archivo = new File(ruta);
		BufferedWriter bw = null;
		if(archivo.exists()) {
			  try {
				
	            bw.write("-. ");
	            	while(itr.hasNext()){
	            		Cliente cliente=itr.next();
	                   bw.write("Nombre: "+ cliente.getNombre()+"\n"
	                		+   "Apellidos: "+cliente.getApellidos()+"\n"
	                		+   "DNI: "+ cliente.getVISA()+"\n"); 
	            	}
	            	bw.flush();
	            } catch (IOException e) {
				e.printStackTrace();
			}
		      
		} else {
			try {
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write("Los clientes que tiene el hotel son: \n");
	            bw.write("-------------------------------------\n");
	            bw.write("-. ");
				while(itr.hasNext()){
	        		Cliente cliente=itr.next();
	               bw.write("Nombre: "+ cliente.getNombre()+"\n"
	            	     	+"Apellidos: "+cliente.getApellidos()+"\n"
	            		    +"DNI: "+ cliente.getVISA()+"\n"); 
	        	}
				bw.flush();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        	
		 }
	}
	
	/**
	 * Leer clientes de un fichero de texto
	 */
	public static void leerClientes(){
		File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
           archivo = new File ("/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/clientes.txt");
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String linea;
           while((linea=br.readLine())!=null)
              System.out.println(linea);
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
	}
	
	/**
	 * Menu para reservar habitaciones
	 * @return
	 */
	public static int menuReservarHabitacion(){
		int opcion = 0;
		final String MENURESERVAS = "\n\n1.- Reservar Habitacion Individual\n"
				+ "2.- Reservar Habitacion Doble\n"
				+ "3.- Reservar Habitacion Triple\n"
				+ "4.- Reservar Habitacion Suite\n"
				+ "0.- Salir\n"
				+ "Introduce la opcion deseada:";
		System.out.println(MENURESERVAS);
		try {
			opcion = entrada.nextInt();
		} catch (Exception e) {
			opcion = -1;
		}
		while (opcion < 0 || opcion > 4) {
			System.out.println("Opcion no valida\n\n");
			System.out.println(MENURESERVAS);
			try {
				opcion = entrada.nextInt();
			} catch (Exception e) {
				opcion = -1;
			}
		}
		return opcion;
	}
	
	/**
	 * Submenu leer opciones tipo habitaciok
	 */
	public static void reservarHabitacion(){
		String[] args2= new String[1];
		LinkedList<Habitacion>habitaciones= new LinkedList<Habitacion>();
		Habitacion habitacion=null;
		int opcion = menuReservarHabitacion();
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				habitacion=habitacionIndividual(habitaciones);
				break;
			case 2:
				habitacion=habitacionDoble(habitaciones);
				break;
			case 3:
				habitacion=habitacionTriple(habitaciones);
				break;
			case 4:
				habitacion=habitacionSuite(habitaciones);
				break;
			case 0:
				main(args2);
				
				break;
			default:
					System.out.println("Opcion no valida, ingresa otra opcion");
					menuReservarHabitacion();
					break;
			}
			opcion = menuReservarHabitacion();
		}
		
	}
	
	/**
	 * Reservar Habitacion Suite
	 * @param todasHabitaciones
	 */
    public static Habitacion habitacionSuite(LinkedList<Habitacion> todasHabitaciones) {
    	ListIterator<Habitacion>itr= todasHabitaciones.listIterator();
		System.out.println("Reserva una habitacion suite del Hotel ");
		System.out.println("---------------------------------------");
    	Habitacion habitacion=null;
    	System.out.println("¿Desea que su estancia tenga todo incluido?");
        System.out.println("Si/No");
    	String opciones=entrada.next();
    	if(opciones.equals("Si")){
    		System.out.println("Dia / Mes/ Año/ Nº de la Habitacion/ Dias de Estancia/\n"
        			+ " Datos Cliente(Nombre, Apellidos, DNI, VISA, Fumador)/ Ocupar(Poner true)/\n "
        			+ "Habitacion para fumadores(true o false)/ Precio de la Habitacion doble");
    	HabitacionSuite HabitacionSuite= new HabitacionSuite(entrada.nextInt(), entrada.nextInt(), 
        		entrada.nextInt(), entrada.nextInt(), entrada.nextInt(),
        		new Cliente(entrada.next(),entrada.next(),entrada.next(),entrada.nextLong(), entrada.nextBoolean()),
        		entrada.nextBoolean(),entrada.nextBoolean(),entrada.nextInt(),Extras.TODOINCLUIDO);
    	System.out.println("¿Son los datos correctos?");
    	System.out.println("La habitacion ha sido reservada el "+ HabitacionSuite.getDia()+"/ "+HabitacionSuite.getMes()+"/ "+
    			HabitacionSuite.getAnyo()+" con el Nº de habitacion "+ HabitacionSuite.getNumeroHabitacion()+" durante "+HabitacionSuite.getDiasEstancia()
                +" dias, a nombre de "+ HabitacionSuite.getCliente().getNombre()+" " + HabitacionSuite.getCliente().getApellidos() + "\n con los datos ,"+
                HabitacionSuite.getCliente().getDNI() +" ,"+ HabitacionSuite.getCliente().getVISA()+ ". El cliente " +HabitacionSuite.getCliente().traduccion()+" y se encuentra en una "
                +HabitacionSuite.traduccion()+". Su habitacion ya esta\n "+  HabitacionSuite.traduccion1()+" por un precio de "+ HabitacionSuite.getPrecioSuite()+ 
                "€/dia. Con todo incluido, el cual se le suma" +HabitacionSuite.getExtras());
    	System.out.println("Escribe: Si / No"); 
    	String respuesta1;
            respuesta1 = entrada.next(); 
            if(respuesta1.equals("Si")) {
            	todasHabitaciones.addFirst(HabitacionSuite);
            	escribirReservas(todasHabitaciones, "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/reservas.txt");
            	leerReservas();
            	System.out.println("La habitacion ha sido reservada");
            }else if(respuesta1.equals("No")){
            	reservarHabitacion();
            }
    	}else if(opciones.equals("No")){
    		System.out.println("Dia / Mes/ Año/ Nº de la Habitacion/ Dias de Estancia/\n"
        			+ " Datos Cliente(Nombre, Apellidos, DNI, VISA, Fumador)/ Ocupar(Poner true)/\n "
        			+ "Habitacion para fumadores(true o false)/ Precio de la Habitacion doble");
    		HabitacionSuite HabitacionSuite= new HabitacionSuite(entrada.nextInt(), entrada.nextInt(), 
            		entrada.nextInt(), entrada.nextInt(), entrada.nextInt(),
            		new Cliente(entrada.next(),entrada.next(),entrada.next(),entrada.nextLong(), entrada.nextBoolean()),
            		entrada.nextBoolean(),entrada.nextBoolean(),entrada.nextInt(),Extras.TODOINCLUIDO);
        	System.out.println("¿Son los datos correctos?");
        	System.out.println("La habitacion ha sido reservada el "+ HabitacionSuite.getDia()+"/ "+HabitacionSuite.getMes()+"/ "+
        			HabitacionSuite.getAnyo()+" con el Nº de habitacion "+ HabitacionSuite.getNumeroHabitacion()+" durante "+HabitacionSuite.getDiasEstancia()
                    +" dias, a nombre de "+ HabitacionSuite.getCliente().getNombre()+" " + HabitacionSuite.getCliente().getApellidos() + "\n con los datos ,"+
                    HabitacionSuite.getCliente().getDNI() +" ,"+ HabitacionSuite.getCliente().getVISA()+ ". El cliente " +HabitacionSuite.getCliente().traduccion()+" y se encuentra en una "
                    +HabitacionSuite.traduccion()+". Su habitacion ya esta\n "+  HabitacionSuite.traduccion1()+" por un precio de "+ HabitacionSuite.getPrecioSuite()+ "€/dia.");
        	System.out.println("Escribe: Si / No"); 
        	String respuesta1;
	            respuesta1 = entrada.next(); 
	            if(respuesta1.equals("Si")) {
	            	todasHabitaciones.addFirst(HabitacionSuite);
	            	escribirReservas(todasHabitaciones, "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/reservas.txt");
	            	leerReservas();
	            	System.out.println("La habitacion ha sido reservada");
	            }else if(respuesta1.equals("No")){
	            	reservarHabitacion();
	            }
    	}
            System.out.println();
    		System.out.println ("¿Desea reservar otra habitacion?");
            String respuesta2;
            respuesta2 = entrada.next(); 
            if(respuesta2.equals("Si")) {
            	reservarHabitacion();
            }else if(respuesta2.equals("No")){
            	leerMenuHotel();
            }
		return habitacion;
	}
    
	/**
	 * Reservar Habitacion Triple
	 * @param todasHabitaciones
	 * @return habitacion
	 */
    public static Habitacion habitacionTriple(LinkedList<Habitacion> todasHabitaciones) {
    	ListIterator<Habitacion>itr= todasHabitaciones.listIterator();
		System.out.println("Reserva una habitacion doble del Hotel ");
		System.out.println("---------------------------------------");
    	Habitacion habitacion=null;
    	System.out.println("¿Desea que su estancia tenga todo incluido?");
        System.out.println("Si/No");
    	String opciones3=entrada.next();
    	if(opciones3.equals("Si")){
    		System.out.println("Dia / Mes/ Año/ Nº de la Habitacion/ Dias de Estancia/\n"
        			+ " Datos Cliente(Nombre, Apellidos, DNI, VISA, Fumador)/ Ocupar(Poner true)/\n "
        			+ "Habitacion para fumadores(true o false)/ Precio de la Habitacion doble");
    	HabitacionTriple HabitacionTriple= new HabitacionTriple(entrada.nextInt(), entrada.nextInt(), 
        		entrada.nextInt(), entrada.nextInt(), entrada.nextInt(),
        		new Cliente(entrada.next(),entrada.next(),entrada.next(),entrada.nextLong(), entrada.nextBoolean()),
        		entrada.nextBoolean(),entrada.nextBoolean(),entrada.nextInt(),Extras.TODOINCLUIDO);
    	System.out.println("¿Son los datos correctos?");
    	System.out.println("La habitacion ha sido reservada el "+ HabitacionTriple.getDia()+"/ "+HabitacionTriple.getMes()+"/ "+
    			HabitacionTriple.getAnyo()+" con el Nº de habitacion "+ HabitacionTriple.getNumeroHabitacion()+" durante "+HabitacionTriple.getDiasEstancia()
                +" dias, a nombre de "+ HabitacionTriple.getCliente().getNombre()+" " + HabitacionTriple.getCliente().getApellidos() + "\n con los datos ,"+
                HabitacionTriple.getCliente().getDNI() +" ,"+ HabitacionTriple.getCliente().getVISA()+ ". El cliente " +HabitacionTriple.getCliente().traduccion()+" y se encuentra en una "
                +HabitacionTriple.traduccion()+". Su habitacion ya esta\n "+  HabitacionTriple.traduccion1()+" por un precio de "+ HabitacionTriple.getPrecioTriple()+ 
                "€/dia. Con todo incluido, el cual se le suma" +HabitacionTriple.getExtras());
    	System.out.println("Escribe: Si / No"); 
        String respuesta1;
        respuesta1 = entrada.next(); 
        if(respuesta1.equals("Si")) {
        	todasHabitaciones.addFirst(HabitacionTriple);
        	escribirReservas(todasHabitaciones, "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/reservas.txt");
            leerReservas();
        	System.out.println("La habitacion ha sido reservada");
        }else if(respuesta1.equals("No")){
        	reservarHabitacion();
        }
    	}else if(opciones3.equals("No")){
    		System.out.println("Dia / Mes/ Año/ Nº de la Habitacion/ Dias de Estancia/\n"
        			+ " Datos Cliente(Nombre, Apellidos, DNI, VISA, Fumador)/ Ocupar(Poner true)/\n "
        			+ "Habitacion para fumadores(true o false)/ Precio de la Habitacion doble");
    		HabitacionTriple HabitacionTriple= new HabitacionTriple(entrada.nextInt(), entrada.nextInt(), 
            		entrada.nextInt(), entrada.nextInt(), entrada.nextInt(),
            		new Cliente(entrada.next(),entrada.next(),entrada.next(),entrada.nextLong(), entrada.nextBoolean()),
            		entrada.nextBoolean(),entrada.nextBoolean(),entrada.nextInt(),Extras.TODOINCLUIDO);
        	System.out.println("¿Son los datos correctos?");
        	System.out.println("La habitacion ha sido reservada el "+ HabitacionTriple.getDia()+"/ "+HabitacionTriple.getMes()+"/ "+
        			HabitacionTriple.getAnyo()+" con el Nº de habitacion "+ HabitacionTriple.getNumeroHabitacion()+" durante "+HabitacionTriple.getDiasEstancia()
                    +" dias, a nombre de "+ HabitacionTriple.getCliente().getNombre()+" " + HabitacionTriple.getCliente().getApellidos() + "\n con los datos ,"+
                    HabitacionTriple.getCliente().getDNI() +" ,"+ HabitacionTriple.getCliente().getVISA()+ ". El cliente " +HabitacionTriple.getCliente().traduccion()+" y se encuentra en una "
                    +HabitacionTriple.traduccion()+". Su habitacion ya esta\n "+  HabitacionTriple.traduccion1()+" por un precio de "+ HabitacionTriple.getPrecioTriple()+
                    "€/dia. ");
        	System.out.println("Escribe: Si / No"); 
            String respuesta1;
            respuesta1 = entrada.next(); 
            if(respuesta1.equals("Si")) {
            	todasHabitaciones.addFirst(HabitacionTriple);
            	escribirReservas(todasHabitaciones, "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/reservas.txt");
	            leerReservas();
            	System.out.println("La habitacion ha sido reservada");
            }else if(respuesta1.equals("No")){
            	System.out.println();
            	reservarHabitacion();
            }
    	}
        System.out.println();
		System.out.println ("¿Desea reservar otra habitacion?");
        String respuesta2;
        respuesta2 = entrada.next(); 
        if(respuesta2.equals("Si")) {
        	reservarHabitacion();
        }else if(respuesta2.equals("No")){
        	System.out.println();
        	leerMenuHotel();
        }
    	
        return habitacion;
	}
    
    /**
     * Reservar Habitacion Doble
     * @param todasHabitaciones
     */
    public static Habitacion habitacionDoble(LinkedList<Habitacion> todasHabitaciones) {
    	ListIterator<Habitacion>itr= todasHabitaciones.listIterator();
		System.out.println("Reserva una habitacion doble del Hotel ");
		System.out.println("---------------------------------------");
		Habitacion habitacion=null;
		System.out.println("¿Desea que su estancia tenga todo incluido?");
        System.out.println("Si/No");
    	String opciones2=entrada.next();
        if(opciones2.equals("Si")){
        	System.out.println("Dia / Mes/ Año/ Nº de la Habitacion/ Dias de Estancia/\n"
        			+ " Datos Cliente(Nombre, Apellidos, DNI, VISA, Fumador)/ Ocupar(Poner true)/\n "
        			+ "Habitacion para fumadores(true o false)/ Precio de la Habitacion doble");
    	HabitacionDoble HabitacionDoble= new HabitacionDoble(entrada.nextInt(), entrada.nextInt(), 
        		entrada.nextInt(), entrada.nextInt(), entrada.nextInt(),
        		new Cliente(entrada.next(),entrada.next(),entrada.next(),entrada.nextLong(), entrada.nextBoolean()),
        		entrada.nextBoolean(),entrada.nextBoolean(),entrada.nextInt(),Extras.TODOINCLUIDO);
    	System.out.println("¿Son los datos correctos?");
    	System.out.println("La habitacion ha sido reservada el "+ HabitacionDoble.getDia()+"/"+HabitacionDoble.getMes()+"/"+
    			HabitacionDoble.getAnyo()+" con el Nº de habitacion "+ HabitacionDoble.getNumeroHabitacion()+" durante "+HabitacionDoble.getDiasEstancia()
                +" dias, a nombre de "+ HabitacionDoble.getCliente().getNombre()+" "+ HabitacionDoble.getCliente().getApellidos() + "\n con los datos ,"+
    			HabitacionDoble.getCliente().getDNI() +" ,"+ HabitacionDoble.getCliente().getVISA()+ ". El cliente " +HabitacionDoble.getCliente().traduccion()+"\n y se encuentra en una "
                +HabitacionDoble.traduccion()+". Su habitacion ya esta\n "+  HabitacionDoble.traduccion1()+" por un precio de "+ HabitacionDoble.getPrecioHabitacionDoble()+ 
                "€/dia.Con todo incluido, el cual se le suma" +HabitacionDoble.getExtras()+ "€/estancia");
    	System.out.println("Escribe: Si / No"); 
        String respuesta1;
        respuesta1 = entrada.next(); 
        if(respuesta1.equals("Si")) {
        	todasHabitaciones.addFirst(HabitacionDoble);
            escribirReservas(todasHabitaciones, "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/reservas.txt");
        	leerReservas();
        	System.out.println("La habitacion ha sido reservada");
        }else if(respuesta1.equals("No")){
        	reservarHabitacion();
        }
        }else if(opciones2.equals("No")){
        	System.out.println("Dia / Mes/ Año/ Nº de la Habitacion/ Dias de Estancia/\n"
        			+ " Datos Cliente(Nombre, Apellidos, DNI, VISA, Fumador)/ Ocupar(Poner true)/\n "
        			+ "Habitacion para fumadores(true o false)/ Precio de la Habitacion doble");
        	HabitacionDoble HabitacionDoble= new HabitacionDoble(entrada.nextInt(), entrada.nextInt(), 
            		entrada.nextInt(), entrada.nextInt(), entrada.nextInt(),
            		new Cliente(entrada.next(),entrada.next(),entrada.next(),entrada.nextLong(), entrada.nextBoolean()),
            		entrada.nextBoolean(),entrada.nextBoolean(),entrada.nextInt(),null);
        	System.out.println("¿Son los datos correctos?");
        	System.out.println("La habitacion ha sido reservada el "+ HabitacionDoble.getDia()+"/"+HabitacionDoble.getMes()+"/"+
        			HabitacionDoble.getAnyo()+" con el Nº de habitacion "+ HabitacionDoble.getNumeroHabitacion()+" durante "+HabitacionDoble.getDiasEstancia()
                    +" dias, a nombre de "+ HabitacionDoble.getCliente().getNombre()+" "+ HabitacionDoble.getCliente().getApellidos() + "\n con los datos ,"+
        			HabitacionDoble.getCliente().getDNI() +" ,"+ HabitacionDoble.getCliente().getVISA()+ ". El cliente " +HabitacionDoble.getCliente().traduccion()+"\n y se encuentra en una "
                    +HabitacionDoble.traduccion()+". Su habitacion ya esta\n "+  HabitacionDoble.traduccion1()+" por un precio de "+ HabitacionDoble.getPrecioHabitacionDoble()+ "€/dia.");
        	System.out.println("Escribe: Si / No"); 
            String respuesta1;
            respuesta1 = entrada.next(); 
            if(respuesta1.equals("Si")) {
            	todasHabitaciones.addFirst(HabitacionDoble);
                escribirReservas(todasHabitaciones, "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/reservas.txt");
            	leerReservas();
            	System.out.println("La habitacion ha sido reservada");
        }
        }
        System.out.println();
		System.out.println ("¿Desea reservar otra habitacion?");
        String respuesta2;
        respuesta2 = entrada.next(); 
        if(respuesta2.equals("Si")) {
        	reservarHabitacion();
        }else if(respuesta2.equals("No")){
        	System.out.println();
        	leerMenuHotel();
        }
    	return habitacion;
		
		
	}
    
    /**
     * Reservar Habitacion Individual
     * @param todasHabitaciones
     * @return habitacion
     */
    public static Habitacion habitacionIndividual(LinkedList<Habitacion>todasHabitaciones){
    	ListIterator<Habitacion>itr= todasHabitaciones.listIterator();
		System.out.println("Reserva una habitacion individual del Hotel ");
		System.out.println("---------------------------------");
		Habitacion habitacion = null;
	        	System.out.println("¿Desea que su estancia tenga todo incluido?");
	        	System.out.println("Si/No");
	            String opciones=entrada.next();
	            if(opciones.equals("Si")){
	            	System.out.println("Dia / Mes/ Año/ Nº de la Habitacion/ Dias de Estancia/\n"
		        			+ " Datos Cliente(Nombre, Apellidos, DNI, VISA, Fumador)/ Ocupar(Poner true)/\n "
		        			+ "Habitacion para fumadores(true o false)/ Precio de la Habitacion doble");
	            HabitacionIndividual HabitacionIndividual= new HabitacionIndividual(entrada.nextInt(), entrada.nextInt(), 
	            		entrada.nextInt(), entrada.nextInt(), entrada.nextInt(),
	            		new Cliente(entrada.next(),entrada.next(),entrada.next(),entrada.nextLong(), entrada.nextBoolean()),
	            		entrada.nextBoolean(),entrada.nextBoolean(),entrada.nextInt(),Extras.TODOINCLUIDO);
	            
	            System.out.println("¿Son los datos correctos?");
	            System.out.println("La habitacion ha sido reservada el "+ HabitacionIndividual.getDia()+"/ "+HabitacionIndividual.getMes()+"/ "+
	        			HabitacionIndividual.getAnyo()+" con el Nº de habitacion "+ HabitacionIndividual.getNumeroHabitacion()+" durante "+ HabitacionIndividual.getDiasEstancia()+
                        "dias, a nombre de "+ HabitacionIndividual.getCliente().getNombre()+" " + HabitacionIndividual.getCliente().getApellidos() + "\n con los datos ,"+
                        HabitacionIndividual.getCliente().getDNI() +" ,"+ HabitacionIndividual.getCliente().getVISA()+ ". El cliente " +HabitacionIndividual.getCliente().traduccion()+" y se encuentra en una "
                        +HabitacionIndividual.traduccion()+". Su habitacion ya esta "+  HabitacionIndividual.traduccion1()+" por un precio de  "+ HabitacionIndividual.getPrecioIndividual()+
                        "€/dia. Con todo incluido, el cual se le suma" +HabitacionIndividual.getExtras()+ "€/estancia");
	            System.out.println("Escribe: Si / No"); 
	            String respuesta1;
	            respuesta1 = entrada.next(); 
	            if(respuesta1.equals("Si")) {
	            	todasHabitaciones.addFirst(HabitacionIndividual);
	                escribirReservas(todasHabitaciones, "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/reservas.txt");
	            	leerReservas();
	            	System.out.println("La habitacion ha sido reservada");
	            }else if(respuesta1.equals("No")){
	            	reservarHabitacion();
	            }
	            }else if(opciones.equals("No")){
	            	System.out.println("Dia / Mes/ Año/ Nº de la Habitacion/ Dias de Estancia/\n"
		        			+ " Datos Cliente(Nombre, Apellidos, DNI, VISA, Fumador)/ Ocupar(Poner true)/\n "
		        			+ "Habitacion para fumadores(true o false)/ Precio de la Habitacion doble");
	    	            HabitacionIndividual HabitacionInd= new HabitacionIndividual(entrada.nextInt(), entrada.nextInt(), 
	    	            		entrada.nextInt(), entrada.nextInt(), entrada.nextInt(),
	    	            		new Cliente(entrada.next(),entrada.next(),entrada.next(),entrada.nextLong(), entrada.nextBoolean()),
	    	            		entrada.nextBoolean(),entrada.nextBoolean(),entrada.nextInt(),Extras.TODOINCLUIDO);
	    	            
	    	            System.out.println("¿Son los datos correctos?");
	    	            System.out.println("La habitacion ha sido reservada el "+ HabitacionInd.getDia()+"/ "+HabitacionInd.getMes()+"/ "+
	    	        			HabitacionInd.getAnyo()+" con el Nº de habitacion "+ HabitacionInd.getNumeroHabitacion()+" durante "+ HabitacionInd.getDiasEstancia()+
	                            "dias, a nombre de "+ HabitacionInd.getCliente().getNombre()+" " + HabitacionInd.getCliente().getApellidos() + "\n con los datos ,"+
	                            HabitacionInd.getCliente().getDNI() +" ,"+ HabitacionInd.getCliente().getVISA()+ ". El cliente " +HabitacionInd.getCliente().traduccion()+" y se encuentra en una "
	                            +HabitacionInd.traduccion()+". Su habitacion ya esta "+  HabitacionInd.traduccion1()+" por un precio de  "+ HabitacionInd.getPrecioIndividual()+
	                            "€/dia. Con todo incluido, el cual se le suma" +HabitacionInd.getExtras()+ "€/estancia");
	    	            System.out.println("Escribe: Si / No"); 
	    	            String respuesta1;
	    	            respuesta1 = entrada.next(); 
	    	            if(respuesta1.equals("Si")) {
	    	            	todasHabitaciones.addFirst(HabitacionInd);
	    	                escribirReservas(todasHabitaciones, "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/reservas.txt");
	    	            	leerReservas();
	    	            	System.out.println("La habitacion ha sido reservada");
	    	            }
	            }
	            System.out.println();
	    		System.out.println ("¿Desea reservar otra habitacion?");
	            String respuesta2;
	            respuesta2 = entrada.next(); 
	            if(respuesta2.equals("Si")) {
	            	reservarHabitacion();
	            }else if(respuesta2.equals("No")){
	            	System.out.println();
	            	leerMenuHotel();
	            }
	       return habitacion;
	 
	 }
	
	/**
	 * Metodo para leer las reservas de un fichero de texto 
	 */
    public static void leerReservas(){
    	File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
           archivo = new File ("/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/reservas.txt");
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String linea;
           while((linea=br.readLine())!=null)
              System.out.println(linea);
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
    }
    
    /**
     * Metodo para desocupar una habitacion de la lista de reservas del hotel, buscando por el numero de habitacion
     * @param todasHabitaciones
     * @return
     */
    public static boolean desOcuparHabitacion(LinkedList<Habitacion>todasHabitaciones){
    	Iterator<Habitacion>itr=todasHabitaciones.iterator();
    	Habitacion actual= null;
    	boolean libre=false;
    	System.out.println("Digame el numero de habitacion que quiere liberar");
    	int numHabitacion=entrada.nextInt();
    	while(itr.hasNext()){
    		actual=itr.next();
    		if(actual.getNumeroHabitacion()==numHabitacion){
    			actual.setOcupada(false);
    			libre=true;
    			System.out.println("La habitacion ha sido liberada, esta libre");
    		}
    	}
    	return libre;
    }
   
    /**
     * Metodo para realizar la factura del hotel
     * @param todasHabitaciones
     */
    public static void hacerFactura(LinkedList<Habitacion>todasHabitaciones){
    	String respuesta;
    	String servicios;
    	int diasDeUso;
    	System.out.println("Se procede a realizar la factura del Cliente");
    	System.out.println("--------------------------------------------");
    	ListIterator<Habitacion> itr= todasHabitaciones.listIterator(todasHabitaciones.size());
    	System.out.println("Dame el DNI del cliente saliente");
    	String dni=entrada.next();
    	int preciototal=0;
    	while(itr.hasNext()){
    		Habitacion habitacion= itr.next();
    		if(dni.equals(habitacion.getCliente().getDNI())){
    			System.out.println("Cliente correcto, vamos a leer si la habitacion ya esta libre");
    			if(habitacion.isOcupada()==true){
        			desOcuparHabitacion(todasHabitaciones);
        			if(desOcuparHabitacion(todasHabitaciones)==true){
        			System.out.println("Se puede empezar ha realizar la factura, la habitacion esta libre.");
                          if(habitacion instanceof HabitacionIndividual){
        				  preciototal=((HabitacionIndividual) habitacion).getDiasEstancia()*
        						  ((HabitacionIndividual) habitacion).getPrecioIndividual();
        				  System.out.println("¿Ha solicitado algun servicio como, Wi-Fi, Parking, Lavanderia o el Servicio de Habitaciones?");
        				  System.out.println("Si/No");
        				  respuesta=entrada.next();
        				  if(respuesta.equals("Si")){
        					  System.out.println("¿Que servicios ha pedido?");
        					  int i = 1;
        					  while(i<4){
        						  servicios=entrada.next();
        						  if(servicios.equals("Wi-Fi")){
        							  System.out.println("Diageme el dias que ha usado Wi-Fi");
        							  diasDeUso=entrada.nextInt();
        				              preciototal=Extras.WIFI.getPrecio()*diasDeUso;
        						  }else if(servicios.equals("Lavanderia")){
        							  System.out.println("Diageme el dias que ha usado la Lavanderia");
        							  diasDeUso=entrada.nextInt();
        							  preciototal=Extras.LAVANDERIA.getPrecio()*diasDeUso;
        						  }else if(servicios.equals("Parking")){
        							  System.out.println("Diageme el dias que ha usado el Parking");
        							  diasDeUso=entrada.nextInt();
        							  preciototal=Extras.PARKING.getPrecio()*diasDeUso;
        						  }else if(servicios.equals("Servicio de Habitaciones")){
        							  System.out.println("Diageme el dias que ha usado el Servicio de Habitaciones");
        							  diasDeUso=entrada.nextInt();
        							  preciototal=Extras.LAVANDERIA.getPrecio()*diasDeUso;
        						  }
        						  i++;
        					  }
        					  System.out.println("Ha usado servicios extra");
        					  System.out.println("------------------------");
        					  System.out.println("El precio total de la estancia es de: "+preciototal);
        				  }else{
        					  System.out.println("No ha usado ningun servicio extra");
        					  System.out.println("---------------------------------");
        					  System.out.println("El precio total de la estancia es de: "+preciototal);
        				  }
        				  
        			      }else if(habitacion instanceof HabitacionDoble){
        			    	  preciototal=((HabitacionDoble) habitacion).getDiasEstancia()*
            						  ((HabitacionDoble) habitacion).getPrecioHabitacionDoble();
            				  System.out.println("¿Ha solicitado algun servicio como, Wi-Fi, Parking, Lavanderia o el Servicio de Habitaciones?");
            				  System.out.println("Si/No");
            				  respuesta=entrada.next();
            				  if(respuesta.equals("Si")){
            					  System.out.println("¿Que servicios ha pedido?");
            					  int i = 1;
            					  while(i<4){
            						  servicios=entrada.next();
            						  if(servicios.equals("Wi-Fi")){
            							  System.out.println("Diageme el dias que ha usado Wi-Fi");
            							  diasDeUso=entrada.nextInt();
            				              preciototal=Extras.WIFI.getPrecio()*diasDeUso;
            						  }else if(servicios.equals("Lavanderia")){
            							  System.out.println("Diageme el dias que ha usado la Lavanderia");
            							  diasDeUso=entrada.nextInt();
            							  preciototal=Extras.LAVANDERIA.getPrecio()*diasDeUso;
            						  }else if(servicios.equals("Parking")){
            							  System.out.println("Diageme el dias que ha usado el Parking");
            							  diasDeUso=entrada.nextInt();
            							  preciototal=Extras.PARKING.getPrecio()*diasDeUso;
            						  }else if(servicios.equals("Servicio de Habitaciones")){
            							  System.out.println("Diageme el dias que ha usado el Servicio de Habitaciones");
            							  diasDeUso=entrada.nextInt();
            							  preciototal=Extras.LAVANDERIA.getPrecio()*diasDeUso;
            						  }
            						  i++;
            					  }
            					  System.out.println("Ha usado servicios extra");
            					  System.out.println("------------------------");
            					  System.out.println("El precio total de la estancia es de: "+preciototal);
            				  }else{
            					  System.out.println("No ha usado ningun servicio extra");
            					  System.out.println("---------------------------------");
            					  System.out.println("El precio total de la estancia es de: "+preciototal);
            				  }
        				
        			      }else if(habitacion instanceof HabitacionTriple){
        			    	  preciototal=((HabitacionTriple) habitacion).getDiasEstancia()*
            						  ((HabitacionTriple) habitacion).getPrecioTriple();
            				  System.out.println("¿Ha solicitado algun servicio como, Wi-Fi, Parking, Lavanderia o el Servicio de Habitaciones?");
            				  System.out.println("Si/No");
            				  respuesta=entrada.next();
            				  if(respuesta.equals("Si")){
            					  System.out.println("¿Que servicios ha pedido?");
            					  int i = 1;
            					  while(i<4){
            						  servicios=entrada.next();
            						  if(servicios.equals("Wi-Fi")){
            							  System.out.println("Diageme el dias que ha usado Wi-Fi");
            							  diasDeUso=entrada.nextInt();
            				              preciototal=Extras.WIFI.getPrecio()*diasDeUso;
            						  }else if(servicios.equals("Lavanderia")){
            							  System.out.println("Diageme el dias que ha usado la Lavanderia");
            							  diasDeUso=entrada.nextInt();
            							  preciototal=Extras.LAVANDERIA.getPrecio()*diasDeUso;
            						  }else if(servicios.equals("Parking")){
            							  System.out.println("Diageme el dias que ha usado el Parking");
            							  diasDeUso=entrada.nextInt();
            							  preciototal=Extras.PARKING.getPrecio()*diasDeUso;
            						  }else if(servicios.equals("Servicio de Habitaciones")){
            							  System.out.println("Diageme el dias que ha usado el Servicio de Habitaciones");
            							  diasDeUso=entrada.nextInt();
            							  preciototal=Extras.LAVANDERIA.getPrecio()*diasDeUso;
            						  }
            						  i++;
            					  }
            					  System.out.println("Ha usado servicios extra");
            					  System.out.println("------------------------");
            					  System.out.println("El precio total de la estancia es de: "+preciototal);
            				  }else{
            					  System.out.println("No ha usado ningun servicio extra");
            					  System.out.println("---------------------------------");
            					  System.out.println("El precio total de la estancia es de: "+preciototal);
            				  }
        				
        			      }else if(habitacion instanceof HabitacionSuite){
        			    	  preciototal=((HabitacionSuite) habitacion).getDiasEstancia()*
            						  ((HabitacionSuite) habitacion).getPrecioSuite();
            				  System.out.println("¿Ha solicitado algun servicio como, Wi-Fi, Parking, Lavanderia o el Servicio de Habitaciones?");
            				  System.out.println("Si/No");
            				  respuesta=entrada.next();
            				  if(respuesta.equals("Si")){
            					  System.out.println("¿Que servicios ha pedido?");
            					  int i = 1;
            					  while(i<4){
            						  servicios=entrada.next();
            						  if(servicios.equals("Wi-Fi")){
            							  System.out.println("Diageme el dias que ha usado Wi-Fi");
            							  diasDeUso=entrada.nextInt();
            				              preciototal=Extras.WIFI.getPrecio()*diasDeUso;
            						  }else if(servicios.equals("Lavanderia")){
            							  System.out.println("Diageme el dias que ha usado la Lavanderia");
            							  diasDeUso=entrada.nextInt();
            							  preciototal=Extras.LAVANDERIA.getPrecio()*diasDeUso;
            						  }else if(servicios.equals("Parking")){
            							  System.out.println("Diageme el dias que ha usado el Parking");
            							  diasDeUso=entrada.nextInt();
            							  preciototal=Extras.PARKING.getPrecio()*diasDeUso;
            						  }else if(servicios.equals("Servicio de Habitaciones")){
            							  System.out.println("Diageme el dias que ha usado el Servicio de Habitaciones");
            							  diasDeUso=entrada.nextInt();
            							  preciototal=Extras.LAVANDERIA.getPrecio()*diasDeUso;
            						  }
            						  i++;
            					  }
            					  System.out.println("Ha usado servicios extra");
            					  System.out.println("------------------------");
            					  System.out.println("El precio total de la estancia es de: "+preciototal);
            				  }else{
            					  System.out.println("No ha usado ningun servicio extra");
            					  System.out.println("---------------------------------");
            					  System.out.println("El precio total de la estancia es de: "+preciototal);
            				  }
        				
        			      }else{
        				     System.out.println("Error");
        			      }
        			}else{
        				System.out.println("Aun no se ha liberado");
        			}
    		}
    		
    			
    		
    	}
    	}
    	
    }
   
    /**
     * Metodo para escribir las reservas del hotel en un fichero de texto
     * @param todasHabitaciones
     * @param Fich
     */
    public static void escribirReservas(LinkedList<Habitacion>todasHabitaciones, String Fich){
    	Iterator<Habitacion>itr= todasHabitaciones.iterator();
		String ruta= "/Users/bmtadeo/Documents/Universidad/Proyectos Programacion/Hotel/reservas.txt";
		File archivo = new File(ruta);
		BufferedWriter bw = null;
		if(archivo.exists()) {
			  try {
				bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write("-. ");
	            	while(itr.hasNext()){
	            		Habitacion habitacion=itr.next();
	            		bw.write(habitacion.getCliente().getNombre()+" " + habitacion.getCliente().getApellidos() +" con DNI: "+ habitacion.getCliente().getDNI()+" y tarjeta VISA: "
		                		+habitacion.getCliente().getVISA()+"\n"+"Tiene una reserva el dia "+habitacion.getDia()+"/"+habitacion.getMes()+"/"+habitacion.getAnyo()+" en la habitacion "
		                	    +habitacion.getNumeroHabitacion());    
	            	}
	            	bw.flush();
	            } catch (IOException e) {
				e.printStackTrace();
			}
		      
		} else {
			try {
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write("Los clientes que tiene el hotel son: \n");
	            bw.write("-------------------------------------\n");
	            bw.write("-. ");
				while(itr.hasNext()){
	        		Habitacion habitacion=itr.next();
	               bw.write(habitacion.getCliente().getNombre()+" " + habitacion.getCliente().getApellidos() +" con DNI: "+ habitacion.getCliente().getDNI()+" y tarjeta VISA:"
	                		+habitacion.getCliente().getVISA()+"\n"+"Tiene una reserva el dia "+habitacion.getDia()+"/"+habitacion.getMes()+"/"+habitacion.getAnyo()+" en la habitacion"
	                	    +habitacion.getNumeroHabitacion());
				}
	               bw.flush();
	             
	        	
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        	
		 }
}
}
