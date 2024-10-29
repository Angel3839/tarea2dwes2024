package modelo;

public class Seguimiento {

		private long idPersona;
		private long idEjemplar;
		private long idMensaje;
		public Seguimiento() {
			
		}

		public Seguimiento(long idPersona, long idEjemplar, long idMensaje) {
			this.idPersona = idPersona;
			this.idEjemplar = idEjemplar;
			this.idMensaje = idMensaje;
		}

		public long getIdPersona() {
			return idPersona;
		}

		public void setIdPersona(long idPersona) {
			this.idPersona = idPersona;
		}

		public long getIdEjemplar() {
			return idEjemplar;
		}

		public void setIdEjemplar(long idEjemplar) {
			this.idEjemplar = idEjemplar;
		}

		public long getIdMensaje() {
			return idMensaje;
		}

		public void setIdMensaje(long idMensaje) {
			this.idMensaje = idMensaje;
		}
		
		public String toString() {
			String ret = "";
			ret += "Persona: " + this.idPersona;
			ret += "\nId de ejemplar: " + this.idEjemplar;
			ret += "\nId de mensaje: " + this.idMensaje;
			return ret;
		}
		
	}

