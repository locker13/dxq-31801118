package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	JButton button_1 = new JButton("\u5546\u54C1\u7BA1\u7406");
	JButton button_2 = new JButton("\u6EE1\u51CF\u7BA1\u7406");
	JButton button_3 = new JButton("\u4F18\u60E0\u5238\u7BA1\u7406");
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
		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u5916\u5356\uFF01");
		label.setFont(new Font("宋体", Font.PLAIN, 27));
		label.setBounds(131, 10, 240, 62);
		contentPane.add(label);
	}
	public void actionPerformed(ActionEvent ac)
	{
		if(ac.getSource()==this.button)
		{
			AddressUI frame=new AddressUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
		/*else if(ac.getSource()==this.button_1)
		{
			GoodsUI frame=new GoodsUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.getSource()==this.button_2)
		{
			ReduceUI frame=new ReduceUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.getSource()==this.button_3)
		{
			CouponUI frame=new CouponUI();
			frame.setVisible(true);
			this.setVisible(false);
		}*/
	}
}
