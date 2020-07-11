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
import java.util.Random;

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
import javassist.compiler.Javac;
import control.*;
import model.BeanGoodDts;
import model.BeanGoodKind;
import model.BeanGoodOrder;
import model.BeanOrderDts;
import model.BeanShop;
import model.BeanUserCoup;
import model.BeanUserMsg;
import model.Beanaddr;
import util.BaseException;
import util.DbException;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Component;
import javax.swing.JComboBox;

public class UserMainUI extends JFrame implements ActionListener{
//combobox �±��0��ʼ item���ַ��� ��ַ���û���+��ַ��Ӧ   �̼����±��Ӧ�ñ�ž��� ��ѯ����Ȼ����ӵ��µ�order�����У�Ȼ����� �Ż�ȯ�������һ����ʹ��ѡ��
	private JPanel contentPane;
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_1 = new JComboBox();
	JComboBox comboBox_2 = new JComboBox();
	private JTextField ShopName;
	private JButton button;
	private JButton button_1;
	List<BeanGoodKind> allKind=null;//��¼����
	List<BeanGoodDts> allGood=null;//��¼��Ʒ
	List<BeanGoodDts> allCart=null;//��¼���ﳵ
	List<BeanUserCoup> allCou=null;//��¼����ȯ
	private Object tblShopTitle[]=BeanGoodKind.ShopTitle;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableKind=new JTable(tabShopModel);
	private Object tblGoodTitle[]=BeanGoodDts.tblGoodTitle;
	private Object tblGoodData[][];
	DefaultTableModel tabStepModel=new DefaultTableModel();
	private JTable dataGoods=new JTable(tabStepModel);
	private Object tblCartTitle[]=BeanGoodDts.tblCartTitle;
	private Object tblCartData[][]=new Object[20][BeanGoodDts.tblCartTitle.length];//���20��;
	DefaultTableModel tabCartModel=new DefaultTableModel();
	private JTable dataCart=new JTable(tabCartModel);
	private BeanGoodKind curKind=null;
	private BeanGoodDts curGoods=null;
	Long time=System.currentTimeMillis();//��ȡ��ǰʱ�䣬���Ż�ȯ�Ƿ����
	int isCou=0;//�Ƿ�ʹ��������ȯ
	int flag=-1;//��Ʒ�±�
	int number=0;
	double sum=0;//�ܼ�
	double fsum=0;//���������Ż�֮���
	double ffsum=0;//�����������Ż�ȯ֮���
	static int count=0;//��¼���ﳵ�����ж��ټ���Ʒ
	private JButton button_3;
	private JButton button_5;
	private JTextField textGoods;
	private JLabel label_1;
	JButton button_4 = new JButton("\u6E05\u7A7A\u8D2D\u7269\u8F66");
	private JLabel label_2;
	private JLabel Labelsum;
	List<Beanaddr> alladdr=new ArrayList<Beanaddr>(20);
	List<BeanShop> allshop=new ArrayList<BeanShop>();
	List<BeanOrderDts> allorder=new ArrayList<BeanOrderDts>();//ÿ����������Ʒ��
	private JButton button_2;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JButton button_6;
	JButton button_7 = new JButton("\u5237\u65B0\u4EF7\u683C");
	private JLabel labelfsum;
	JLabel labelfin = new JLabel("\u6682\u65F6\u6CA1\u6709\u5546\u54C1");
	BeanGoodOrder order=new BeanGoodOrder();//˳���ʼ�������Ķ���id
	
