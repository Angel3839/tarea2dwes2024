package control;

import java.util.Collection;

import dao.MensajeDAO;
import dao.PersonaDAO;
import modelo.Mensaje;
import modelo.Persona;
import dao.ConexionBD;

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
	}