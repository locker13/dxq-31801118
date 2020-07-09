package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import control.RiderMannager;
import control.ShopMannager;
import model.BeanRider;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class RiderInsUI extends JFrame implements ActionListener{
	BeanRider sf=new BeanRider();
	private ButtonGroup buttongroup = new ButtonGroup();
	private JPanel contentPane;
	private JTextField textname;
	private JTextField textdate;
	JButton button = new JButton("\u786E\u8BA4");
	JButton button_1 = new JButton("\u53D6\u6D88");
	JRadioButton radioButton = new JRadioButton("\u65B0\u4EBA");
	JRadioButton radioButton_1 = new JRadioButton("\u6B63\u5F0F\u5458\u5DE5");
	JRadioButton radioButton_2 = new JRadioButton("\u5355\u738B");

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
	
	public RiderInsUI(){

		setTitle("\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 263, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_2 = new JLabel("\u59D3\u540D");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(10, 37, 64, 34);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5165\u804C\u65E5\u671F");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(10, 104, 64, 34);
		contentPane.add(label_3);
		
		textname = new JTextField();
		//textaver.setText(String.valueOf(s.getRde_limit()));
		textname.setColumns(10);
		textname.setBounds(84, 45, 125, 21);
		contentPane.add(textname);
		
		textdate = new JTextField();
		//textsum.setText(String.valueOf(s.getRde_money()));
		textdate.setColumns(10);
		textdate.setBounds(84, 112, 125, 21);
		contentPane.add(textdate);
		
		
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(10, 300, 104, 34);
		contentPane.add(button);
		button.addActionListener(this);
		
		
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(133, 300, 104, 34);
		button_1.addActionListener(this);
		contentPane.add(button_1);
		
		
		radioButton.setBounds(104, 161, 121, 23);
		contentPane.add(radioButton);
		radioButton_1.setBounds(104, 195, 121, 23);
		contentPane.add(radioButton_1);
		buttongroup.add(radioButton);
		buttongroup.add(radioButton_1);
		buttongroup.add(radioButton_2);
		//if(s.isRde_plus()==true)
			radioButton.setSelected(true);
		//else
		//	radioButton_1.setSelected(true);
		
		JLabel label = new JLabel("\u8EAB\u4EFD");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(26, 186, 35, 41);
		contentPane.add(label);
		
		
		radioButton_2.setBounds(104, 228, 121, 23);
		contentPane.add(radioButton_2);
		
		

	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		if (ac.getSource() == this.button) {
			SimpleDateFormat q = new SimpleDateFormat("yyyy-MM-dd");
			try {
				
				sf.setRd_name(textname.getText());
				Date fd=q.parse(textdate.getText());
				sf.setRd_in(new java.sql.Date(fd.getTime()));
				if(radioButton.isSelected())
					sf.setRd_status("新人");
				else if(radioButton_1.isSelected())
					sf.setRd_status("正式员工");
				else
					sf.setRd_status("单王");
			}catch (NumberFormatException |  ParseException e) {
			    //e.printStackTrace();//捕捉处理异常，暂时没有
			    winMessage("请输入正确格式");
			    return;
			}
			try {
				RiderMannager sm=new RiderMannager();
				sm.InsRider(sf);
				
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
