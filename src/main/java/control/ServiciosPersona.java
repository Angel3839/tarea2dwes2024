package control;

import java.util.Collection;

import dao.PersonaDAO;
import modelo.Persona;
import utils.ConexionBD;

	public class ServiciosPersona {
			private ConexionBD con;
			private PersonaDAO personaDAO;
			
		public ServiciosPersona() {
			con=ConexionBD.getInstance();
			personaDAO = (PersonaDAO) con.getPersonaDAO();
		}
	
			public long insertar(Persona pers) {
				return personaDAO.insertar(pers);
			}
			
			public Collection<Persona> verTodos(){
				return personaDAO.verTodos();
			}
			
			public boolean emailExistente(String email) {
				return personaDAO.emailExistente(email);
			}
	
			public long IdUsuarioAutenticado(String usuario) {
				return personaDAO.IdUsuarioAutenticado(usuario);
			}
		
			/**
			 * Método para validar a una persona.
			 */
			public boolean validarPersona(Persona pers) {
			    if(pers == null) {
			        return false;
			    }
			    if(pers.getNombre() == null || pers.getNombre().isEmpty()) {
			        return false;
			    }
			    if (pers.getNombre().length() < 3 || pers.getNombre().length() > 20) {
			        return false;
			    }
			    if(pers.getEmail() == null || pers.getEmail().isEmpty()) {
			        return false;
			    }
			    if(pers.getEmail().length() < 5 || !pers.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") || pers.getEmail().length() > 40){
			        return false;
			    }
			    if (pers.getNombre().length()<3 || pers.getNombre().length()>40)
			    	return false;
			    
			    return true;
			}
			
	}

