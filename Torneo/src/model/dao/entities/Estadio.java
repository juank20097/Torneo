package model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estadio database table.
 * 
 */
@Entity
@NamedQuery(name="Estadio.findAll", query="SELECT e FROM Estadio e")
public class Estadio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTADIO_IDESTADIO_GENERATOR", sequenceName="SEQ_ESTADIO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTADIO_IDESTADIO_GENERATOR")
	@Column(name="id_estadio")
	private long idEstadio;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="estadio")
	private List<Partido> partidos;

	public Estadio() {
	}

	public long getIdEstadio() {
		return this.idEstadio;
	}

	public void setIdEstadio(long idEstadio) {
		this.idEstadio = idEstadio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Partido> getPartidos() {
		return this.partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

	public Partido addPartido(Partido partido) {
		getPartidos().add(partido);
		partido.setEstadio(this);

		return partido;
	}

	public Partido removePartido(Partido partido) {
		getPartidos().remove(partido);
		partido.setEstadio(null);

		return partido;
	}

}