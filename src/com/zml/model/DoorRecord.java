package com.zml.model;

import java.util.Date;

public class DoorRecord {
	private String stdNum;
	private Date open_date;
	private int node_id;	
	public String getStdNum() {
		return stdNum;
	}
	public void setStdNum(String stdNum) {
		this.stdNum = stdNum;
	}
	public Date getOpen_date() {
		return open_date;
	}
	public void setOpen_date(Date open_date) {
		this.open_date = open_date;
	}
	public int getNode_id() {
		return node_id;
	}
	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}
	public DoorRecord(String stdNum, Date open_date, int node_id) {
		this.stdNum = stdNum;
		this.open_date = open_date;
		this.node_id = node_id;
	}
}
