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

class TestCliente {

	private final static Logger log = LoggerFactory.getLogger(TestCliente.class);
	
	static EntityManagerFactory entityManagerFactory = null;
	
	static EntityManager entityManager = null;
	
	private BigDecimal clieId = new BigDecimal(142021);
	
	@BeforeAll
	public static void iniciar() {
		log.info("Ejecuto el BeforeAll");
		entityManagerFactory = Persistence.createEntityManagerFactory("banco-logica");//esta en el persistence.xml
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Test
	@DisplayName("CreaCliente")
	void atest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNull(cliente, "El cliente ya existe");
		cliente = new Cliente();
		
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("calle se fue");
		cliente.setEmail("notengo@gmail.com");
		cliente.setNombre("Bart Simpson");
		cliente.setTelefono("1234567");
		
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 2L);
		assertNotNull(tipoDocumento, "El tipo de documento no eiste");
		cliente.setTipoDocumento(tipoDocumento);
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("ConsultarClientePorId")
	void btest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente no existe");
		
		log.info("Id:"+cliente.getClieId());
		log.info("Nombre:"+cliente.getNombre());
	}
	
	@Test
	@DisplayName("ModificarCliente")
	void ctest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente no existe");
		
		cliente.setActivo('N');
		cliente.setDireccion("calle se fue aca");
		cliente.setEmail("notengo@gmail.com");
		cliente.setNombre("Bart Simpson");
		cliente.setTelefono("1234567");
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarCliente")
	void dtest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente no existe");
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
	}

	@Test
	@DisplayName("ConsultarCliente")
	void etest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		
		String jpql = "SELECT cli FROM Cliente cli";
		
		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();
		
		losClientes.forEach(cliente->{
			log.info("ID:"+ cliente.getClieId());
			log.info("NOMBRE:"+ cliente.getNombre());
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
