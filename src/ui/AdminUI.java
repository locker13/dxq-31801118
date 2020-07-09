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

public class AdminUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	JButton button = new JButton("\u5546\u5BB6\u7BA1\u7406");
	JButton button_1 = new JButton("\u5546\u54C1\u7BA1\u7406");
	JButton button_2 = new JButton("\u6EE1\u51CF\u7BA1\u7406");
	JButton button_3 = new JButton("\u4F18\u60E0\u5238\u7BA1\u7406");
	JButton button_4 = new JButton("\u9A91\u624B\u7BA1\u7406");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUI frame = new AdminUI();
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
	public AdminUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(10, 149, 112, 23);
		contentPane.add(button);
		button.addActionListener(this);
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(132, 149, 107, 23);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		
		
		button_2.setFont(new Font("宋体", Font.PLAIN, 15));
		button_2.setBounds(249, 149, 106, 23);
		contentPane.add(button_2);
		button_2.addActionListener(this);
		
		
		button_3.setFont(new Font("宋体", Font.PLAIN, 15));
		button_3.setBounds(365, 149, 117, 23);
		contentPane.add(button_3);
		button_3.addActionListener(this);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u7BA1\u7406\u5458\uFF01");
		label.setFont(new Font("宋体", Font.PLAIN, 27));
		label.setBounds(155, 10, 200, 62);
		contentPane.add(label);
		
		
		button_4.setFont(new Font("宋体", Font.PLAIN, 15));
		button_4.setBounds(75, 198, 112, 23);
		button_4.addActionListener(this);
		contentPane.add(button_4);
	}
	public void actionPerformed(ActionEvent ac)
	{
		if(ac.getSource()==this.button)
		{
			ShopMUI frame=new ShopMUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.getSource()==this.button_1)
		{
			GoodsMUI frame=new GoodsMUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.getSource()==this.button_2)
		{
			ReduceMUI frame=new ReduceMUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.getSource()==this.button_3)
		{
			CouponMUI frame=new CouponMUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.getSource()==this.button_4)
		{
			RiderMUI frame=new RiderMUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
	}
}
