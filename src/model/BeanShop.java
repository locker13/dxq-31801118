package model;

public class BeanShop {
	private int sp_id;
	private String sp_name;
	private int sp_star;
	private double sp_aver;
	private int sp_sum;
	public static final String[] ShopTitle= {"商家编号","商家名", "星级", "人均消费", "总销量"};
	public int getSp_id() {
		return sp_id;
	}
	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}
	public String getSp_name() {
		return sp_name;
	}
	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}
	public int getSp_star() {
		return sp_star;
	}
	public void setSp_star(int sp_star) {
		this.sp_star = sp_star;
	}
	public double getSp_aver() {
		return sp_aver;
	}
	public void setSp_aver(double sp_aver) {
		this.sp_aver = sp_aver;
	}
	public int getSp_sum() {
		return sp_sum;
	}
	public void setSp_sum(int sp_sum) {
		this.sp_sum = sp_sum;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(sp_id);
		else if(col==1) return sp_name;
		else if(col==2) return String.valueOf(sp_star);
		else if(col==3) return String.valueOf(sp_aver);
		else if(col==4) return String.valueOf(sp_sum);
		else return "";
}
}
