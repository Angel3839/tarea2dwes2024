package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Mensaje;
import utils.ConexionBD;

public class MensajeDAO implements OperacionesCRUD<Mensaje> {
	Connection conex;


	public MensajeDAO(Connection conex) {
			this.conex = conex;
	}

	@Override
	public long insertar(Mensaje mensaje) {
		System.out.print(mensaje);
		int filas = 0;
		String consulta = "INSERT INTO mensajes (fechaHora, mensaje, idEjemplar, idPersona) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = conex.prepareStatement(consulta)) {
			ps.setTimestamp(1, Timestamp.valueOf(mensaje.getFechaHora()));
			ps.setString(2, mensaje.getMensaje()); 
			ps.setLong(3, mensaje.getIdEjemplar()); 
			ps.setLong(4, mensaje.getIdPersona()); 
			filas = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filas;
	}
	
	public ArrayList<Mensaje> verMensajesPorPersona(long idPersona) {
	    ArrayList<Mensaje> mensajesPersona = new ArrayList<>();
	    String consulta = "SELECT * FROM mensajes WHERE idpersona = ?";
	    try (PreparedStatement ps = conex.prepareStatement(consulta)) {
	        ps.setLong(1, idPersona);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Mensaje m = new Mensaje();
	                m.setId(rs.getLong("id"));
	                m.setFechaHora(rs.getTimestamp("fechahora").toLocalDateTime());
	                m.setMensaje(rs.getString("mensaje"));
	                m.setIdEjemplar(rs.getLong("idejemplar"));
	                m.setIdPersona(rs.getLong("idpersona"));
	                mensajesPersona.add(m);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener los mensajes: " + e.getMessage());
	    }
	    return mensajesPersona;
	}
	
	public ArrayList<Mensaje> verMensajesPorCodigoPlanta(String codigoPlanta) {
	    String consulta = "SELECT mensajes.id, fechahora, mensaje, mensajes.idejemplar, mensajes.idpersona " +
	           "FROM mensajes " +"INNER JOIN ejemplares ON mensajes.idejemplar = ejemplares.id " + "INNER JOIN plantas ON ejemplares.id_planta = plantas.codigo " +
	            	"WHERE plantas.codigo = ?";
	    ArrayList<Mensaje> mensajesPlanta = new ArrayList<>();
	    try (PreparedStatement ps = conex.prepareStatement(consulta)) {
	        ps.setString(1, codigoPlanta);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Mensaje m = new Mensaje();
	                m.setId(rs.getLong("id"));
	                m.setFechaHora(rs.getTimestamp("fechahora").toLocalDateTime());
	                m.setMensaje(rs.getString("mensaje"));
	                m.setIdEjemplar(rs.getLong("idejemplar"));
	                m.setIdPersona(rs.getLong("idpersona"));
	                mensajesPlanta.add(m);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al consultar los mensajes por tipo de planta: " + e.getMessage());
	    }
	    return mensajesPlanta;
	}
	
	public ArrayList<Mensaje> verMensajesPorFecha(LocalDateTime primeraFecha, LocalDateTime segundaFecha) {
	    String consulta = "SELECT mensajes.id, fechahora, mensaje, mensajes.idejemplar, mensajes.idpersona " +"FROM mensajes " +
	                      "WHERE fechahora BETWEEN ? AND ?";
	    ArrayList<Mensaje> mensajes = new ArrayList<>();
	    try (PreparedStatement ps = conex.prepareStatement(consulta)) {
	        ps.setTimestamp(1, Timestamp.valueOf(primeraFecha));
	        ps.setTimestamp(2, Timestamp.valueOf(segundaFecha));
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Mensaje m = new Mensaje();
	                m.setFechaHora(rs.getTimestamp("fechahora").toLocalDateTime());
	                m.setMensaje(rs.getString("mensaje"));
	                m.setIdEjemplar(rs.getLong("idejemplar"));
	                m.setIdPersona(rs.getLong("idpersona"));
	                mensajes.add(m);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al ver los mensajes: " + e.getMessage());
	    }
	    return mensajes;
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
	    } catch (SQLException e) {
	        System.out.println("Error al obtener todas las plantas: " + e.getMessage());
	        e.printStackTrace();
	    
	    }
	    
	    return todos;

	}
	
	public ArrayList<Mensaje> verMensajesPorEjemplar(long idEjemplar) {
	    String consulta = "SELECT id, fechahora, mensaje, idejemplar, idpersona " +
	                      "FROM mensajes " + "WHERE idejemplar = ? " +
	                        "ORDER BY fechahora";
	    ArrayList<Mensaje> mensajes = new ArrayList<>();
	    try (PreparedStatement ps = conex.prepareStatement(consulta)) {
	        ps.setLong(1, idEjemplar);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Mensaje m = new Mensaje();
	                m.setId(rs.getLong("id"));
	                m.setFechaHora(rs.getTimestamp("fechahora").toLocalDateTime());
	                m.setMensaje(rs.getString("mensaje"));
	                m.setIdEjemplar(rs.getLong("idejemplar"));
	                m.setIdPersona(rs.getLong("idpersona"));
	                mensajes.add(m);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener los mensajes del ejemplar: " + e.getMessage());
	    }
	    return mensajes;
	}





	

	
	
}