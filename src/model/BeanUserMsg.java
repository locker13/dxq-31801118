package model;

import java.sql.Date;

public class BeanUserMsg {
	public static BeanUserMsg currentLoginUser=null;
	private int um_id;
	private String um_name;
	private String um_sex;
	private String um_code;
	//private int um_phone;
	private String um_phone;
	private String um_mail;
	private String um_city;
	private Date um_regt;
	private int um_status;
	private Date um_endtime;
	public int getUm_id() {
		return um_id;
	}
	public void setUm_id(int um_id) {
		this.um_id = um_id;
	}
	public String getUm_name() {
		return um_name;
	}
	public void setUm_name(String um_name) {
		this.um_name = um_name;
	}
	public String getUm_sex() {
		return um_sex;
	}
	public void setUm_sex(String um_sex) {
		this.um_sex = um_sex;
	}
	public String getUm_code() {
		return um_code;
	}
	public void setUm_code(String um_code) {
		this.um_code = um_code;
	}
	public String getUm_phone() {
		return um_phone;
	}
	public void setUm_phone(String um_phone) {
		this.um_phone = um_phone;
	}
	public String getUm_mail() {
		return um_mail;
	}
	public void setUm_mail(String um_mail) {
		this.um_mail = um_mail;
	}
	public String getUm_city() {
		return um_city;
	}
	public void setUm_city(String um_city) {
		this.um_city = um_city;
	}
	public Date getUm_regt() {
		return um_regt;
	}
	public void setUm_regt(Date um_regt) {
		this.um_regt = um_regt;
	}
	public int getUm_status() {
		return um_status;
	}
	public void setUm_status(int um_status) {
		this.um_status = um_status;
	}
	public Date getUm_endtime() {
		return um_endtime;
	}
	public void setUm_endtime(Date um_endtime) {
		this.um_endtime = um_endtime;
	}
}
