package control;

import java.awt.event.ActionEvent;
import java.beans.Beans;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.*;
import model.BeanShop;

public class ShopMannager {
	public static List<BeanShop> loadallShop() throws BaseException{
		List<BeanShop> result=new ArrayList<BeanShop>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from shop order by sp_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanShop s=new BeanShop();
				s.setSp_id(rs.getInt(1));
				s.setSp_name(rs.getString(2));
				s.setSp_star(rs.getInt(3));
				s.setSp_aver(rs.getDouble(4));
				s.setSp_sum(rs.getInt(5));
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
	public static List<BeanShop> loadselectShop(String name) throws BaseException
	{
		List<BeanShop> result=new ArrayList<BeanShop>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from shop where sp_name LIKE ? order by sp_id ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,"%"+name+"%");
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				do {
				BeanShop s=new BeanShop();
				s.setSp_id(rs.getInt(1));
				s.setSp_name(rs.getString(2));
				s.setSp_star(rs.getInt(3));
				s.setSp_aver(rs.getDouble(4));
				s.setSp_sum(rs.getInt(5));
				result.add(s);
				}while(rs.next());
			}
			else {
				throw new BusinessException("商家不 存在");
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
	public void actionPerformed(ActionEvent ac) {
		
	}
}
