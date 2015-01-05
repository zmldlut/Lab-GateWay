package com.zml.model;

import java.util.Date;

public class Humidity {
	private int node_id;
	private String humidity_value;
	private Date acq_time;
	public int getNode_id() {
		return node_id;
	}
	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}
	public String getHumidity_value() {
		return humidity_value;
	}
	public void setHumidity_value(String humidity_value) {
		this.humidity_value = humidity_value;
	}
	public Date getAcq_time() {
		return acq_time;
	}
	public void setAcq_time(Date acq_time) {
		this.acq_time = acq_time;
	}
	
	public Humidity(int node_id, String humidity_value, Date acq_time) {
		this.node_id = node_id;
		this.humidity_value = humidity_value;
		this.acq_time = acq_time;
	}
}
