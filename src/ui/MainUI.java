package ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ui.*;
public class MainUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar();//用于上方菜单
	private LoginUI dlgLogin=null;//登陆相关
	private JPanel statusBar = new JPanel();//窗口容器之类
	public MainUI(){
	this.setTitle("个人计划管理系统");
	dlgLogin=new LoginUI();
	dlgLogin.setVisible(false);
	}
}
