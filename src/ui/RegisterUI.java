package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class RegisterUI extends JFrame {
	private ButtonGroup buttongroup = new ButtonGroup();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	JLabel label = new JLabel("\u7528\u6237\u540D");
	JLabel label_1 = new JLabel("\u5BC6\u7801");
	JLabel label_2 = new JLabel("\u91CD\u590D\u5BC6\u7801");
	JLabel label_3 = new JLabel("\u6027\u522B");
	JRadioButton radioButton = new JRadioButton("\u5973");
	JRadioButton radioButton_1 = new JRadioButton("\u7537");
	JLabel label_4 = new JLabel("\u624B\u673A\u53F7");
	JLabel label_5 = new JLabel("\u7535\u5B50\u90AE\u7BB1");
	JButton button = new JButton("\u6CE8\u518C");
	JButton button_1 = new JButton("\u8FD4\u56DE");
	JLabel label_7 = new JLabel("\u7528\u6237\u6CE8\u518C");
	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUI frame = new RegisterUI();
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
	public RegisterUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 485);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(74, 68, 45, 34);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(129, 75, 185, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(89, 112, 30, 34);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(129, 115, 185, 21);
		contentPane.add(textField_1);
		
		
		label_2.setFont(new Font("宋体", Font.PLAIN, 15));
		label_2.setBounds(59, 156, 60, 28);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(129, 156, 185, 21);
		contentPane.add(textField_2);
		
		
		label_3.setFont(new Font("宋体", Font.PLAIN, 15));
		label_3.setBounds(89, 200, 30, 25);
		contentPane.add(label_3);
		
		
		radioButton.setBackground(Color.LIGHT_GRAY);
		radioButton.setFont(new Font("宋体", Font.PLAIN, 15));
		radioButton.setBounds(217, 201, 121, 23);
		contentPane.add(radioButton);
		
		
		radioButton_1.setBackground(Color.LIGHT_GRAY);
		radioButton_1.setSelected(true);
		radioButton_1.setFont(new Font("宋体", Font.PLAIN, 15));
		radioButton_1.setBounds(129, 201, 60, 23);
		contentPane.add(radioButton_1);
		buttongroup.add(radioButton);
		buttongroup.add(radioButton_1);
		
		
		label_4.setFont(new Font("宋体", Font.PLAIN, 15));
		label_4.setBounds(74, 245, 45, 20);
		contentPane.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(129, 245, 185, 21);
		contentPane.add(textField_3);
		
		
		label_5.setFont(new Font("宋体", Font.PLAIN, 15));
		label_5.setBounds(59, 288, 60, 28);
		contentPane.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(129, 292, 185, 21);
		contentPane.add(textField_4);
		
		JLabel label_6 = new JLabel("\u6240\u5728\u57CE\u5E02");
		label_6.setFont(new Font("宋体", Font.PLAIN, 15));
		label_6.setBounds(59, 333, 60, 28);
		contentPane.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(129, 337, 185, 21);
		contentPane.add(textField_5);
		
		
		button.setFont(new Font("宋体", Font.PLAIN, 21));
		button.setBounds(59, 380, 130, 44);
		contentPane.add(button);
		
		
		button_1.setFont(new Font("宋体", Font.PLAIN, 21));
		button_1.setBounds(236, 380, 130, 44);
		contentPane.add(button_1);
		
		
		label_7.setFont(new Font("宋体", Font.PLAIN, 30));
		label_7.setBounds(147, 10, 167, 71);
		contentPane.add(label_7);
	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	public void actionPerformed(ActionEvent ac) {
		if(ac.getSource()==this.button_1)
		{
			String name=textField.getText();
			String pwd1=textField_1.getText();
			String pwd2=textField_2.getText();
			if(name.equals("")||pwd1.equals("")||pwd2.equals("")) winMessage("账号、密码不能为空！");
			String phone=textField_3.getText();
			String mail=textField_4.getText();
			String city=textField_5.getText();
		}
	}
	
}
