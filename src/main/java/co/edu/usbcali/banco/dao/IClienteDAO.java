package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.Cliente;

public interface IClienteDAO {
	
	public void grabar(Cliente cliente);
	
	public void modificar(Cliente cliente);
	
	public void borrar(Cliente cliente);
	
	public Cliente consultarClientePorId(BigDecimal clieId);
	
	public List<Cliente> consultarTodos();

}
