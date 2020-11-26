package br.com.sga.dto;

import java.io.Serializable;

public class ComunicacaoDTO implements Serializable{



	private Long codigo;

	private Long codigoAtivo;

	private String grauRisco;
	
	private String tipoComunicacao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigoAtivo() {
		return codigoAtivo;
	}

	public void setCodigoAtivo(Long codigoAtivo) {
		this.codigoAtivo = codigoAtivo;
	}

	public String getGrauRisco() {
		return grauRisco;
	}

	public void setGrauRisco(String grauRisco) {
		this.grauRisco = grauRisco;
	}

	public String getTipoComunicacao() {
		return tipoComunicacao;
	}

	public void setTipoComunicacao(String tipoComunicacao) {
		this.tipoComunicacao = tipoComunicacao;
	}

	

}
