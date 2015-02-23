package model.dao.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.dao.manager.ManagerTorneo;
import model.dao.entities.*;

@SessionScoped
@ManagedBean
public class UsuarioBean {
	private ManagerTorneo manager;
	private long idUsuario;
	private String nombre;
	private String apellido;
	private String telefono;
	private String correo;
	private String nick;
	private String pass;
	static String nic;
	static String pas;
	private long idTipo;
	
	public UsuarioBean() {
		manager = new ManagerTorneo();
	}
	
	public long getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public static String getNic() {
		return nic;
	}
	public static void setNic(String nic) {
		UsuarioBean.nic = nic;
	}
	public static String getPas() {
		return pas;
	}
	public static void setPas(String pas) {
		UsuarioBean.pas = pas;
	}
	
	public List<Usuario> getListUsuarios(){
		return manager.findAllUsuarios();
	}
	
	//accion para invocar el manager y crear Usuarios
	public String actionCrearUsuario(){
		manager.crearUsuario(idUsuario, nombre, apellido, telefono, correo, nick, pass);
		//reiniciamos datos (limpiamos el formulario)
		idUsuario=0;
		nombre="";
		apellido="";
		telefono="";
		correo="";
		nick="";
		pas="";
		idTipo=0;
		return "";
	}
	
	//cargar usuario
	public String cargarUsuario(Usuario u){
		idUsuario= u.getIdUsuario();
		nombre=u.getNombre();
		apellido=u.getApellido();
		telefono=u.getTelefono();
		correo=u.getCorreo();
		nick=u.getNick();
		pass=u.getPass();
		idTipo=u.getTipoUsuario().getIdTipo();
		//navegamos a la pagina de edicion
		return "edicionUsuario";
	}
		
	//accion para llamar al manager
	public String actualizarUsuario(){
		manager.actualizarUsuario(idUsuario, nombre, apellido, telefono, correo, nick, pass);
		//limpiamos los datos
		idUsuario=0;
		nombre="";
		apellido="";
		telefono="";
		correo="";
		nick="";
		pas="";
		idTipo=0;
		return "usuario";
		
	}
		
		//cerrar sesion
		public String cerrar(){
			nic="";
			pas="";
			return "login";
		}
		
	//Login y redireccionamiento
		public String acceso(){
			String dir="";
			Usuario u = null; 
			u=manager.findByNick(nick);
			if (u==null){
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage("Error...!!!!",  "Nick o Contraseña Erroneas"));
		        dir="";
			} else{
			if(u.getPass().equals(pass)){
				String rol = u.getTipoUsuario().getNombre();
				if(rol.equals("Administrador")){
					dir="torneo";
				}else if(rol.equals("Supervisor")){
					dir="jugador";
				}
			}
			else{
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage("Error...!!!!",  "Nick o Contraseña Erroneas"));
		        dir="";
			}
			if (dir.length()>1){
				nic=u.getNick();
				pas=u.getPass();
			}
			}
			
			return dir;
		}
		
}
