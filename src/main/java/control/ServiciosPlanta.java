package control;

import java.util.Collection;

import dao.PlantaDAO;
import modelo.Planta;
import utils.ConexionBD;

	public class ServiciosPlanta {
		private ConexionBD con;
		private PlantaDAO plantaDAO;
		
		public ServiciosPlanta() {
			con=ConexionBD.getInstance();
			plantaDAO = (PlantaDAO) con.getPlantaDAO();
		}
	
		public long insertar(Planta p) {
			return plantaDAO.insertar(p);
		}
		public Collection<Planta> verTodos(){
			return plantaDAO.verTodos();
		}
		public Planta buscarPorID(long id) {
			return plantaDAO.buscarPorID(id);
		}
		public boolean actualizarNombreComun(String codigo, String nombreComun) {
			return plantaDAO.actualizarNombreComun(codigo, nombreComun);
		}
		public boolean actualizarNombreCientifico(String codigo, String nombreComun) {
			return plantaDAO.actualizarNombreCientifico(codigo, nombreComun);
		}
		public boolean codigoExistente(String codigo) {
			return plantaDAO.codigoExistente(codigo);
		}
		
		/**
		 * Método para validar una planta.
		 */
		public boolean validarPlanta(Planta p) {
	        if(p.getCodigo().isEmpty()) 
	        	return false;
	        if(p.getCodigo().length()<3 || p.getCodigo().length()>50)
	        	return false;
	        if(p.getNombrecientifico().isEmpty() || p.getNombrecomun().isEmpty())
	        	return false;
	        if (!p.getCodigo().matches("^[A-Za-z0-9]+$")) 
		        return false;
		    if(p.getNombrecientifico().length()<3 || p.getNombrecientifico().length()>100)
		        return false;
		    
	        if(p.getNombrecomun().length()<3 || p.getNombrecomun().length()>100)
	        	return false;
	        if(!p.getNombrecientifico().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$") || !p.getNombrecomun().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"))
	        	return false;
	        return true;
	    }
		
		/**
		 * Método para validar el código de una planta.
		 */
		public boolean validarCodigo(String codigo) {
		    if (codigo == null ||codigo.isEmpty()) {
		        return false;
		    }
		    if (!codigo.matches("^[A-Za-z0-9]+$")) {
		        return false;
		    }
		    if (codigo.length()<3 || codigo.length()>50)
		    	return false;
		    return true;
		}
	}
	
	