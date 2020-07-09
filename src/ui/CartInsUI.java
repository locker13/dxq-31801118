package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CartInsUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	JButton button = new JButton("\u8FD4\u56DE");
	JButton Button_1 = new JButton("\u786E\u5B9A");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartInsUI frame = new CartInsUI();
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
	public CartInsUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u6570\u91CF");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(63, 96, 136, 66);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(173, 116, 110, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		Button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		Button_1.setBounds(157, 225, 110, 30);
		contentPane.add(Button_1);
		
		
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(321, 225, 110, 30);
		contentPane.add(button);
	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		/*if(ac.getSource()==Button_1)
		{
			try {
				int a=Integer.parseInt(textField.getText());
				UserMainUI.number=a;
				}catch (NumberFormatException e) {
				    //e.printStackTrace();//捕捉处理异常，暂时没有
				    winMessage("请输入正确格式");
				    return;
				}
		}
		else if(ac.getSource()==button)
		{
			this.setVisible(false);
		}*/
	}

}
