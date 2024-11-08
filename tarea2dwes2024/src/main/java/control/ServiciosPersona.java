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
    
    public boolean emailExistente(String email) {
        return personaDAO.emailExistente(email);
    }
    
    public boolean validarPersona(Persona pers) {
        if (pers.getNombre() == null || pers.getNombre().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return false;
        }
        if (pers.getEmail() == null || pers.getEmail().isEmpty()) {
            System.out.println("El correo electrónico no puede estar vacío.");
            return false;
        }
        
        return true;
    }
}
