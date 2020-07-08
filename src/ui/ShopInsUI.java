package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ShopMannager;
import model.BeanShop;
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

public class ShopInsUI extends JFrame implements ActionListener{
	BeanShop sf=new BeanShop();
	private JPanel contentPane;
	private JTextField txtname;
	private JTextField textaver;
	private JTextField textsum;
	JButton button = new JButton("\u786E\u8BA4");
	JButton button_1 = new JButton("\u53D6\u6D88");
	JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopModUI frame = new ShopModUI();
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
	
	public ShopInsUI(){

		setTitle("\u4FE1\u606F\u6DFB\u52A0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 263, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u5BB6\u540D\u79F0");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(10, 29, 64, 34);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5546\u5BB6\u661F\u7EA7");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(10, 85, 64, 34);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u4EBA\u5747\u6D88\u8D39");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(10, 145, 64, 34);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u603B\u9500\u552E\u91CF");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(10, 209, 64, 34);
		contentPane.add(label_3);
		
		txtname = new JTextField();
		txtname.setBounds(97, 37, 125, 21);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		textaver = new JTextField();
		textaver.setColumns(10);
		textaver.setBounds(97, 153, 125, 21);
		contentPane.add(textaver);
		
		textsum = new JTextField();
		textsum.setColumns(10);
		textsum.setBounds(97, 217, 125, 21);
		contentPane.add(textsum);
		
		
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(10, 300, 104, 34);
		contentPane.add(button);
		button.addActionListener(this);
		
		
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(133, 300, 104, 34);
		button_1.addActionListener(this);
		contentPane.add(button_1);
		
		
		comboBox.setBounds(97, 93, 125, 21);
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		comboBox.addItem("5");
		comboBox.addActionListener(this);;
		contentPane.add(comboBox);
		
		

	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		if (ac.getSource() == this.button) {
		
		sf.setSp_name(txtname.getText());
		try {
			sf.setSp_star(Integer.parseInt((String) comboBox.getSelectedItem()));
			sf.setSp_aver(Double.parseDouble(textaver.getText()));
			sf.setSp_sum(Integer.parseInt(textsum.getText()));
		}catch (NumberFormatException e) {
		    //e.printStackTrace();//捕捉处理异常，暂时没有
		    winMessage("请输入正确格式");
		    return;
		}
		try {
			ShopMannager sm=new ShopMannager();
			sm.Insshop(sf);
			this.setVisible(false);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
		}
		
	}
		else if(ac.getSource() == this.button_1)
		{
			this.setVisible(false);
		}
}
}
