package br.com.sga.model;

/**
 * @author sga
 *
 */
public enum PotentialDamage {

	HIGH("HIGH"),
	MEDIUM("MEDIUM"),
	LOW("LOW");
	
	private String description;
	
	PotentialDamage(String description){
		this.description =description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
