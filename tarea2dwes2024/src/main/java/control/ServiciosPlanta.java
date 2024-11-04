package control;

public class ServiciosPlanta {

	private ConexionBD con;
	private PlantaDAO plantaDAO;
	
	public ServiciosPlanta() {
		con=ConexionBD.getInstance();
		plantaDAO=(PlantaDAO)con.getPlantaDAO();
	}
	
	public int insertar(Planta e) {
		return plantaDAO.insertar(e);
	}
	
}
