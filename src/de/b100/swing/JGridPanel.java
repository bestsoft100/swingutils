package de.b100.swing;

import static de.b100.swing.SwingUtils.c;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class JGridPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints gridBagConstraints;

	public double defaultHorizontalWeight = 1.0;
	public double defaultVerticalWeight = 1.0;
	
	public JGridPanel() {
		this(0);
	}
	
	public JGridPanel(int cellSpacing) {
		setLayout(new GridBagLayout());
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(cellSpacing, cellSpacing, cellSpacing, cellSpacing);
	}
	
	public void add(Component component, int x, int y) {
		this.add(component, x, y, 1, 1);
	}
	
	public void add(Component component, int x, int y, double weightX, double weightY) {
		this.add(component, x, y, weightX, weightY, 1, 1);
	}
	
	public void add(Component component, int x, int y, double weightX, double weightY, int w, int h) {
		super.add(component, c(gridBagConstraints, x, y, weightX, weightY, w, h));
	}
	
	@Deprecated
	public Component add(Component comp) {
		super.add(comp);
		return comp;
	}
	
	public GridBagConstraints getGridBagConstraints() {
		return gridBagConstraints;
	}
	
	public int getFillMode() {
		return gridBagConstraints.fill;
	}
	
	public void setFillMode(int fillMode) {
		gridBagConstraints.fill = fillMode;
	}

}
