package br.com.sga.binder;

import java.beans.PropertyEditorSupport;

import br.com.sga.client.BarragemClient;
import br.com.sga.client.CategoriaRiscoClient;
import br.com.sga.dto.Barragem;
import br.com.sga.dto.CategoriaRisco;

/**
 * @author sga
 *
 */
public class CategoriaRiscoPropertyEditor extends PropertyEditorSupport {

	CategoriaRiscoClient cliente;

	/**
	 * @param cliente
	 */
	public CategoriaRiscoPropertyEditor(CategoriaRiscoClient cliente) {
		this.cliente = cliente;
	}

	/**
	 *
	 */
	@Override
	public void setAsText(String text) {
		Long id = Long.parseLong(text);
		CategoriaRisco categoriaRisco = cliente.findById(id);
		super.setValue(categoriaRisco);
	}
}