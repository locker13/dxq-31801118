package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.CouponMannager;
import model.BeanCoupon;
import util.BaseException;

public class CouponInsUI extends JFrame implements ActionListener{

	BeanCoupon sf=new BeanCoupon();
	private JPanel contentPane;
	private JTextField txtmoney;
	private JTextField textcount;
	private JTextField textsttime;
	JButton button = new JButton("\u786E\u8BA4");
	JButton button_1 = new JButton("\u53D6\u6D88");
	private JTextField textEndtime;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CouponInsUI frame = new CouponInsUI();
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
	public CouponInsUI() {
		
		setTitle("\u6DFB\u52A0\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 263, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u4F18\u60E0\u91D1\u989D");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(10, 29, 64, 34);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u96C6\u5355\u8981\u6C42\u6570");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(0, 73, 87, 46);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u8D77\u59CB\u65E5\u671F");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(10, 145, 64, 34);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u7ED3\u675F\u65E5\u671F");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(10, 209, 64, 34);
		contentPane.add(label_3);
		
		txtmoney = new JTextField();
		txtmoney.setBounds(97, 37, 125, 21);
		contentPane.add(txtmoney);
		txtmoney.setColumns(10);
		
		textcount = new JTextField();
		textcount.setColumns(10);
		textcount.setBounds(97, 93, 125, 21);
		contentPane.add(textcount);
		
		textsttime = new JTextField();
		textsttime.setColumns(10);
		textsttime.setBounds(97, 153, 125, 21);
		contentPane.add(textsttime);
		
		
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(10, 300, 104, 34);
		contentPane.add(button);
		button.addActionListener(this);
		
		
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(133, 300, 104, 34);
		button_1.addActionListener(this);
		contentPane.add(button_1);
		
		textEndtime = new JTextField();
		textEndtime.setText((String) null);
		textEndtime.setColumns(10);
		textEndtime.setBounds(97, 217, 125, 21);
		contentPane.add(textEndtime);
	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		if (ac.getSource() == this.button) {
			SimpleDateFormat q = new SimpleDateFormat("yyyy-MM-dd");

			
			try {
				sf.setCp_money(Double.parseDouble(txtmoney.getText()));
				sf.setCp_count(Integer.parseInt(textcount.getText()));
				Date sd=q.parse(textsttime.getText());
				Date fd=q.parse(textEndtime.getText());
				sf.setCp_stdate(new java.sql.Date(sd.getTime()));
				sf.setCp_eddate(new java.sql.Date(fd.getTime()));
			}catch (NumberFormatException |  ParseException e) {
			    //e.printStackTrace();//捕捉处理异常，暂时没有
			    winMessage("请输入正确格式");
			    return;
			}
			try {
				CouponMannager sm=new CouponMannager();
				sm.InsCou(sf);
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
