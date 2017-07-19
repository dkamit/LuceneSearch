package amith.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Language bean to hold language data
 * 
 * @author Amith
 *
 */
public class Language {

	@JsonProperty("Name")
	private String name;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("Designed by")
	private String designedBy;

	public Language() {
		super();
	}

	public Language(String name, String type, String designedBy) {
		super();
		this.name = name;
		this.type = type;
		this.designedBy = designedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((designedBy == null) ? 0 : designedBy.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Language other = (Language) obj;
		if (designedBy == null) {
			if (other.designedBy != null)
				return false;
		} else if (!designedBy.equals(other.designedBy))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Language [name=" + name + ", type=" + type + ", designedBy=" + designedBy + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesignedBy() {
		return designedBy;
	}

	public void setDesignedBy(String designedBy) {
		this.designedBy = designedBy;
	}
}
