package control;

import java.util.Collection;

import dao.EjemplarDAO;
import dao.PlantaDAO;
import modelo.Ejemplar;
import modelo.Planta;
import dao.ConexionBD;

	public class ServiciosEjemplar {
		private ConexionBD con;
		private EjemplarDAO ejemplarDAO;
		
		public ServiciosEjemplar() {
			con=ConexionBD.getInstance();
			ejemplarDAO = (EjemplarDAO) con.getEjemplarDAO();
		}
	
		
		public long insertar(Ejemplar e) {
			return ejemplarDAO.insertar(e);
		}
		
		public boolean modificar(Ejemplar e) {
			return ejemplarDAO.modificar(e);
		}
	
		
		public Collection<Ejemplar> verTodos(){
			return ejemplarDAO.verTodos();
		}
		
		public Ejemplar buscarPorID(long id) {
			return ejemplarDAO.buscarPorID(id);
		}
		
		public static boolean validarEjemplar(Ejemplar e) {
	        boolean ret = false;
	        if(e.getCodigoPlanta().isEmpty()) 
	        	return false;
	        if(e.getCodigoPlanta().length()<3 || e.getCodigoPlanta().length()>20)
	        return false;
	        
	        return true;
	    }
	
	}