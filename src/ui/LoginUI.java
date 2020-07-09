package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import control.LoginMannager;
import model.BeanAdmin;
import model.BeanUserMsg;
import util.BaseException;

public class LoginUI extends JDialog implements ActionListener {
	private JFrame jf = new JFrame("外卖系统");
	private Container con = jf.getContentPane();// 获得面板
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension sc = toolkit.getScreenSize();// 获得屏幕尺寸
	private JLabel title = new JLabel("欢迎使用外卖");
	private JLabel name1 = new JLabel("用户名");
	private JLabel pass1 = new JLabel("密  码");
	private JTextField textName = new JTextField();
	private JPasswordField textPs = new JPasswordField();// 密码框

	private JRadioButton choice1 = new JRadioButton("用户");
	private JRadioButton choice2 = new JRadioButton("管理员");

	private JLabel code1 = new JLabel("验证码");
	private JTextField textCode = new JTextField();
	private JLabel code2 = new JLabel();

	private JButton btn_login = new JButton("登录");
	private JButton btn_rgist = new JButton("注册");
	
	// 按钮

	private Font font = new Font("宋体", 1, 28);
	private Font font1 = new Font("宋体", 0, 20);
	private Font font2 = new Font("宋体", 0, 18);
	private ImageIcon loginbg = new ImageIcon("images/aaa.jpg");
	// 字体，样式（粗体，斜体），大小
	public LoginUI() {
		con.setLayout(null);
		jf.setSize(1000, 618);
		jf.setLocation((sc.width - 1000) / 2, (sc.height - 618) / 2);
		jf.setResizable(false);// 窗口大小不可变
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con.setVisible(true);
		JLabel maxlabel = new JLabel(loginbg);
		title.setBounds(375, 10, 370, 30);
		title.setFont(font);
//		title.setForeground(Color.black);
		title.setForeground(Color.white);

		name1.setBounds(140, 140, 85, 30);// 账号的位置大小
		name1.setFont(font1);// 字体
		name1.setForeground(Color.blue);// name1字的颜色

		pass1.setBounds(140, 190, 85, 30);// 密码的位置大小
		pass1.setForeground(Color.blue);
		pass1.setFont(font1);

		textName.setBounds(200, 143, 140, 25);
		textName.setBorder(null);// 边框
		textName.setFont(font1);
		textPs.setBounds(200, 193, 140, 25);
		textPs.setBorder(null);
		textPs.setEchoChar('*');// 可以将密码显示为* ；默认为·
		textPs.setFont(font1);
		choice1.setBounds(140, 290, 120, 25);
		choice1.setSelected(true);// 选择用户，默认普通用户登录
		choice2.setBounds(260, 290, 80, 25);

		code1.setBounds(140, 240, 60, 25);
		code1.setFont(font1);
		code1.setForeground(Color.black);
		textCode.setBounds(200, 240, 95, 25);
		textCode.setBorder(null);
		textCode.setFont(font1);
		code2.setBounds(300, 240, 70, 25);
		code2.setFont(font1);
		code2.setText(code());
		code2.setForeground(Color.black);
		code1.setVisible(false);
		code2.setVisible(false);
		textCode.setVisible(false);

		btn_login.setBounds(140, 340, 90, 25);//140, 340, 90, 25
		btn_login.setFont(font2);
		btn_login.addActionListener(this);
		jf.getRootPane().setDefaultButton(btn_login);//回车默认是登录按钮
		
		
		btn_rgist.setBounds(250, 340, 90, 25);//250, 340, 90, 25
		btn_rgist.setFont(font2);
		btn_rgist.addActionListener(this);

		
		JLabel bg = new JLabel(loginbg);
		
		maxlabel.add(title);
		maxlabel.add(name1);
		maxlabel.add(pass1);
		maxlabel.add(textName);
		maxlabel.add(textPs);
		maxlabel.add(choice1);
		maxlabel.add(choice2);

		buttongroup.add(choice1);
		buttongroup.add(choice2);

		maxlabel.add(code1);
		maxlabel.add(code2);
		maxlabel.add(textCode);
		maxlabel.add(btn_login);
		maxlabel.add(btn_rgist);
		maxlabel.setBounds(0, 0, 999, 617);
		con.add(maxlabel);
	}
	private String code() {//用于密码加密？
		int m = 0;
		for (int i = 0; i < 4; i++) {
			m *= 10;
			m += (int) (Math.random() * 9 + 1);
		}
		return ((Integer) m).toString();
	}
	public void cleanUserInfo() {
		this.textName.setText("");
		this.textPs.setText("");
		this.textCode.setText("");
	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	private ButtonGroup buttongroup = new ButtonGroup();
		@Override
		public void actionPerformed(ActionEvent ac) {
			if (ac.getSource() == this.btn_login) {
				String name = textName.getText();
				String pswd = new String(textPs.getPassword());
				if (name.equals("") || pswd.equals("")) {
					winMessage("账号、密码不能为空！");
					cleanUserInfo();
					this.code2.setText(code());
				} else {
					/*int code1 = 1;
					if (code1==1) {*/
						int choice;
						if (choice1.isSelected())//读取管理员模式的登陆情况
							choice = 0;
						else
							choice = 1;
						LoginMannager lg = new LoginMannager();
						try {
						if(choice==0)
							BeanUserMsg.currentLoginUser = lg.loginUser(name, pswd);//搜索该用户返回即可，通过choice来选择搜索哪个表
						else if(choice==1)
							BeanAdmin.currentLoginAdmin=lg.loginAdmin(name, pswd);
							} catch (BaseException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
								return;
							}
						if(BeanAdmin.currentLoginAdmin==null) {//进入用户界面
							UserMainUI frame;
							try {
								frame = new UserMainUI();
								frame.setVisible(true);
							} catch (BaseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						else {
							AdminUI frame=new AdminUI();
							frame.setVisible(true);
						}
								this.jf.dispose();
						//}
					/*} else {
						winMessage("验证码不正确！");
						textCode.setText("");
						this.code2.setText(code());
					}*/
				}
			} else if (ac.getSource() == this.btn_rgist) {
				RegisterUI rg=new RegisterUI();
				//rg.main(null);//跳出注册界面
				//new RegisterUI();
				RegisterUI frame = new RegisterUI();
				frame.setVisible(true);
				//this.jf.dispose();// 点击按钮时,new一个frame，原先frame销毁
			}
		
		}
}
