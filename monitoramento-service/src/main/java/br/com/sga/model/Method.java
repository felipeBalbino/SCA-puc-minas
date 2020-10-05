package br.com.sga.model;

/**
 * @author sga
 *
 */
public enum Method{

	ETAPA_UNICA("Etapa única"),
	ALTEAMENTO_JUSANTE("Alteamento a jusante"),
	ALTEAMENTO_MONTANTE("Alteamento a montante"),
	ALTEAMENTO_POR_LINHA_CENTRO("Alteamento por linha de centro"),
	UNKNOWN("Unknown");
	
	private String description;
	
	Method(String description){
		this.description =description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
