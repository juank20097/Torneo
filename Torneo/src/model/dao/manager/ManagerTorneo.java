package model.dao.manager;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.dao.entities.Equipo;
import model.dao.entities.Jugadore;
import model.dao.entities.TipoUsuario;
import model.dao.entities.Torneo;
import model.dao.entities.Usuario;


public class ManagerTorneo {
	
	private ManagerDAO managerDAO;
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static Torneo t;
	private static Equipo e;
	private static TipoUsuario tu;
	
public ManagerTorneo() {
		managerDAO = new ManagerDAO();
		
		if(emf==null)
			emf=Persistence.createEntityManagerFactory("Torneo");
		if(em==null)
			em=emf.createEntityManager();
	}

	
	//TORNEO

		//Listar Todos los tiposUsr
		public List<Torneo> findAllTorneo(){
		 List<Torneo> listado;
		 Query q;
		 em.getTransaction().begin();
		 q=em.createQuery("SELECT u FROM Torneo u ORDER BY u.idTorneo");
		 listado= q.getResultList();
		 em.getTransaction().commit();
		 return listado;
		 
		}
		
		//metodo ingresar torneo
		 public void crearTorneo (Long idTorneo,String nombre, String tipo){
			 em.getTransaction().begin();
			 Torneo u= new Torneo();
			 u.setNombre(nombre);
			 u.setTipo(tipo);
			 em.persist(u);
			 em.getTransaction().commit();
			 
		 }
		 
		 //metodo para buscar un torneo por id
		 	public Torneo findbyIdTorneo(Long idTorneo){
			 em.getTransaction().begin();
			 Torneo t= em.find(Torneo.class, idTorneo);
			 em.getTransaction().commit();
			 return t;
		 }
		 	
		 
		 //metodo eliminar torneo
		 	public void eliminarTorneo(Long idTorneo){
		 		//buscamos el tipo a ser eliminado
		 		Torneo t=findbyIdTorneo(idTorneo);
		 		em.getTransaction().begin();
		 		em.remove(t);
		 		em.getTransaction().commit();
		 	}

	//EQUIPO

			//Listar Todos los EQUIPOS
			public List<Equipo> findAllEquipo(){
			 List<Equipo> listado;
			 Query q;
			 em.getTransaction().begin();
			 q=em.createQuery("SELECT u FROM Equipo u ORDER BY u.idEquipo");
			 listado= q.getResultList();
			 em.getTransaction().commit();
			 return listado;
			 
			}
			
			//metodo ingresar equipo
			 public void crearEquipo (Long idEquipo,String nombre, String lugar,String descripcion){
				 em.getTransaction().begin();
				 Equipo u= new Equipo();
				 u.setNombre(nombre);
				 u.setLugar(lugar);
				 u.setDescripcion(descripcion);
				 u.setTorneo(t);
				 em.persist(u);
				 em.getTransaction().commit();
				 
			 }
			 
			 //metodo para buscar un equipo por id
			 	public Equipo findbyIdEquipo(Long idEquipo){
				 em.getTransaction().begin();
				 Equipo t= em.find(Equipo.class, idEquipo);
				 em.getTransaction().commit();
				 return t;
			 }
			 	
			 
			 //metodo eliminar equipo
			 	public void eliminarEquipo(Long idEquipo){
			 		//buscamos el tipo a ser eliminado
			 		Equipo t=findbyIdEquipo(idEquipo);
			 		em.getTransaction().begin();
			 		em.remove(t);
			 		em.getTransaction().commit();
			 	}
			 	
				//metodo para aisgnar el torneo al equipo
			 	public Torneo asignarTorneo(Long idTorneo) {
			 		t=findbyIdTorneo(idTorneo);
			 		return t;
				}
			 
			 	//metodo para actualizar un equipo
			 	 public void actualizarEquipo(Long idEquipo,String nombre,String lugar,String descripcion){
					 //buscamos el objeto que debe ser actualizado:
					 Equipo u = findbyIdEquipo(idEquipo);
					 em.getTransaction().begin();
					 // no se actualiza la clave primaria, en este caso solo la descripcion
					 u.setNombre(nombre);
					 u.setLugar(lugar);
					 u.setDescripcion(descripcion);
					 u.setTorneo(t);
					 em.merge(u);
					 em.getTransaction().commit();
				 }

		//JUGADORES

					//Listar Todos los JUGADORES
					public List<Jugadore> findAllJugadores(){
					 List<Jugadore> listado;
					 Query q;
					 em.getTransaction().begin();
					 q=em.createQuery("SELECT u FROM Jugadore u ORDER BY u.idJugador");
					 listado= q.getResultList();
					 em.getTransaction().commit();
					 return listado;
					 
					}
					
