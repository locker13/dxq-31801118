package model;

public class BeanReduce {
	private int rde_id;
	private int rde_limit;
	private double rde_money;
	private boolean rde_plus;
	public static final String[] ShopTitle= {"Âú¼õ±àºÅ","Âú¼õ½ğ¶î", "ÓÅ»İ½ğ¶î", "ÄÜ·ñÓëÈ¯µş¼Ó"};
	public int getRde_id() {
		return rde_id;
	}
	public void setRde_id(int rde_id) {
		this.rde_id = rde_id;
	}
	public int getRde_limit() {
		return rde_limit;
	}
	public void setRde_limit(int rde_limit) {
		this.rde_limit = rde_limit;
	}
	public double getRde_money() {
		return rde_money;
	}
	public void setRde_money(double rde_money) {
		this.rde_money = rde_money;
	}
	public boolean isRde_plus() {
		return rde_plus;
	}
	public void setRde_plus(boolean rde_plus) {
		this.rde_plus = rde_plus;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(rde_id);
		else if(col==1) return String.valueOf(rde_limit);
		else if(col==2) return String.valueOf(rde_money);
		else if(col==3) 
			{
			if(rde_plus==true) return "¿É";
			else return "·ñ";
			}
		else return "";
}
}
