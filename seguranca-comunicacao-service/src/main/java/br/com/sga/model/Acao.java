package br.com.sga.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ACAO")
public class Acao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_ACAO")
	private Long codigo;

	@ManyToMany
	@JsonIgnore
	private List<Pessoa> pessoas;

	private Long codigoBarragem;

	@NotNull(message = "PlanoAcao requerido")
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "CODIGO_PLANO_ACAO")
	private PlanoAcao planoAcao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Data de inclusão requerida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "DATA_INCLUSAO")
	private Date dataInclusao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "DATA_INATIVACAO")
	private Date dataInativacao;

	public Acao() {
		super();
	}

	public Acao(Long codigo) {
		super();
		this.codigo = codigo;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public PlanoAcao getPlanoAcao() {
		return planoAcao;
	}

	public void setPlanoAcao(PlanoAcao planoAcao) {
		this.planoAcao = planoAcao;
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
		Acao other = (Acao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
