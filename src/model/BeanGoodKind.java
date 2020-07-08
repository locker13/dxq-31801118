package model;

public class BeanGoodKind {
	public static final String[] ShopTitle= {"商品类编号","商品类名","商品数"};
	private int gk_id;
	private String gk_name;
	private int gk_num;
	public int getGk_id() {
		return gk_id;
	}
	public void setGk_id(int gk_id) {
		this.gk_id = gk_id;
	}
	public String getGk_name() {
		return gk_name;
	}
	public void setGk_name(String gk_name) {
		this.gk_name = gk_name;
	}
	public int getGk_num() {
		return gk_num;
	}
	public void setGk_num(int gk_num) {
		this.gk_num = gk_num;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(gk_id);
		else if(col==1) return gk_name;
		else if(col==2) return String.valueOf(gk_num);
		else return "";
}
}
