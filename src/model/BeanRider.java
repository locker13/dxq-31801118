package model;

import java.sql.Date;

public class BeanRider {
	private int rd_id;
	public int getRd_id() {
		return rd_id;
	}
	public void setRd_id(int rd_id) {
		this.rd_id = rd_id;
	}
	public String getRd_name() {
		return rd_name;
	}
	public void setRd_name(String rd_name) {
		this.rd_name = rd_name;
	}
	public Date getRd_in() {
		return rd_in;
	}
	public void setRd_in(Date rd_in) {
		this.rd_in = rd_in;
	}
	public String getRd_status() {
		return rd_status;
	}
	public void setRd_status(String rd_status) {
		this.rd_status = rd_status;
	}
	private String rd_name;
	private Date rd_in;
	private String rd_status;
}
