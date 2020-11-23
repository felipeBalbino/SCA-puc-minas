package br.com.sga.dto;

import java.io.Serializable;

public class PlanoAcaoDTO implements Serializable{



	private Long codigo;

	private Long codigoBarragem;

	private String grauRisco;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigoBarragem() {
		return codigoBarragem;
	}

	public void setCodigoBarragem(Long codigoBarragem) {
		this.codigoBarragem = codigoBarragem;
	}

	public String getGrauRisco() {
		return grauRisco;
	}

	public void setGrauRisco(String grauRisco) {
		this.grauRisco = grauRisco;
	}

	

}
