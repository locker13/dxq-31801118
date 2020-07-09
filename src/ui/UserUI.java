package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BeanUserMsg;
import util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class UserUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	JButton button = new JButton("\u6536\u8D27\u5730\u5740\u7BA1\u7406");
	JButton button_1 = new JButton("\u6210\u4E3A\u4F1A\u5458");
	JButton button_2 = new JButton("\u5BC6\u7801\u4FEE\u6539");
	JButton button_3 = new JButton("\u67E5\u770B\u4F18\u60E0\u5238");
	JButton button_4 = new JButton("\u8FD4\u56DE\u8D2D\u4E70\u9875\u9762");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserUI frame = new UserUI();
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
	public UserUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(10, 72, 137, 42);
		contentPane.add(button);
		button.addActionListener(this);
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(10, 184, 137, 42);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		
		
		button_2.setFont(new Font("宋体", Font.PLAIN, 15));
		button_2.setBounds(303, 72, 137, 42);
		contentPane.add(button_2);
		button_2.addActionListener(this);
		
		
		button_3.setFont(new Font("宋体", Font.PLAIN, 15));
		button_3.setBounds(303, 181, 137, 48);
		contentPane.add(button_3);
		button_3.addActionListener(this);
		
		JLabel label = new JLabel("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		label.setFont(new Font("宋体", Font.PLAIN, 27));
		label.setBounds(131, 10, 240, 62);
		contentPane.add(label);
		button_4.setFont(new Font("宋体", Font.PLAIN, 15));
		button_4.setBounds(148, 121, 145, 56);
		button_4.addActionListener(this);
		
		contentPane.add(button_4);
	}
	public void actionPerformed(ActionEvent ac)
	{
		if(ac.getSource()==this.button)
		{
			AddressUI frame=new AddressUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.getSource()==this.button_1)
		{
			if(BeanUserMsg.currentLoginUser.getUm_status()==1)
			{
				MenberUI frame=new MenberUI();
				frame.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				MemberInUI frame=new MemberInUI();
				frame.setVisible(true);
				this.setVisible(false);
			}
			
		}
		else if(ac.getSource()==this.button_2)
		{
			PwdModUI frame=new PwdModUI();
			frame.setVisible(true);
		}
		/*else if(ac.getSource()==this.button_3)
		{
			CouponUI frame=new CouponUI();
			frame.setVisible(true);
			this.setVisible(false);
		}*/
		else if(ac.getSource()==this.button_4)
		{
			this.setVisible(false);
			UserMainUI frame;
			try {
				frame = new UserMainUI();
				frame.setVisible(true);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
