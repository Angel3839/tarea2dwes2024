package control;

import dao.CredencialesDAO;
import utils.ConexionBD;

	public class ServiciosCredenciales {
		private ConexionBD con;
		private CredencialesDAO credencialesDAO;
		
		public ServiciosCredenciales() {
			con=ConexionBD.getInstance();
			credencialesDAO = (CredencialesDAO) con.getCredencialesDAO();
		}
		
		public boolean autenticar(String usuario, String password) {
		
		    return credencialesDAO.autenticar(usuario, password);
		}
		
		public boolean usuarioExistente(String usuario) {
			return credencialesDAO.usuarioExistente(usuario);
		}
		
		public int insertar(String usuario, String contrasena, Long idPersona) {
			return credencialesDAO.insertar(usuario, contrasena, idPersona);
		}
		
		/** 
		 * Método para validar una contraseña.
		 */
		public boolean validarContraseña(String contraseña) {
		    if(contraseña.matches("^(?=.*[.,])[A-Za-z0-9.,]{8,}$")) {
		    	return true;
		    }
		    
		    return false;
		}
	}