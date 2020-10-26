package br.com.sga.model;

/**
 * @author sga
 *
 */
public enum Metodo{

	ETAPA_UNICA("Etapa única"),
	ALTEAMENTO_JUSANTE("Alteamento a jusante"),
	ALTEAMENTO_MONTANTE("Alteamento a montante"),
	ALTEAMENTO_POR_LINHA_CENTRO("Alteamento por linha de centro"),
	UNKNOWN("Unknown");
	
	private String descricao;
	
	Metodo(String descricao){
		this.descricao =descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
