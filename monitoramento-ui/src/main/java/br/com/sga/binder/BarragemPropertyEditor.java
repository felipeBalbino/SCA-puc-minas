package br.com.sga.binder;

import java.beans.PropertyEditorSupport;

import br.com.sga.client.BarragemClient;
import br.com.sga.dto.Barragem;

/**
 * @author sga
 *
 */
public class BarragemPropertyEditor extends PropertyEditorSupport {

	BarragemClient cliente;

	/**
	 * @param cliente
	 */
	public BarragemPropertyEditor(BarragemClient cliente) {
		this.cliente = cliente;
	}

	/**
	 *
	 */
	@Override
	public void setAsText(String text) {
		Long id = Long.parseLong(text);
		Barragem barragem = cliente.findById(id);
		super.setValue(barragem);
	}
}