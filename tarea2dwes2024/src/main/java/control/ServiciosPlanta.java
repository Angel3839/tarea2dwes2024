package control;

import java.util.Collection;

import dao.ConexionBD;
import dao.PlantaDAO;
import modelo.Planta;

public class ServiciosPlanta {

	private ConexionBD con;
	private PlantaDAO plantaDAO;
	
	public ServiciosPlanta() {
		con=ConexionBD.getInstance();
		plantaDAO=(PlantaDAO)con.getPlantaDAO();
	}
	
	public boolean validarPlanta(Planta p) {
		boolean ret = false;
		if(p.getCodigo().isEmpty())return false;
		if(p.getCodigo().length()<3||p.getCodigo().length()>20)return false;
	
		return true;
	}
	
	public int insertar(Planta e) {
		return plantaDAO.insertar(e);
	}
	
	public boolean modificar(Planta p) {
		return plantaDAO.modificar(p);
	}
	
	public Collection<Planta> verTodos(){
		return plantaDAO.verTodos();
	}
	public Planta buscarPorID(long id) {
		return plantaDAO.buscarPorID(id);
	}
	
	public static boolean validarPlanta(Planta p) {
        boolean ret = false;
        if(p.getCodigo().isEmpty()) 
        	return false;
        if(p.getCodigo().length()<3 || p.getCodigo().length()>20)
        	return false;
        
        return true;
    }
}
