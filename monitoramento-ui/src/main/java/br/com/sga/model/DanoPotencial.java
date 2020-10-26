package br.com.sga.model;

/**
 * @author sga
 *
 */
public enum DanoPotencial {

	HIGH("Alto"),
	MEDIUM("Médio"),
	LOW("Baixo");
	
	private String descricao;
	
	DanoPotencial(String descricao){
		this.descricao =descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
