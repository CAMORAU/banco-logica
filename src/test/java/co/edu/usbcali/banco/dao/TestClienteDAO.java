package co.edu.usbcali.banco.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)//este es por que en un test no hace commit sino rollback, con esta instruccion si hace commit
class TestClienteDAO {

	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	BigDecimal clieId= new BigDecimal(154120);
	
	@Test
	@DisplayName("CrearCliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	void atest() {
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		
		Cliente cliente = new Cliente();
		
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("calle se fue");
		cliente.setEmail("notengo@gmail.com");
		cliente.setNombre("Bart Simpson");
		cliente.setTelefono("1234567");
		
		TipoDocumento tipoDocumento=tipoDocumentoDAO.consultarTipoDocumentoPorId(1L);
		assertNotNull(tipoDocumento,"El tipo documento no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteDAO.grabar(cliente);
	}
	
	@Test
	@DisplayName("ConsultarClientePorId")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	void btest() {
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		
		Cliente cliente =clienteDAO.consultarClientePorId(clieId);
		assertNotNull(cliente,"El cliente a consultar no existe");

	}
	
	@Test
	@DisplayName("ModificarCliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	void ctest() {
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		
		Cliente cliente =clienteDAO.consultarClientePorId(clieId);
		assertNotNull(cliente,"El cliente a modificar no existe");
		
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("calle se fue");
		cliente.setEmail("notengo@gmail.com");
		cliente.setNombre("Bart Simpson 2");
		cliente.setTelefono("7412589");
		
		TipoDocumento tipoDocumento=tipoDocumentoDAO.consultarTipoDocumentoPorId(2L);
		assertNotNull(tipoDocumento,"El tipo documento no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteDAO.modificar(cliente);
	}
	
	@Test
	@DisplayName("BorrarCliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	void dtest() {
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		
		Cliente cliente =clienteDAO.consultarClientePorId(clieId);
		assertNotNull(cliente,"El cliente a borrar no existe no existe");
		
		clienteDAO.borrar(cliente);
	}
	
	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	void etest() {
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		
		List<Cliente> losClientes = clienteDAO.consultarTodos();
		assertNotNull(losClientes,"la lista de clientes es nula");
		assertNotEquals(0, losClientes.size());
	}

}
