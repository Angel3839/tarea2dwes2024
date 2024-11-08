package control;


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
		
		public boolean modificar(Mensaje m) {
			return mensajeDAO.modificar(m);
		}
	
		
		public Collection<Mensaje> verTodos(){
			return mensajeDAO.verTodos();
		}
		
		public Mensaje buscarPorID(long id) {
			return mensajeDAO.buscarPorID(id);
		}
		
		public boolean validarMensaje(String mensaje) {
	        if (mensaje == null || mensaje.trim().isEmpty()) {
	            return false;
	        }
	        
	        if (!mensaje.matches("[a-zA-Z0-9 ]*")) {
	            return false;
	        }

	        return true;
	    }
		
		public ArrayList<Mensaje> verMensajesPorPersona(long idPersona) {
	        ArrayList<Mensaje> mensajes = new ArrayList<>();
	        
	        return mensajes;
	    }
		
		public ArrayList<Mensaje> verMensajesPorCodigoPlanta(String codigoPlanta) {
	        ArrayList<Mensaje> mensajes = new ArrayList<>();
	        
	        return mensajes;
	    }
	}