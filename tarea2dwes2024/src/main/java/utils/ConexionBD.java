package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

import dao.CredencialesDAO;
import dao.EjemplarDAO;
import dao.MensajeDAO;
import dao.PersonaDAO;
import dao.PlantaDAO;


public class ConexionBD {
	private static Connection con;

	public static Connection getConexion() {
		try {
			if (con == null || con.isClosed()) {
				Properties propiedades = new Properties();
				MysqlDataSource m = new MysqlDataSource();
				FileInputStream fis;
				fis = new FileInputStream("src/resources/db.properties");
				propiedades.load(fis);
				m.setUrl(propiedades.getProperty("url"));
				m.setPassword(propiedades.getProperty("password"));
				m.setUser(propiedades.getProperty("usuario"));
				fis.close();
				con = m.getConnection();
			}
			return con;
		} catch (FileNotFoundException e) {
			System.out.println("Error al acceder al fichero properties " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer las propiedades del fichero properties" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	public static void cerrarConexion() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
