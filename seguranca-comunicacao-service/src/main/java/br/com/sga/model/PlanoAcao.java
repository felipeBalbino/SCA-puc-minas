package br.com.sga.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PLANO_ACAO")
public class PlanoAcao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_ACAO")
	private Long codigo;

	@ManyToMany
	@JsonIgnore
	private List<Pessoa> pessoas;

	private Long codigoBarragem;

	@NotNull
	@Size(min = 3, max = 60)
	@Column(name = "DESCRICAO", length = 60, nullable = false)
	private String descricao;

	@Enumerated(EnumType.ORDINAL)
	private GrauRiscoEnum grauRisco;

	@NotNull
	@Size(max = 130)
	@Column(name = "MENSAGEM_ALERTA", length = 130, nullable = false)
	private String mensagemAlterta;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Data de inclusão requerida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "DATA_INCLUSAO")
	private Date dataInclusao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "DATA_INATIVACAO")
	private Date dataInativacao;

	public PlanoAcao() {
		super();
	}

	public PlanoAcao(Long codigo) {
		super();
		this.codigo = codigo;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
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

	public Long getCodigoBarragem() {
		return codigoBarragem;
	}

	public void setCodigoBarragem(Long codigoBarragem) {
		this.codigoBarragem = codigoBarragem;
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

	public String getMensagemAlterta() {
		return mensagemAlterta;
	}

	public void setMensagemAlterta(String mensagemAlterta) {
		this.mensagemAlterta = mensagemAlterta;
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
