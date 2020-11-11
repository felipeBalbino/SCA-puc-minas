package br.com.sga.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GrauRiscoEnum {
	BAIXO("Baixo"), MEDIO("Media"), TELEFONE("Alto");

	private String descricao;

	GrauRiscoEnum(String descricao) {
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

}
