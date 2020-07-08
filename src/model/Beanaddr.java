package model;

public class Beanaddr {
	public static final String[] ShopTitle= {"省","市","区","地址","联系人","电话"};
	private int ad_id;
	private int ad_uid;
	private String ad_prin;
	private String ad_city;
	private String ad_block;
	private String ad_addr;
	private String ad_person;
	//private int ad_phone;
	private String ad_phone;
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	public int getAd_uid() {
		return ad_uid;
	}
	public void setAd_uid(int ad_uid) {
		this.ad_uid = ad_uid;
	}
	public String getAd_prin() {
		return ad_prin;
	}
	public void setAd_prin(String ad_prin) {
		this.ad_prin = ad_prin;
	}
	public String getAd_city() {
		return ad_city;
	}
	public void setAd_city(String ad_city) {
		this.ad_city = ad_city;
	}
	public String getAd_block() {
		return ad_block;
	}
	public void setAd_block(String ad_block) {
		this.ad_block = ad_block;
	}
	public String getAd_addr() {
		return ad_addr;
	}
	public void setAd_addr(String ad_addr) {
		this.ad_addr = ad_addr;
	}
	public String getAd_person() {
		return ad_person;
	}
	public void setAd_person(String ad_person) {
		this.ad_person = ad_person;
	}
	public String getAd_phone() {
		return ad_phone;
	}
	public void setAd_phone(String ad_phone) {
		this.ad_phone = ad_phone;
	}
	public String getCell(int col){
		if(col==0) return ad_prin;
		else if(col==1) return ad_city;
		else if(col==2) return ad_block;
		else if(col==3) return ad_addr;
		else if(col==4) return ad_person;
		else if(col==5) return ad_phone;
		else return "";
}
}
