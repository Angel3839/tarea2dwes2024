package modelo;

	public class Ejemplar {
		private long id;
		private String nombre;
		private String codigoPlanta;
		
		public Ejemplar() {
			
		}
		
		public Ejemplar(long id, String nombre, String codigoPlanta) {
			this.id = id;
			this.nombre = nombre;
			this.codigoPlanta = codigoPlanta;	
			
		}
		
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
		
		public String getNombre() {
			return nombre;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public String getCodigoPlanta() {
			return codigoPlanta;
		}
		
		public void setCodigoPlanta(String codigoPlanta) {
			this.codigoPlanta = codigoPlanta;
		}
		
		@Override
		public String toString() {
			String ret = "";
			ret += "Id de ejemplar: " + this.id;
			ret += "\nNombre de ejemplar: " + this.nombre;
			ret += "\nCodigo de planta: " + this.codigoPlanta;
			return ret;
		}
		
		
	}



