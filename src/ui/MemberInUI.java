package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.UserMannager;
import model.BeanUserMsg;
import util.BaseException;

public class MemberInUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	JButton button = new JButton("\u6210\u4E3A\u4F1A\u5458");
	JButton button_1 = new JButton("\u6211\u518D\u60F3\u60F3");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberInUI frame = new MemberInUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemberInUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button.setFont(new Font("宋体", Font.PLAIN, 25));
		button.setBounds(10, 160, 140, 68);
		contentPane.add(button);
		button.addActionListener(this);
		
		button_1.setFont(new Font("宋体", Font.PLAIN, 25));
		button_1.setBounds(240, 160, 140, 68);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		JLabel label = new JLabel("\u60A8\u5F53\u524D\u4E0D\u662F\u4F1A\u5458\uFF01");
		label.setFont(new Font("宋体", Font.PLAIN, 27));
		label.setBounds(89, -13, 240, 163);
		contentPane.add(label);
	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		if(ac.getSource()==this.button)
		{
			try {
				UserMannager.MemberIn(BeanUserMsg.currentLoginUser);
				BeanUserMsg.currentLoginUser.setUm_status(1);
				winMessage("成功开通一个月会员！");
				this.setVisible(false);
				UserUI frame=new UserUI();
				frame.setVisible(true);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(ac.getSource()==this.button_1)
		{
			this.setVisible(false);
			UserUI frame=new UserUI();
			frame.setVisible(true);
		}
		
	}

}
