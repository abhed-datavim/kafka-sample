package com.datavim.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 
 * @author abhed
 *
 */
public class Driver {
	String drivername;
	String customername;
	String contactnumber;
	Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	
	public String getContactnumber() {
		return contactnumber;
	}
	
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public Driver(String name, String contact) {
		this.drivername = name;
		this.contactnumber = contact;
	}

	public Driver(String j, String i, String name) {
		this.drivername = name;
	}

	public Driver() {

	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customer) {
		this.customername = customer;
	}
	
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	
	@Override
    public String toString() {
		final StringBuffer sb = new StringBuffer("Driver{");
        sb.append("DriverName='").append(drivername).append('\'');
        sb.append(", ContactNumber='").append(contactnumber).append('\'');
        sb.append(", CustomerName='").append(customername).append('\'');
        sb.append(", Error='").append(additionalProperties.get("error")).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
