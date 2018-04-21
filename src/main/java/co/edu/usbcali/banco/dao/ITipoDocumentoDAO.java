package co.edu.usbcali.banco.dao;

import java.util.List;

import co.edu.usbcali.banco.modelo.TipoDocumento;

public interface ITipoDocumentoDAO {
	
public void grabar(TipoDocumento tipoDocumento);
	
	public void modificar(TipoDocumento tipoDocumento);
	
	public void borrar(TipoDocumento tipoDocumento);
	
	public TipoDocumento consultarTipoDocumentoPorId(long tdocId);
	
	public List<TipoDocumento> consultarTodos();

}
