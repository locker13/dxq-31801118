package control;

import java.sql.Connection;
import java.sql.SQLException;

import util.DbException;
import model.BeanAdmin;
import model.BeanUserMsg;
import util.BaseException;
import util.DBUtil;
import util.BusinessException;
public class LoginMannager {
	public BeanAdmin loginAdmin(String userid,String pwd) throws BaseException
	{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select am_id,am_name,am_code from admin where am_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("µÇÂ½ÕËºÅ²» ´æÔÚ");
			BeanAdmin a=new BeanAdmin();
			a.setAm_id(rs.getInt(1));
			a.setAm_name(rs.getString(2));
			a.setAm_code(rs.getString(3));
			if(!(pwd.equals(a.getAm_code())))throw new BusinessException("ÃÜÂë´íÎó");
			rs.close();
			pst.close();
			return a;
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
	public BeanUserMsg loginUser(String userid,String pwd) throws BaseException
	{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user_msg where um_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("µÇÂ½ÕËºÅ²» ´æÔÚ");
			BeanUserMsg a=new BeanUserMsg();
			a.setUm_id(rs.getInt(1));
			a.setUm_name(rs.getString(2));
			a.setUm_sex(rs.getString(3));
			a.setUm_code(rs.getString(4));
			//a.setUm_phone(rs.getInt(5));
			a.setUm_phone(rs.getString(5));
			a.setUm_mail(rs.getString(6));
			a.setUm_city(rs.getString(7));
			a.setUm_status(rs.getBoolean(8));
			a.setUm_endtime(rs.getDate(9));
			if(!(pwd.equals(a.getUm_code())))throw new BusinessException("ÃÜÂë´íÎó");
			rs.close();
			pst.close();
			return a;
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
