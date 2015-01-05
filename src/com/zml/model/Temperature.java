package com.zml.model;

import java.util.Date;

public class Temperature {
	private int node_id;
	private String temperature_value;
	private Date acq_time;
	public int getNode_id() {
		return node_id;
	}
	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}
	public String getTemperature_value() {
		return temperature_value;
	}
	public void setTemperature_value(String temperature_value) {
		this.temperature_value = temperature_value;
	}
	public Date getAcq_time() {
		return acq_time;
	}
	public void setAcq_time(Date acq_time) {
		this.acq_time = acq_time;
	}
	
	public Temperature(int node_id, String temperature_value, Date acq_time) {
		this.node_id = node_id;
		this.temperature_value = temperature_value;
		this.acq_time = acq_time;
	}
}
