package de.b100.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Application implements WindowListener{
	
	public boolean running = false;
	
	private JFrame frame;
	private JPanel panel;
	
	public final void start(int width, int height) {
		frame = new JFrame();
		panel = new ApplicationPanel(this);
		
		Dimension dim1 = new Dimension(width, height);
		panel.setPreferredSize(dim1);
		
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(this);
		frame.setVisible(true);
		
		running = true;
		run();
		
		frame.setVisible(false);
		frame.dispose();
	}
	
	public void update() {
		panel.repaint();
	}
	
	public abstract void run();
	
	public abstract void update(Graphics g);
	
	public void windowClosing(WindowEvent e) {
		running = false;
	}
	
	public class ApplicationPanel extends JPanel{

		private static final long serialVersionUID = 1L;
		
		private Application app;
		
		public ApplicationPanel(Application app) {
			this.app = app;
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			app.update(g);
		}
		
	}
	
	public void windowActivated(WindowEvent e) {}
	
	public void windowClosed(WindowEvent e) {}
	
	public void windowDeactivated(WindowEvent e) {}
	
	public void windowDeiconified(WindowEvent e) {}
	
	public void windowIconified(WindowEvent e) {}
	
	public void windowOpened(WindowEvent e) {}
	
}
