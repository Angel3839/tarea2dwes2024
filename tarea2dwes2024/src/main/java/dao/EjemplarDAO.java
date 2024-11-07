package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import modelo.Ejemplar;
import modelo.Planta;
import utils.ConexionBD;

	public class EjemplarDAO implements OperacionesCRUD<Ejemplar> {
		Connection conex;
		private PreparedStatement ps;
		private ResultSet rs;

		public EjemplarDAO(Connection conex) {
			
				this.conex = conex;
		}


		@Override
		public long insertar(Ejemplar ej) {
			try {
				ps = conex.prepareStatement("INSERT INTO ejemplares (id, nombre, id_planta) values (?,?,?)");
				ps.setLong(1, ej.getId());
				ps.setString(2, ej.getNombre());
				ps.setString(3, ej.getCodigoPlanta());
				return ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al insertar en plantas " + e.getMessage());
			}
			
			
			return 0;
		}


		@Override
		public Ejemplar buscarPorID(long id) {
			String consulta = "SELECT * FROM ejemplares WHERE id = ?";
		    Ejemplar ejemplar = null;
		    try {
		        PreparedStatement pstmt = conex.prepareStatement(consulta);
		        pstmt.setLong(1, id); 
		        ResultSet result = pstmt.executeQuery();
		        while(result.next()) {
		        	long idEjemplar = result.getLong("id");
		            String nombre = result.getString("nombre");
		            String codigoPlanta = result.getString("id_planta");
		            ejemplar = new Ejemplar(idEjemplar, nombre, codigoPlanta);
		        }
		    } catch (SQLException e) {
		        System.out.println("Se ha producido una SQLException: " + e.getMessage());
		        e.printStackTrace();
		    } catch (Exception e) {
		        System.out.println("Se ha producido una Exception: " + e.getMessage());
		        e.printStackTrace();
		    }
		    
			return ejemplar; 
		}


		@Override
		public Collection<Ejemplar> verTodos() {
			ArrayList<Ejemplar> todos = new ArrayList<Ejemplar>(); 
		    String consulta = "SELECT * FROM ejemplares"; 
		    try {
		        if (this.conex == null || this.conex.isClosed()) {
		            this.conex = ConexionBD.getConexion();
		        }
		        PreparedStatement ps = conex.prepareStatement(consulta);
		        ResultSet resultado = ps.executeQuery();
		        while (resultado.next()) {
		            Ejemplar ejemplar = new Ejemplar(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("id_planta"));		            
		            todos.add(ejemplar); 
		        }
		        conex.close();
		    } catch (SQLException e) {
		        System.out.println("Error al obtener todas las plantas: " + e.getMessage());
		        e.printStackTrace();
		    
		    }
		    
		    return todos;
		}


		@Override
		public boolean modificar(Ejemplar elemento) {
			// TODO Auto-generated method stub
			return false;
		}

	}