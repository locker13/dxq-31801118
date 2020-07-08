package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.BeanCoupon;
import model.BeanReduce;
import model.BeanShop;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class CouponMannager {
	public static List<BeanCoupon> loadallShop() throws BaseException{
		List<BeanCoupon> result=new ArrayList<BeanCoupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from coupon order by cp_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanCoupon s=new BeanCoupon();
				s.setCp_id(rs.getInt(1));
				s.setCp_money(rs.getDouble(2));
				s.setCp_count(rs.getInt(3));
				s.setCp_stdate(rs.getDate(4));
				s.setCp_eddate(rs.getDate(5));
				result.add(s);
			}
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
		return result;
	}
	public static List<BeanCoupon> loadselectShop(String id) throws BaseException
	{
		List<BeanCoupon> result=new ArrayList<BeanCoupon>();
		Connection conn=null;
		try {
			int a=Integer.parseInt(id);
			conn=DBUtil.getConnection();
			String sql="select * from coupon where cp_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,a);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				do {
				BeanCoupon s=new BeanCoupon();
				s.setCp_id(a);
				s.setCp_money(rs.getDouble(2));
				s.setCp_count(rs.getInt(3));
				s.setCp_stdate(rs.getDate(4));
				s.setCp_eddate(rs.getDate(5));
				result.add(s);
				}while(rs.next());
			}
			else {
				throw new BusinessException("优惠券不 存在");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "请正确输入编号", "提示",JOptionPane.INFORMATION_MESSAGE);
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
		return result;
	}
	public static void deleteCou(BeanCoupon s)throws BaseException{
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from coupon where cp_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getCp_id());
			pst.execute();
			pst.close();
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
	public static void modCou(BeanCoupon s) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE coupon set cp_money=?,cp_count=?,cp_stdate=?,cp_eddate=? where cp_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setDouble(1,s.getCp_money());
			pst.setInt(2, s.getCp_count());
			pst.setDate(3, s.getCp_stdate());
			pst.setDate(4, s.getCp_eddate());
			pst.execute();
			pst.close();
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
	public static void InsCou(BeanCoupon s) throws BaseException {
		//if(s.getSp_name().length()==0) throw new BusinessException("商家名不能为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select max(cp_id) from coupon";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			int i=1;
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
				i+=rs.getInt(1);
			else
				i=1;
			sql="insert into coupon values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setDouble(2,s.getCp_money());
			pst.setInt(3, s.getCp_count());
			pst.setDate(4, s.getCp_stdate());
			pst.setDate(5, s.getCp_eddate());
			pst.setInt(1,i);
			pst.execute();
			pst.close();
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
