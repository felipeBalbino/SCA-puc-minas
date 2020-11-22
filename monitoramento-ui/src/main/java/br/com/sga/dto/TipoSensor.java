package br.com.sga.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author sga
 *
 */

public class TipoSensor {

	private Long codigo;

	private String nome;

	@NotNull(message = "Leitura maxLeitura requerido")
	private Double maxLeitura;

	@NotNull(message = "Leitura  minLeitura requerido")
	private Double minLeitura;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInclusao;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInativacao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Double getMaxLeitura() {
		return maxLeitura;
	}

	public void setMaxLeitura(Double maxLeitura) {
		this.maxLeitura = maxLeitura;
	}

	public Double getMinLeitura() {
		return minLeitura;
	}

	public void setMinLeitura(Double minLeitura) {
		this.minLeitura = minLeitura;
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
		TipoSensor other = (TipoSensor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
