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
		
		public boolean validarPlanta(Planta p) {
	        if(p.getCodigo().isEmpty()) 
	        	return false;
	        if(p.getCodigo().length()<3 || p.getCodigo().length()>20)
	        	return false;
	        if(p.getNombrecientifico().isEmpty() || p.getNombrecomun().isEmpty())
	        	return false;
	        return true;
	    }
		public boolean validarCodigo(String codigo) {
		    if (codigo == null ||codigo.isEmpty()) {
		        return false;
		    }
		    if (!codigo.matches("^[a-zA-Z]+$")) {
		        return false;
		    } return true;
		}
	}