package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.AddressManager;
import model.Beanaddr;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AddrModUI extends JFrame implements ActionListener{
	Beanaddr sf=new Beanaddr();
	private JPanel contentPane;
	private JTextField textprin;
	private JTextField textblock;
	private JTextField textaddr;
	JButton button = new JButton("\u786E\u8BA4");
	JButton button_1 = new JButton("\u53D6\u6D88");
	private JTextField textcity;
	private JTextField textperson;
	private JTextField textphone;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddrModUI frame = new AddrModUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	
	public AddrModUI(Beanaddr s){
		sf.setAd_id(s.getAd_id());
		
		setTitle("\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7701");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(10, 29, 64, 34);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5E02");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(10, 85, 64, 34);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u533A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(10, 145, 64, 34);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5730\u5740");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(10, 209, 64, 34);
		contentPane.add(label_3);
		
		textprin = new JTextField();
		textprin.setBounds(97, 37, 125, 21);
		textprin.setText(s.getAd_prin());
		contentPane.add(textprin);
		textprin.setColumns(10);
		
		textblock = new JTextField();
		textblock.setText(s.getAd_block());
		textblock.setColumns(10);
		textblock.setBounds(97, 153, 125, 21);
		contentPane.add(textblock);
		
		textaddr = new JTextField();
		textaddr.setText(s.getAd_addr());
		textaddr.setColumns(10);
		textaddr.setText(s.getAd_addr());
		textaddr.setBounds(97, 217, 125, 21);
		contentPane.add(textaddr);
		
		
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(10, 368, 104, 34);
		contentPane.add(button);
		button.addActionListener(this);
		
		
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(138, 368, 104, 34);
		button_1.addActionListener(this);
		contentPane.add(button_1);
		
		textcity = new JTextField();
		textcity.setText((String) null);
		textcity.setColumns(10);
		textcity.setBounds(97, 93, 125, 21);
		textcity.setText(s.getAd_city());
		
		contentPane.add(textcity);
		
		JLabel label_4 = new JLabel("\u8054\u7CFB\u4EBA");
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		label_4.setBounds(10, 253, 64, 34);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("\u7535\u8BDD");
		label_5.setFont(new Font("宋体", Font.PLAIN, 16));
		label_5.setBounds(10, 297, 64, 34);
		contentPane.add(label_5);
		
		textperson = new JTextField();
		textperson.setText((String) null);
		textperson.setColumns(10);
		textperson.setBounds(97, 261, 125, 21);
		textperson.setText(s.getAd_person());
		contentPane.add(textperson);
		
		textphone = new JTextField();
		textphone.setText((String) null);
		textphone.setColumns(10);
		textphone.setBounds(97, 305, 125, 21);
		textphone.setText(s.getAd_phone());
		contentPane.add(textphone);
		
		

	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		if (ac.getSource() == this.button) {
		
		sf.setAd_prin(textprin.getText());
		sf.setAd_city(textcity.getText());
		sf.setAd_block(textblock.getText());
		sf.setAd_person(textperson.getText());
		sf.setAd_addr(textaddr.getText());
		sf.setAd_phone(textphone.getText());
		try {
			AddressManager sm=new AddressManager();
			sm.Modaddr(sf);
			this.setVisible(false);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
		else if(ac.getSource() == this.button_1)
		{
			this.setVisible(false);
		}
}
}
