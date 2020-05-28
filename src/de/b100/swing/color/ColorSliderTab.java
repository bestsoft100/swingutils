package de.b100.swing.color;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.b100.swing.JGridPanel;

public abstract class ColorSliderTab extends JGridPanel implements ChangeListener{

	private static final long serialVersionUID = 1L;

	public ColorListener colorPicker;

	private JLabel nameLabel1;
	private JLabel nameLabel2;
	private JLabel nameLabel3;
	
	public JLabel label1;
	public JLabel label2;
	public JLabel label3;
	
	public JSlider slider1;
	public JSlider slider2;
	public JSlider slider3;
	
	public ColorSliderTab(ColorListener colorPicker, String slider1name, String slider2name, String slider3name) {
		this.colorPicker = colorPicker;
		getGridBagConstraints().fill = GridBagConstraints.HORIZONTAL;
		getGridBagConstraints().anchor = GridBagConstraints.NORTH;

		nameLabel1 = new JLabel(slider1name);
		nameLabel2 = new JLabel(slider2name);
		nameLabel3 = new JLabel(slider3name);

		add(nameLabel1, 0, 0, 2, 1, 1, 0);
		add(nameLabel2, 0, 2, 2, 1, 1, 0);
		add(nameLabel3, 0, 4, 2, 1, 1, 0);
		
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		
		Dimension dim1 = new Dimension(32, 0);

		label1.setPreferredSize(dim1);
		label2.setPreferredSize(dim1);
		label3.setPreferredSize(dim1);
	}
	
	public void stateChanged(ChangeEvent e) {
		stateChanged((JSlider) e.getSource());
	}
	
	public void addSliders() {
		add(label1, 0, 1, 1, 1, 1, 0);
		add(label2, 0, 3, 1, 1, 1, 0);
		add(label3, 0, 5, 1, 1, 1, 0);
		
		add(slider1, 1, 1, 1, 1, 1, 0);
		add(slider2, 1, 3, 1, 1, 1, 0);
		add(slider3, 1, 5, 1, 1, 1, 0);

		updateLabelValues(slider1.getValue(), slider2.getValue(), slider3.getValue());
	}
	
	public void updateLabelValues(int s1, int s2, int s3) {
		label1.setText(""+s1);
		label2.setText(""+s2);
		label3.setText(""+s3);
	}
	
	public abstract void stateChanged(JSlider slider);
	
	public void setValue(int v1, int v2, int v3) {
		slider1.setValue(v1);
		slider2.setValue(v2);
		slider3.setValue(v3);
	}
	
}
