package model;

public class BeanAdmin {
	public static BeanAdmin currentLoginAdmin=null;
	private int am_id;
	private String am_name;
	private String am_code;
	public int getAm_id() {
		return am_id;
	}
	public void setAm_id(int am_id) {
		this.am_id = am_id;
	}
	public String getAm_name() {
		return am_name;
	}
	public void setAm_name(String am_name) {
		this.am_name = am_name;
	}
	public String getAm_code() {
		return am_code;
	}
	public void setAm_code(String ad_code) {
		this.am_code = ad_code;
	}
}
