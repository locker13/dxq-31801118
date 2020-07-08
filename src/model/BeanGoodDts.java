package model;

public class BeanGoodDts {
	public static final String[] tblGoodTitle= {"商品编号","所属分类", "商品名", "价格","优惠价格"};
	private int gd_id;
	private int gd_kindid;
	private String gd_name;
	private double gd_price;
	private double gd_reduce;
	public int getGd_id() {
		return gd_id;
	}
	public void setGd_id(int gd_id) {
		this.gd_id = gd_id;
	}
	public int getGd_kindid() {
		return gd_kindid;
	}
	public void setGd_kindid(int gd_kindid) {
		this.gd_kindid = gd_kindid;
	}
	public String getGd_name() {
		return gd_name;
	}
	public void setGd_name(String gd_name) {
		this.gd_name = gd_name;
	}
	public double getGd_price() {
		return gd_price;
	}
	public void setGd_price(double gd_price) {
		this.gd_price = gd_price;
	}
	public double getGd_reduce() {
		return gd_reduce;
	}
	public void setGd_reduce(double gd_reduce) {
		this.gd_reduce = gd_reduce;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(gd_id);
		else if(col==1) return String.valueOf(gd_kindid);
		else if(col==2) return gd_name;
		else if(col==3) return String.valueOf(gd_price);
		else if(col==4) return String.valueOf(gd_reduce);	
		else return "";
}
}
