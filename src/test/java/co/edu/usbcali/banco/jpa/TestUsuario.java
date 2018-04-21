package co.edu.usbcali.banco.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;

class TestUsuario {

	private final static Logger log = LoggerFactory.getLogger(TestUsuario.class);
	
	static EntityManagerFactory entityManagerFactory = null;
	
	static EntityManager entityManager = null;
	
	private String usuUsuario = "user132323";
	
	@BeforeAll
	public static void iniciar() {
		log.info("Ejecuto el BeforeAll");
		entityManagerFactory = Persistence.createEntityManagerFactory("banco-logica");//esta en el persistence.xml
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Test
	@DisplayName("CreaUsuario")
	void atest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNull(usuario, "El usuario ya existe");
		usuario = new Usuario();
		
		usuario.setActivo('S');
		usuario.setClave("1234");
		usuario.setIdentificacion(new BigDecimal(1234));
		usuario.setNombre("Bart Simpson");
		
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 1L);
		assertNotNull(tipoUsuario, "El tipo de usuario no eiste");
		usuario.setTipoUsuario(tipoUsuario);
		
		usuario.setUsuUsuario(usuUsuario);
		
		
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("ConsultarUsuarioPorUsuario")
	void btest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "El usuario no existe");
		
		log.info("Usuario:"+usuario.getUsuUsuario());
		log.info("Nombre:"+usuario.getNombre());
	}
	
	@Test
	@DisplayName("ModificarUsuario")
	void ctest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "El usuario no existe");
		
		usuario.setActivo('N');
		usuario.setClave("12345");
		usuario.setNombre("Bart Simpson1");
		
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarUsuario")
	void dtest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Usuario Usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(Usuario, "El usuario no existe");
		
		entityManager.getTransaction().begin();
		entityManager.remove(Usuario);
		entityManager.getTransaction().commit();
	}

	@Test
	@DisplayName("ConsultarUsuarios")
	void etest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		
		String jpql = "SELECT usu FROM Usuario usu";
		
		List<Usuario> losUsuarios = entityManager.createQuery(jpql).getResultList();
		
		losUsuarios.forEach(usuario->{
			log.info("Usuario:"+ usuario.getUsuUsuario());
			log.info("NOMBRE:"+ usuario.getNombre());
		});
		
		/*for (Cliente cliente : losClientes) {
			log.info("ID:"+ cliente.getClieId());
			log.info("NOMBRE:"+ cliente.getNombre());
		}*/
	}
	
	@BeforeEach
	public void antes() {
		log.info("Ejecuto el BeforeEach");
	}
	
	@AfterEach
	public void despues() {
		log.info("Ejecuto el AfterEach");
	}
	
	@AfterAll
	public static void finalizar() {
		log.info("Ejecuto el AfterAll");
		entityManager.close();
		entityManagerFactory.close();
	}
}
