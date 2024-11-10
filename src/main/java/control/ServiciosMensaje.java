package control;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import dao.MensajeDAO;
import modelo.Mensaje;
import utils.ConexionBD;

	public class ServiciosMensaje {
		private ConexionBD con;
		private MensajeDAO mensajeDAO;
		
		public ServiciosMensaje() {
			con=ConexionBD.getInstance();
			mensajeDAO = (MensajeDAO) con.getMensajeDAO();
		}

		public long insertar(Mensaje m) {
			return mensajeDAO.insertar(m);
		}
		
	
		public ArrayList<Mensaje> verMensajesFecha(LocalDateTime primeraFecha, LocalDateTime segundaFecha) {
			return mensajeDAO.verMensajesPorFecha(primeraFecha, segundaFecha);
		}
		
		public Collection<Mensaje> verTodos(){
			return mensajeDAO.verTodos();
		}
		
		public ArrayList<Mensaje> verMensajesPorPersona(long idPersona) {
			return mensajeDAO.verMensajesPorPersona(idPersona);
		}
		
		public ArrayList<Mensaje> verMensajesPorCodigoPlanta(String codigoPlanta) {
			return mensajeDAO.verMensajesPorCodigoPlanta(codigoPlanta);
		}
		
		public ArrayList<Mensaje> verMensajesPorEjemplar(long idEjemplar) {
			return mensajeDAO.verMensajesPorEjemplar(idEjemplar);
		}
	
		/**
		 * Método para validar un mensaje.
		 */
		public boolean validarMensaje(String mensaje) {
			if (mensaje ==null || mensaje.trim().isEmpty()) {
		        System.out.println("El mensaje está vacio.");
		        return false;
		    }
		    if (mensaje.length() > 500) {
		        System.out.println("El mensaje es muy largo");
		        return false;
		    }
			return true;
		}
	
	}
	
	