package control;

import java.util.Collection;

import dao.PersonaDAO;
import modelo.Persona;
import utils.ConexionBD;

public class ServiciosPersona {
    private ConexionBD con;
    private PersonaDAO personaDAO;
    
    public ServiciosPersona() {
        con = ConexionBD.getInstance();
        personaDAO = (PersonaDAO) con.getPersonaDAO();
    }

    public long insertar(Persona pers) {
        return personaDAO.insertar(pers);
    }

    public boolean modificar(Persona pers) {
        return personaDAO.modificar(pers);
    }

    public Collection<Persona> verTodos() {
        return personaDAO.verTodos();
    }

    public Persona buscarPorID(long id) {
        return personaDAO.buscarPorID(id);
    }

    public long obtenerIdUsuarioAutenticado(String nombreUsuario) {
        Persona persona = personaDAO.buscarPorNombreUsuario(nombreUsuario);
        
        if (persona != null) {
            return persona.getId(); 
        } else {
            return -1;
        }
    }
}
