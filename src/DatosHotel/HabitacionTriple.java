package DatosHotel;

public class HabitacionTriple extends Habitacion {
	private int precioTriple;
	private Extras extras;
	
	public HabitacionTriple(int dia, int mes, int anyo, int numeroHabitacion, int diasEstancia, 
			Cliente cliente, boolean ocupada, boolean fumadores, int precioTriple, Extras extras){
		super(dia,  mes,  anyo, numeroHabitacion, diasEstancia, cliente, ocupada, fumadores);
		this.precioTriple=precioTriple;
	
	}
	public Extras getExtras(){
		return extras;
	}
	public int getPrecioTriple() {
		return precioTriple;
	}

	public void setPrecioTriple(int precioTriple) {
		this.precioTriple = precioTriple;
	}
}
