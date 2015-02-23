package model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the equipos database table.
 * 
 */
@Entity
@Table(name="equipos")
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EQUIPOS_IDEQUIPO_GENERATOR", sequenceName="SEQ_EQUIPOS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EQUIPOS_IDEQUIPO_GENERATOR")
	@Column(name="id_equipo")
	private long idEquipo;

	private BigDecimal amarillas;

	private String descripcion;

	private BigDecimal ganados;

	private BigDecimal goles;

	private String lugar;

	private String nombre;

	private BigDecimal perdidos;

	private BigDecimal rojas;

	//bi-directional many-to-one association to Torneo
	@ManyToOne
	@JoinColumn(name="id_torneo")
	private Torneo torneo;

	//bi-directional many-to-one association to Jugadore
	@OneToMany(mappedBy="equipo")
	private List<Jugadore> jugadores;

	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="equipo")
	private List<Partido> partidos;

	public Equipo() {
	}

	public long getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public BigDecimal getAmarillas() {
		return this.amarillas;
	}

	public void setAmarillas(BigDecimal amarillas) {
		this.amarillas = amarillas;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getGanados() {
		return this.ganados;
	}

	public void setGanados(BigDecimal ganados) {
		this.ganados = ganados;
	}

	public BigDecimal getGoles() {
		return this.goles;
	}

	public void setGoles(BigDecimal goles) {
		this.goles = goles;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPerdidos() {
		return this.perdidos;
	}

	public void setPerdidos(BigDecimal perdidos) {
		this.perdidos = perdidos;
	}

	public BigDecimal getRojas() {
		return this.rojas;
	}

	public void setRojas(BigDecimal rojas) {
		this.rojas = rojas;
	}

	public Torneo getTorneo() {
		return this.torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	public List<Jugadore> getJugadores() {
		return this.jugadores;
	}

	public void setJugadores(List<Jugadore> jugadores) {
		this.jugadores = jugadores;
	}

	public Jugadore addJugadore(Jugadore jugadore) {
		getJugadores().add(jugadore);
		jugadore.setEquipo(this);

		return jugadore;
	}

	public Jugadore removeJugadore(Jugadore jugadore) {
		getJugadores().remove(jugadore);
		jugadore.setEquipo(null);

		return jugadore;
	}

	public List<Partido> getPartidos() {
		return this.partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

	public Partido addPartido(Partido partido) {
		getPartidos().add(partido);
		partido.setEquipo(this);

		return partido;
	}

	public Partido removePartido(Partido partido) {
		getPartidos().remove(partido);
		partido.setEquipo(null);

		return partido;
	}

}