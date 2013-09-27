package com.jsandusky.jrpgexample;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JRadioButton;

import com.jsandusky.drt.AnimEditorApp;
import com.jsandusky.drt.AnimEditorApp.Mode;

import javax.swing.JSplitPane;
import javax.swing.JList;

public class AnimEditor extends JDialog {


	private final JPanel contentPanel = new JPanel();

	JList bonesList;
	DefaultListModel boneModel = new DefaultListModel();
	
	AnimEditorApp gdxApp;
	com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas canvas;
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
			bones.setLayout(new BorderLayout(0, 0));
			{
				JSplitPane splitPane = new JSplitPane();
				splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
				bones.add(splitPane, BorderLayout.CENTER);
				{
					JPanel panel = new JPanel();
					splitPane.setRightComponent(panel);
				}
				{
					bonesList = new JList();
					bonesList.setModel(boneModel);
					
					boneModel.addElement("Bone 1");
					boneModel.addElement("Bone 2");
					splitPane.setLeftComponent(bonesList);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				AnimTrack animTrack = new AnimTrack();
				animTrack.ticks = new ArrayList<AnimTrack.Tick>();
				for (int i = 0; i < 20; ++i) {
					AnimTrack.Tick t = new AnimTrack.Tick();
					animTrack.ticks.add(t);
				}
				//JPanel animTrack = new JPanel();
				panel.add(animTrack, BorderLayout.SOUTH);
			}
			{
				gdxApp = new AnimEditorApp();
				canvas = new com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas(gdxApp, false);
				panel.add(canvas.getCanvas(), BorderLayout.CENTER);
			}
		}
		{
			JToolBar toolBar = new JToolBar();
			ButtonGroup grp = new ButtonGroup();
			contentPanel.add(toolBar, BorderLayout.NORTH);
			{
				JRadioButton rdbtnNoAction = new JRadioButton("No Action");
				toolBar.add(rdbtnNoAction);
				grp.add(rdbtnNoAction);
				rdbtnNoAction.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						AnimEditor.this.gdxApp.setMode(Mode.None);
					}
				});
			}
			{
				JRadioButton rdbtnMove = new JRadioButton("Move");
				toolBar.add(rdbtnMove);
				grp.add(rdbtnMove);
				rdbtnMove.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						AnimEditor.this.gdxApp.setMode(Mode.Move);
					}
				});
			}
			{
				JRadioButton rdbtnRotate = new JRadioButton("Rotate");
				toolBar.add(rdbtnRotate);
				grp.add(rdbtnRotate);
				rdbtnRotate.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						AnimEditor.this.gdxApp.setMode(Mode.Rotate);
					}
				});
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
