package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import java.sql.Date;

import model.BeanOrderDts;
import model.BeanGoodOrder;
import model.BeanUserMsg;
import model.BeanGoodOrder;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class OrderMannager {
	public static List<BeanGoodOrder> loadallGoodK() throws BaseException{
		List<BeanGoodOrder> result=new ArrayList<BeanGoodOrder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from goods_order where go_uid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUserMsg.currentLoginUser.getUm_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanGoodOrder s=new BeanGoodOrder();
				s.setGo_oid(rs.getInt(1));
				s.setGo_sid(rs.getInt(2));
				s.setGo_uid(rs.getInt(3));
				s.setGo_rid(rs.getInt(4));
				s.setGo_newpri(rs.getDouble(5));
				s.setGo_reid(rs.getInt(6));
				s.setGo_cid(rs.getInt(7));
				s.setGo_sttime(rs.getTimestamp(8));
				s.setGo_edtime(rs.getTimestamp(9));
				/*s.setGo_sttime(rs.getTimestamp(8));
				s.setGo_edtime(rs.getTimestamp(9));*/
				s.setGo_addr(rs.getString(10));
				s.setGo_status(rs.getString(11));
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
	public static List<BeanGoodOrder> loadselectKind(String id) throws BaseException
	{
		List<BeanGoodOrder> result=new ArrayList<BeanGoodOrder>();
		Connection conn=null;
		try {
			int a=Integer.parseInt(id);
			conn=DBUtil.getConnection();
			String sql="select * from goods_order where go_uid=? and go_oid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUserMsg.currentLoginUser.getUm_id());
			pst.setInt(2,a);
			//pst.setString(1, id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				do {
				BeanGoodOrder s=new BeanGoodOrder();
				s.setGo_oid(rs.getInt(1));
				s.setGo_sid(rs.getInt(2));
				s.setGo_uid(rs.getInt(3));
				s.setGo_rid(rs.getInt(4));
				s.setGo_newpri(rs.getDouble(5));
				s.setGo_reid(rs.getInt(6));
				s.setGo_cid(rs.getInt(7));
				s.setGo_sttime(rs.getTimestamp(8));//转化为long型算了，后面再弄把
				s.setGo_edtime(rs.getTimestamp(9));
				/*s.setGo_sttime(rs.getTimestamp(8));
				s.setGo_edtime(rs.getTimestamp(9));*/
				s.setGo_addr(rs.getString(10));
				s.setGo_status(rs.getString(11));
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
	
	public static List<BeanOrderDts> loadGoods(BeanGoodOrder kind) throws BaseException {
		List<BeanOrderDts> result=new ArrayList<BeanOrderDts>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from order_detail where od_oid = ? order by od_oid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, kind.getGo_oid());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanOrderDts p=new BeanOrderDts();
				p.setOd_oid(rs.getInt(1));
				p.setOd_gid(rs.getInt(2));
				p.setOd_count(rs.getInt(3));
				p.setOd_price(rs.getDouble(4));
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
	public static void InsOrder(BeanGoodOrder s) throws BaseException {//已经初始化过了。应该是不用再进行最大的id查找了
		//if(s.getSp_name().length()==0) throw new BusinessException("商家名不能为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			/* sql="select max(rd_id) from rider";
			 pst=conn.prepareStatement(sql);
			int i=1;
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
				i+=rs.getInt(1);
			else
				i=1;*/
			String sql="insert into goods_order values(?,?,?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getGo_oid());
			pst.setInt(2,s.getGo_sid());
			pst.setInt(3, s.getGo_uid());
			pst.setInt(4, s.getGo_rid());
			pst.setDouble(5, s.getGo_newpri());
			pst.setInt(6, s.getGo_reid());
			pst.setInt(7, s.getGo_cid());
			/*pst.setDate(8, new java.sql.Date(s.getGo_sttime().getTime()));
			pst.setDate(9, new java.sql.Date(s.getGo_edtime().getTime()));*/
			pst.setTimestamp(8,new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(9,new java.sql.Timestamp(System.currentTimeMillis()+60*1000*60));
			pst.setString(10,s.getGo_addr());
			pst.setString(11, s.getGo_status());
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
	public static void InsOrderDts(BeanOrderDts s) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="insert into order_detail values(?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,s.getOd_oid());
			pst.setInt(2, s.getOd_gid());
			pst.setInt(3, s.getOd_count());
			pst.setDouble(4, s.getOd_price());
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
