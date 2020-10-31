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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author sga
 *
 */
@Entity
@Table(name = "BARRAGEM")
public class Barragem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_BARRAGEM")
	private Long codigo;

	@NotNull(message = "Código do ativo requerido")
	@Column(name = "CODIGO_ATIVO", nullable = false, unique = false)
	private Long codigoAtivo;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "LATITUDE")
	private Double latitude;

	@Column(name = "LONGITUDE")
	private Double longitude;

	@NotNull(message = "Tipo de método da barragem requerido")
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "CODIGO_TIPO_METODO")
	private TipoMetodo tipoMetodo;

	@OneToMany(mappedBy = "barragem")
	@JsonIgnore
	private List<Inspecao> inspecoes;

	@OneToMany(mappedBy = "barragem")
	@JsonIgnore
	private List<Sensor> sensores;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Data de inclusão requerida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "DATA_INCLUSAO")
	private Date dataInclusao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "DATA_INATIVACAO")
	private Date dataInativacao;

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
		Barragem other = (Barragem) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
