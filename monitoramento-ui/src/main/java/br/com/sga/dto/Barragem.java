package br.com.sga.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author sga
 *
 */
public class Barragem {

	private Long codigo;

	private Long codigoAtivo;

	private String nome;

	private Double latitude;

	private Double longitude;

	private TipoMetodo tipoMetodo;

	private List<Inspecao> inspecoes;

	private List<Sensor> sensores;

	@Enumerated(EnumType.ORDINAL)
	private ClassificacaoEnum classificacao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataInclusao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataInativacao;
	
	@JsonIgnore
	private Ativo ativo;

	public Long getCodigo() {
		return codigo;
	}

	public ClassificacaoEnum getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(ClassificacaoEnum classificacao) {
		this.classificacao = classificacao;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public TipoMetodo getTipoMetodo() {
		return tipoMetodo;
	}

	public void setTipoMetodo(TipoMetodo tipoMetodo) {
		this.tipoMetodo = tipoMetodo;
	}

	public List<Inspecao> getInspecoes() {
		return inspecoes;
	}

	public void setInspecoes(List<Inspecao> inspecoes) {
		this.inspecoes = inspecoes;
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
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
		Barragem other = (Barragem) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
