package model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the posiciones database table.
 * 
 */
@Entity
@Table(name="posiciones")
@NamedQuery(name="Posicione.findAll", query="SELECT p FROM Posicione p")
public class Posicione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="POSICIONES_IDPOSICIONES_GENERATOR", sequenceName="SEQ_POSICIONES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POSICIONES_IDPOSICIONES_GENERATOR")
	@Column(name="id_posiciones")
	private long idPosiciones;

	private BigDecimal gol;

	private BigDecimal posicion;

	private BigDecimal puntos;

	//bi-directional many-to-one association to Partido
	@ManyToOne
	@JoinColumn(name="id_partido")
	private Partido partido;

	public Posicione() {
	}

	public long getIdPosiciones() {
		return this.idPosiciones;
	}

	public void setIdPosiciones(long idPosiciones) {
		this.idPosiciones = idPosiciones;
	}

	public BigDecimal getGol() {
		return this.gol;
	}

	public void setGol(BigDecimal gol) {
		this.gol = gol;
	}

	public BigDecimal getPosicion() {
		return this.posicion;
	}

	public void setPosicion(BigDecimal posicion) {
		this.posicion = posicion;
	}

	public BigDecimal getPuntos() {
		return this.puntos;
	}

	public void setPuntos(BigDecimal puntos) {
		this.puntos = puntos;
	}

	public Partido getPartido() {
		return this.partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

}