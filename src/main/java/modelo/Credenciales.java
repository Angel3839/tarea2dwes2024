package modelo;

	public class Credenciales {
		private long id;
		private String usuario;
		private String password;
		private long idPersona;
		
		public Credenciales() {
			
		}
		
		public Credenciales(long id, String usuario, String password, long idPersona) {
			this.id = id;
			this.usuario = usuario;
			this.password = password;
			this.idPersona = idPersona;
		}
	
		public long getId() {
			return id;
		}
	
		public void setId(long id) {
			this.id = id;
		}
	
		public String getUsuario() {
			return usuario;
		}
	
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
	
		public String getPassword() {
			return password;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}
		
		
		
		public long getIdPersona() {
			return idPersona;
		}
	
		public void setIdPersona(long idPersona) {
			this.idPersona = idPersona;
		}
	
		@Override
		public String toString() {
			String ret = "";
			ret += "Id de credencial: " + this.id;
			ret += "\nUsuario: " + this.usuario;
			ret += "\nPassword: " + this.password;
			ret += "\nId de persona: " + this.idPersona;
			return ret;
		}
		
		
	}
	
	
	