package de.b100.swing;

import javax.swing.JFrame;

public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Window() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public Window(String title) {
		this();
		setTitle(title);
	}
	
}
