package de.b100.swing.color;

import static de.b100.swing.SwingUtils.newJSlider;

import javax.swing.JSlider;

import de.b100.swing.JColorPicker;

public class RGBSliderTab extends ColorSliderTab{

	private static final long serialVersionUID = 1L;

	public RGBSliderTab(JColorPicker colorPicker, int r, int g, int b) {
		super(colorPicker, "Red", "Green", "Blue");
		
		slider1 = newJSlider(0, 255, r, this);
		slider2 = newJSlider(0, 255, g, this);
		slider3 = newJSlider(0, 255, b, this);
		
		addSliders();
	}

	public void stateChanged(JSlider slider) {
		updateLabelValues(slider1.getValue(), slider2.getValue(), slider3.getValue());
		colorPicker.updateColor(slider1.getValue(), slider2.getValue(), slider3.getValue());
	}
	
	
}
