package DatosHotel;

public class HabitacionIndividual extends Habitacion {
	private int precioIndividual;
	private Extras extras;
	public HabitacionIndividual(int dia, int mes, int anyo, int numeroHabitacion, int diasEstancia, 
			Cliente cliente,boolean ocupada, boolean fumadores, int precioIndividual, Extras extras){
		super(dia, mes, anyo,  numeroHabitacion,  diasEstancia, cliente, ocupada,fumadores);
		this.precioIndividual=precioIndividual;
		this.extras=extras;
	}
	public Extras getExtras(){
		return extras;
	}
	public int getPrecioIndividual() {
		return precioIndividual;
	}

	public void setPrecioIndividual(int precioIndividual) {
		this.precioIndividual = precioIndividual;
	}

}
