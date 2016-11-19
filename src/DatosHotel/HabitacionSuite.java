package DatosHotel;

public class HabitacionSuite extends Habitacion {
	private int precioSuite;
	private Extras extras;
	public HabitacionSuite(int dia, int mes, int anyo, int numeroHabitacion, int diasEstancia,  
			Cliente cliente,boolean ocupada, boolean fumadores,int precioSuite, Extras extras){
		super(dia,  mes,  anyo, numeroHabitacion, diasEstancia, cliente, ocupada, fumadores);
		this.precioSuite=precioSuite;
		this.extras=extras;
	}
	public Extras getExtras(){
		return extras;
	}
	
	public int getPrecioSuite() {
		return precioSuite;
	}
	public void setPrecioSuite(int precioSuite) {
		this.precioSuite = precioSuite;
	}
}
