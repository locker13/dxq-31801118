package model;

import java.sql.Date;

public class BeanCoupon {
	private int cp_id;
	private double cp_money;
	private int cp_count;
	private Date cp_stdate;
	private Date cp_eddate;
	private int cp_sid;
	public int getCp_id() {
		return cp_id;
	}
	public void setCp_id(int cp_id) {
		this.cp_id = cp_id;
	}
	public double getCp_money() {
		return cp_money;
	}
	public void setCp_money(double cp_money) {
		this.cp_money = cp_money;
	}
	public int getCp_count() {
		return cp_count;
	}
	public void setCp_count(int cp_count) {
		this.cp_count = cp_count;
	}
	public Date getCp_stdate() {
		return cp_stdate;
	}
	public void setCp_stdate(Date cp_stdate) {
		this.cp_stdate = cp_stdate;
	}
	public Date getCp_eddate() {
		return cp_eddate;
	}
	public void setCp_eddate(Date cp_eddate) {
		this.cp_eddate = cp_eddate;
	}
	public int getCp_sid() {
		return cp_sid;
	}
	public void setCp_sid(int cp_sid) {
		this.cp_sid = cp_sid;
	}
}
