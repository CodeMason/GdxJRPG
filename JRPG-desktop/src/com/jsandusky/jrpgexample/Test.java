package com.jsandusky.jrpgexample;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class Test extends JPanel {

	/**
	 * Create the panel.
	 */
	public Test() {
		setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("New button");
		add(btnNewButton_1, BorderLayout.WEST);

	}

}
