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

import control.GoodsMannager;
import control.ReduceMannager;
import model.BeanReduce;
import model.BeanGoodKind;
import model.BeanGoodDts;
import util.BaseException;
import javax.swing.table.TableModel;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GoodsMUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField ShopName;
	private JButton button;
	private JButton button_1;
	List<BeanGoodKind> allKind=null;
	List<BeanGoodDts> allGood=null;
	private Object tblShopTitle[]=BeanGoodKind.ShopTitle;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableKind=new JTable(tabShopModel);
	private Object tblGoodTitle[]=BeanGoodDts.tblGoodTitle;
	private Object tblGoodData[][];
	DefaultTableModel tabStepModel=new DefaultTableModel();
	private JTable dataGoods=new JTable(tabStepModel);
	private JButton button_2;
	private BeanGoodKind curKind=null;
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_2;
	private JMenu menu_1;
	private JMenuItem menuItem_3;
	private JMenuItem menuItem_5;

	private void reloadShopTable() {
		try {
			allKind=GoodsMannager.loadallGoodK();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allKind.size()][BeanGoodKind.ShopTitle.length];
		for(int i=0;i<allKind.size();i++){
			for(int j=0;j<BeanGoodKind.ShopTitle.length;j++)
				tblShopData[i][j]=allKind.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShopTitle);
		this.dataTableKind.validate();
		this.dataTableKind.repaint();
	}
	private void reloadOneShopTable(String name) {
		try {
			allKind=GoodsMannager.loadselectKind(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allKind.size()][BeanGoodKind.ShopTitle.length];
		for(int i=0;i<allKind.size();i++){
			for(int j=0;j<BeanGoodKind.ShopTitle.length;j++)
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
			allGood=GoodsMannager.loadGoods(curKind);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblGoodData =new Object[allGood.size()][BeanGoodDts.tblGoodTitle.length];
		for(int i=0;i<allGood.size();i++){
			for(int j=0;j<BeanGoodDts.tblGoodTitle.length;j++)
				tblGoodData[i][j]=allGood.get(i).getCell(j);
		}
		tabStepModel.setDataVector(tblGoodData,tblGoodTitle);
		this.dataGoods.validate();
		this.dataGoods.repaint();
	}
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodsMUI frame = new GoodsMUI();
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
	public GoodsMUI() {
		setTitle("\u6EE1\u51CF\u7BA1\u7406");
		 this.dataTableKind.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {//点鼠标事件
					int i=GoodsMUI.this.dataTableKind.getSelectedRow();
					if(i<0) {
						return;
					}
					GoodsMUI.this.reloadGoods(i);
				}
		    	
		    });
		/* this.dataGoods.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {//点鼠标事件
					int i=GoodsMUI.this.dataGoods.getSelectedRow();
					if(i<0) {
						return;
					}
					GoodsMUI.this.reloadGoods(i);
				}
		    	
		    });*/
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1035, 665);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u5546\u54C1\u7C7B\u7BA1\u7406");
		menu.setFont(new Font("宋体", Font.PLAIN, 15));
		menuBar.add(menu);
		
		menuItem = new JMenuItem("\u6DFB\u52A0\u5546\u54C1\u7C7B");
		menu.add(menuItem);
		menuItem.addActionListener(this);
		
		menuItem_1 = new JMenuItem("\u4FEE\u6539\u5546\u54C1\u7C7B");
		menu.add(menuItem_1);
		menuItem_1.addActionListener(this);
		
		menuItem_2 = new JMenuItem("\u5220\u9664\u5546\u54C1\u7C7B");
		menu.add(menuItem_2);
		menuItem_2.addActionListener(this);
		
		menu_1 = new JMenu("\u5546\u54C1\u7BA1\u7406");
		menu_1.setFont(new Font("宋体", Font.PLAIN, 15));
		menuBar.add(menu_1);
		
		menuItem_3 = new JMenuItem("\u6DFB\u52A0\u5546\u54C1");
		menu_1.add(menuItem_3);
		menuItem_3.addActionListener(this);
		
		menuItem_5 = new JMenuItem("\u5220\u9664\u5546\u54C1");
		menu_1.add(menuItem_5);
		menuItem_5.addActionListener(this);
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
		scrollPane.setSize(406, 588);
		scrollPane.setLocation(0, 38);
		this.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(this.dataGoods);
		scrollPane_1.setBounds(406, 39, 603, 587);
		this.getContentPane().add(scrollPane_1);
		
		JLabel label = new JLabel("\u8F93\u5165\u5546\u54C1\u7C7B\u540D");
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
		else if(ac.getSource()==this.menuItem_3) {
			int i=GoodsMUI.this.dataTableKind.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品类", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			GoodsDtsInsUI frame=new GoodsDtsInsUI(this.allKind.get(i));
			frame.setVisible(true);
		}
		else if(ac.getSource()==this.menuItem_5) {
			int i=GoodsMUI.this.dataGoods.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				GoodsMannager.DeleteGoodDts(this.allGood.get(i));
			}catch (BaseException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(ac.getSource()==this.menuItem_1) {
			int i=GoodsMUI.this.dataTableKind.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品类", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			GoodKindModUI frame=new GoodKindModUI(this.allKind.get(i));
			frame.setVisible(true);
			
		}
		else if(ac.getSource()==this.menuItem_2) {
			int i=GoodsMUI.this.dataTableKind.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品类", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				GoodsMannager.DeleteGoodKind(this.allKind.get(i));
			}catch (BaseException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"存在商品，无法删除", "错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(ac.getSource()==this.menuItem) {
				GoodKindInsUI frame=new GoodKindInsUI();
				frame.setVisible(true);
		}
	}
}
