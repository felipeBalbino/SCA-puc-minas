package br.com.sga.binder;

import java.beans.PropertyEditorSupport;

import br.com.sga.client.DamClient;
import br.com.sga.model.Dam;

/**
 * @author sga
 *
 */
public class DamPropertyEditor extends PropertyEditorSupport {

	DamClient cliente;

	/**
	 * @param cliente
	 */
	public DamPropertyEditor(DamClient cliente) {
		this.cliente = cliente;
	}

	/**
	 *
	 */
	@Override
	public void setAsText(String text) {
		Long id = Long.parseLong(text);
		Dam dam = cliente.findById(id);
		super.setValue(dam);
	}
}