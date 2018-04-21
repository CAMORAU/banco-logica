package co.edu.usbcali.banco.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.dao.ITipoDocumentoDAO;
import co.edu.usbcali.banco.modelo.TipoDocumento;

@Service
@Scope("singleton")
public class TipoDocumentoLogicaImpl implements ITipoDocumentoLogica {

	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	@Override
	public void grabar(TipoDocumento tipoDocumento) throws Exception {

	}

	@Override
	public void modificar(TipoDocumento tipoDocumento) throws Exception {

	}

	@Override
	public void borrar(TipoDocumento tipoDocumento) throws Exception {

	}

	@Override
	@Transactional(readOnly=true)
	public TipoDocumento consultarTipoDocumentoPorId(long tdocId) {
		return tipoDocumentoDAO.consultarTipoDocumentoPorId(tdocId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoDocumento> consultarTodos() {
		return tipoDocumentoDAO.consultarTodos();
	}

}
