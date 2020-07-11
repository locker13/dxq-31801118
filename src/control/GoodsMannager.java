package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.BeanCoupon;
import model.BeanGoodComt;
import model.BeanGoodDts;
import model.BeanGoodKind;
import model.BeanUserMsg;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class GoodsMannager {
	//实现购买，读取所有商品/读取推荐商品、评论共鞥你
	public static List<BeanGoodKind> loadallGoodK() throws BaseException{
		List<BeanGoodKind> result=new ArrayList<BeanGoodKind>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goods_kind order by gk_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanGoodKind s=new BeanGoodKind();
				s.setGk_id(rs.getInt(1));
				s.setGk_name(rs.getString(2));
				s.setGk_num(rs.getInt(3));
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
	public static List<BeanGoodKind> loadselectKind(String id) throws BaseException
	{
		List<BeanGoodKind> result=new ArrayList<BeanGoodKind>();
		Connection conn=null;
		try {
			int a=Integer.parseInt(id);
			conn=DBUtil.getConnection();
			String sql="select * from goods_kind where gk_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,a);
			//pst.setString(1, id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				do {
				BeanGoodKind s=new BeanGoodKind();
				s.setGk_id(rs.getInt(1));
				s.setGk_name(rs.getString(2));
				s.setGk_num(rs.getInt(3));
				result.add(s);
				}while(rs.next());
			}
			else {
				throw new BusinessException("商品类别不 存在");
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
	
	public static List<BeanGoodDts> loadGoods(BeanGoodKind kind) throws BaseException {
		List<BeanGoodDts> result=new ArrayList<BeanGoodDts>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goods_detail where gd_kindid = ? order by gd_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, kind.getGk_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanGoodDts p=new BeanGoodDts();
				p.setGd_id(rs.getInt(1));
				p.setGd_kindid(rs.getInt(2));
				p.setGd_name(rs.getString(3));
				p.setGd_price(rs.getDouble(4));
				p.setGd_reduce(rs.getDouble(5));
				result.add(p);
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
	/*public static void ModGoodDts(BeanGoodDts s) throws BaseException //暂时用不到 0
	 * +
	{
		Connection conn=null; 
		try {
			conn=DBUtil.getConnection();
			String sql="Update goods_detail set gk_name=?,gk_num=? where gk_id=?";
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
}*/
	public static void DeleteGoodDts(BeanGoodDts s) throws BaseException{
		Connection conn=null; 
		try {
			conn=DBUtil.getConnection();
			String sql="delete from goods_detail where gd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getGd_id());
			pst.execute();
			sql="Update goods_kind set gk_num=gk_num-1 where gk_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getGd_kindid());
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
	public static void InsGoodDts(BeanGoodDts s) throws BaseException{
		Connection conn=null; 
		try {
			conn=DBUtil.getConnection();
			String sql="select max(gd_id) from goods_detail";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			int i=1;;
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
				i+=rs.getInt(1);
			else
				i=1;
			sql="Insert into goods_detail(gd_id,gd_kindid,gd_name,gd_price,gd_reduce) values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setInt(2, s.getGd_kindid());
			pst.setString(3, s.getGd_name());
			pst.setDouble(4, s.getGd_price());
			pst.setDouble(5, s.getGd_reduce());
			pst.execute();
			sql="Update goods_kind set gk_num=gk_num+1 where gk_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getGd_kindid());
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
					e.printStackTrace();
				}
		}
	}
	public static void InsGoodKind(String s) throws BaseException{
		Connection conn=null; 
		try {
			conn=DBUtil.getConnection();
			String sql="select max(gd_id) from goods_detail";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			int i=1;;
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
				i+=rs.getInt(1);
			else
				i=1;
			sql="Insert into goods_kind(gk_id,gk_name,gk_num) values(?,?,0)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setString(2, s);
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
					e.printStackTrace();
				}
		}
	}
	public static void DeleteGoodKind(BeanGoodKind s) throws BaseException{
		Connection conn=null; 
		try {
			conn=DBUtil.getConnection();
			String sql="delete from goods_kind where gk_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getGk_id());
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
	public static void ModGoodKind(BeanGoodKind s) throws BaseException{
		Connection conn=null; 
		try {
			conn=DBUtil.getConnection();
			String sql="Update goods_kind set gk_name=? where gk_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, s.getGk_name());
			pst.setInt(2, s.getGk_id());
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
	public static List<BeanGoodComt> loadallcomm(int gid) throws BaseException {
		List<BeanGoodComt> result=new ArrayList<BeanGoodComt>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goods_comment where gc_gid=? and gc_uid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, gid);
			pst.setInt(2, BeanUserMsg.currentLoginUser.getUm_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanGoodComt p=new BeanGoodComt();
				p.setGc_gid(rs.getInt(1));
				p.setGc_sid(rs.getInt(2));
				p.setGc_uid(rs.getInt(3));
				p.setGc_word(rs.getString(4));
				p.setGc_date(rs.getDate(5));
				p.setGc_star(rs.getInt(6));
				result.add(p);
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
	public static void InsComm(BeanGoodComt s) throws BaseException{
		Connection conn=null; 
		try {
			conn=DBUtil.getConnection();
			String sql="Insert into goods_comment values(?,?,?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getGc_gid());
			pst.setInt(2, s.getGc_sid());
			pst.setInt(3, s.getGc_uid());
			pst.setString(4, s.getGc_word());
			pst.setDate(5, s.getGc_date());
			pst.setInt(6, s.getGc_star());
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
					e.printStackTrace();
				}
		}
	}
	public static void deleteComm(BeanGoodComt s) throws BaseException{
		Connection conn=null; 
		try {
			conn=DBUtil.getConnection();
			String sql="delete from goods_comment where gc_gid=? and gc_sid=? and gc_uid=? and gc_word=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getGc_gid());
			pst.setInt(2, s.getGc_sid());
			pst.setInt(3, s.getGc_uid());
			pst.setString(4, s.getGc_word());
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
					e.printStackTrace();
				}
		}
	}
}
