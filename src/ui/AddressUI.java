package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.AddressManager;
import control.CouponMannager;
import control.ShopMannager;
import model.Beanaddr;
import model.BeanShop;
import model.BeanUserMsg;
import model.Beanaddr;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import util.*;

public class AddressUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton button_1;
	List<Beanaddr> allshop=null;
	private Object tblShopTitle[]=Beanaddr.ShopTitle;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableShop=new JTable(tabShopModel);
	private JButton button_2;
	private JButton button_3;
	private Beanaddr curShop=null;
	private JButton button_4;
	private JButton button_5;
	
	private void reloadShopTable() {
		try {
			allshop=AddressManager.loadallShop(BeanUserMsg.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allshop.size()][Beanaddr.ShopTitle.length];
		for(int i=0;i<allshop.size();i++){
			for(int j=0;j<Beanaddr.ShopTitle.length;j++)
				tblShopData[i][j]=allshop.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShopTitle);
		this.dataTableShop.validate();
		this.dataTableShop.repaint();
	}
	/*private void reloadOneShopTable(String name) {
		try {
			allshop=CouponMannager.loadselectShop(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allshop.size()][Beanaddr.ShopTitle.length];
		for(int i=0;i<allshop.size();i++){
			for(int j=0;j<Beanaddr.ShopTitle.length;j++)
				tblShopData[i][j]=allshop.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShopTitle);
		this.dataTableShop.validate();
		this.dataTableShop.repaint();
	}*/
	private void reloadShopDetails(int planIdx){//�޸� ����ҳ�������Ҫ��ȡ�� curshop��ȡ������
		if(planIdx<0) return;
		curShop=allshop.get(planIdx);
		/*try {//��������Ϊ�޸ĵ�ת�Ƶ���ShopModUI��
			ShopMannager.modshop(curShop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		//reloadShopTable();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMUI frame = new ShopMUI();
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
	public AddressUI() {
		setTitle("\u4F18\u60E0\u5238\u7BA1\u7406");
		 this.dataTableShop.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {//������¼�
					int i=AddressUI.this.dataTableShop.getSelectedRow();
					if(i<0) {
						return;
					}
					AddressUI.this.reloadShopDetails(i);
				}
		    	
		    });
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button_1 = new JButton("\u663E\u793A\u6240\u6709");
		button_1.setFont(new Font("����", Font.PLAIN, 15));
		button_1.setBounds(228, 8, 105, 27);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		
		
		button_2 = new JButton("\u8FD4\u56DE");
		button_2.setFont(new Font("����", Font.PLAIN, 15));
		button_2.setBounds(657, 8, 105, 27);
		contentPane.add(button_2);
		button_2.addActionListener(this);
		
		//contentPane.add(dataTableShop);
		//dataTablePlan = new JTable();
		dataTableShop.setBounds(10, 38, 678, 382);
		JScrollPane scrollPane = new JScrollPane(this.dataTableShop);
		scrollPane.setSize(590, 392);
		scrollPane.setLocation(0, 38);
		this.getContentPane().add(scrollPane);
		
		button_3 = new JButton("\u4FEE\u6539\u4FE1\u606F");
		button_3.setFont(new Font("����", Font.PLAIN, 22));
		button_3.setBounds(645, 106, 129, 62);
		contentPane.add(button_3);
		button_3.addActionListener(this);
		
		button_4 = new JButton("\u5220\u9664");
		button_4.setFont(new Font("����", Font.PLAIN, 22));
		button_4.setBounds(645, 204, 129, 62);
		button_4.addActionListener(this);
		contentPane.add(button_4);
		
		button_5 = new JButton("\u6DFB\u52A0");
		button_5.setFont(new Font("����", Font.PLAIN, 22));
		button_5.setBounds(648, 300, 129, 62);
		button_5.addActionListener(this);
		contentPane.add(button_5);
		
		this.reloadShopTable();
		
	}
	@Override
	public void actionPerformed(ActionEvent ac)
	{
		if(ac.getSource()==this.button_1)
		{
			this.reloadShopTable();			
		}
		else if(ac.getSource()==this.button_2)
		{
			UserUI frame=new UserUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.getSource()==this.button_3)
		{
			AddrModUI frame=new AddrModUI(curShop);
			frame.setVisible(true);
			//this.setVisible(false);
		}
		else if(ac.getSource()==this.button_4) {
			AddressManager sm=new AddressManager();
			try {
				sm.deleteaddr(curShop);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(ac.getSource()==this.button_5) {
			AddressInsUI frame=new AddressInsUI();
			frame.setVisible(true);
		}
	}
	
}
