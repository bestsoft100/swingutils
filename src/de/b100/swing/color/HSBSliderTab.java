package de.b100.swing.color;

import static de.b100.swing.SwingUtils.newJSlider;

import javax.swing.JSlider;

import de.b100.swing.JColorPicker;

public class HSBSliderTab extends ColorSliderTab{

	private static final long serialVersionUID = 1L;

	public HSBSliderTab(JColorPicker colorPicker, int h, int s, int b) {
		super(colorPicker, "Hue", "Saturation", "Brightness");

		slider1 = newJSlider(0, 360, h, this);
		slider2 = newJSlider(0, 100, s, this);
		slider3 = newJSlider(0, 100, b, this);
		
		addSliders();
	}

	public void stateChanged(JSlider slider) {
		int hue = slider1.getValue();
		int saturation = slider2.getValue();
		int brightness = slider3.getValue();
		
		double[] color = rainbowColor(hue);
		
		int i=0;
		while(i<3) {
			color[i] = mix(color[i], 1.0, saturation / 100.0);
			color[i] *= brightness / 100.0;
			i++;
		}
		
		updateLabelValues(hue, saturation, brightness);
		colorPicker.updateColor((int)(color[0]*255), (int)(color[1]*255), (int)(color[2]*255));
	}
	
	public static double mix(double a, double b, double c) {
		return a*c + b*((c*-1)+1);
	}
	
	public static double invert(double a) {
		return a*-1+1;
	}
	
	public static double[] rainbowColor(double angle){
		int c = (int)(angle / 360.0);
		
		angle -= 360.0 * c;
		
		double[] color = new double[3];
		
		if(angle < 60.0){
			color[0] = 1.0;
			color[1] = angle/60.0;
			color[2] = 0.0;
			return color;
		}
		if(angle < 120.0){
			color[0] = invert((angle-60.0)/60.0);
			color[1] = 1.0;
			color[2] = 0.0;
			return color;
		}
		if(angle < 180.0){
			color[0] = 0.0;
			color[1] = 1.0;
			color[2] = (angle-120.0)/60.0;
			return color;
		}
		if(angle < 240.0){
			color[0] = 0.0;
			color[1] = invert((angle-180.0)/60.0);
			color[2] = 1.0;
			return color;
		}
		if(angle < 300.0){
			color[0] = (angle-240.0)/60.0;
			color[1] = 0.0;
			color[2] = 1.0;
			return color;
		}
		color[0] = 1.0;
		color[1] = 0.0;
		color[2] = invert((angle-300.0)/60.0);
		return color;
	}
	
}
