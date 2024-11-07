package modelo;

import java.time.LocalDateTime;

public class Mensaje {
	private long id;
	private LocalDateTime fechaHora;
	private String mensaje;
	
	private long idEjemplar;
	private long idPersona;
	
	public Mensaje() {
		
	}

	public Mensaje(long id, LocalDateTime fechaHora, String mensaje, long idEjemplar, long idPersona) {
		this.id = id;
		this.fechaHora = fechaHora;
		this.mensaje = mensaje;
		this.idEjemplar = idEjemplar;
		this.idPersona = idPersona;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	public long getIdEjemplar() {
		return idEjemplar;
	}

	public void setIdEjemplar(long idEjemplar) {
		this.idEjemplar = idEjemplar;
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
		ret += "Id de mensaje: " + this.id;
		ret += "\nFecha y hora: " + this.fechaHora;
		ret += "\nMensaje: " + this.mensaje;
		ret += "\nEjemplar: " + this.idEjemplar;
		ret += "\nPersona: " + this.idPersona;
		return ret;
	}
	
	
}