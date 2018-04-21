package co.edu.usbcali.banco.logica;

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
class TestClienteLogica {

	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	BigDecimal clieId= new BigDecimal(154120);
	
	@Test
	@DisplayName("CrearCliente")
	void atest() throws Exception {
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		
		Cliente cliente = new Cliente();
		
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("calle 41");
		cliente.setEmail("notengo@gmail.com");
		cliente.setNombre("Bart Simpson");
		cliente.setTelefono("1234567");
		
		TipoDocumento tipoDocumento=tipoDocumentoLogica.consultarTipoDocumentoPorId(1L);
		assertNotNull(tipoDocumento,"El tipo documento no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteLogica.grabar(cliente);
	}
	
	@Test
	@DisplayName("ConsultarClientePorId")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	void btest() {
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		
		Cliente cliente =clienteLogica.consultarClientePorId(clieId);
		assertNotNull(cliente,"El cliente a consultar no existe");

	}
	
	@Test
	@DisplayName("ModificarCliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	void ctest() throws Exception{
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		
		Cliente cliente =clienteLogica.consultarClientePorId(clieId);
		assertNotNull(cliente,"El cliente a modificar no existe");
		
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("calle se fue");
		cliente.setEmail("notengo@gmail.com");
		cliente.setNombre("Bart Simpson 2");
		cliente.setTelefono("7412589");
		
		TipoDocumento tipoDocumento=tipoDocumentoLogica.consultarTipoDocumentoPorId(2L);
		assertNotNull(tipoDocumento,"El tipo documento no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteLogica.modificar(cliente);
	}
	
	@Test
	@DisplayName("BorrarCliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	void dtest() throws Exception{
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		
		Cliente cliente =clienteLogica.consultarClientePorId(clieId);
		assertNotNull(cliente,"El cliente a borrar no existe no existe");
		
		clienteLogica.borrar(cliente);
	}
	
	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	void etest() {
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		
		List<Cliente> losClientes = clienteLogica.consultarTodos();
		assertNotNull(losClientes,"la lista de clientes es nula");
		assertNotEquals(0, losClientes.size());
	}

}
