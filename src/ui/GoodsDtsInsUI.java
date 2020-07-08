package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.GoodsMannager;
import model.BeanGoodDts;
import model.BeanGoodKind;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class GoodsDtsInsUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	int i=0;
	JButton button = new JButton("\u786E\u8BA4");
	JButton button_1 = new JButton("\u53D6\u6D88");
	BeanGoodDts sf=new BeanGoodDts();
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodsDtsInsUI frame = new GoodsDtsInsUI();
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
	public GoodsDtsInsUI(BeanGoodKind a) {
		i=a.getGk_id();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 257, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u540D");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		label.setBounds(38, 57, 51, 33);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u4EF7\u683C");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		label_1.setBounds(50, 145, 39, 33);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u4F18\u60E0\u4EF7\u683C");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		label_2.setBounds(21, 229, 68, 33);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(99, 62, 107, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(99, 152, 107, 21);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(99, 236, 107, 21);
		contentPane.add(textField_2);
		
		
		button.setBounds(10, 316, 93, 23);
		contentPane.add(button);
		button.addActionListener(this);;
		
		button_1.setBounds(138, 316, 93, 23);
		contentPane.add(button_1);
		button_1.addActionListener(this);
	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		if(ac.getSource()==button)
		{		
		try {
			sf.setGd_kindid(i);
			sf.setGd_name(textField.getText());
			sf.setGd_price(Double.parseDouble(textField_1.getText()));
			sf.setGd_reduce(Double.parseDouble(textField_2.getText()));
		}catch (NumberFormatException e) {
		    winMessage("请输入正确格式");
		    return;
		}
		try {
			GoodsMannager.InsGoodDts(sf);
			this.setVisible(false);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		}
		else if(ac.getSource()==button_1)
		{
			this.setVisible(false);
		}
	}
}
