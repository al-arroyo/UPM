package cliente;


import CalculadoraGUI.CalculadoraGUI;

public class ClienteCalculadora {
	
	public static void main(String[] args) {
		/*AdaptadorOperacionesCalculadoraGUI adaptador = new AdaptadorOperacionesCalculadoraGUI();
		try {
			InformacionReserva info = adaptador.crearReserva("Nerea", "123");
			System.out.println();info.getNombre();
			System.out.println();info.getHabitacion();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}*/
		
		new CalculadoraGUI(new AdaptadorOperacionesCalculadoraGUI());
	}

}
