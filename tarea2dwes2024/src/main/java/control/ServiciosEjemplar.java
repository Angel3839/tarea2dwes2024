package control;

import java.util.ArrayList;
import java.util.Collection;

import dao.EjemplarDAO;
import modelo.Ejemplar;
import utils.ConexionBD;

public class ServiciosEjemplar {
    private ConexionBD con;
    private EjemplarDAO ejemplarDAO;
    
    public ServiciosEjemplar() {
        con = ConexionBD.getInstance();
        ejemplarDAO = (EjemplarDAO) con.getEjemplarDAO();
    }

    public long insertar(Ejemplar e) {
        return ejemplarDAO.insertar(e);
    }

    public boolean modificar(Ejemplar e) {
        return ejemplarDAO.modificar(e);
    }

    public Collection<Ejemplar> verTodos() {
        return ejemplarDAO.verTodos();
    }

    public int contarEjemplares() {
        Collection<Ejemplar> ejemplares = ejemplarDAO.verTodos();
        return ejemplares != null ? ejemplares.size() : 0;
    }
    
    public Ejemplar buscarPorID(long id) {
        return ejemplarDAO.buscarPorID(id);
    }

    public static boolean validarEjemplar(Ejemplar e) {
        boolean ret = false;
        if (e.getCodigoPlanta().isEmpty()) 
            return false;
        if (e.getCodigoPlanta().length() < 3 || e.getCodigoPlanta().length() > 20)
            return false;

        return true;
    }
    
    public ArrayList<Ejemplar> ejemplaresPorTipoPlanta(String codigoPlanta) {
        ArrayList<Ejemplar> ejemplaresFiltrados = new ArrayList<>();
       
        return ejemplaresFiltrados;
    }
}
