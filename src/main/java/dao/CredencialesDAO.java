package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

	public class CredencialesDAO {
		Connection conex;
		private PreparedStatement ps;
		private ResultSet rs;
	
	
		public CredencialesDAO(Connection conex) {
				this.conex = conex;
		}
		
		public boolean autenticar(String usuario, String password) {
	        String consulta = "SELECT COUNT(*) FROM credenciales WHERE usuario = ? AND password = ?";
	        try (PreparedStatement ps = conex.prepareStatement(consulta)) {
	            ps.setString(1, usuario);
	            ps.setString(2, password);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    int total = rs.getInt(1);
	                    return total > 0;
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al autenticar usuario: " + e.getMessage());
	        }
	        return false;
	    }
		
		public boolean usuarioExistente(String usuario) {
			String consulta = "SELECT usuario FROM CREDENCIALES";
			ArrayList<String> usuariosExistentes = new ArrayList<String>();
			try {
				ps = conex.prepareStatement(consulta);
				rs = ps.executeQuery();
				while (rs.next()) {
					usuariosExistentes.add(rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (consulta.contains(usuario)) {
				return true;
			} else {
				return false;
			}
	
		}
		
		public int insertar(String usuario, String contraseña, Long idPersona) {
			int filas = 0;
			String insertarCredenciales = "INSERT INTO credenciales (usuario, password, idPersona) VALUES (?, ?, ?)";
			try (PreparedStatement ps = conex.prepareStatement(insertarCredenciales,
					PreparedStatement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, usuario);
				ps.setString(2, contraseña);
				ps.setLong(3, idPersona);
				filas = ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al insertar las credenciales.");
			}
			return filas;
	
		}
		
	}




	
	
	