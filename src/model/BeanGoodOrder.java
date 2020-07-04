package model;

import java.sql.Date;

public class BeanGoodOrder {
	private int go_oid;
	private int go_sid;
	private int go_uid;
	private int go_rid;
	private int go_reid;
	private int go_cid;
	private int go_addr;
	private double go_oldpri;
	private double go_newpri;
	private Date go_sttime;
	private Date go_edtime;
	public int getGo_oid() {
		return go_oid;
	}
	public void setGo_oid(int go_oid) {
		this.go_oid = go_oid;
	}
	public int getGo_sid() {
		return go_sid;
	}
	public void setGo_sid(int go_sid) {
		this.go_sid = go_sid;
	}
	public int getGo_uid() {
		return go_uid;
	}
	public void setGo_uid(int go_uid) {
		this.go_uid = go_uid;
	}
	public int getGo_rid() {
		return go_rid;
	}
	public void setGo_rid(int go_rid) {
		this.go_rid = go_rid;
	}
	public int getGo_reid() {
		return go_reid;
	}
	public void setGo_reid(int go_reid) {
		this.go_reid = go_reid;
	}
	public int getGo_cid() {
		return go_cid;
	}
	public void setGo_cid(int go_cid) {
		this.go_cid = go_cid;
	}
	public int getGo_addr() {
		return go_addr;
	}
	public void setGo_addr(int go_addr) {
		this.go_addr = go_addr;
	}
	public double getGo_oldpri() {
		return go_oldpri;
	}
	public void setGo_oldpri(double go_oldpri) {
		this.go_oldpri = go_oldpri;
	}
	public double getGo_newpri() {
		return go_newpri;
	}
	public void setGo_newpri(double go_newpri) {
		this.go_newpri = go_newpri;
	}
	public Date getGo_sttime() {
		return go_sttime;
	}
	public void setGo_sttime(Date go_sttime) {
		this.go_sttime = go_sttime;
	}
	public Date getGo_edtime() {
		return go_edtime;
	}
	public void setGo_edtime(Date go_edtime) {
		this.go_edtime = go_edtime;
	}
	public String getGo_status() {
		return go_status;
	}
	public void setGo_status(String go_status) {
		this.go_status = go_status;
	}
	private String go_status;
}
