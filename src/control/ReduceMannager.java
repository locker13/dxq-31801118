package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.BeanReduce;
import model.BeanShop;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class ReduceMannager {
	public static List<BeanReduce> loadallRed() throws BaseException{
		List<BeanReduce> result=new ArrayList<BeanReduce>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from reduce order by rde_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanReduce s=new BeanReduce();
				s.setRde_id(rs.getInt(1));
				s.setRde_limit(rs.getInt(2));
				s.setRde_money(rs.getInt(3));
				if(rs.getInt(4)==1)
					s.setRde_plus(true);
				else
					s.setRde_plus(false);
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
	public static List<BeanReduce> loadselectShop(String id) throws BaseException
	{
		List<BeanReduce> result=new ArrayList<BeanReduce>();
		Connection conn=null;
		try {
			int a=Integer.parseInt(id);
			conn=DBUtil.getConnection();
			String sql="select * from reduce where rde_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,a);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				do {
				BeanReduce s=new BeanReduce();
				s.setRde_id(a);
				s.setRde_limit(rs.getInt(2));
				s.setRde_money(rs.getInt(3));
				if(rs.getInt(4)==1)
					s.setRde_plus(true);
				else
					s.setRde_plus(false);
				result.add(s);
				}while(rs.next());
			}
			else {
				throw new BusinessException("满减不 存在");
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
public static void deleteRed(BeanReduce s)throws BaseException{
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from reduce where rde_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getRde_id());
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
public static void modRed(BeanReduce s) throws BaseException {
	Connection conn=null;
	try {
		conn=DBUtil.getConnection();
		String sql="UPDATE reduce set rde_limit=?,rde_money=?,rde_plus=? where rde_id=?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,s.getRde_limit());
		pst.setDouble(2, s.getRde_money());
		if(s.isRde_plus()==true)
			pst.setInt(3, 1);
		else
			pst.setInt(3, 0);
		pst.setInt(4, s.getRde_id());
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
public static void InsRed(BeanReduce s) throws BaseException {
	//if(s.getSp_name().length()==0) throw new BusinessException("商家名不能为空");
	Connection conn=null;
	try {
		conn=DBUtil.getConnection();
		String sql="select max(rde_id) from reduce";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		int i=1;
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next())
			i+=rs.getInt(1);
		else
			i=1;
		sql="insert into reduce values(?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setInt(2,s.getRde_limit());
		pst.setDouble(3, s.getRde_money());
		if(s.isRde_plus()==true)
			pst.setInt(4, 1);
		else
			pst.setInt(4, 0);
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
