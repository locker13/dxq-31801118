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
	private JFrame jf = new JFrame("����ϵͳ");
	private Container con = jf.getContentPane();// ������
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension sc = toolkit.getScreenSize();// �����Ļ�ߴ�
	private JLabel title = new JLabel("��ӭʹ������");
	private JLabel name1 = new JLabel("�û���");
	private JLabel pass1 = new JLabel("��  ��");
	private JTextField textName = new JTextField();
	private JPasswordField textPs = new JPasswordField();// �����

	private JRadioButton choice1 = new JRadioButton("�û�");
	private JRadioButton choice2 = new JRadioButton("����Ա");

	private JLabel code1 = new JLabel("��֤��");
	private JTextField textCode = new JTextField();
	private JLabel code2 = new JLabel();

	private JButton btn_login = new JButton("��¼");
	private JButton btn_rgist = new JButton("ע��");
	
	// ��ť

	private Font font = new Font("����", 1, 28);
	private Font font1 = new Font("����", 0, 20);
	private Font font2 = new Font("����", 0, 18);
	private ImageIcon loginbg = new ImageIcon("images/aaa.jpg");
	// ���壬��ʽ�����壬б�壩����С
	public LoginUI() {
		con.setLayout(null);
		jf.setSize(1000, 618);
		jf.setLocation((sc.width - 1000) / 2, (sc.height - 618) / 2);
		jf.setResizable(false);// ���ڴ�С���ɱ�
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con.setVisible(true);
		JLabel maxlabel = new JLabel(loginbg);
		title.setBounds(375, 10, 370, 30);
		title.setFont(font);
//		title.setForeground(Color.black);
		title.setForeground(Color.yellow);

		name1.setBounds(140, 140, 85, 30);// �˺ŵ�λ�ô�С
		name1.setFont(font1);// ����
		name1.setForeground(Color.blue);// name1�ֵ���ɫ

		pass1.setBounds(140, 190, 85, 30);// �����λ�ô�С
		pass1.setForeground(Color.blue);
		pass1.setFont(font1);

		textName.setBounds(200, 143, 140, 25);
		textName.setBorder(null);// �߿�
		textName.setFont(font1);
		textPs.setBounds(200, 193, 140, 25);
		textPs.setBorder(null);
		textPs.setEchoChar('*');// ���Խ�������ʾΪ* ��Ĭ��Ϊ��
		textPs.setFont(font1);
		choice1.setBounds(140, 290, 120, 25);
		choice1.setSelected(true);// ѡ���û���Ĭ����ͨ�û���¼
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
		jf.getRootPane().setDefaultButton(btn_login);//�س�Ĭ���ǵ�¼��ť
		
		
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
	private String code() {//����������ܣ�
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
	public static void winMessage(String str) {// ��ʾ���ڣ��ж���ط�����
		JOptionPane.showMessageDialog(null, str, "��ʾ",JOptionPane.INFORMATION_MESSAGE);
	}
	private ButtonGroup buttongroup = new ButtonGroup();
		@Override
		public void actionPerformed(ActionEvent ac) {
			if (ac.getSource() == this.btn_login) {
				String name = textName.getText();
				String pswd = new String(textPs.getPassword());
				if (name.equals("") || pswd.equals("")) {
					winMessage("�˺š����벻��Ϊ�գ�");
					cleanUserInfo();
					this.code2.setText(code());
				} else {
					/*int code1 = 1;
					if (code1==1) {*/
						int choice;
						if (choice1.isSelected())//��ȡ����Աģʽ�ĵ�½���
							choice = 0;
						else
							choice = 1;
						LoginMannager lg = new LoginMannager();
						try {
						if(choice==0)
							BeanUserMsg.currentLoginUser = lg.loginUser(name, pswd);//�������û����ؼ��ɣ�ͨ��choice��ѡ�������ĸ���
						else if(choice==1)
							BeanAdmin.currentLoginAdmin=lg.loginAdmin(name, pswd);
							} catch (BaseException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
								return;
							}
						if(BeanAdmin.currentLoginAdmin==null) {//�����û�����
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
				}
			} else if (ac.getSource() == this.btn_rgist) {
				RegisterUI rg=new RegisterUI();
				RegisterUI frame = new RegisterUI();
				frame.setVisible(true);
			}
		
		}
}
