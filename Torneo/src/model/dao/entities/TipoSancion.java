package model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tipo_sancion database table.
 * 
 */
@Entity
@Table(name="tipo_sancion")
@NamedQuery(name="TipoSancion.findAll", query="SELECT t FROM TipoSancion t")
public class TipoSancion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_SANCION_IDTIPO_GENERATOR", sequenceName="SEQ_TIPO_SAN", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_SANCION_IDTIPO_GENERATOR")
	@Column(name="id_tipo")
	private long idTipo;

	private String descripcion;

	private String nombre;

	@Column(name="num_susp")
	private BigDecimal numSusp;

	@Column(name="tiempo_sus")
	private String tiempoSus;

	//bi-directional many-to-one association to Jugadore
	@OneToMany(mappedBy="tipoSancion")
	private List<Jugadore> jugadores;

	public TipoSancion() {
	}

	public long getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
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

	public BigDecimal getNumSusp() {
		return this.numSusp;
	}

	public void setNumSusp(BigDecimal numSusp) {
		this.numSusp = numSusp;
	}

	public String getTiempoSus() {
		return this.tiempoSus;
	}

	public void setTiempoSus(String tiempoSus) {
		this.tiempoSus = tiempoSus;
	}

	public List<Jugadore> getJugadores() {
		return this.jugadores;
	}

	public void setJugadores(List<Jugadore> jugadores) {
		this.jugadores = jugadores;
	}

	public Jugadore addJugadore(Jugadore jugadore) {
		getJugadores().add(jugadore);
		jugadore.setTipoSancion(this);

		return jugadore;
	}

	public Jugadore removeJugadore(Jugadore jugadore) {
		getJugadores().remove(jugadore);
		jugadore.setTipoSancion(null);

		return jugadore;
	}

}