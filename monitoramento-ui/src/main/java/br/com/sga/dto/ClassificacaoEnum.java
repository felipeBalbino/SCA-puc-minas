package br.com.sga.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ClassificacaoEnum {

	A("A"),
	B("B"),
	C("C");
	
	private String descricao;
	
	ClassificacaoEnum(String descricao){
		this.descricao =descricao;
	}
	
	@JsonValue
	public String getDescricao() {
		return descricao;
	}
	
}
