package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Planta;
import utils.ConexionBD;

	public class PlantaDAO implements OperacionesCRUD<Planta> {
			Connection conex;
			private PreparedStatement ps;
			private ResultSet rs;
	
	
			public PlantaDAO(Connection conex) {
					this.conex = conex;
			}
	
	
			@Override
			public long insertar(Planta p) {
				try {
			        ps = conex.prepareStatement("INSERT INTO plantas (codigo, nombrecomun, nombrecientifico) VALUES (?, ?, ?)");
			        ps.setString(1, p.getCodigo());
			        ps.setString(2, p.getNombrecomun());
			        ps.setString(3, p.getNombrecientifico());
			        return ps.executeUpdate();
			    } catch (SQLException e) {
			        System.out.println("Error al insertar en plantas: " + e.getMessage());
			    }
			    return 0;
			}
	
			
			public Planta buscarPorID(long id) {
			    String consulta = "SELECT * FROM plantas WHERE codigo = ?";
			    Planta planta = null;
			    try {
			        PreparedStatement pstmt = conex.prepareStatement(consulta);
			        pstmt.setLong(1, id); 
			        ResultSet result = pstmt.executeQuery();
			        
			        
			        while(result.next()) {
			            String codigo = result.getString("codigo");
			            String nombreComun = result.getString("nombrecomun");
			            String nombreCientifico = result.getString("nombrecientifico");
			            planta = new Planta(codigo, nombreComun, nombreCientifico);
			        }
			    } catch (SQLException e) {
			        System.out.println("Se ha producido una SQLException: " + e.getMessage());
			        e.printStackTrace();
			    } catch (Exception e) {
			        System.out.println("Se ha producido una Exception: " + e.getMessage());
			        e.printStackTrace();
			    }
				return planta; 
			}
	
	
			@Override
			public Collection<Planta> verTodos() {
				ArrayList<Planta> todas = new ArrayList<Planta>(); 
			    String consulta = "SELECT * FROM plantas"; 
			    try {
			        if (this.conex == null || this.conex.isClosed()) {
			            this.conex = ConexionBD.getConexion();
			        }
			        PreparedStatement ps = conex.prepareStatement(consulta);
			        ResultSet resultado = ps.executeQuery();
			        while (resultado.next()) {
			            Planta planta = new Planta(                
			                resultado.getString("codigo"),           
			                resultado.getString("nombrecomun"),      
			                resultado.getString("nombrecientifico"));
			            todas.add(planta); 
			        }
			        
			    } catch (SQLException e) {
			        System.out.println("Error al obtener todas las plantas: " + e.getMessage());
			        e.printStackTrace();
			    
			    }
			    return todas;
			}
			
			public boolean actualizarNombreComun(String codigo, String nombreComun) {
			    String consulta = "UPDATE plantas SET nombrecomun = ? WHERE codigo = ?";
			    try (Connection connection = ConexionBD.getConexion(); 
			         PreparedStatement ps = connection.prepareStatement(consulta)) {
			        ps.setString(1, nombreComun);
			        ps.setString(2, codigo);
			        int filas = ps.executeUpdate();
			        return filas > 0;
			    } catch (SQLException e) {
			        System.out.println("Error al modificar el nombre común: " + e.getMessage());
			        return false;
			    }
			}
			
			public boolean actualizarNombreCientifico(String codigo, String nombreCientifico) {
			    String consulta = "UPDATE plantas SET nombrecientifico = ? WHERE codigo = ?";
			    try (Connection connection = ConexionBD.getConexion(); 
			         PreparedStatement ps = connection.prepareStatement(consulta)) {
			        ps.setString(1, nombreCientifico);
			        ps.setString(2, codigo);
			        int filas = ps.executeUpdate();
			        return filas > 0; // 
			    } catch (SQLException e) {
			        System.out.println("Error al modificar el nombre común: " + e.getMessage());
			        return false;
			    }
			}
			
			public boolean codigoExistente(String codigo) {
				String consulta = "SELECT codigo FROM PLANTAS";
				ArrayList<String> cod = new ArrayList<String>();
				try {
					ps = conex.prepareStatement(consulta);
					rs = ps.executeQuery();
					while (rs.next()) {
						cod.add(rs.getString(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (cod.contains(codigo.toUpperCase())) {
					return true;
				} else {
					return false;
				}
	
			}		
			
	}

