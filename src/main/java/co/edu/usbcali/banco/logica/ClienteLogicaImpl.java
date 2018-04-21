package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.dao.IClienteDAO;
import co.edu.usbcali.banco.dao.ITipoDocumentoDAO;
import co.edu.usbcali.banco.modelo.Cliente;

@Service
@Scope("singleton")
public class ClienteLogicaImpl implements IClienteLogica {
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	@Autowired
	private Validator validator;
	
	public void validarClientes(Cliente cliente) throws Exception {
	    try {
	        Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<Cliente> constraintViolation : constraintViolations) {
	                strMessage.append(constraintViolation.getPropertyPath()
	                                                     .toString());
	                strMessage.append(" - ");
	                strMessage.append(constraintViolation.getMessage());
	                strMessage.append(". \n");
	            }

	            throw new Exception(strMessage.toString());
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)//para que haga commit
	public void grabar(Cliente cliente) throws Exception {
		
		if (cliente == null) {
			throw new Exception("El cliente es nulo");
		}
		
		validarClientes(cliente);
		
		clienteDAO.grabar(cliente);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)//para que haga commit
	public void modificar(Cliente cliente) throws Exception {

		if (cliente == null) {
			throw new Exception("El cliente es nulo");
		}
		
		validarClientes(cliente);
		
		clienteDAO.modificar(cliente);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)//para que haga commit
	public void borrar(Cliente cliente) throws Exception {

		if (cliente == null || cliente.getClieId() == null) {
			throw new Exception("El cliente es nulo o no tiene Id");
		}
		
		Cliente entity = clienteDAO.consultarClientePorId(cliente.getClieId());
		if (entity == null) {
			throw new Exception("El cliente no existe");
		}
		clienteDAO.borrar(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente consultarClientePorId(BigDecimal clieId) {
		return clienteDAO.consultarClientePorId(clieId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cliente> consultarTodos() {
		return clienteDAO.consultarTodos();
	}

}
