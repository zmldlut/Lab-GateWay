package com.zml.model;

import java.util.Date;

public class Light {
	private int node_id;
	private String light_value;
	private Date acq_time;
	public int getNode_id() {
		return node_id;
	}
	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}
	public String getLight_value() {
		return light_value;
	}
	public void setHumidity_value(String light_value) {
		this.light_value = light_value;
	}
	public Date getAcq_time() {
		return acq_time;
	}
	public void setAcq_time(Date acq_time) {
		this.acq_time = acq_time;
	}
	
	public Light(int node_id, String light_value, Date acq_time) {
		this.node_id = node_id;
		this.light_value = light_value;
		this.acq_time = acq_time;
	}
}
