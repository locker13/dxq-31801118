package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.GoodsMannager;
import control.ShopMannager;
import model.BeanGoodComt;
import model.BeanUserMsg;
import util.BaseException;

public class ComentInsUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField textaver;
	JButton button = new JButton("\u786E\u8BA4");
	JButton button_1 = new JButton("\u53D6\u6D88");
	JComboBox comboBox = new JComboBox();
	BeanGoodComt sf=new BeanGoodComt();
	int gid1=0;
	
	public ComentInsUI(int gid) {
		gid1=gid;
		setTitle("\u4FE1\u606F\u6DFB\u52A0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u5BB6\u540D\u79F0");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(10, 29, 64, 34);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u661F\u7EA7");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(10, 85, 64, 34);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u8BC4\u4EF7");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(10, 145, 64, 34);
		contentPane.add(label_2);
		
		txtname = new JTextField();
		txtname.setBounds(97, 37, 125, 21);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		textaver = new JTextField();
		textaver.setColumns(10);
		textaver.setBounds(97, 153, 278, 21);
		contentPane.add(textaver);
		
		
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(0, 264, 104, 34);
		contentPane.add(button);
		button.addActionListener(this);
		
		
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(141, 264, 104, 34);
		button_1.addActionListener(this);
		contentPane.add(button_1);
		
		
		comboBox.setBounds(97, 93, 125, 21);
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		comboBox.addItem("5");
		comboBox.addActionListener(this);;
		contentPane.add(comboBox);
	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		if(ac.getSource()==this.button)
		{
			sf.setGc_star(comboBox.getSelectedIndex()+1);
			try {
				sf.setGc_sid(Integer.parseInt(txtname.getText()));
				sf.setGc_word(textaver.getText());
				sf.setGc_gid(gid1);
				sf.setGc_uid(BeanUserMsg.currentLoginUser.getUm_id());
			}catch(NumberFormatException e) {
				winMessage("请输入正确格式");
			}
			try {
				GoodsMannager.InsComm(sf);
				this.setVisible(false);
			} catch (BaseException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(ac.getSource()==this.button_1)
		{
			this.setVisible(false);
		}
	}
}
