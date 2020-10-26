package br.com.sga.model;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author sga
 *
 */
public class Inspecao {

	private Long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Required date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date date;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Required Categoria de Risco")
	private DanoPotencial categoriaRisco;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Required Dano Potencial")
	private DanoPotencial danoPotencial;

	@NotNull(message = "Required descricao")
	private String descricao;

	@NotNull(message = "Required height")
	private Double height;

	@NotNull(message = "Required volume")
	private Double volume;

	private Barragem barragem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DanoPotencial getCategoriaRisco() {
		return categoriaRisco;
	}

	public void setCategoriaRisco(DanoPotencial categoriaRisco) {
		this.categoriaRisco = categoriaRisco;
	}

	public DanoPotencial getDanoPotencial() {
		return danoPotencial;
	}

	public void setDanoPotencial(DanoPotencial	 danoPotencial) {
		this.danoPotencial = danoPotencial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Barragem getBarragem() {
		return barragem;
	}

	public void setBarragem(Barragem barragem) {
		this.barragem = barragem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Inspecao other = (Inspecao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inspecao [id=" + id + ", date=" + date + ", categoriaRisco=" + categoriaRisco + ", danoPotencial="
				+ danoPotencial + ", descricao=" + descricao + ", height=" + height + ", volume=" + volume
				+ ", barragem=" + barragem + "]";
	}

}
