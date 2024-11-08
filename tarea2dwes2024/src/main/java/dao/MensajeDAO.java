package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Persona;
import modelo.Mensaje;
import utils.ConexionBD;

	public class MensajeDAO implements OperacionesCRUD<Mensaje> {
		Connection conex;
		private PreparedStatement ps;
		private ResultSet rs;
	
	
		public MensajeDAO(Connection conex) {
				this.conex = conex;
		}
	
	
		public long insertar(Mensaje mensaje) {
			int filas = 0;
			String consulta = "INSERT INTO mensajes (fechaHora, mensaje, idEjemplar, idPersona) VALUES (?, ?, ?, ?)";
			try (PreparedStatement statement = conex.prepareStatement(consulta)) {
				statement.setTimestamp(1, Timestamp.valueOf(mensaje.getFechaHora()));
				statement.setString(2, mensaje.getMensaje()); 
				statement.setLong(3, mensaje.getIdEjemplar()); 
				statement.setLong(4, mensaje.getIdPersona()); 
				filas = statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return filas;
		}
	
	
		@Override
		public Mensaje buscarPorID(long idEjemplar) {
		    Mensaje mensaje = null;  
		    String consulta = "SELECT mensajes.id, mensajes.fechahora, mensajes.mensaje, " +
		            "personas.id, personas.nombre, personas.email " +
		            "FROM mensajes " +
		            "INNER JOIN personas ON mensajes.idpersona = personas.id " +
		            "WHERE mensajes.idejemplar = ? " +
		            "ORDER BY mensajes.fechahora;";
		    try(PreparedStatement ps = conex.prepareStatement(consulta)) {
		        ps.setLong(1, idEjemplar);
		        rs = ps.executeQuery();
		        if (rs.next()) { 
		            Persona persona = new Persona(
		                rs.getLong("id"),
		                rs.getString("nombre"),
		                rs.getString("email"));
		            mensaje = new Mensaje(
		                rs.getLong("id"),
		                rs.getTimestamp("fechahora").toLocalDateTime(),
		                rs.getString("mensaje"),
		                idEjemplar,
		                rs.getLong("idpersona")
		            );
		        }
		    } catch (SQLException e) {
		        System.out.println("Error al obtener los mensajes del ejemplar: " + e.getMessage());
		        e.printStackTrace();
		    }
		    return mensaje; 
		}

	
	
		@Override
		public Collection<Mensaje> verTodos() {
			ArrayList<Mensaje> todos = new ArrayList<Mensaje>(); 
		    String consulta = "SELECT * FROM mensajes"; 
		    try {
		        if (this.conex == null || this.conex.isClosed()) {
		            this.conex = ConexionBD.getConexion();
		        }
		        PreparedStatement ps = conex.prepareStatement(consulta);
		        ResultSet resultado = ps.executeQuery();
		        while (resultado.next()) {
		            Mensaje mensaje = new Mensaje(                
		                resultado.getInt("id"),                     
		                resultado.getTimestamp("fechahora").toLocalDateTime(),      
		                resultado.getString("mensaje"),             
		                resultado.getInt("idejemplar"),            
		                resultado.getInt("idpersona")               
		            );
		            todos.add(mensaje); 
		        }
		        conex.close();
		    } catch (SQLException e) {
		        System.out.println("Error al obtener todas las plantas: " + e.getMessage());
		        e.printStackTrace();
		    
		    }
		    
		    return todos;
	
		}
	
	
		@Override
		public boolean modificar(Mensaje elemento) {
			// TODO Auto-generated method stub
			return false;
		}
	
	
		@Override
		public boolean eliminar(Mensaje elemento) {
			// TODO Auto-generated method stub
			return false;
		}
		
		
	}