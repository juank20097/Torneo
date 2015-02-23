package model.dao.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import model.dao.entities.Equipo;
import model.dao.entities.Jugadore;
import model.dao.manager.ManagerTorneo;

@SessionScoped
@ManagedBean
public class JugadorBean {

private ManagerTorneo manager;
//atributos
private long idJugador;
private BigDecimal amarillas;
private String apellido;
private String edad;
private Date fechaNac;
private byte[] foto;
private BigDecimal goles;
private BigDecimal invicto;
private String nombre;
private String numero;
private BigDecimal rojas;
private BigDecimal sancion;
private Jugadore selectJug;
private long idEquipo;
//
private UploadedFile file;
private String message;

public JugadorBean(){
	manager= new ManagerTorneo();
}

public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public UploadedFile getFile() {
	return file;
}
public void setFile(UploadedFile file) {
	this.file = file;
}
public long getIdJugador() {
	return idJugador;
}
public void setIdJugador(long idJugador) {
	this.idJugador = idJugador;
}
public BigDecimal getAmarillas() {
	return amarillas;
}
public void setAmarillas(BigDecimal amarillas) {
	this.amarillas = amarillas;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getEdad() {
	return edad;
}
public void setEdad(String edad) {
	this.edad = edad;
}
public Date getFechaNac() {
	return fechaNac;
}
public void setFechaNac(Date fechaNac) {
	this.fechaNac = fechaNac;
}
public byte[] getFoto() {
	return foto;
}
public void setFoto(byte[] foto) {
	this.foto = foto;
}
public BigDecimal getGoles() {
	return goles;
}
public void setGoles(BigDecimal goles) {
	this.goles = goles;
}
public BigDecimal getInvicto() {
	return invicto;
}
public void setInvicto(BigDecimal invicto) {
	this.invicto = invicto;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
}
public BigDecimal getRojas() {
	return rojas;
}
public void setRojas(BigDecimal rojas) {
	this.rojas = rojas;
}
public BigDecimal getSancion() {
	return sancion;
}
public void setSancion(BigDecimal sancion) {
	this.sancion = sancion;
}
public Jugadore getSelectJug() {
	return selectJug;
}
public void setSelectJug(Jugadore selectJug) {
	this.selectJug = selectJug;
}
public long getIdEquipo() {
	return idEquipo;
}
public void setIdEquipo(long idEquipo) {
	this.idEquipo = idEquipo;
}

public List<Jugadore> getListJugador(){
	return manager.findAllJugadores();
}

//IMAGENES

//metodo para guardar imagenes en la base de datos
public void UploadImage(FileUploadEvent event){
	file= event.getFile();
	try {
		foto =IOUtils.toByteArray(file.getInputstream());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

//Metodo para recuperar las imagenes y mostrar
public StreamedContent getImage()
{
	FacesContext fc= FacesContext.getCurrentInstance();
	if (fc.getCurrentPhaseId()==PhaseId.RENDER_RESPONSE){
		return new DefaultStreamedContent();
	}
	
	
	String st = fc.getExternalContext().getRequestParameterMap().get("idJug");
    byte[] j = manager.findbyIdJugador(Long.valueOf(st)).getFoto();
    return new DefaultStreamedContent(new ByteArrayInputStream(j));
}

//accion para invocar el manager y crear jugador
	public String CrearJugador(){
		manager.crearJugador(idJugador, nombre, apellido, numero, edad, fechaNac, foto);
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Exito...!!!!",  "Ingreso Satisfactorio"));
		//reiniciamos datos (limpiamos el formulario)
		idJugador=0;
		nombre="";
		apellido="";
		numero="";
		edad="";
		fechaNac=null;
		foto=null;
		file=null;
		idEquipo=0;
		return "";
	}

	//accion para eliminar una jugador
		public String EliminarJugador(Jugadore Jugador){
			manager.eliminarJugador(Jugador.getIdJugador());
			return "";
		}
		
		//metodo para mostrar los equipos en jugadores
		public List<SelectItem> getListaEquipos(){
			List<SelectItem> listadoSI=new ArrayList<SelectItem>();
			List<Equipo> listadoEquipo=manager.findAllEquipo();
			
			for(Equipo t:listadoEquipo){
				SelectItem item=new SelectItem(t.getIdEquipo(),t.getNombre());
				listadoSI.add(item);
			}
			return listadoSI;
		}
		
		//metodo para asignar el equipo al jugador
		public String asignar(){
			manager.asignarEquipo(idEquipo);
			return "";
		}
		
		//accion para cargar los datos en el formulario
			public String cargarJugador(Jugadore t){
				idJugador=t.getIdJugador();
				nombre=t.getNombre();
				apellido=t.getApellido();
				numero=t.getNumero();
				edad=t.getEdad();
				fechaNac=t.getFechaNac();
				foto= t.getFoto();
				idEquipo=t.getEquipo().getIdEquipo();
				return "editarJugador";
			}
		//accion para llamar al manager
			public String actualizarJugador(){
				manager.actualizarJugador(idJugador, nombre, apellido, numero, edad, fechaNac, foto);
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage("Exito...!!!!",  "Actualizacion Satisfactorio"));
				//limpiamos los datos
				idEquipo=0;
				nombre="";
				apellido="";
				numero="";
				edad="";
				fechaNac=null;
				foto=null;
				idEquipo=0;
				return "equipo";
				
			}
			
}
