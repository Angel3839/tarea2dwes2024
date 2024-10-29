package modelo;
import java.time.LocalDateTime;
import java.util.Objects;


public class Mensaje {

	private Long id;
	private LocalDateTime fechahora;
	private String nombrecientifico;
	
	public Mensaje() {
		
	}
	
	public Mensaje(Long id, LocalDateTime fechahora, String nombrecientifico) {
		super();
		this.id = id;
		this.fechahora = fechahora;
		this.nombrecientifico = nombrecientifico;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getFechahora() {
		return fechahora;
	}
	public void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}
	public String getNombrecientifico() {
		return nombrecientifico;
	}
	public void setNombrecientifico(String nombrecientifico) {
		this.nombrecientifico = nombrecientifico;
	}
	@Override
	public int hashCode() {
		return Objects.hash(fechahora, id, nombrecientifico);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return Objects.equals(fechahora, other.fechahora) && Objects.equals(id, other.id)
				&& Objects.equals(nombrecientifico, other.nombrecientifico);
	}
	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", fechahora=" + fechahora + ", nombrecientifico=" + nombrecientifico
				+ ", getId()=" + getId() + ", getFechahora()=" + getFechahora() + ", getNombrecientifico()="
				+ getNombrecientifico() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	
}
