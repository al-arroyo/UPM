package cliente;

import entidades.InformacionReserva;

public class PruebaServicioCrud {
	public static void main(String[] args) {
		AdaptadorClienteServicioCrud adaptador = new AdaptadorClienteServicioCrud();
				try {
					InformacionReserva info = adaptador.crearReserva("Nerea", "123");
					System.out.println();info.getNombre();
					System.out.println();info.getHabitacion();
				}catch(Exception e) {
					System.out.print(e.getMessage());
				}
		
		
		
		
		
		
		
		
		
		
	}
	

}
