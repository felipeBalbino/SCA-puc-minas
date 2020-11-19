package br.com.sga.binder;

import java.beans.PropertyEditorSupport;

import br.com.sga.client.BarragemClient;
import br.com.sga.client.PlanoAcaoClient;
import br.com.sga.dto.Barragem;
import br.com.sga.dto.PlanoAcao;

/**
 * @author sga
 *
 */
public class PlanoAcaoPropertyEditor extends PropertyEditorSupport {

	PlanoAcaoClient cliente;

	/**
	 * @param cliente
	 */
	public PlanoAcaoPropertyEditor(PlanoAcaoClient cliente) {
		this.cliente = cliente;
	}

	/**
	 *
	 */
	@Override
	public void setAsText(String text) {
		Long id = Long.parseLong(text);
		PlanoAcao planoAcao = cliente.findById(id);
		super.setValue(planoAcao);
	}
}