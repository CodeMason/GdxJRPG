package com.jsandusky.jrpgexample;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.JRadioButton;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class ScriptEditor extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ScriptEditor dialog = new ScriptEditor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ScriptEditor() {
		setTitle("GdxJRPG Script Editor");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				JTextPane textPane = new JTextPane();
				scrollPane.setViewportView(textPane);
			}
		}
		{
			JList list = new JList();
			contentPanel.add(list, BorderLayout.WEST);
		}
		{
			JToolBar toolBar = new JToolBar();
			contentPanel.add(toolBar, BorderLayout.NORTH);
			{
				JRadioButton rdbtnScripts = new JRadioButton("Scripts");
				toolBar.add(rdbtnScripts);
			}
			{
				JRadioButton rdbtnErrors = new JRadioButton("Errors");
				toolBar.add(rdbtnErrors);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnFile = new JMenu("File");
				menuBar.add(mnFile);
				{
					JMenuItem mntmLoadFile = new JMenuItem("Load FIle");
					mnFile.add(mntmLoadFile);
				}
			}
			{
				JMenu mnEdit = new JMenu("Edit");
				menuBar.add(mnEdit);
				{
					JMenuItem mntmUndo = new JMenuItem("Undo");
					mnEdit.add(mntmUndo);
				}
				{
					JMenuItem mntmRedo = new JMenuItem("REdo");
					mnEdit.add(mntmRedo);
				}
				{
					JSeparator separator = new JSeparator();
					mnEdit.add(separator);
				}
				{
					JMenuItem mntmSelectAll = new JMenuItem("Select All");
					mnEdit.add(mntmSelectAll);
				}
			}
		}
	}

}
