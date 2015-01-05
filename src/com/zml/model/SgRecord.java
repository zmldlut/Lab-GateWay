package com.zml.model;

import java.util.Date;

public class SgRecord {
	private String stdNum;
	private Date sgDate;
	private Date lvDate;
	private int node_id;
	public String getStdNum() {
		return stdNum;
	}
	public void setStdNum(String stdNum) {
		this.stdNum = stdNum;
	}
	public Date getSgDate() {
		return sgDate;
	}
	public void setSgDate(Date sgDate) {
		this.sgDate = sgDate;
	}
	public Date getLvDate() {
		return lvDate;
	}
	public void setLvDate(Date lvDate) {
		this.lvDate = lvDate;
	}
	public int getNode_id() {
		return node_id;
	}
	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}

	public SgRecord(String stdNum, Date sgDate, Date lvDate, int node_id) {
		this.stdNum = stdNum;
		this.sgDate = sgDate;
		this.lvDate = lvDate;
		this.node_id = node_id;
	}
}
