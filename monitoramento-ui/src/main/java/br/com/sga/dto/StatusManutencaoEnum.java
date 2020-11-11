package br.com.sga.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusManutencaoEnum {

	ABERTA("A"),
	FECHADA("F");
	
	private String descricao;
	
	StatusManutencaoEnum(String descricao){
		this.descricao =descricao;
	}
	
	@JsonValue
	public String getDescricao() {
		return descricao;
	}
	
}
