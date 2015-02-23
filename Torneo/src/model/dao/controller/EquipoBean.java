package model.dao.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import model.dao.entities.Equipo;
import model.dao.entities.Torneo;
import model.dao.manager.ManagerTorneo;

@SessionScoped
@ManagedBean
public class EquipoBean {

private ManagerTorneo manager;
//atributos
private long idEquipo;
private BigDecimal amarillas;
private String descripcion;
private BigDecimal ganados;
private BigDecimal goles;
private String lugar;
private String nombre;
private BigDecimal perdidos;
private BigDecimal rojas;
private long idTorneo;

public EquipoBean(){
	manager= new ManagerTorneo();
}

public long getIdTorneo() {
	return idTorneo;
}
public void setIdTorneo(long idTorneo) {
	this.idTorneo = idTorneo;
}
public long getIdEquipo() {
	return idEquipo;
}
public void setIdEquipo(long idEquipo) {
	this.idEquipo = idEquipo;
}
public BigDecimal getAmarillas() {
	return amarillas;
}
public void setAmarillas(BigDecimal amarillas) {
	this.amarillas = amarillas;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public BigDecimal getGanados() {
	return ganados;
}
public void setGanados(BigDecimal ganados) {
	this.ganados = ganados;
}
public BigDecimal getGoles() {
	return goles;
}
public void setGoles(BigDecimal goles) {
	this.goles = goles;
}
public String getLugar() {
	return lugar;
}
public void setLugar(String lugar) {
	this.lugar = lugar;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public BigDecimal getPerdidos() {
	return perdidos;
}
public void setPerdidos(BigDecimal perdidos) {
	this.perdidos = perdidos;
}
public BigDecimal getRojas() {
	return rojas;
}
public void setRojas(BigDecimal rojas) {
	this.rojas = rojas;
}

public List<Equipo> getListEquipo(){
	return manager.findAllEquipo();
}

//accion para invocar el manager y crear equipo
	public String CrearEquipo(){
		manager.crearEquipo(idEquipo, nombre, lugar, descripcion);
		//reiniciamos datos (limpiamos el formulario)
		idEquipo=0;
		nombre="";
		lugar="";
		descripcion="";
		idTorneo=0;
		return "";
	}

	//accion para eliminar una equipo
		public String EliminarEquipo(Equipo equipo){
			manager.eliminarEquipo(equipo.getIdEquipo());
			return "";
		}
		
		//metodo para mostrar los torneos en equipo
		public List<SelectItem> getListaTorneo(){
			List<SelectItem> listadoSI=new ArrayList<SelectItem>();
			List<Torneo> listadoTorneo=manager.findAllTorneo();
			
			for(Torneo t:listadoTorneo){
				SelectItem item=new SelectItem(t.getIdTorneo(),t.getNombre());
				listadoSI.add(item);
			}
			return listadoSI;
		}
		
		//metodo para asignar el torneo al equipo
		public String asignar(){
			manager.asignarTorneo(idTorneo);
			return "";
		}
		
		//accion para cargar los datos en el formulario
			public String cargarEquipo(Equipo t){
				idEquipo=t.getIdEquipo();
				nombre= t.getNombre();
				lugar=t.getLugar();
				descripcion=t.getDescripcion();
				idTorneo=t.getTorneo().getIdTorneo();
				return "editarEquipo";
			}
		//accion para llamar al manager
			public String actualizarEquipo(){
				manager.actualizarEquipo(idEquipo, nombre, lugar, descripcion);
				//limpiamos los datos
				idEquipo=0;
				nombre="";
				lugar="";
				descripcion="";
				idTorneo=0;
				return "equipo";
				
			}
}
