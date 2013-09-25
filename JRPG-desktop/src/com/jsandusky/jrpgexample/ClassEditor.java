package com.jsandusky.jrpgexample;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jsandusky.jrpg.model.Base;
import com.jsandusky.jrpg.model.CharacterClass;
import com.jsandusky.jrpg.model.Database;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ClassEditor extends JPanel {

	/**
	 * Create the panel.
	 */
	Database data;
	JList list;
	DefaultListModel model;
	JScrollPane sp;
	AutoForm form;
	Class clazz;
	
	public ClassEditor(Database data, Class c) {
		clazz = c;
		this.data = data;
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);
		
		
		JSplitPane listSide = new JSplitPane();
		listSide.setResizeWeight(0.99);
		listSide.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		list = new JList();
		list.setModel(model = new DefaultListModel());
		listSide.setLeftComponent(list);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg) {
				int idx = arg.getFirstIndex();
				if (idx >= 0) {
					sp.invalidate();
					form.setTarget(model.get(idx));
				}
			}
		});
		
		JPanel buttons = new JPanel();
		BorderLayout l = new BorderLayout(0,0);
		buttons.setLayout(l);
		
		JButton btn = new JButton("New");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Object obj = clazz.newInstance();
					model.addElement(obj);
					ClassEditor.this.data.Objects.put(((Base)obj).getID(), (Base)obj);
				} catch (Exception ex) {
					
				}
			}
		});
		buttons.add(btn,BorderLayout.WEST);
		JButton del = new JButton("Delete");
		buttons.add(del,BorderLayout.EAST);
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int sub = ClassEditor.this.list.getSelectedIndex();
				if (sub >= 0) {
					model.remove(sub);
				}
			}
		});
		
		listSide.setDividerLocation(0.99);
		listSide.setRightComponent(buttons);
		
		splitPane.setLeftComponent(listSide);
		
		sp = new JScrollPane();
		sp.setViewportView(form = new AutoForm());
		
		splitPane.setRightComponent(sp);
		
		reload();
	}
	
	void reload() {
		model.clear();
		ArrayList clist = data.get(clazz);
		for (int i = 0; i < clist.size(); ++i) {
			model.addElement(clist.get(i).toString());
		}
	}
}
