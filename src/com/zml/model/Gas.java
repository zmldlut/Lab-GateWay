package com.zml.model;

import java.util.Date;

public class Gas {
	private int node_id;
	private int gas_value;
	private Date acq_time;
	public int getNode_id() {
		return node_id;
	}
	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}
	public int getGas_value() {
		return gas_value;
	}
	public void setGas_value(int gas_value) {
		this.gas_value = gas_value;
	}
	public Date getAcq_time() {
		return acq_time;
	}
	public void setAcq_time(Date acq_time) {
		this.acq_time = acq_time;
	}
	public Gas(int node_id, int gas_value, Date acq_time) {
		this.node_id = node_id;
		this.gas_value = gas_value;
		this.acq_time = acq_time;
	}
}
