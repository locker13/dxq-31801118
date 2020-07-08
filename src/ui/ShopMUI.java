package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.ShopMannager;
import model.BeanShop;
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

public class ShopMUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField ShopName;
	private JButton button;
	private JButton button_1;
	List<BeanShop> allshop=null;
	private Object tblShopTitle[]=BeanShop.ShopTitle;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableShop=new JTable(tabShopModel);
	private JButton button_2;
	private JButton button_3;
	private BeanShop curShop=null;
	private JButton button_4;
	private JButton button_5;
	
	private void reloadShopTable() {
		try {
			allshop=ShopMannager.loadallShop();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allshop.size()][BeanShop.ShopTitle.length];
		for(int i=0;i<allshop.size();i++){
			for(int j=0;j<BeanShop.ShopTitle.length;j++)
				tblShopData[i][j]=allshop.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShopTitle);
		this.dataTableShop.validate();
		this.dataTableShop.repaint();
	}
	private void reloadOneShopTable(String name) {
		try {
			allshop=ShopMannager.loadselectShop(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allshop.size()][BeanShop.ShopTitle.length];
		for(int i=0;i<allshop.size();i++){
			for(int j=0;j<BeanShop.ShopTitle.length;j++)
				tblShopData[i][j]=allshop.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShopTitle);
		this.dataTableShop.validate();
		this.dataTableShop.repaint();
	}
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

	/**
	 * Create the frame.
	 */
	public ShopMUI() {
		setTitle("\u5546\u5BB6\u7BA1\u7406");
		 this.dataTableShop.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {//点鼠标事件
					int i=ShopMUI.this.dataTableShop.getSelectedRow();
					if(i<0) {
						return;
					}
					ShopMUI.this.reloadShopDetails(i);
				}
		    	
		    });
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u5BB6\u540D");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(176, 10, 45, 18);
		contentPane.add(label);
		
		ShopName = new JTextField();
		ShopName.setFont(new Font("宋体", Font.PLAIN, 15));
		ShopName.setBounds(242, 8, 100, 21);
		contentPane.add(ShopName);
		ShopName.setColumns(10);
		
		button = new JButton("\u67E5\u8BE2\u5546\u5BB6");
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(370, 8, 105, 27);
		contentPane.add(button);
		button.addActionListener(this);
		
		button_1 = new JButton("\u663E\u793A\u6240\u6709");
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(500, 8, 105, 27);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		
		
		button_2 = new JButton("\u8FD4\u56DE");
		button_2.setFont(new Font("宋体", Font.PLAIN, 15));
		button_2.setBounds(648, 8, 105, 27);
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
		button_3.setFont(new Font("宋体", Font.PLAIN, 22));
		button_3.setBounds(645, 106, 129, 62);
		contentPane.add(button_3);
		button_3.addActionListener(this);
		
		button_4 = new JButton("\u5220\u9664\u5546\u5BB6");
		button_4.setFont(new Font("宋体", Font.PLAIN, 22));
		button_4.setBounds(645, 204, 129, 62);
		button_4.addActionListener(this);
		contentPane.add(button_4);
		
		button_5 = new JButton("\u6DFB\u52A0\u5546\u5BB6");
		button_5.setFont(new Font("宋体", Font.PLAIN, 22));
		button_5.setBounds(648, 300, 129, 62);
		button_5.addActionListener(this);
		contentPane.add(button_5);
		
		this.reloadShopTable();
		
	}
	@Override
	public void actionPerformed(ActionEvent ac)
	{
		if(ac.getSource()==this.button)
		{
			allshop=null;
			String name=ShopName.getText();
			this.reloadOneShopTable(name);
			
		}
		else if(ac.getSource()==this.button_1)
		{
			this.reloadShopTable();			
		}
		else if(ac.getSource()==this.button_2)
		{
			AdminUI frame=new AdminUI();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.getSource()==this.button_3)
		{
			ShopModUI frame=new ShopModUI(curShop);
			frame.setVisible(true);
			//this.setVisible(false);
		}
		else if(ac.getSource()==this.button_4) {
			ShopMannager sm=new ShopMannager();
			try {
				sm.deleteshop(curShop);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(ac.getSource()==this.button_5) {
			ShopInsUI frame=new ShopInsUI();
			frame.setVisible(true);
		}
	}
}
