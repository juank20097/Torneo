package model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the partido database table.
 * 
 */
@Entity
@NamedQuery(name="Partido.findAll", query="SELECT p FROM Partido p")
public class Partido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PARTIDO_IDPARTIDO_GENERATOR", sequenceName="SEQ_PARTIDO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PARTIDO_IDPARTIDO_GENERATOR")
	@Column(name="id_partido")
	private long idPartido;

	private BigDecimal amarillas;

	private String empate;

	@Column(name="equi_gan")
	private String equiGan;

	@Column(name="equi_per")
	private String equiPer;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="gol_gan")
	private BigDecimal golGan;

	@Column(name="gol_per")
	private BigDecimal golPer;

	private BigDecimal goles;

	private String hora;

	@Column(name="num_fecha")
	private BigDecimal numFecha;

	private String partido;

	private BigDecimal rojas;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="id_equipo")
	private Equipo equipo;

	//bi-directional many-to-one association to Estadio
	@ManyToOne
	@JoinColumn(name="id_estadio")
	private Estadio estadio;

	//bi-directional many-to-one association to Posicione
	@OneToMany(mappedBy="partido")
	private List<Posicione> posiciones;

	public Partido() {
	}

	public long getIdPartido() {
		return this.idPartido;
	}

	public void setIdPartido(long idPartido) {
		this.idPartido = idPartido;
	}

	public BigDecimal getAmarillas() {
		return this.amarillas;
	}

	public void setAmarillas(BigDecimal amarillas) {
		this.amarillas = amarillas;
	}

	public String getEmpate() {
		return this.empate;
	}

	public void setEmpate(String empate) {
		this.empate = empate;
	}

	public String getEquiGan() {
		return this.equiGan;
	}

	public void setEquiGan(String equiGan) {
		this.equiGan = equiGan;
	}

	public String getEquiPer() {
		return this.equiPer;
	}

	public void setEquiPer(String equiPer) {
		this.equiPer = equiPer;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getGolGan() {
		return this.golGan;
	}

	public void setGolGan(BigDecimal golGan) {
		this.golGan = golGan;
	}

	public BigDecimal getGolPer() {
		return this.golPer;
	}

	public void setGolPer(BigDecimal golPer) {
		this.golPer = golPer;
	}

	public BigDecimal getGoles() {
		return this.goles;
	}

	public void setGoles(BigDecimal goles) {
		this.goles = goles;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public BigDecimal getNumFecha() {
		return this.numFecha;
	}

	public void setNumFecha(BigDecimal numFecha) {
		this.numFecha = numFecha;
	}

	public String getPartido() {
		return this.partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public BigDecimal getRojas() {
		return this.rojas;
	}

	public void setRojas(BigDecimal rojas) {
		this.rojas = rojas;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Estadio getEstadio() {
		return this.estadio;
	}

	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}

	public List<Posicione> getPosiciones() {
		return this.posiciones;
	}

	public void setPosiciones(List<Posicione> posiciones) {
		this.posiciones = posiciones;
	}

	public Posicione addPosicione(Posicione posicione) {
		getPosiciones().add(posicione);
		posicione.setPartido(this);

		return posicione;
	}

	public Posicione removePosicione(Posicione posicione) {
		getPosiciones().remove(posicione);
		posicione.setPartido(null);

		return posicione;
	}

}