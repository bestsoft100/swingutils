package de.b100.swing.color;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.b100.swing.JColorPicker;

public class ColorPreviewPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JColorPicker colorPicker;
	
	public ColorPreviewPanel(JColorPicker colorPicker, int w, int h) {
		this.colorPicker = colorPicker;
		
		Dimension dim1 = new Dimension(w, h);
		
		setPreferredSize(dim1);
		setMinimumSize(dim1);
		setMaximumSize(dim1);
	}
	
	public void paint(Graphics g) {
		g.setColor(colorPicker.getColor());
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
