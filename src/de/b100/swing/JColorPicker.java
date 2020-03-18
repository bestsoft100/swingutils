package de.b100.swing;

import static de.b100.swing.SwingUtils.newJButton;
import static de.b100.swing.SwingUtils.newJPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import de.b100.swing.color.ColorListener;
import de.b100.swing.color.ColorPreviewPanel;
import de.b100.swing.color.HSBSliderTab;
import de.b100.swing.color.RGBSliderTab;

public class JColorPicker implements WindowListener, ActionListener, ColorListener{

	public static final int ACTION_OK = 0;
	public static final int ACTION_CANCEL = 1;
	
	private JFrame frame;
	
	private JTabbedPane tabs;
	
	private JGridPanel panel;
	private JPanel preview;

	private JButton buttonOk;
	private JButton buttonCancel;
	
	private ActionListener actionListener;

	private int red;
	private int green;
	private int blue;
	
	public JColorPicker(ActionListener actionListener) {
		this(null, Color.black, actionListener);
	}
	
	public JColorPicker(JFrame parent, Color defaultVal, ActionListener actionListener) {
		this.actionListener = actionListener;
		
		frame = new JFrame("Color picker");
		
		tabs = new JTabbedPane();
		tabs.addTab("RGB", new RGBSliderTab(this, defaultVal.getRed(), defaultVal.getGreen(), defaultVal.getBlue()));
		tabs.addTab("HSB", new HSBSliderTab(this, 0, 100, 100));
		tabs.setPreferredSize(new Dimension(320, 180));
		
		panel = new JGridPanel(0);
		preview = new JPanel();
		preview.add(new ColorPreviewPanel(this, 64, 64));

		buttonOk = newJButton("Ok", this);
		buttonCancel = newJButton("Cancel", this);

		panel.add(preview, 0, 0, 1, 1, 0.0, 0.0);
		panel.add(tabs, 1, 0);
		panel.add(newJPanel(buttonCancel, buttonOk), 0, 1, 2, 1);
		
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(parent);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(this);
		frame.setVisible(true);
	}
	
	public Color getColor() {
		return new Color(red, green, blue);
	}

	public void windowClosing(WindowEvent e) {
		close();
	}
	
	public void close() {
		frame.setVisible(false);
		frame.dispose();
		frame = null;
	}
	
	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public void setRed(int red) {
		this.red = red;
		preview.repaint();
	}
	
	public void setGreen(int green) {
		this.green = green;
		preview.repaint();
	}
	
	public void setBlue(int blue) {
		this.blue = blue;
		preview.repaint();
	}
	
	public void updateColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		preview.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonOk) {
			if(actionListener != null) {
				actionListener.actionPerformed(new ActionEvent(this, ACTION_OK, "ok"));
			}else {
				System.out.println("No Action");
			}
		}
		if(e.getSource() == buttonCancel) {
			if(actionListener != null) {
				actionListener.actionPerformed(new ActionEvent(this, ACTION_CANCEL, "cancel"));
			}else {
				System.out.println("No Action");
				close();
			}
		}
	}

	public void windowOpened(WindowEvent e) {}

	public void windowClosed(WindowEvent e) {}

	public void windowIconified(WindowEvent e) {}

	public void windowDeiconified(WindowEvent e) {}

	public void windowActivated(WindowEvent e) {}

	public void windowDeactivated(WindowEvent e) {}
	
	/**
	 * Just for Testing purposes
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new JColorPicker(null);
	}
	
}
