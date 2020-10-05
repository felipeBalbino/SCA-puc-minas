package br.com.sga.model;

import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Inspect {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Required date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date date;

	@Enumerated(EnumType.ORDINAL)
	@NotNull(message = "Required Risk Category")
	private PotentialDamage riskCategory;

	@Enumerated(EnumType.ORDINAL)
	@NotNull(message = "Required Potentiation Damage")
	private PotentialDamage potentialDamage;

	@NotNull(message = "Required considerations")
	private String considerations;

	@NotNull(message = "Required height")
	private Double height;

	@NotNull(message = "Required volume")
	private Double volume;

	@ManyToOne
	@JoinColumn(name = "dam_id")
	private Dam dam;

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

	public PotentialDamage getRiskCategory() {
		return riskCategory;
	}

	public void setRiskCategory(PotentialDamage riskCategory) {
		this.riskCategory = riskCategory;
	}

	public PotentialDamage getPotentialDamage() {
		return potentialDamage;
	}

	public void setPotentialDamage(PotentialDamage potentialDamage) {
		this.potentialDamage = potentialDamage;
	}

	public String getConsiderations() {
		return considerations;
	}

	public void setConsiderations(String considerations) {
		this.considerations = considerations;
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

	public Dam getDam() {
		return dam;
	}

	public void setDam(Dam dam) {
		this.dam = dam;
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
		Inspect other = (Inspect) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inspect [id=" + id + ", date=" + date + ", riskCategory=" + riskCategory + ", potentialDamage="
				+ potentialDamage + ", considerations=" + considerations + ", height=" + height + ", volume=" + volume
				+ ", dam=" + dam + "]";
	}

}
