package br.com.sga.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GrauRiscoEnum {
	BAIXO("Baixo"), MEDIO("Media"), ALTO("Alto");

	private String descricao;

	GrauRiscoEnum(String descricao) {
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

}
