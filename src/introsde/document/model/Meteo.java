package introsde.document.model;

//import introsde.document.dao.*;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

public class Meteo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String precipitation;
	private Double max_temperature;
	private Double min_temperature;

	public Meteo(String precipitation, Double max_temperature, Double min_temperature) {
		this.precipitation = precipitation;
		this.max_temperature = max_temperature;
		this.min_temperature = min_temperature;
	}

	public Meteo() {

	}

	public String getPrecipitation() {
		return this.precipitation;
	}
	
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}
	
	public Double getMaxTemperature() {
		return this.max_temperature;
	}
	
	public void setMaxTemperature(Double max_temperature) {
		this.max_temperature = max_temperature;
	}
	
	public Double getMinTemperature() {
		return this.min_temperature;
	}
	
	public void setMinTemperature(Double min_temperature) {
		this.min_temperature = min_temperature;
	}

}