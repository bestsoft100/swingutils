package de.b100.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

public class JPlaceholderTextField extends JTextField{

	public static Color defaultPlaceholderColor = Color.DARK_GRAY; 
	
	private static final long serialVersionUID = 1L;
	
	private String placeholder;
	private Color placeHolderColor;
	
	public JPlaceholderTextField(String text, String placeholder) {
		setText(text);
		this.placeholder = placeholder;
		this.placeHolderColor = defaultPlaceholderColor;
	}
	
	public JPlaceholderTextField(String text, String placeholder, Color placeholderColor) {
		setText(text);
		this.placeholder = placeholder;
		this.placeHolderColor = placeholderColor;
	}
	
	public JPlaceholderTextField(String placeholder) {
		this.placeholder = placeholder;
		this.placeHolderColor = defaultPlaceholderColor;
	}
	
	public JPlaceholderTextField(String placeholder, Color placeholderColor) {
		this.placeholder = placeholder;
		this.placeHolderColor = placeholderColor;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(getText() == null || getText().length() < 1) {
			g.setFont(getFont());
			g.setColor(placeHolderColor);
			g.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
		}
		
	}
	
	public String getPlaceholder() {
		return placeholder;
	}
	
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	
	public Color getPlaceHolderColor() {
		return placeHolderColor;
	}
	
	public void setPlaceHolderColor(Color placeHolderColor) {
		this.placeHolderColor = placeHolderColor;
	}
	
}
