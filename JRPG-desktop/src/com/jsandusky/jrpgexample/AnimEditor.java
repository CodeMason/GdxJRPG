package com.jsandusky.jrpgexample;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JRadioButton;

public class AnimEditor extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AnimEditor dialog = new AnimEditor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AnimEditor() {
		setTitle("Animation Editor");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel textures = new JPanel();
			contentPanel.add(textures, BorderLayout.WEST);
		}
		{
			JPanel bones = new JPanel();
			contentPanel.add(bones, BorderLayout.EAST);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel animTrack = new JPanel();
				panel.add(animTrack, BorderLayout.SOUTH);
			}
			{
				JPanel display = new JPanel();
				panel.add(display, BorderLayout.CENTER);
			}
		}
		{
			JToolBar toolBar = new JToolBar();
			contentPanel.add(toolBar, BorderLayout.NORTH);
			{
				JRadioButton rdbtnNoAction = new JRadioButton("No Action");
				toolBar.add(rdbtnNoAction);
			}
			{
				JRadioButton rdbtnMove = new JRadioButton("Move");
				toolBar.add(rdbtnMove);
			}
			{
				JRadioButton rdbtnRotate = new JRadioButton("Rotate");
				toolBar.add(rdbtnRotate);
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
				JMenu mnImport = new JMenu("Import");
				menuBar.add(mnImport);
				{
					JMenuItem mntmAnimation = new JMenuItem("Animation");
					mnImport.add(mntmAnimation);
				}
				{
					JMenuItem mntmTextures = new JMenuItem("Textures");
					mnImport.add(mntmTextures);
				}
			}
		}
	}

}
