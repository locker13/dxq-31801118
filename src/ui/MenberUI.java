package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.UserMannager;
import model.BeanUserMsg;
import util.BaseException;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MenberUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	JButton button_2 = new JButton("\u8FD4\u56DE");

	public MenberUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
			JLabel lblNewLabel = new JLabel(String.valueOf(BeanUserMsg.currentLoginUser.getUm_endtime()));
			JLabel label_1 = new JLabel("\u5C0A\u656C\u7684\u4F1A\u5458\uFF0C\u60A8\u597D\uFF01");
			label_1.setFont(new Font("宋体", Font.PLAIN, 27));
			label_1.setBounds(113, 38, 270, 70);
			contentPane.add(label_1);
			
			JLabel label_2 = new JLabel("\u4F1A\u5458\u5230\u671F\u65E5\u671F\uFF1A");
			label_2.setFont(new Font("宋体", Font.PLAIN, 20));
			label_2.setBounds(34, 159, 158, 38);
			contentPane.add(label_2);
			
			
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			lblNewLabel.setBounds(246, 164, 234, 24);
			contentPane.add(lblNewLabel);
			
			
			button_2.setFont(new Font("宋体", Font.PLAIN, 20));
			button_2.setBounds(315, 279, 135, 38);
			contentPane.add(button_2);
			button_2.addActionListener(this);
		
		
	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		
		if(ac.getSource()==this.button_2)
		{
			this.setVisible(false);
			UserUI frame=new UserUI();
			frame.setVisible(true);
		}
	}
}
