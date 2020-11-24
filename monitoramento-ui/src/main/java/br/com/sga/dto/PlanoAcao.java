package br.com.sga.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlanoAcao {

	private Long codigo;

	private Set<Pessoa> pessoas;

	private Long codigoAtivo;

	private String descricao;

	@Enumerated(EnumType.ORDINAL)
	private GrauRiscoEnum grauRisco;

	private String mensagemAlerta;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInclusao;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInativacao;
	
	@JsonIgnore
	private Ativo ativo;

	public PlanoAcao() {
		super();
	}

	public PlanoAcao(Long codigo) {
		super();
		this.codigo = codigo;
	}

	public Set<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Set<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Long getCodigoAtivo() {
		return codigoAtivo;
	}

	public void setCodigoAtivo(Long codigoAtivo) {
		this.codigoAtivo = codigoAtivo;
	}

	public Date getDataInativacao() {
		return dataInativacao;
	}

	public void setDataInativacao(Date dataInativacao) {
		this.dataInativacao = dataInativacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public GrauRiscoEnum getGrauRisco() {
		return grauRisco;
	}

	public void setGrauRisco(GrauRiscoEnum grauRisco) {
		this.grauRisco = grauRisco;
	}

	public String getMensagemAlerta() {
		return mensagemAlerta;
	}

	public void setMensagemAlerta(String mensagemAlerta) {
		this.mensagemAlerta = mensagemAlerta;
	}

	
	
	
	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoAcao other = (PlanoAcao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
