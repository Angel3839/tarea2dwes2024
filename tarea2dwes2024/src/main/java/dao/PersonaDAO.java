package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Persona;
import utils.ConexionBD;

public class PersonaDAO implements OperacionesCRUD<Persona> {
	Connection conex;
	private PreparedStatement ps;
	private ResultSet rs;


	public PersonaDAO(Connection conex) {
		
			this.conex = conex;
	}


	@Override
	public long insertar(Persona pers) {
		try {
			ps = conex.prepareStatement("INSERT INTO personas (id, nombre, email) values (?,?,?)");
			ps.setLong(1, pers.getId());
			ps.setString(2, pers.getNombre());
			ps.setString(3, pers.getEmail());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar en plantas " + e.getMessage());
		}
		
		return 0;
	}
	public boolean emailExistente(String email) {

		String emailsExistentes = "SELECT email FROM personas";
		ArrayList<String> listaEmail = new ArrayList<String>();
		try{
			ps = conex.prepareStatement(emailsExistentes);
			rs = ps.executeQuery();
			while (rs.next()) {
				listaEmail.add(rs.getString(1));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		if (listaEmail.contains(email)){
			return true;
		} else {
			return false;
		}

	}


	@Override
	public Persona buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Collection<Persona> verTodos() {
		ArrayList<Persona> todas = new ArrayList<Persona>(); 
	    String consulta ="SELECT * FROM personas"; 
	    try {
	        if (this.conex == null ||this.conex.isClosed()) {
	            this.conex = ConexionBD.getConexion();
	        }
	        PreparedStatement ps = conex.prepareStatement(consulta);
	        ResultSet resultado = ps.executeQuery();
	        while (resultado.next()) {
	            Persona persona = new Persona(                
	                resultado.getLong("id"),           
	                resultado.getString("nombre"),
	                resultado.getString("email"));		            
	            todas.add(persona); 
	        }
	        conex.close();
	    } catch (SQLException e) {
	        System.out.println("Error al obtener todas las personas: " + e.getMessage());
	        e.printStackTrace();
	    
	    }
	    
	    return todas;
	}
	public long IdUsuarioAutenticado(String usuario) {
        long idPersona = -1;
        String consulta = "SELECT personas.id FROM personas INNER JOIN credenciales ON personas.id = idPersona WHERE usuario = ?";
        try (PreparedStatement ps = conex.prepareStatement(consulta)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idPersona = rs.getLong("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el id de: " + usuario + ": " + e.getMessage());
        }
        return idPersona;
    }

	public Persona buscarPorNombreUsuario(String nombreUsuario) {
        
        String query = "SELECT * FROM persona WHERE nombre_usuario = ?";
   
        Persona persona = new Persona();
        persona.setId(123);
        
        return persona;
    }

	@Override
	public boolean modificar(Persona elemento) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean eliminar(Persona elemento) {
		// TODO Auto-generated method stub
		return false;
	}


	



	
}