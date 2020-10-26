package br.com.sga.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusManutencaoEnum {

	ABERTA("Aberta"),
	FECHADA("Fechada");
	
	private String descricao;
	
	StatusManutencaoEnum(String descricao){
		this.descricao =descricao;
	}
	
	@JsonValue
	public String getDescricao() {
		return descricao;
	}
	
}
