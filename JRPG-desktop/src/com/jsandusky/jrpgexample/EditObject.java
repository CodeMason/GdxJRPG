package com.jsandusky.jrpgexample;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.jsandusky.jrpg.model.Database;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditObject extends JPanel {

	/**
	 * Create the panel.
	 */
	
	AutoForm form;
	JButton save;
	JLabel msg;
	Object editing;
	Database db;
	
	public interface SaveCallBack {
		public void onSave();
	}
	
	SaveCallBack call_;
	
	public EditObject(Database db, SaveCallBack onsave) {
		call_ = onsave;
		this.db = db;
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		save = new JButton("Save");
		panel.add(save,BorderLayout.EAST);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (EditObject.this.editing != null) {
					EditObject.this.form.saveChanges();
					EditObject.this.call_.onSave();
				}
			}
		});
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.isControlDown() && arg0.getKeyCode() == KeyEvent.VK_S) {
					if (EditObject.this.editing != null) {
						EditObject.this.form.saveChanges();
						EditObject.this.call_.onSave();
						JOptionPane.showMessageDialog(null, "Object Saved");
					}
					arg0.consume();	
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		msg = new JLabel("");
		panel.add(msg,BorderLayout.WEST);
		
		form = new AutoForm(db);
		splitPane.setRightComponent(form);
	}
	
	public void setObject(Object obj) {
		editing  = obj;
		form.setTarget(editing);
	}
	
	

}
