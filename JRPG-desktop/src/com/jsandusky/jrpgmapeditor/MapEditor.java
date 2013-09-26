package com.jsandusky.jrpgmapeditor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JToolBar;

public class MapEditor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapEditor frame = new MapEditor();
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
	public MapEditor() {
		setTitle("GdxJRPG Map Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem newMap = new JMenuItem("New");
		mnFile.add(newMap);
		
		JMenuItem openMap = new JMenuItem("Open");
		mnFile.add(openMap);
		
		JMenuItem cMap = new JMenuItem("Close");
		mnFile.add(cMap);
		
		JMenuItem exMap = new JMenuItem("Exit");
		mnFile.add(exMap);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem entMap = new JMenuItem("Actors");
		mnEdit.add(entMap);
		
		JMenuItem scMap = new JMenuItem("Scripting");
		mnEdit.add(scMap);
		
		JMenuItem paintMap = new JMenuItem("Paint Tiles");
		mnEdit.add(paintMap);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem setMap = new JMenuItem("Database");
		mnSettings.add(setMap);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.WEST);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		
		JList list = new JList();
		scrollPane_1.setViewportView(list);
		
		JPanel panel_2 = new JPanel();
		splitPane.setLeftComponent(panel_2);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.9);
		contentPane.add(splitPane_1, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane_1.setLeftComponent(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_1.setRightComponent(scrollPane);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		ButtonGroup grp = new ButtonGroup();
		
		JButton btn = new JButton("Save");
		toolBar.add(btn);
		
		btn = new JButton("Map Info");
		toolBar.add(btn);
		
		JRadioButton abtn = new JRadioButton("Paint Mode");
		toolBar.add(abtn);
		grp.add(abtn);
		abtn = new JRadioButton("Edit Actors");
		toolBar.add(abtn);
		grp.add(abtn);
	}

}
