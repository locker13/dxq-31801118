package control;

import util.BaseException;
import util.BusinessException;
import util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import util.DbException;
import model.BeanUserMsg;

public class UserMannager {
	public static String nextMonth(){
		java.util.Date date = new java.util.Date();
		int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));//取到年份值
		int month=Integer.parseInt(new SimpleDateFormat("MM").format(date))+1;//取到鱼粉值
		int day=Integer.parseInt(new SimpleDateFormat("dd").format(date));//取到天值
		if(month==0){
			year-=1;month=12;
		}
		else if(day>28){
			if(month==2){
				if(year%400==0||(year %4==0&&year%100!=0)){
					day=29;
				}else day=28;
			}else if((month==4||month==6||month==9||month==11)&&day==31)
			{
				day=30;
			}
		}
		String y = year+"";String m ="";String d ="";
		if(month<10) m = "0"+month;
		else m=month+"";
		if(day<10) d = "0"+day;
		else d = day+"";
		//System.out.println(y+"-"+m+"-"+d);
		return y+"-"+m+"-"+d;
	}
	public static void reg(String userid,String pwd,String pwd2,int sex,String phone,
			String mail,String city) throws BaseException {
		if(userid.length()==0) throw new BusinessException("用户名不能为空");
		if(pwd.length()==0) throw new BusinessException("密码不能为空");
		if(pwd2.length()==0) throw new BusinessException("密码不能为空");
		if("0".equals(String.valueOf(phone))) throw new BusinessException("电话番号不能为空");
		if(!(pwd.equals(pwd2))) throw new BusinessException("两次密码不相同");
		if(mail.length()==0) throw new BusinessException("邮箱不能为空");
		if(city.length()==0) throw new BusinessException("城市不能为空");
		//这里要添加一个电话番号的位数检查？
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select um_name from user_msg where um_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("用户名已存在");
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
				pst.setString(3, "男");
			else
				pst.setString(3, "女");
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
	public static void modUserpwd(BeanUserMsg user,String pwd,String pwd1,String pwd2)throws BaseException{//注册用
		Connection conn=null;
		if(pwd1.length()==0) throw new BusinessException("密码不能为空");
		if(pwd2.length()==0) throw new BusinessException("密码不能为空");
		if(pwd.length()==0) throw new BusinessException("密码不能为空");
		if(!(pwd1.equals(pwd2))) throw new BusinessException("两次密码不相同");
		if(pwd==user.getUm_code())
		{
			try {
				conn=DBUtil.getConnection();
				String sql="UPDATE user_msg SET um_code=? where um_id=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,pwd1);
				pst.setInt(2,user.getUm_id());
				pst.execute();
				pst.close();
			} catch (SQLException e) {
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
	public static void MemberIn(BeanUserMsg s) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE user_msg SET um_status=1,um_endtime=? where um_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,nextMonth());
			pst.setInt(2,s.getUm_id());
			pst.execute();
			SimpleDateFormat q = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fd=q.parse(nextMonth());
			BeanUserMsg.currentLoginUser.setUm_endtime(new java.sql.Date(fd.getTime()));
			sql="select * from user_coupon where uc_uid=? and uc_cid=1";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUserMsg.currentLoginUser.getUm_id());
			java.sql.ResultSet rs=pst.executeQuery();
			sql="Insert into user_coupon values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUserMsg.currentLoginUser.getUm_id());
			pst.setInt(2, 1);
			pst.setInt(3, 10);
			pst.setInt(4, 2);
			pst.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()+604800*1000));
			pst.setInt(6, 1);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
