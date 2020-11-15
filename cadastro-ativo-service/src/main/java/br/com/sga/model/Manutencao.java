package br.com.sga.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author fjbalbino
 *
 */
@Entity
public class Manutencao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_manutencao")
	private Long codigo;
	
	@Size(max=250)
	private String descricao;

	@Enumerated(EnumType.ORDINAL)
	private TipoManutencaoEnum tipoManutencao;

	@Enumerated(EnumType.ORDINAL)
	private StatusManutencaoEnum statusManutencao;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataManutencao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataProxManutencao;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@NotNull(message="Data de inclusão obrigatória")
	@JsonFormat
	  (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataInclusao;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@JsonFormat
	  (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataInativacao;

	@ManyToOne
	@JoinColumn(name = "codigo_ativo")
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
