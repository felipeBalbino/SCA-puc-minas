package br.com.sga.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoManutencaoEnum {

	CORRETIVA("CO"),
	PROGRAMADA("PG"),
	PREVENTIVA("PV");
	
	private String descricao;
	
	TipoManutencaoEnum(String descricao){
		this.descricao =descricao;
	}
	
	@JsonValue
	public String getDescricao() {
		return descricao;
	}
	
}
