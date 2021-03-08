package br.com.sga.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author fjbalbino
 *
 */

public class Manutencao {


	private Long codigo;
	
	private String descricao;

	@Enumerated(EnumType.ORDINAL)
	private TipoManutencaoEnum tipoManutencao;

	@Enumerated(EnumType.ORDINAL)
	private StatusManutencaoEnum statusManutencao;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataManutencao;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataProxManutencao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInclusao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInativacao;

	private Ativo ativo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoManutencaoEnum getTipoManutencao() {
		return tipoManutencao;
	}

	public void setTipoManutencao(TipoManutencaoEnum tipoManutencao) {
		this.tipoManutencao = tipoManutencao;
	}

	public StatusManutencaoEnum getStatusManutencao() {
		return statusManutencao;
	}

	public void setStatusManutencao(StatusManutencaoEnum statusManutencao) {
		this.statusManutencao = statusManutencao;
	}

	public Date getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public Date getDataProxManutencao() {
		return dataProxManutencao;
	}

	public void setDataProxManutencao(Date dataProxManutencao) {
		this.dataProxManutencao = dataProxManutencao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataInativacao() {
		return dataInativacao;
	}

	public void setDataInativacao(Date dataInativacao) {
		this.dataInativacao = dataInativacao;
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
		Manutencao other = (Manutencao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	

}
