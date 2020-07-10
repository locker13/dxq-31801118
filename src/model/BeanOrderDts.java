package model;

public class BeanOrderDts {
	public static final String[] ShopTitle= {"商品编号","数量","价格"};
	private int od_oid;
	private int od_gid;
	private int od_count;
	private double od_price;
	private double od_disc;
	public int getOd_oid() {
		return od_oid;
	}
	public void setOd_oid(int od_oid) {
		this.od_oid = od_oid;
	}
	public int getOd_gid() {
		return od_gid;
	}
	public void setOd_gid(int od_gid) {
		this.od_gid = od_gid;
	}
	public int getOd_count() {
		return od_count;
	}
	public void setOd_count(int od_count) {
		this.od_count = od_count;
	}
	public double getOd_price() {
		return od_price;
	}
	public void setOd_price(double od_price) {
		this.od_price = od_price;
	}
	public double getOd_disc() {
		return od_disc;
	}
	public void setOd_disc(double od_disc) {
		this.od_disc = od_disc;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(od_gid);
		else if(col==1) return String.valueOf(od_count);
		else if(col==2) return String.valueOf(od_price);
		else return "";
}
}
