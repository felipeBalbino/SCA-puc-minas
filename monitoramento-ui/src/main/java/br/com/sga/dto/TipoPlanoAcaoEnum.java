package br.com.sga.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPlanoAcaoEnum {
	EMAIL("E-mail"), SMS("SMS"), TELEFONE("Telefone");

	private String descricao;

	TipoPlanoAcaoEnum(String descricao) {
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

}
