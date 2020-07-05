package control;

import util.BaseException;
import util.BusinessException;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

import util.DbException;
import model.BeanUserMsg;

public class UserMannager {
	public static BeanUserMsg currentUser=null;
	public void reg(String userid,String pwd,String pwd2,int sex,String phone,
			String mail,String city) throws BaseException {
		if(userid.length()==0) throw new BusinessException("�û�������Ϊ��");
		if(pwd.length()==0) throw new BusinessException("���벻��Ϊ��");
		if(pwd2.length()==0) throw new BusinessException("���벻��Ϊ��");
		if("0".equals(String.valueOf(phone))) throw new BusinessException("�绰���Ų���Ϊ��");
		if(!(pwd.equals(pwd2))) throw new BusinessException("�������벻��ͬ");
		if(mail.length()==0) throw new BusinessException("���䲻��Ϊ��");
		if(city.length()==0) throw new BusinessException("���в���Ϊ��");
		//����Ҫ���һ���绰���ŵ�λ����飿
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select um_name from user_msg where um_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("�û����Ѵ���");
			rs.close();pst.close();
			sql="select max(um_id) from user_msg";
			pst=conn.prepareStatement(sql);
			int i=1;
			rs=pst.executeQuery();
			if(rs.next())
				i+=rs.getInt(1);
			else
				i=1;
			sql="Insert into user_msg(um_id,um_name,um_sex,um_code,um_phone,um_mail,um_city,um_regt,um_status) values(?,?,?,?,?,?,?,?,0)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,i);
			pst.setString(2, userid);
			if(sex==1)
				pst.setString(3, "��");
			else
				pst.setString(3, "Ů");
			pst.setString(4, pwd);
			//pst.setInt(5, phone);
			pst.setString(5, phone);
			pst.setString(6, mail);
			pst.setString(7,city);
			pst.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
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
