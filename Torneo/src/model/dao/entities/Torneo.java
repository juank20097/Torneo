package model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the torneo database table.
 * 
 */
@Entity
@NamedQuery(name="Torneo.findAll", query="SELECT t FROM Torneo t")
public class Torneo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TORNEO_IDTORNEO_GENERATOR", sequenceName="SEQ_TORNEO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TORNEO_IDTORNEO_GENERATOR")
	@Column(name="id_torneo")
	private long idTorneo;

	private BigDecimal amarillas;

	private BigDecimal goles;

	private String nombre;

	private BigDecimal partidos;

	private BigDecimal rojas;

	private String tipo;

	//bi-directional many-to-one association to Equipo
	@OneToMany(mappedBy="torneo")
	private List<Equipo> equipos;

	public Torneo() {
	}

	public long getIdTorneo() {
		return this.idTorneo;
	}

	public void setIdTorneo(long idTorneo) {
		this.idTorneo = idTorneo;
	}

	public BigDecimal getAmarillas() {
		return this.amarillas;
	}

	public void setAmarillas(BigDecimal amarillas) {
		this.amarillas = amarillas;
	}

	public BigDecimal getGoles() {
		return this.goles;
	}

	public void setGoles(BigDecimal goles) {
		this.goles = goles;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPartidos() {
		return this.partidos;
	}

	public void setPartidos(BigDecimal partidos) {
		this.partidos = partidos;
	}

	public BigDecimal getRojas() {
		return this.rojas;
	}

	public void setRojas(BigDecimal rojas) {
		this.rojas = rojas;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Equipo addEquipo(Equipo equipo) {
		getEquipos().add(equipo);
		equipo.setTorneo(this);

		return equipo;
	}

	public Equipo removeEquipo(Equipo equipo) {
		getEquipos().remove(equipo);
		equipo.setTorneo(null);

		return equipo;
	}

}