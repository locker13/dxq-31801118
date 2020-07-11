package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.GoodsMannager;
import model.BeanGoodComt;
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

public class CommentUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton button_1;
	List<BeanGoodComt> allshop=null;
	private Object tblShopTitle[]=BeanGoodComt.ShopTitle;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableShop=new JTable(tabShopModel);
	private JButton button_2;
	private JButton button_3;
	private BeanGoodComt curShop=null;
	private JButton button_4;
	private JButton button_5;
	int gid1=0;
	private void reloadShopTable(int gid) {
		try {
			allshop=GoodsMannager.loadallcomm(gid);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allshop.size()][BeanGoodComt.ShopTitle.length];
		for(int i=0;i<allshop.size();i++){
			for(int j=0;j<BeanGoodComt.ShopTitle.length;j++)
				tblShopData[i][j]=allshop.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShopTitle);
		this.dataTableShop.validate();
		this.dataTableShop.repaint();
	}
	/*private void reloadOneShopTable(String name) {
		try {
			allshop=GoodsMannager.loadselectShop(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allshop.size()][BeanGoodComt.ShopTitle.length];
		for(int i=0;i<allshop.size();i++){
			for(int j=0;j<BeanGoodComt.ShopTitle.length;j++)
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
			GoodsMannager.modshop(curShop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		//reloadShopTable();
	}
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommentUI frame = new CommentUI();
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
	public CommentUI(int gid) {
		setTitle("\u8BC4\u8BBA");
		 this.dataTableShop.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {//点鼠标事件
					int i=CommentUI.this.dataTableShop.getSelectedRow();
					if(i<0) {
						return;
					}
					CommentUI.this.reloadShopDetails(i);
				}
		    	
		    });
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1195, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button_1 = new JButton("\u663E\u793A\u6240\u6709");
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(217, 8, 105, 27);
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
		scrollPane.setSize(997, 468);
		scrollPane.setLocation(0, 38);
		this.getContentPane().add(scrollPane);
		
		button_3 = new JButton("\u4FEE\u6539\u4FE1\u606F");
		button_3.setFont(new Font("宋体", Font.PLAIN, 22));
		button_3.setBounds(1007, 80, 129, 62);
		contentPane.add(button_3);
		button_3.addActionListener(this);
		
		button_4 = new JButton("\u5220\u9664\u8BC4\u8BBA");
		button_4.setFont(new Font("宋体", Font.PLAIN, 22));
		button_4.setBounds(1007, 192, 129, 62);
		button_4.addActionListener(this);
		contentPane.add(button_4);
		
		button_5 = new JButton("\u6DFB\u52A0\u8BC4\u8BBA");
		button_5.setFont(new Font("宋体", Font.PLAIN, 22));
		button_5.setBounds(1007, 311, 129, 62);
		button_5.addActionListener(this);
		contentPane.add(button_5);
		
		this.reloadShopTable(gid);
		
	}
	@Override
	public void actionPerformed(ActionEvent ac)
	{
		if(ac.getSource()==this.button_2)
		{
			this.setVisible(false);
		}
		else if(ac.getSource()==this.button_1)
		{
			this.reloadShopTable(gid1);	
		}
		else if(ac.getSource()==this.button_4)
		{
			try {
				GoodsMannager.deleteComm(curShop);
			}catch (BaseException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(ac.getSource()==this.button_5)
		{
			ComentInsUI frame=new ComentInsUI(gid1);
			frame.setVisible(true);
		}
	}
}
