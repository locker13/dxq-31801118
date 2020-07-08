package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.GoodsMannager;
import model.BeanGoodKind;
import util.BaseException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.Action;
import javax.swing.JButton;

public class GoodKindModUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	JButton button = new JButton("\u786E\u8BA4");
	JButton button_1 = new JButton("\u53D6\u6D88");
	BeanGoodKind gk=new BeanGoodKind();
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodKindInsUI frame = new GoodKindInsUI();
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
	public GoodKindModUI(BeanGoodKind a) {
		gk=a;
		setTitle("\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 252, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5206\u7C7B\u540D\u79F0");
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		label.setBounds(68, 31, 75, 41);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(10, 82, 201, 21);
		textField.setText(gk.getGk_name());
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		button.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		button.setBounds(10, 208, 93, 23);
		contentPane.add(button);
		button.addActionListener(this);
		
		button_1.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		button_1.setBounds(133, 208, 93, 23);
		contentPane.add(button_1);
		button_1.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ac) {
		if(ac.getSource()==button)
		{
			String a=textField.getText();
			gk.setGk_name(a);
			try {
				GoodsMannager.ModGoodKind(gk);
				this.setVisible(false);
			}catch (BaseException e) {
				e.printStackTrace();
			}
		}
		else if(ac.getSource()==button_1)
		{
			this.setVisible(false);
		}
	}

}
