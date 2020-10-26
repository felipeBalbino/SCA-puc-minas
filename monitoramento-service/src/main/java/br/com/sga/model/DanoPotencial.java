package br.com.sga.model;

public enum DanoPotencial {

	HIGH("HIGH"),
	MEDIUM("MEDIUM"),
	LOW("LOW");
	
	private String descricao;
	
	DanoPotencial(String descricao){
		this.descricao =descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
