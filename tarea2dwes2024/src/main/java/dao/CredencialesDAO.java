package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import modelo.Credenciales;
import modelo.Planta;

	public class CredencialesDAO {
		Connection conex;
		private PreparedStatement ps;
		private ResultSet rs;
	
	
		public CredencialesDAO(Connection conex) {
				this.conex = conex;
		}
		
		public boolean autenticar (String usuario, String password) {
			        String consulta = "SELECT COUNT(*) FROM credenciales WHERE usuario = ? AND contrasena = ?";
			        try (PreparedStatement ps= conex.prepareStatement(consulta)) {
			            ps.setString(1, usuario);
			            ps.setString(2, password);
			            ResultSet rs = ps.executeQuery();
			            if (rs.next()) {
			                return true;
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
			        return false; 
		}
	
	}