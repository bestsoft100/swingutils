package de.b100.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public abstract class SwingUtils {
	
	public static GridBagConstraints c(GridBagConstraints c, int x, int y, int w, int h, double wx, double wy) {
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
		c.weightx = wx;
		c.weighty = wy;
		return c;
	}
	
	public static JButton newJButton(String name) {
		JButton button = new JButton(name);
		return button;
	}
	
	public static JButton newJButton(String name, ActionListener actionListener) {
		JButton button = new JButton(name);
		button.addActionListener(actionListener);
		return button;
	}
	
	public static JSlider newJSlider(int min, int max, int value, Dimension size, ChangeListener changeListener) {
		JSlider slider = new JSlider(min, max, value);
		slider.setPreferredSize(size);
		slider.addChangeListener(changeListener);
		return slider;
	}
	
	public static JSlider newJSlider(int min, int max, int value, ChangeListener changeListener) {
		JSlider slider = new JSlider(min, max, value);
		slider.addChangeListener(changeListener);
		return slider;
	}
	
	public static JLabel newJLabel(String text, Dimension size) {
		JLabel label = new JLabel(text);
		label.setPreferredSize(size);
		return label;
	}
	
	public static JPanel newJPanel(Component...components) {
		JPanel panel = new JPanel();
		for(Component component : components) {
			panel.add(component);
		}
		return panel;
	}
	
	public static JMenu newJMenu(String name, JMenuItem... menuitems) {
		JMenu menu = new JMenu(name);
		
		for(JMenuItem menuItem : menuitems) {
			menu.add(menuItem);
		}
		
		return menu;
	}
	
	public static JMenu newJMenu(String name, ActionListener actionListener, String... entries) {
		JMenuItem[] menuitems = new JMenuItem[entries.length];
		
		int i=0;
		for(String str : entries) {
			menuitems[i] = new JMenuItem(str);
			menuitems[i].addActionListener(actionListener);
			i++;
		}
		
		return newJMenu(name, menuitems);
	}
	
	public static JFrame newJFrame(JPanel panel) {
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		return frame;
	}
	
	public static JComboBox<String> newJComboBox(ArrayList<String> values){
		JComboBox<String> comboBox = new JComboBox<>();
		
		for(String str : values) {
			comboBox.addItem(str);
		}
		
		return comboBox;
	}
	
	public static JComboBox<String> newJComboBox(String... values){
		JComboBox<String> comboBox = new JComboBox<>();
		
		for(String str : values) {
			comboBox.addItem(str);
		}
		
		return comboBox;
	}
	
}
