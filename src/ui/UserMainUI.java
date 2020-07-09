package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.GoodsMannager;
import control.*;
import model.BeanGoodDts;
import model.BeanGoodKind;
import model.BeanUserMsg;
import model.Beanaddr;
import util.BaseException;


import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Component;
import javax.swing.JComboBox;

public class UserMainUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	JComboBox comboBox = new JComboBox();
	private JTextField ShopName;
	private JButton button;
	private JButton button_1;
	List<BeanGoodKind> allKind=null;
	List<BeanGoodDts> allGood=null;
	List<BeanGoodDts> allCart=null;
	private Object tblShopTitle[]=BeanGoodKind.ShopTitle;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableKind=new JTable(tabShopModel);
	private Object tblGoodTitle[]=BeanGoodDts.tblGoodTitle;
	private Object tblGoodData[][];
	DefaultTableModel tabStepModel=new DefaultTableModel();
	private JTable dataGoods=new JTable(tabStepModel);
	private Object tblCartTitle[]=BeanGoodDts.tblCartTitle;
	private Object tblCartData[][]=new Object[20][BeanGoodDts.tblCartTitle.length];//最多20件;
	DefaultTableModel tabCartModel=new DefaultTableModel();
	private JTable dataCart=new JTable(tabCartModel);
	private BeanGoodKind curKind=null;
	private BeanGoodDts curGoods=null;
	int flag=-1;
	int number=0;
	double sum=0;//总价
	static int count=0;//记录购物车里面有多少件物品
	private JButton button_3;
	private JButton button_5;
	private JTextField textGoods;
	private JLabel label_1;
	JButton button_4 = new JButton("\u6E05\u7A7A\u8D2D\u7269\u8F66");
	private JLabel label_2;
	private JLabel Labelsum;
	List<Beanaddr> alladdr=new ArrayList<Beanaddr>(20);
	
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
	private void reloadCartTable() {
		/*try {//实际上carttable添加后就不会为空，也不再数据库内，不用每次转换。注意每一次加入后调用刷新就行
			allCart=GoodsMannager.loadallCart();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		tblCartData =  new Object[allCart.size()][BeanGoodDts.tblCartTitle.length];
		for(int i=0;i<allCart.size();i++){
			for(int j=0;j<BeanGoodDts.tblCartTitle.length;j++)
				tblCartData[i][j]=allCart.get(i).getCell2(j);
		}
		tabCartModel.setDataVector(tblCartData,tblCartTitle);
		this.dataCart.validate();
		this.dataCart.repaint();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMainUI frame = new UserMainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public UserMainUI() throws BaseException {
		this.dataTableKind.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {//点鼠标事件
				int i=UserMainUI.this.dataTableKind.getSelectedRow();
				if(i<0) {
					return;
				}
				UserMainUI.this.reloadGoods(i);
			}
	    	
	    });
		this.dataGoods.addMouseListener(new MouseAdapter (){//选中商品
			@Override
			public void mouseClicked(MouseEvent e) {//点鼠标事件
				flag=UserMainUI.this.dataGoods.getSelectedRow();
				if(flag<0) {
					return;
				}
				//UserMainUI.this.reloadGoods(i);
			}
	    });
		try {
			alladdr=AddressManager.loadallShop(BeanUserMsg.currentLoginUser);//把地址都添加进来
		}catch (BaseException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1679, 866);
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
		
		button_1 = new JButton("\u663E\u793A\u6240\u6709\u5546\u54C1\u7C7B");
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(509, 6, 156, 27);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		
		//contentPane.add(dataTableShop);
		//dataTablePlan = new JTable();
		dataTableKind.setBounds(10, 38, 678, 382);
		JScrollPane scrollPane = new JScrollPane(this.dataTableKind);
		scrollPane.setSize(406, 789);
		scrollPane.setLocation(0, 38);
		this.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(this.dataGoods);
		scrollPane_1.setBounds(406, 39, 603, 788);
		this.getContentPane().add(scrollPane_1);
		
		JLabel label = new JLabel("\u8F93\u5165\u5546\u54C1\u7C7B\u7F16\u53F7");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(112, 10, 125, 19);
		contentPane.add(label);
		
		button_3 = new JButton("\u52A0\u5165\u8D2D\u7269\u8F66");
		button_3.setFont(new Font("宋体", Font.PLAIN, 17));
		button_3.setBounds(891, 7, 146, 23);
		button_3.addActionListener(this);
		contentPane.add(button_3);
		
		
		button_4.setFont(new Font("宋体", Font.PLAIN, 17));
		button_4.setBounds(1168, 7, 156, 23);
		button_4.addActionListener(this);
		contentPane.add(button_4);
		
		JScrollPane scrollPane_2 = new JScrollPane(this.dataCart);
		scrollPane_2.setBounds(1011, 38, 603, 414);
		this.getContentPane().add(scrollPane_2);
		
		button_5 = new JButton("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		button_5.setFont(new Font("宋体", Font.PLAIN, 17));
		button_5.setBounds(1334, 7, 160, 23);
		button_5.addActionListener(this);
		contentPane.add(button_5);
		
		textGoods = new JTextField();
		textGoods.setBounds(746, 9, 100, 21);
		contentPane.add(textGoods);
		textGoods.setColumns(10);
		
		label_1 = new JLabel("\u5546\u54C1\u6570\u91CF");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(675, 12, 84, 15);
		contentPane.add(label_1);
		
		label_2 = new JLabel("\u603B\u4EF7\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		label_2.setBounds(1029, 472, 51, 42);
		contentPane.add(label_2);
		
		Labelsum = new JLabel("");
		Labelsum.setFont(new Font("宋体", Font.PLAIN, 17));
		Labelsum.setBounds(1090, 480, 125, 27);
		Labelsum.setText("暂时没有商品");
		contentPane.add(Labelsum);
		
		
		comboBox.setFont(new Font("宋体", Font.PLAIN, 17));
		comboBox.setBounds(1444, 484, 125, 30);
		int y=0;
		while(y<alladdr.size())
		{
			comboBox.addItem(alladdr.get(y).getAd_addr());
			y++;
		}
		contentPane.add(comboBox);
		
		JLabel label_3 = new JLabel("\u9009\u62E9\u6536\u8D27\u5730\u5740");
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		label_3.setBounds(1314, 487, 120, 27);
		contentPane.add(label_3);

		this.reloadShopTable();
	}
	public static void winMessage(String str) {// 提示窗口，有多个地方调用
		JOptionPane.showMessageDialog(null, str, "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		if(ac.getSource()==this.button)
		{
			allKind=null;
			allGood=null;
			String name=ShopName.getText();
			this.reloadOneShopTable(name);
			this.dataGoods.validate();
			this.dataGoods.repaint();
		}
		else if(ac.getSource()==this.button_1)
		{
			this.reloadShopTable();	
		}
		else if(ac.getSource()==this.button_3)
		{
			curGoods=allGood.get(flag);//找到某个商品
			try {
				number=Integer.parseInt(textGoods.getText());
			}catch (NumberFormatException e) {
			    //e.printStackTrace();//捕捉处理异常，暂时没有
			    winMessage("请输入商品数量或者正确输入商品数量");
			    return;
			}
			if(BeanUserMsg.currentLoginUser.getUm_status()==0)
				sum=sum+number*curGoods.getGd_price();
			else
				sum=sum+number*curGoods.getGd_reduce();
			for(int j=0;j<4;j++)
				tblCartData[count][j]=allGood.get(flag).getCell2(j);
			tblCartData[count][4]=String.valueOf(number);
			tabCartModel.setDataVector(tblCartData,tblCartTitle);
			this.dataCart.validate();
			this.dataCart.repaint();
			count++;
			Labelsum.setText(String.valueOf(sum));
		}
		else if(ac.getSource()==this.button_4)
		{
			tblCartData=null;
			tabCartModel.setDataVector(tblCartData,tblCartTitle);
			this.dataCart.validate();
			this.dataCart.repaint();
			count=0;
			sum=0;
			Labelsum.setText("暂时没有商品");
		}
		else if(ac.getSource()==this.button_5)
		{
			this.setVisible(false);
			UserUI frame=new UserUI();
			frame.setVisible(true);
		}
	}
}
