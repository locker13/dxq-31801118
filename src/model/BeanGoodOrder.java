package model;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.BaseException;
import util.DBUtil;
import util.DbException;

public class BeanGoodOrder {
	public static final String[] ShopTitle= {"订单编号","商家编号","骑手编号","结算金额","满减编号","优惠券编号","下单时间","要求送达时间","地址","订单状态"};
	private int go_oid;
	private int go_sid;
	private int go_uid;
	private int go_rid;
	private int go_reid;
	private int go_cid;
	private String go_addr;
	private double go_oldpri;
	private double go_newpri;
	private Date go_sttime;
	private Date go_edtime;
	public int getGo_oid() {
		return go_oid;
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
	public String getGo_addr() {
		return go_addr;
	}
	public void setGo_addr(String go_addr) {
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
	/*public java.sql.Timestamp getGo_sttime() {
		return go_sttime;
	}
	public void setGo_sttime(java.sql.Timestamp timestamp) {
		this.go_sttime = timestamp;
	}
	public java.sql.Timestamp getGo_edtime() {
		return go_edtime;
	}
	public void setGo_edtime(java.sql.Timestamp timestamp) {
		this.go_edtime = timestamp;
	}*/
	public String getGo_status() {
		return go_status;
	}
	public void setGo_status(String go_status) {
		this.go_status = go_status;
	}
	private String go_status;
	
	public String getCell(int col){
		if(col==0) return String.valueOf(go_oid);
		else if(col==1) return String.valueOf(go_sid);
		else if(col==2) return String.valueOf(go_rid);
		else if(col==3) return String.valueOf(go_newpri);
		else if(col==4) return String.valueOf(go_reid);
		else if(col==5) return String.valueOf(go_cid);
		else if(col==6) return new SimpleDateFormat("HH:mm:ss").format(go_sttime.getTime());
		else if(col==7) return new SimpleDateFormat("HH:mm:ss").format(go_edtime.getTime());
		//else if(col==6) return String.valueOf(go_sttime); 
		//else if(col==7) return String.valueOf(go_edtime);
		else if(col==8) return String.valueOf(go_addr);
		else if(col==9) return String.valueOf(go_status);
		else return "";
}
	public BeanGoodOrder() throws BaseException{//生成订单号
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select max(go_oid) from goods_order";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			int i=1;
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
				i+=rs.getInt(1);
			else
				i=1;
			this.go_oid=i;
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
			}
			finally{
				if(conn!=null)
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
	}
}
