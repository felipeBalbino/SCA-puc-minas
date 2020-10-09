package br.com.sga.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoManutencaoEnum {

	CORRETIVA("C"),
	PROGRAMADA("P");
	
	private String descricao;
	
	TipoManutencaoEnum(String descricao){
		this.descricao =descricao;
	}
	
	@JsonValue
	public String getDescricao() {
		return descricao;
	}
	
}
