package control;

public class Controlador {

	private static Controlador servicios;
	
	private ServiciosPlanta servPlanta;
	private ServiciosEjemplar serEJ;
	private ServiciosPersona serPers;
	private ServiciosMensaje servMensaje;

	public static Controlador getServicios() {
		if(servicios == null)
			servicios=new Controlador();
		return servicios;
	}
	
	private Controlador getServicios() {
		ServiciosPlanta servPlanta = new ServiciosPlanta();
		serEj = new ServiciosEjemplar();
		servPersona = new ServiciosPersona();
		servMensaje = new ServiciosMensaje();
	}
	
	public ServiciosPlanta getServiciosPlanta() {
		return servPlanta;
	}
	
	public ServiciosEjemplar getServiciosEjemplar() {
		return serEj;
	}
}