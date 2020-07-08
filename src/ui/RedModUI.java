package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jboss.jandex.ThrowsTypeTarget;

import control.ReduceMannager;
import control.ShopMannager;
import model.BeanReduce;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class RedModUI extends JFrame implements ActionListener{
	BeanReduce sf=new BeanReduce();
	private ButtonGroup buttongroup = new ButtonGroup();
	private JPanel contentPane;
	private JTextField textaver;
	private JTextField textsum;
	JButton button = new JButton("\u786E\u8BA4");
	JButton button_1 = new JButton("\u53D6\u6D88");
	JRadioButton radioButton = new JRadioButton("\u53EF");
	JRadioButton radioButton_1 = new JRadioButton("\u5426");

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
	
	public RedModUI(BeanReduce s){
		sf.setRde_id(s.getRde_id());
		
		setTitle("\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 263, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_2 = new JLabel("\u6EE1\u51CF\u91D1\u989D");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(10, 37, 64, 34);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u4F18\u60E0\u91D1\u989D");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(10, 104, 64, 34);
		contentPane.add(label_3);
		
		textaver = new JTextField();
		textaver.setText(String.valueOf(s.getRde_limit()));
		textaver.setColumns(10);
		textaver.setBounds(84, 45, 125, 21);
		contentPane.add(textaver);
		
		textsum = new JTextField();
		textsum.setText(String.valueOf(s.getRde_money()));
		textsum.setColumns(10);
		textsum.setBounds(84, 112, 125, 21);
		contentPane.add(textsum);
		
		
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(10, 300, 104, 34);
		contentPane.add(button);
		button.addActionListener(this);
		
		
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(133, 300, 104, 34);
		button_1.addActionListener(this);
		contentPane.add(button_1);
		
		
		radioButton.setBounds(116, 176, 121, 23);
		contentPane.add(radioButton);
		radioButton_1.setBounds(116, 225, 121, 23);
		contentPane.add(radioButton_1);
		buttongroup.add(radioButton);
		buttongroup.add(radioButton_1);
		if(s.isRde_plus()==true)
			radioButton.setSelected(true);
		else
			radioButton_1.setSelected(true);
		
		JLabel label = new JLabel("\u53E0\u52A0\u6D88\u8D39\u5238");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(10, 199, 104, 41);
		contentPane.add(label);
		
		

	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		if (ac.getSource() == this.button) {
		try {
			sf.setRde_money(Double.parseDouble(textsum.getText()));
			sf.setRde_limit(Integer.parseInt(textaver.getText()));
			if(sf.getRde_money()>sf.getRde_limit()) { winMessage("优惠金额不可以超出满减金额"); return;}
			if(radioButton.isSelected())
				sf.setRde_plus(true);
			else
				sf.setRde_plus(false);
		}catch (NumberFormatException e) {
		    //e.printStackTrace();//捕捉处理异常，暂时没有
		    winMessage("请输入正确格式");
		    return;
		}
		try {
			ReduceMannager sm=new ReduceMannager();
			sm.modRed(sf);
			
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
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
