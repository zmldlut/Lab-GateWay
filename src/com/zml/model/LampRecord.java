package com.zml.model;

import java.util.Date;

public class LampRecord {
	private String stdNum;
	private Date operate_date;
	private int node_id;
	private int operate;
	public String getStdNum() {
		return stdNum;
	}
	public void setStdNum(String stdNum) {
		this.stdNum = stdNum;
	}
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}
	public int getNode_id() {
		return node_id;
	}
	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}
	public int getOperate() {
		return operate;
	}
	public void setOperate(int operate) {
		this.operate = operate;
	}
	public LampRecord(String stdNum, Date operate_date, int node_id, int operate) {
		this.stdNum = stdNum;
		this.operate_date = operate_date;
		this.node_id = node_id;
		this.operate = operate;
	}
}
