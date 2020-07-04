package model;

import java.sql.Date;

public class BeanRiderIn {
	private int ri_rid;
	private int ri_oid;
	private Date ri_time;
	private String ri_comm;
	private double ri_in;
	public int getRi_rid() {
		return ri_rid;
	}
	public void setRi_rid(int ri_rid) {
		this.ri_rid = ri_rid;
	}
	public int getRi_oid() {
		return ri_oid;
	}
	public void setRi_oid(int ri_oid) {
		this.ri_oid = ri_oid;
	}
	public Date getRi_time() {
		return ri_time;
	}
	public void setRi_time(Date ri_time) {
		this.ri_time = ri_time;
	}
	public String getRi_comm() {
		return ri_comm;
	}
	public void setRi_comm(String ri_comm) {
		this.ri_comm = ri_comm;
	}
	public double getRi_in() {
		return ri_in;
	}
	public void setRi_in(double ri_in) {
		this.ri_in = ri_in;
	}
}
