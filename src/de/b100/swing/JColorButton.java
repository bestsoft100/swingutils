package de.b100.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JColorButton extends JButton implements ValueChangeListener{

	private static JColorPicker colorPicker;
	private static int padding = 4;
	
	static {
		colorPicker = new JColorPicker(null);
		colorPicker.setAlwaysOnTop(true);
	}
	
	private static final long serialVersionUID = 1L;
	
	private Color color;
	
	private ButtonListener onColorChange;
	
	public JColorButton(Color color) {
		this.color = color;
		
		addActionListener((e) -> {
			//Colorbutton Pressed
			colorPicker.getValueChangeListeners().clear();
			colorPicker.getValueChangeListeners().add(this);
			colorPicker.setColor(this.color);
			colorPicker.setVisible(true);
			colorPicker.setButtonListener((source) -> {
				colorPicker.setVisible(false);
				if(onColorChange != null) {
					onColorChange.buttonPressed(source);
				}
			});
		});
		
		Dimension dim1 = new Dimension(20, 20);
		setMinimumSize(dim1);
		setPreferredSize(dim1);;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(color);
		g.fillRect(padding, padding, getWidth() - padding*2, getHeight() - padding*2);
	}

	public void valueChanged(Object source) {
		if(source == colorPicker) {
			setColor(colorPicker.getColor());
			
			
		}
	}
	
	public void setOnColorChange(ButtonListener onColorChange) {
		this.onColorChange = onColorChange;
	}
	
	public ButtonListener getOnColorChange() {
		return onColorChange;
	}
	
}
