package model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the jugadores database table.
 * 
 */
@Entity
@Table(name="jugadores")
@NamedQuery(name="Jugadore.findAll", query="SELECT j FROM Jugadore j")
public class Jugadore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JUGADORES_IDJUGADOR_GENERATOR", sequenceName="SEQ_JUGADORES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JUGADORES_IDJUGADOR_GENERATOR")
	@Column(name="id_jugador")
	private long idJugador;

	private BigDecimal amarillas;

	private String apellido;

	private String edad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nac")
	private Date fechaNac;

	private byte[] foto;

	private BigDecimal goles;

	private BigDecimal invicto;

	private String nombre;

	private String numero;

	private BigDecimal rojas;

	private BigDecimal sancion;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="id_equipo")
	private Equipo equipo;

	//bi-directional many-to-one association to TipoSancion
	@ManyToOne
	@JoinColumn(name="id_tipo")
	private TipoSancion tipoSancion;

	public Jugadore() {
	}

	public long getIdJugador() {
		return this.idJugador;
	}

	public void setIdJugador(long idJugador) {
		this.idJugador = idJugador;
	}

	public BigDecimal getAmarillas() {
		return this.amarillas;
	}

	public void setAmarillas(BigDecimal amarillas) {
		this.amarillas = amarillas;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEdad() {
		return this.edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public BigDecimal getGoles() {
		return this.goles;
	}

	public void setGoles(BigDecimal goles) {
		this.goles = goles;
	}

	public BigDecimal getInvicto() {
		return this.invicto;
	}

	public void setInvicto(BigDecimal invicto) {
		this.invicto = invicto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public BigDecimal getRojas() {
		return this.rojas;
	}

	public void setRojas(BigDecimal rojas) {
		this.rojas = rojas;
	}

	public BigDecimal getSancion() {
		return this.sancion;
	}

	public void setSancion(BigDecimal sancion) {
		this.sancion = sancion;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public TipoSancion getTipoSancion() {
		return this.tipoSancion;
	}

	public void setTipoSancion(TipoSancion tipoSancion) {
		this.tipoSancion = tipoSancion;
	}

}