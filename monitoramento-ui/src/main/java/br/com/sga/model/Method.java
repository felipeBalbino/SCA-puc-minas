package br.com.sga.model;

/**
 * @author sga
 *
 */
public enum Method {

	ETAPA_UNICA("Etapa única"),
	ALTEAMENTO_JUSANTE("Alteamento a jusante"),
	ALTEAMENTO_MONTANTE("Alteamento a montante"),
	ALTEAMENTO_POR_LINHA_CENTRO("Alteamento por linha de centro"),
	UNKNOWN("Unknown");
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	Method(String description){
		this.description =description;
	}
	

	
}
