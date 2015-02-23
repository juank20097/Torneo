package model.dao.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.dao.entities.Torneo;
import model.dao.manager.ManagerTorneo;

@SessionScoped
@ManagedBean
public class TorneoBean {
	
private ManagerTorneo manager;
//atributos
private long idTorneo;
private BigDecimal amarillas;
private BigDecimal goles;
private String nombre;
private BigDecimal partidos;
private BigDecimal rojas;
private String tipo;

public TorneoBean(){
	manager= new ManagerTorneo();
}

public long getIdTorneo() {
	return idTorneo;
}
public void setIdTorneo(long idTorneo) {
	this.idTorneo = idTorneo;
}
public BigDecimal getAmarillas() {
	return amarillas;
}
public void setAmarillas(BigDecimal amarillas) {
	this.amarillas = amarillas;
}
public BigDecimal getGoles() {
	return goles;
}
public void setGoles(BigDecimal goles) {
	this.goles = goles;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public BigDecimal getPartidos() {
	return partidos;
}
public void setPartidos(BigDecimal partidos) {
	this.partidos = partidos;
}
public BigDecimal getRojas() {
	return rojas;
}
public void setRojas(BigDecimal rojas) {
	this.rojas = rojas;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}

public List<Torneo> getListTorneo(){
	return manager.findAllTorneo();
}

//accion para invocar el manager y crear torneo
	public String CrearTorneo(){
		manager.crearTorneo(idTorneo, nombre, tipo);
		//reiniciamos datos (limpiamos el formulario)
		idTorneo=0;
		nombre="";
		tipo="";
		return "";
	}	
	
}
