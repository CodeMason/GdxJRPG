package com.jsandusky.jrpgexample;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.badlogic.gdx.utils.Json;
import com.jsandusky.jrpg.model.CharacterClass;
import com.jsandusky.jrpg.model.Database;
import com.jsandusky.jrpg.model.Equipment;
import com.jsandusky.jrpg.model.Hero;
import com.jsandusky.jrpg.model.Item;
import com.jsandusky.jrpg.model.Monster;
import com.jsandusky.jrpg.model.MonsterTeam;
import com.jsandusky.jrpg.model.Skill;

public class EditorCore extends JFrame {

	private JPanel contentPane;
	Database activeDB;
	JTabbedPane tabbedPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorCore frame = new EditorCore();
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
	public EditorCore() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenuItem newFile = new JMenuItem("New");
		file.add(newFile);
		newFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EditorCore.this.activeDB = new Database();
				tabbedPane.removeAll();
				tabbedPane.add("Character Classes", new ClassEditor(activeDB,CharacterClass.class));
				tabbedPane.add("Characters", new ClassEditor(activeDB,Hero.class));
				tabbedPane.add("Skills",new ClassEditor(activeDB,Skill.class));
				tabbedPane.add("Items",new ClassEditor(activeDB,Item.class));
				tabbedPane.add("Equipment",new ClassEditor(activeDB,Equipment.class));
				tabbedPane.add("Monsters",new ClassEditor(activeDB,Monster.class));
				tabbedPane.add("Monster Groups",new ClassEditor(activeDB,MonsterTeam.class));
			}
		});
		
		JMenuItem saveFile = new JMenuItem("Save");
		file.add(saveFile);
		saveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chose = new JFileChooser();
				try {
					int result = chose.showSaveDialog(EditorCore.this);
					if (result == JFileChooser.APPROVE_OPTION) {
						Json json = new Json();
						String data = json.toJson(EditorCore.this.activeDB);
						File file = chose.getSelectedFile();
						PrintWriter p = new PrintWriter(file.getCanonicalPath());
						p.print(data);
						p.flush();
					}
				} catch (Exception ex) {
					int i = 0;
				}
			}
		});
		
		JMenuItem openFile = new JMenuItem("Open");
		file.add(openFile);
		
		JMenuItem exit = new JMenuItem("Quit");
		file.add(exit);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("Char Classes", new JPanel());
		tabbedPane.addTab("Characters",new JPanel());
		tabbedPane.addTab("Skills",new JPanel());
		tabbedPane.addTab("Items",new JPanel());
		tabbedPane.addTab("Equipment",new JPanel());
		tabbedPane.addTab("Skills",new JPanel());
		tabbedPane.addTab("Monsters",new JPanel());
		tabbedPane.addTab("Monster Groups",new JPanel());
		tabbedPane.addTab("Text",new JPanel());
	}

}
