package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BeanRider;
import util.BaseException;
import util.DBUtil;
import util.DbException;

public class RiderMannager {
	public static List<BeanRider> loadallRider() throws BaseException{
		List<BeanRider> result=new ArrayList<BeanRider>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from rider order by rd_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanRider s=new BeanRider();
				s.setRd_id(rs.getInt(1));
				s.setRd_name(rs.getString(2));
				s.setRd_in(rs.getDate(3));
				s.setRd_status(rs.getString(4));
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
public static void deleteRider(BeanRider s)throws BaseException{
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from rider where rd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getRd_id());
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
public static void modRider(BeanRider s) throws BaseException {
	Connection conn=null;
	try {
		conn=DBUtil.getConnection();
		String sql="UPDATE rider set rd_name=?,rd_in=?,rd_status=? where rd_id=?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(4, s.getRd_id());
		pst.setString(1,s.getRd_name());
		pst.setDate(2, s.getRd_in());
		pst.setString(3, s.getRd_status());
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
public static void InsRider(BeanRider s) throws BaseException {
	//if(s.getSp_name().length()==0) throw new BusinessException("商家名不能为空");
	Connection conn=null;
	try {
		conn=DBUtil.getConnection();
		String sql="select max(rd_id) from rider";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		int i=1;
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next())
			i+=rs.getInt(1);
		else
			i=1;
		sql="insert into rider values(?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setInt(1, i);
		pst.setString(2,s.getRd_name());
		pst.setDate(3, s.getRd_in());
		pst.setString(4, s.getRd_status());
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
public static int MaxRdId() throws BaseException {
	Connection conn=null;
	try {
		conn=DBUtil.getConnection();
		String sql="select max(rd_id) from rider";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		int i=0;
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next())
			i+=rs.getInt(1);
		else
			i=1;
		return i;
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
