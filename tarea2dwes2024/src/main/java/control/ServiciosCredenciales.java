package control;

import dao.CredencialesDAO;
import dao.PlantaDAO;
import modelo.Planta;
import dao.ConexionBD;

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
	}