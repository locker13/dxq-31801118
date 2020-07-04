package model;

import java.sql.Date;

public class BeanUserCoup {
	private int uc_uid;
	private int uc_cid;
	private int uc_sid;
	private int uc_red;
	private int uc_sum;
	private Date endtime;
	public int getUc_uid() {
		return uc_uid;
	}
	public void setUc_uid(int uc_uid) {
		this.uc_uid = uc_uid;
	}
	public int getUc_cid() {
		return uc_cid;
	}
	public void setUc_cid(int uc_cid) {
		this.uc_cid = uc_cid;
	}
	public int getUc_sid() {
		return uc_sid;
	}
	public void setUc_sid(int uc_sid) {
		this.uc_sid = uc_sid;
	}
	public int getUc_red() {
		return uc_red;
	}
	public void setUc_red(int uc_red) {
		this.uc_red = uc_red;
	}
	public int getUc_sum() {
		return uc_sum;
	}
	public void setUc_sum(int uc_sum) {
		this.uc_sum = uc_sum;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
}
