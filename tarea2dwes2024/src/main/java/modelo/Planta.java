package modelo;

public class Planta {
	private String codigo;
	private String nombrecomun;
	private String nombrecientifico;
	
	public Planta() {
		
	}

	public Planta(String codigo, String nombrecomun, String nombrecientifico) {
		this.codigo = codigo;
		this.nombrecomun = nombrecomun;
		this.nombrecientifico = nombrecientifico;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombrecomun() {
		return nombrecomun;
	}

	public void setNombrecomun(String nombrecomun) {
		this.nombrecomun = nombrecomun;
	}

	public String getNombrecientifico() {
		return nombrecientifico;
	}

	public void setNombrecientifico(String nombrecientifico) {
		this.nombrecientifico = nombrecientifico;
	}

	
	@Override
	public String toString() {
		String ret = "";
		ret += "Codigo de planta: " + this.codigo;
		ret += "\nNombre científico: " + this.nombrecientifico;
		ret += "\nNombre común: " + this.nombrecomun;
		return ret;
	}
	
	
	
	
}