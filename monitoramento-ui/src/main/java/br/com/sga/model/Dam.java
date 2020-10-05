package br.com.sga.model;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author sga
 *
 */
public class Dam {

	private Long id;

	private String name;

	private Double latitude;

	private Double longitude;

	private String ore;

	@Enumerated(EnumType.ORDINAL)
	private Method method;

	private List<Inspect> inspects;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getOre() {
		return ore;
	}

	public void setOre(String ore) {
		this.ore = ore;
	}

	public List<Inspect> getInspects() {
		return inspects;
	}

	public void setInspects(List<Inspect> inspects) {
		this.inspects = inspects;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
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
		Dam other = (Dam) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
