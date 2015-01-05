package com.zml.model;

import java.util.Date;

public class PM2_5 {
	private int node_id;
	private int pm_value;
	private Date acq_time;
	public int getNode_id() {
		return node_id;
	}
	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}
	public int getPm_value() {
		return pm_value;
	}
	public void setPm_value(int pm_value) {
		this.pm_value = pm_value;
	}
	public Date getAcq_time() {
		return acq_time;
	}
	public void setAcq_time(Date acq_time) {
		this.acq_time = acq_time;
	}

	public PM2_5(int node_id, int pm_value, Date acq_time) {
		this.node_id = node_id;
		this.pm_value = pm_value;
		this.acq_time = acq_time;
	}
}
