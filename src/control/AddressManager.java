package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BeanShop;
import model.BeanUserMsg;
import model.Beanaddr;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class AddressManager {
	public static List<Beanaddr> loadallShop(BeanUserMsg um) throws BaseException{
		List<Beanaddr> result=new ArrayList<Beanaddr>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from address where ad_uid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, um.getUm_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Beanaddr s=new Beanaddr();
				s.setAd_id(rs.getInt(1));
				s.setAd_uid(rs.getInt(2));
				s.setAd_prin(rs.getString(3));
				s.setAd_city(rs.getString(4));
				s.setAd_block(rs.getString(5));
				s.setAd_addr(rs.getString(6));
				s.setAd_person(rs.getString(7));
				s.setAd_phone(rs.getString(8));
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
public static void deleteaddr(Beanaddr s)throws BaseException{
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from address where ad_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getAd_id());
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
public static void Insaddr(Beanaddr s) throws BaseException {
	if(s.getAd_addr().length()==0) throw new BusinessException("地址不能为空");
	if(s.getAd_prin().length()==0) throw new BusinessException("省不能为空");
	if(s.getAd_city().length()==0) throw new BusinessException("市不能为空");
	if(s.getAd_block().length()==0) throw new BusinessException("区不能为空");
	if(s.getAd_person().length()==0) throw new BusinessException("联系人不能为空");
	Connection conn=null;
	try {
		conn=DBUtil.getConnection();
		String sql="select max(ad_id) from address";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		int i=1;
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next())
			i+=rs.getInt(1);
		else
			i=1;
		sql="insert into address values(?,?,?,?,?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setInt(1,i);
		pst.setInt(2, s.getAd_uid());
		pst.setString(3, s.getAd_prin());
		pst.setString(4, s.getAd_city());
		pst.setString(5, s.getAd_block());
		pst.setString(6, s.getAd_addr());
		pst.setString(7, s.getAd_person());
		pst.setString(8, s.getAd_phone());
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
public static void Modaddr(Beanaddr s) throws BaseException {
	if(s.getAd_addr().length()==0) throw new BusinessException("地址不能为空");
	if(s.getAd_prin().length()==0) throw new BusinessException("省不能为空");
	if(s.getAd_city().length()==0) throw new BusinessException("市不能为空");
	if(s.getAd_block().length()==0) throw new BusinessException("区不能为空");
	if(s.getAd_person().length()==0) throw new BusinessException("联系人不能为空");
	Connection conn=null;
	try {
		conn=DBUtil.getConnection();
		String sql="Update address set ad_prin=?,ad_city=?,ad_block=?,ad_adr=?,ad_person=?,ad_phone=? where ad_id=?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, s.getAd_prin());
		pst.setString(2, s.getAd_city());
		pst.setString(3, s.getAd_block());
		pst.setString(4, s.getAd_addr());
		pst.setString(5, s.getAd_person());
		pst.setString(6, s.getAd_phone());
		pst.setInt(7, s.getAd_id());
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
