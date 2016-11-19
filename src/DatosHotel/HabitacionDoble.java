package DatosHotel;

public class HabitacionDoble extends Habitacion{
	
	private int precioHabitacionDoble;
	private Extras extras;
	public HabitacionDoble(int dia, int mes, int anyo, int numeroHabitacion, int diasEstancia,
			Cliente cliente, boolean ocupada, boolean fumadores, int precioHabitacionDoble, Extras extras){
		super(dia,  mes,  anyo, numeroHabitacion, diasEstancia, cliente, ocupada, fumadores);
		this.precioHabitacionDoble=precioHabitacionDoble;
		this.extras=extras;
		
	}
	public Extras getExtras(){
		return extras;
	}
	public int getPrecioHabitacionDoble() {
		return precioHabitacionDoble;
	}

	public void setPrecioHabitacionDoble(int precioHabitacionDoble) {
		this.precioHabitacionDoble = precioHabitacionDoble;
	}

}
