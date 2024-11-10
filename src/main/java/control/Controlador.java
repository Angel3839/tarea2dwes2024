package control;

	/**
	 * Clase singleton que gestiona el acceso a los distintos servicios de la aplicación,
	 * incluyendo servicios de planta, ejemplar, persona, mensaje y credenciales.
	 * Proporciona un punto de acceso centralizado para las operaciones y facilita
	 * el manejo de la sesión del usuario autenticado.
	 */
	public class Controlador {
		private static Controlador servicios;
		private String usuarioAutenticado;
		private ServiciosPlanta servPlanta;
		private ServiciosEjemplar servEjemplar;
		private ServiciosPersona servPersona;
		private ServiciosMensaje servMensaje;
		private ServiciosCredenciales servCred;
		
		public static Controlador getServicios() {
			if(servicios == null)
				servicios=new Controlador();
			return servicios;
		}
		
		private Controlador() {
			servPlanta = new ServiciosPlanta();
			servEjemplar = new ServiciosEjemplar();
			servPersona = new ServiciosPersona();
			servMensaje = new ServiciosMensaje();
			servCred = new ServiciosCredenciales();
			
		}
		
			public ServiciosPlanta getServiciosPlanta() {
				return servPlanta;
				
			}
			
			public ServiciosEjemplar getServiciosEjemplar() {
				return servEjemplar;
				
			}
			
			public ServiciosPersona getServiciosPersona() {
				return servPersona;
				
			}
			
			public ServiciosMensaje getServiciosMensaje() {
				return servMensaje;
				
			}
			
			public ServiciosCredenciales getServiciosCredenciales() {
				return servCred;
			}
			
			public void setUsuarioAutenticado(String usuario) {
		        this.usuarioAutenticado = usuario;
		    }
			
		    public String getUsuarioAutenticado() {
		        return usuarioAutenticado;
		    }
		    
		    public void cerrarSesion() {
		        this.usuarioAutenticado = null;
		    }
	}