					//metodo ingresar jugador
					 public void crearJugador (Long idJugador,String nombre, String apellido,String numero,String edad, Date fecha, byte[] foto){
						 em.getTransaction().begin();
						 Jugadore u= new Jugadore();
						 u.setNombre(nombre);
						 u.setApellido(apellido);
						 u.setNumero(numero);
						 u.setEdad(edad);
						 u.setFechaNac(fecha);
						 u.setFoto(foto);
						 u.setEquipo(e);
						 em.persist(u);
						 em.getTransaction().commit();
						 
					 }
					 
					 //metodo para buscar un jugador por id
					 	public Jugadore findbyIdJugador(Long idJugador){
						 em.getTransaction().begin();
						 Jugadore t= em.find(Jugadore.class, idJugador);
						 em.getTransaction().commit();
						 return t;
					 }
					 	
					 
					 //metodo eliminar jugador
					 	public void eliminarJugador(Long idJugador){
					 		//buscamos el tipo a ser eliminado
					 		Jugadore t=findbyIdJugador(idJugador);
					 		em.getTransaction().begin();
					 		em.remove(t);
					 		em.getTransaction().commit();
					 	}
					 	
					 	
						//metodo para aisgnar el equipo al jugador
					 	public Equipo asignarEquipo(Long idEquipo) {
					 		e=findbyIdEquipo(idEquipo);
					 		return e;
						}
					 
					 	//metodo para actualizar un jugador
					 	 public void actualizarJugador(Long idJugador,String nombre, String apellido,String numero,String edad, Date fecha,byte[] foto){
							 //buscamos el objeto que debe ser actualizado:
							 Jugadore u = findbyIdJugador(idJugador);
							 em.getTransaction().begin();
							 // no se actualiza la clave primaria, en este caso solo la descripcion
							 u.setNombre(nombre);
							 u.setApellido(apellido);
							 u.setEdad(edad);
							 u.setNumero(numero);
							 u.setFechaNac(fecha);
							 u.setFoto(foto);
							 u.setEquipo(e);
							 em.merge(u);
							 em.getTransaction().commit();
						 }

		//USUARIO

					 	//Listar Todos los Usuarios
					 	public List<Usuario> findAllUsuarios(){
					 	 List<Usuario> listado;
					 	 Query q;
					 	 em.getTransaction().begin();
					 	 q=em.createQuery("SELECT u FROM Usuario u ORDER BY u.idUsuario");
					 	 listado= q.getResultList();
					 	 em.getTransaction().commit();
					 	 return listado;
					 	 
					 	}
					 	
					 	//metodo ingresar Usuario
					 		 public void crearUsuario (Long idUsuario,String nombre, String apellido, String telefono, String correo,String nick,String pass){
					 			 em.getTransaction().begin();
					 			 Usuario u = new Usuario();
					 			 u.setNombre(nombre);
					 			 u.setApellido(apellido);
					 			 u.setTelefono(telefono);
					 			 u.setCorreo(correo);
					 			 u.setNick(nick);
					 			 u.setPass(pass);
					 			 u.setTipoUsuario(tu);
					 			 em.persist(u);
					 			 em.getTransaction().commit();
					 			 
					 		 }
					 		 
					 	//metodo para buscar un usuario por id
					 		 public Usuario findByIdUsuario(Long idUsuario){
					 			 em.getTransaction().begin();
					 			 Usuario u =em.find(Usuario.class, idUsuario);
					 			 em.getTransaction().commit();
					 			 return u;
					 		 }

					 	//metodo para actualizar un Usuario:
					 		 public void actualizarUsuario(Long idUsuario,String nombre, String apellido, String telefono, String correo,String nick,String pass){
					 			 //buscamos el objeto que debe ser actualizado:
					 			 Usuario u = findByIdUsuario(idUsuario);
					 			 em.getTransaction().begin();
					 			 // no se actualiza la clave primaria, en este caso solo la descripcion
					 			 u.setNombre(nombre);
					 			 u.setApellido(apellido);
					 			 u.setTelefono(telefono);
					 			 u.setCorreo(correo);
					 			 u.setNick(nick);
					 			 u.setPass(pass);
					 			 u.setTipoUsuario(tu);
					 			 em.merge(u);
					 			 em.getTransaction().commit();
					 		 }
					 		 

					 	//metodo para buscar por nombre
					 		 public Usuario findByNick(String nick){
					 				List<Usuario> listado;
					 				Usuario u=null;
					 				listado =findAllUsuarios();
					 				em.getTransaction().begin();
					 				for (Usuario us:listado){
					 					if (us.getNick().equals(nick)){
					 						u=us;
					 					}
					 				}
					 				em.getTransaction().commit();
					 				return u;
					 			}
					 	 
		 	
}
