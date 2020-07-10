package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.OrderMannager;
import control.ReduceMannager;
import model.BeanReduce;
import model.BeanGoodOrder;
import model.BeanOrderDts;
import util.BaseException;
import javax.swing.table.TableModel;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class OrderUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField ShopName;
	private JButton button;
	private JButton button_1;
	List<BeanGoodOrder> allKind=null;
	List<BeanOrderDts> allGood=null;
	private Object tblShopTitle[]=BeanGoodOrder.ShopTitle;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableKind=new JTable(tabShopModel);
	private Object tblGoodTitle[]=BeanOrderDts.ShopTitle;
	private Object tblGoodData[][];
	DefaultTableModel tabStepModel=new DefaultTableModel();
	private JTable dataGoods=new JTable(tabStepModel);
	private JButton button_2;
	private BeanGoodOrder curKind=null;

	private void reloadShopTable() {
		try {
			allKind=OrderMannager.loadallGoodK();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allKind.size()][BeanGoodOrder.ShopTitle.length];
		for(int i=0;i<allKind.size();i++){
			for(int j=0;j<BeanGoodOrder.ShopTitle.length;j++)
				tblShopData[i][j]=allKind.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShopTitle);
		this.dataTableKind.validate();
		this.dataTableKind.repaint();
	}
	private void reloadOneShopTable(String name) {
		try {
			allKind=OrderMannager.loadselectKind(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allKind.size()][BeanGoodOrder.ShopTitle.length];
		for(int i=0;i<allKind.size();i++){
			for(int j=0;j<BeanGoodOrder.ShopTitle.length;j++)
				tblShopData[i][j]=allKind.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShopTitle);
		this.dataTableKind.validate();
		this.dataTableKind.repaint();
	}
	private void reloadGoods(int planIdx){
		if(planIdx<0) return;
		curKind=allKind.get(planIdx);
		try {
			allGood=OrderMannager.loadGoods(curKind);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblGoodData =new Object[allGood.size()][BeanOrderDts.ShopTitle.length];
		for(int i=0;i<allGood.size();i++){
			for(int j=0;j<BeanOrderDts.ShopTitle.length;j++)
				tblGoodData[i][j]=allGood.get(i).getCell(j);
		}
		tabStepModel.setDataVector(tblGoodData,tblGoodTitle);
		this.dataGoods.validate();
		this.dataGoods.repaint();
	}
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderUI frame = new OrderUI();
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
	public OrderUI() {
		setTitle("\u5546\u54C1\u7BA1\u7406");
		 this.dataTableKind.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {//点鼠标事件
					int i=OrderUI.this.dataTableKind.getSelectedRow();
					if(i<0) {
						return;
					}
					OrderUI.this.reloadGoods(i);
				}
		    	
		    });
		/* this.dataGoods.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {//点鼠标事件
					int i=OrderUI.this.dataGoods.getSelectedRow();
					if(i<0) {
						return;
					}
					OrderUI.this.reloadGoods(i);
				}
		    	
		    });*/
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ShopName = new JTextField();
		ShopName.setFont(new Font("宋体", Font.PLAIN, 15));
		ShopName.setBounds(247, 8, 100, 21);
		contentPane.add(ShopName);
		ShopName.setColumns(10);
		
		button = new JButton("\u67E5\u8BE2\u5546\u54C1\u7C7B");
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(357, 6, 135, 27);
		contentPane.add(button);
		button.addActionListener(this);
		
		button_1 = new JButton("\u663E\u793A\u6240\u6709");
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(509, 6, 105, 27);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		
		
		button_2 = new JButton("\u8FD4\u56DE");
		button_2.setFont(new Font("宋体", Font.PLAIN, 15));
		button_2.setBounds(653, 6, 105, 27);
		contentPane.add(button_2);
		button_2.addActionListener(this);
		
		//contentPane.add(dataTableShop);
		//dataTablePlan = new JTable();
		dataTableKind.setBounds(10, 38, 678, 382);
		JScrollPane scrollPane = new JScrollPane(this.dataTableKind);
		scrollPane.setSize(923, 892);
		scrollPane.setLocation(0, 38);
		this.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(this.dataGoods);
		scrollPane_1.setBounds(922, 43, 452, 887);
		this.getContentPane().add(scrollPane_1);
		
		JLabel label = new JLabel("\u8F93\u5165\u8BA2\u5355\u7F16\u53F7");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(112, 10, 125, 19);
		contentPane.add(label);
		
		
		
		this.reloadShopTable();
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		if(ac.getSource()==this.button)
		{
			allKind=null;
			allGood=null;
			String name=ShopName.getText();
			this.reloadOneShopTable(name);
		}
		else if(ac.getSource()==this.button_1)
		{
			this.reloadShopTable();	
		}
		else if(ac.getSource()==this.button_2)
		{
			this.setVisible(false);
			UserUI frame=new UserUI();
			frame.setVisible(true);
		}
	}
}