	private void reloadShopTable() {
		try {
			allKind=GoodsMannager.loadallGoodK();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
		/*try {//ʵ����carttable��Ӻ�Ͳ���Ϊ�գ�Ҳ�������ݿ��ڣ�����ÿ��ת����ע��ÿһ�μ�������ˢ�¾���
			allCart=GoodsMannager.loadallCart();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
			public void mouseClicked(MouseEvent e) {//������¼�
				int i=UserMainUI.this.dataTableKind.getSelectedRow();
				if(i<0) {
					return;
				}
				UserMainUI.this.reloadGoods(i);
			}
	    	
	    });
		this.dataGoods.addMouseListener(new MouseAdapter (){//ѡ����Ʒ
			@Override
			public void mouseClicked(MouseEvent e) {//������¼�
				flag=UserMainUI.this.dataGoods.getSelectedRow();
				if(flag<0) {
					return;
				}
				//UserMainUI.this.reloadGoods(i);
			}
	    });
		try {
			alladdr=AddressManager.loadallShop(BeanUserMsg.currentLoginUser);//�ѵ�ַ����ӽ���
		}catch (BaseException e) {
			e.printStackTrace();
		}
		try {
			allshop=ShopMannager.loadallShop();
		}catch (BaseException e) {
			e.printStackTrace();
		}
		try {
			allCou=CouponMannager.loadallCoupon();
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
		ShopName.setFont(new Font("����", Font.PLAIN, 15));
		ShopName.setBounds(247, 8, 100, 21);
		contentPane.add(ShopName);
		ShopName.setColumns(10);
		
		button = new JButton("\u67E5\u8BE2\u5546\u54C1\u7C7B");
		button.setFont(new Font("����", Font.PLAIN, 15));
		button.setBounds(357, 6, 135, 27);
		contentPane.add(button);
		button.addActionListener(this);
		
		button_1 = new JButton("\u663E\u793A\u6240\u6709\u5546\u54C1\u7C7B");
		button_1.setFont(new Font("����", Font.PLAIN, 15));
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
		label.setFont(new Font("����", Font.PLAIN, 15));
		label.setBounds(112, 10, 125, 19);
		contentPane.add(label);
		
		button_3 = new JButton("\u52A0\u5165\u8D2D\u7269\u8F66");
		button_3.setFont(new Font("����", Font.PLAIN, 17));
		button_3.setBounds(891, 7, 146, 23);
		button_3.addActionListener(this);
		contentPane.add(button_3);
		
		
		button_4.setFont(new Font("����", Font.PLAIN, 17));
		button_4.setBounds(1237, 7, 156, 23);
		button_4.addActionListener(this);
		contentPane.add(button_4);
		
		JScrollPane scrollPane_2 = new JScrollPane(this.dataCart);
		scrollPane_2.setBounds(1011, 38, 603, 414);
		this.getContentPane().add(scrollPane_2);
		
		button_5 = new JButton("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		button_5.setFont(new Font("����", Font.PLAIN, 17));
		button_5.setBounds(1422, 7, 160, 23);
		button_5.addActionListener(this);
		contentPane.add(button_5);
		
		textGoods = new JTextField();
		textGoods.setBounds(746, 9, 100, 21);
		contentPane.add(textGoods);
		textGoods.setColumns(10);
		
		label_1 = new JLabel("\u5546\u54C1\u6570\u91CF");
		label_1.setFont(new Font("����", Font.PLAIN, 15));
		label_1.setBounds(675, 12, 84, 15);
		contentPane.add(label_1);
		
		label_2 = new JLabel("\u603B\u4EF7\uFF1A");
		label_2.setFont(new Font("����", Font.PLAIN, 17));
		label_2.setBounds(1029, 472, 51, 42);
		contentPane.add(label_2);
		
		Labelsum = new JLabel("");
		Labelsum.setFont(new Font("����", Font.PLAIN, 17));
		Labelsum.setBounds(1090, 480, 125, 27);
		Labelsum.setText("��ʱû����Ʒ");
		contentPane.add(Labelsum);
		
		
		comboBox.setFont(new Font("����", Font.PLAIN, 17));
		comboBox.setBounds(1444, 484, 125, 30);
		int y=0;
		while(y<alladdr.size())
		{
			comboBox.addItem(alladdr.get(y).getAd_addr());
			y++;
		}
		contentPane.add(comboBox);
		
		JLabel label_3 = new JLabel("\u9009\u62E9\u6536\u8D27\u5730\u5740");
		label_3.setFont(new Font("����", Font.PLAIN, 17));
		label_3.setBounds(1314, 487, 120, 27);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u9009\u62E9\u5546\u5BB6");
		label_4.setFont(new Font("����", Font.PLAIN, 17));
		label_4.setBounds(1314, 542, 120, 27);
		contentPane.add(label_4);
		
		
		comboBox_1.setFont(new Font("����", Font.PLAIN, 17));
		comboBox_1.setBounds(1444, 546, 125, 30);
		int p=0;
		while(p<allshop.size())
		{
			comboBox_1.addItem(allshop.get(p).getSp_name()+" "+String.valueOf(allshop.get(p).getSp_star()));;
			p++;
		}
		contentPane.add(comboBox_1);
		
		button_2 = new JButton("\u521B\u5EFA\u8BA2\u5355");
		button_2.addActionListener(this);
		button_2.setFont(new Font("����", Font.PLAIN, 21));
		button_2.setBounds(1445, 738, 156, 51);
		contentPane.add(button_2);
		
		label_5 = new JLabel("\u6EE1\u51CF\u89C4\u5219");
		label_5.setFont(new Font("����", Font.PLAIN, 18));
		label_5.setBounds(1019, 650, 84, 27);
		contentPane.add(label_5);
		
		label_6 = new JLabel("100-10\uFF1B");
		label_6.setFont(new Font("����", Font.PLAIN, 17));
		label_6.setBounds(1019, 687, 84, 21);
		contentPane.add(label_6);
		
		label_7 = new JLabel("200-30\uFF1B");
		label_7.setFont(new Font("����", Font.PLAIN, 17));
		label_7.setBounds(1019, 718, 84, 21);
		contentPane.add(label_7);
		
		label_8 = new JLabel("300-50\uFF1B");
		label_8.setFont(new Font("����", Font.PLAIN, 17));
		label_8.setBounds(1019, 747, 84, 21);
		contentPane.add(label_8);
		
		label_9 = new JLabel("\u6EE1\u51CF\u4EF7\u683C\uFF1A");
		label_9.setFont(new Font("����", Font.PLAIN, 17));
		label_9.setBounds(1011, 524, 94, 42);
		contentPane.add(label_9);
		
		button_6 = new JButton("\u5546\u54C1\u8BC4\u4EF7");
		button_6.setFont(new Font("����", Font.PLAIN, 17));
		button_6.setBounds(1074, 8, 125, 23);
		button_6.addActionListener(this);
		contentPane.add(button_6);
		
		labelfsum = new JLabel("");
		labelfsum.setFont(new Font("����", Font.PLAIN, 17));
		labelfsum.setText("��ʱû����Ʒ");
		labelfsum.setBounds(1090, 524, 125, 39);
		contentPane.add(labelfsum);
		
		JLabel label_10 = new JLabel("\u9009\u62E9\u4F18\u60E0\u5238");
		label_10.setFont(new Font("����", Font.PLAIN, 17));
		label_10.setBounds(1314, 609, 120, 27);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("\u6700\u7EC8\u4EF7\u683C\uFF1A");
		label_11.setFont(new Font("����", Font.PLAIN, 17));
		label_11.setBounds(1011, 581, 94, 42);
		contentPane.add(label_11);
		
		//���ڼ�¼ʹ���Ż�ȯ֮��
		labelfin.setFont(new Font("����", Font.PLAIN, 17));
		labelfin.setBounds(1090, 576, 125, 39);
		labelfin.setText("");
		contentPane.add(labelfin);
		
		
		comboBox_2.setFont(new Font("����", Font.PLAIN, 17));
		comboBox_2.setBounds(1444, 613, 125, 30);
		int o=0;
		comboBox_2.addItem("��ʹ��");
		while(o<allCou.size())//��¼�Ż�ȯ��Ϣ
		{
			if(time<allCou.get(o).getEndtime().getTime())
				comboBox_2.addItem(allCou.get(o).getUc_red());
			else
				comboBox_2.addItem("�ѹ���");
			o++;
		}
		contentPane.add(comboBox_2);
		
		
		button_7.setFont(new Font("����", Font.PLAIN, 17));
		button_7.setBounds(1154, 625, 120, 42);
		button_7.addActionListener(this);
		contentPane.add(button_7);

		this.reloadShopTable();
	}
	public static void winMessage(String str) {// ��ʾ���ڣ��ж���ط�����
		JOptionPane.showMessageDialog(null, str, "��ʾ",JOptionPane.INFORMATION_MESSAGE);
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
			curGoods=allGood.get(flag);//�ҵ�ĳ����Ʒ
			try {
				number=Integer.parseInt(textGoods.getText());
			}catch (NumberFormatException e) {
			    //e.printStackTrace();//��׽�����쳣����ʱû��
			    winMessage("��������Ʒ����������ȷ������Ʒ����");
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
			if(sum>=100&&sum<200)
				fsum=sum-10;
			else if(sum>=200&&sum<300)
				fsum=sum-30;
			else if(sum>=300)
				fsum=sum-50;
			labelfsum.setText(String.valueOf(fsum));
		}
		else if(ac.getSource()==this.button_4)
		{
			tblCartData=new Object[20][BeanGoodDts.tblCartTitle.length];
			tabCartModel.setDataVector(tblCartData,tblCartTitle);
			this.dataCart.validate();
			this.dataCart.repaint();
			count=0;
			sum=0;
			fsum=0;
			ffsum=0;
			isCou=0;
			Labelsum.setText("��ʱû����Ʒ");
		}
		else if(ac.getSource()==this.button_5)
		{
			this.setVisible(false);
			UserUI frame=new UserUI();
			frame.setVisible(true);
		}
		else if(ac.getSource()==this.button_6)
		{
			CommentUI frame=new CommentUI(curGoods.getGd_id());
			frame.setVisible(true);
		} 
		else if(ac.getSource()==this.button_7)//ˢ�¼۸�
		{
			int status=-1;
			if(comboBox_2.getSelectedIndex()!=0)
				status=allCou.get(comboBox_2.getSelectedIndex()-1).getUc_plus();
			String can=String.valueOf(comboBox_2.getSelectedItem());
			if(can!="�ѹ���")
			{
				if(status==1)
				{
					ffsum=fsum-(int)comboBox_2.getSelectedItem();
					labelfin.setText(String.valueOf(ffsum));
					isCou=1;
				}
				else if(status==0)
				{
					labelfin.setText(String.valueOf(fsum));
					ffsum=fsum;
					winMessage("��ѡ�Ż�ȯ�����Ժ��������ӣ�");
					isCou=0;
				}
			if(comboBox_2.getSelectedIndex()==0) {
				isCou=0;
				ffsum=fsum;
				labelfin.setText(String.valueOf(fsum));
			}
			}
			else
			{
				labelfin.setText(String.valueOf(fsum));
			}
		}
		else if(ac.getSource()==this.button_2)//��������
		{
			Random random = new Random();//��ʱ�����һ�����ֽӵ�
			int maxrid;
			try {
				maxrid = RiderMannager.MaxRdId();
				System.out.println(maxrid);
				int shopIndex=comboBox_1.getSelectedIndex();
				order.setGo_sid(allshop.get(shopIndex).getSp_id());
				order.setGo_uid(BeanUserMsg.currentLoginUser.getUm_id());
				if(fsum==sum-10)//�򻯰汾��ʵ����Ӧ�ò�ѯ����������
					order.setGo_reid(1);
				else if(fsum==sum-30)
					order.setGo_reid(2);
				else if(fsum==sum-50)
					order.setGo_reid(3);
				
				if(isCou==1)//ʹ������ȯ����³�������ȯ����
					CouponMannager.UseCou(allCou.get(comboBox_2.getSelectedIndex()-1).getUc_cid());
				
				order.setGo_newpri(ffsum);//�����Ż�ȯ֮��������
				order.setGo_rid(random.nextInt(maxrid)+1);//�漴���ֽӵ�
				order.setGo_cid(allCou.get(comboBox_2.getSelectedIndex()-1).getUc_cid());
				order.setGo_sttime(new java.sql.Timestamp(System.currentTimeMillis()));//��ȡ��ǰʱ������޸��������ͣ�֮��ʱ���+1����
				Long time=System.currentTimeMillis();
				java.util.Date date=new java.util.Date(time);
				time+=60*1000*60;//��ȡһСʱ���ʱ��
				order.setGo_edtime(new java.sql.Timestamp(time));
				order.setGo_status("������");
				order.setGo_addr((String)comboBox.getSelectedItem());
				for(int k=0;k<count;k++)//�������붩����Ʒ��Ϣ
				{
					BeanOrderDts p=new BeanOrderDts();
					p.setOd_oid(order.getGo_oid());
					p.setOd_gid(Integer.parseInt((String) tblCartData[k][0]));
					p.setOd_price(Double.parseDouble((String)tblCartData[k][3]));
					p.setOd_count(Integer.parseInt((String)tblCartData[k][4]));
					allorder.add(p);
				}
				OrderMannager.InsOrder(order);
				for(int k=0;k<allorder.size();k++)
					OrderMannager.InsOrderDts(allorder.get(k));
				winMessage("�����ɹ���");
				tblCartData=new Object[20][BeanGoodDts.tblCartTitle.length];
				tabCartModel.setDataVector(tblCartData,tblCartTitle);
				this.dataCart.validate();
				this.dataCart.repaint();
				count=0;
				sum=0;
				fsum=0;
				ffsum=0;
				isCou=0;
				Labelsum.setText("��ʱû����Ʒ");//���ù��ﳵ�͸��ֶ���
				order=new BeanGoodOrder();//���³�ʼ������ȡ�µĶ���ID
			}catch (NumberFormatException e) {
			    winMessage("��ʽ���� ���������ù��ﳵ");
			    return;
			}catch(BaseException e) {
				e.printStackTrace();
				winMessage("��ʽ���� ���������ù��ﳵ");
			}
		}
	}
}
