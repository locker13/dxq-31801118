package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class BeanGoodComt {
	public static final String[] ShopTitle= {"商品编号","商家编号","用户编号","评价","日期","星级"};

	private int gc_gid;
	private int gc_sid;
	private int gc_uid;
	private String gc_word;
	private Date gc_date;
	private int gc_star;
	private String gc_pic;
	public int getGc_gid() {
		return gc_gid;
	}
	public void setGc_gid(int gc_gid) {
		this.gc_gid = gc_gid;
	}
	public int getGc_sid() {
		return gc_sid;
	}
	public void setGc_sid(int gc_sid) {
		this.gc_sid = gc_sid;
	}
	public int getGc_uid() {
		return gc_uid;
	}
	public void setGc_uid(int gc_uid) {
		this.gc_uid = gc_uid;
	}
	public String getGc_word() {
		return gc_word;
	}
	public void setGc_word(String gc_word) {
		this.gc_word = gc_word;
	}
	public Date getGc_date() {
		return gc_date;
	}
	public void setGc_date(Date gc_date) {
		this.gc_date = gc_date;
	}
	public int getGc_star() {
		return gc_star;
	}
	public void setGc_star(int gc_star) {
		this.gc_star = gc_star;
	}
	public String getGc_pic() {
		return gc_pic;
	}
	public void setGc_pic(String gc_pic) {
		this.gc_pic = gc_pic;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(gc_gid);
		else if(col==1) return String.valueOf(gc_sid);
		else if(col==2) return String.valueOf(gc_uid);
		else if(col==3) return String.valueOf(gc_word);
		else if(col==4) return String.valueOf(gc_date);
		else if(col==5) return String.valueOf(gc_star);
		else return "";
}
}
