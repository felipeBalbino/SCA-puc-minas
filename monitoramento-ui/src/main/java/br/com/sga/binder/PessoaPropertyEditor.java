package br.com.sga.binder;

import java.beans.PropertyEditorSupport;

import br.com.sga.client.PessoaClient;
import br.com.sga.dto.Pessoa;

/**
 * @author sga
 *
 */
public class PessoaPropertyEditor extends PropertyEditorSupport {

	PessoaClient cliente;

	/**
	 * @param cliente
	 */
	public PessoaPropertyEditor(PessoaClient cliente) {
		this.cliente = cliente;
	}

	/**
	 *
	 */
	@Override
	public void setAsText(String text) {
		Long id = Long.parseLong(text);
		Pessoa pessoa = cliente.findById(id);
		super.setValue(pessoa);
	}
}