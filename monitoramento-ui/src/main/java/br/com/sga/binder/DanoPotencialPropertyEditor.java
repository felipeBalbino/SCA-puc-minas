package br.com.sga.binder;

import java.beans.PropertyEditorSupport;

import br.com.sga.client.BarragemClient;
import br.com.sga.client.CategoriaRiscoClient;
import br.com.sga.client.DanoPotencialClient;
import br.com.sga.dto.Barragem;
import br.com.sga.dto.CategoriaRisco;
import br.com.sga.dto.DanoPotencial;

/**
 * @author sga
 *
 */
public class DanoPotencialPropertyEditor extends PropertyEditorSupport {

	DanoPotencialClient cliente;

	/**
	 * @param cliente
	 */
	public DanoPotencialPropertyEditor(DanoPotencialClient cliente) {
		this.cliente = cliente;
	}

	/**
	 *
	 */
	@Override
	public void setAsText(String text) {
		Long id = Long.parseLong(text);
		DanoPotencial danoPotencial = cliente.findById(id);
		super.setValue(danoPotencial);
	}
}