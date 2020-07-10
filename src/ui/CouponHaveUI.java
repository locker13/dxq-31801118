package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.CouponMannager;
import control.CouponMannager;
import control.ShopMannager;
import model.BeanUserCoup;
import model.BeanShop;
import model.BeanUserMsg;
import model.BeanUserCoup;
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

public class CouponHaveUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	List<BeanUserCoup> allshop=null;
	private Object tblShopTitle[]=BeanUserCoup.ShopTitle;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableShop=new JTable(tabShopModel);
	private JButton button_2;
	private BeanUserCoup curShop=null;
	
	private void reloadShopTable() {
		try {
			allshop=CouponMannager.loadallCoupon();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allshop.size()][BeanUserCoup.ShopTitle.length];
		for(int i=0;i<allshop.size();i++){
			for(int j=0;j<BeanUserCoup.ShopTitle.length;j++)
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allshop.size()][BeanUserCoup.ShopTitle.length];
		for(int i=0;i<allshop.size();i++){
			for(int j=0;j<BeanUserCoup.ShopTitle.length;j++)
				tblShopData[i][j]=allshop.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShopTitle);
		this.dataTableShop.validate();
		this.dataTableShop.repaint();
	}*/
	private void reloadShopDetails(int planIdx){//修改 更新页面最后不需要读取了 curshop读取到即可
		if(planIdx<0) return;
		curShop=allshop.get(planIdx);
		/*try {//本来是作为修改的转移到了ShopModUI中
			ShopMannager.modshop(curShop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
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
	public CouponHaveUI() {
		setTitle("\u4F18\u60E0\u5238\u7BA1\u7406");
		 this.dataTableShop.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {//点鼠标事件
					int i=CouponHaveUI.this.dataTableShop.getSelectedRow();
					if(i<0) {
						return;
					}
					CouponHaveUI.this.reloadShopDetails(i);
				}
		    	
		    });
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		button_2 = new JButton("\u8FD4\u56DE");
		button_2.setFont(new Font("宋体", Font.PLAIN, 15));
		button_2.setBounds(334, 10, 105, 27);
		contentPane.add(button_2);
		button_2.addActionListener(this);
		
		//contentPane.add(dataTableShop);
		//dataTablePlan = new JTable();
		dataTableShop.setBounds(10, 38, 678, 382);
		JScrollPane scrollPane = new JScrollPane(this.dataTableShop);
		scrollPane.setSize(808, 394);
		scrollPane.setLocation(0, 36);
		this.getContentPane().add(scrollPane);
		
		this.reloadShopTable();
		
	}
	@Override
	public void actionPerformed(ActionEvent ac)
	{
		if(ac.getSource()==this.button_2)
		{
			this.setVisible(false);
			UserUI frame=new UserUI();
			frame.setVisible(true);
		}
	}
	
}
