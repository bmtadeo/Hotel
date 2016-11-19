package DatosHotel;

public enum Extras {
	SERVICIOHABITACIONES(8),TODOINCLUIDO(100),LAVANDERIA(20),WIFI(10),PARKING(10);
	/**
	 * El parking vale 10 por dia, se pregunta a la hora de hacer factura
	 * El servicio de habitaciones vale 8 por cada vez que se ha usado, se pregunta en hacer factura
	 * El todo incluido se cobra al final de la estancia, se pide a la hora de la reserva
	 * La lavanderia se paga por cada vez que se ha usado, se pregunta al hacer la factura
	 * El wifi se cobra por dia, se pide a la hora de hacer factura y se suma.
	 */
	private static int precio;
	Extras(int precio){
		this.setPrecio(precio);
	}
	public static int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		Extras.precio = precio;
	}
}
