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
	private static ConexionBD f;

	public static ConexionBD getInstance() {
		if (f == null)
			f = new ConexionBD();
		return f;
	}

	public static Connection getConexion() {
		Properties prop = new Properties();
		MysqlDataSource m = new MysqlDataSource();
		FileInputStream fis;
		try {
			fis = new FileInputStream("src/main/resources/db.properties");
			prop.load(fis);

			m.setUrl(prop.getProperty("url"));
			m.setUser(prop.getProperty("usuario"));
			m.setPassword(prop.getProperty("password"));
			con = m.getConnection();

		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error de SQLException" + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error de FileNotFoundException " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error de IOException " + e.getLocalizedMessage());
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

	public PlantaDAO getPlantaDAO() {
		return new PlantaDAO(con);
	}

	public EjemplarDAO getEjemplarDAO() {
		return new EjemplarDAO(con);
	}

	public PersonaDAO getPersonaDAO() {
		return new PersonaDAO(con);
	}

	public MensajeDAO getMensajeDAO() {
		return new MensajeDAO(con);
	}
	public CredencialesDAO getCredencialesDAO() {
		return new CredencialesDAO(con);
	}

	
}