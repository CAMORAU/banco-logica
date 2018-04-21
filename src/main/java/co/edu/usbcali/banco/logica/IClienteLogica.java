package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.Cliente;

public interface IClienteLogica {
	
	public void grabar(Cliente cliente) throws Exception;
	
	public void modificar(Cliente cliente) throws Exception;
	
	public void borrar(Cliente cliente) throws Exception;
	
	public Cliente consultarClientePorId(BigDecimal clieId);
	
	public List<Cliente> consultarTodos();

}
