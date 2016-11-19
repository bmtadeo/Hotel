package DatosHotel;

import java.util.List;

public class Reservas {
	private Cliente cliente;
	private Habitacion habitacion;
	private List<Reservas>reservas;
	
	
	public Reservas(Cliente cliente, Habitacion habitacion){
		this.cliente=cliente;
		this.habitacion=habitacion;	
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Habitacion getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

}
