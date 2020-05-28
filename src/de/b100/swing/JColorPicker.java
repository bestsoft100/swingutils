package de.b100.swing;

import static de.b100.swing.SwingUtils.newJButton;
import static de.b100.swing.SwingUtils.newJPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import de.b100.swing.color.ColorListener;
import de.b100.swing.color.ColorPreviewPanel;
import de.b100.swing.color.ColorSliderTab;
import de.b100.swing.color.HSBSliderTab;
import de.b100.swing.color.RGBSliderTab;

public class JColorPicker extends Window implements WindowListener, ActionListener, ColorListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final int ACTION_OK = 0;
	public static final int ACTION_CANCEL = 1;
	
	private JTabbedPane tabs;
	
	private JGridPanel panel;
	private JPanel preview;

	private JButton okButton;
	private JButton cancelButton;
	
	private HSBSliderTab hsbSliderTab;
	private RGBSliderTab rgbSliderTab;
	
	private ButtonListener buttonListener;
	private List<ValueChangeListener> valueChangeListeners;

	private int red;
	private int green;
	private int blue;
	
	public JColorPicker(ButtonListener buttonListener) {
		this(null, Color.black, buttonListener);
	}
	
	public JColorPicker(JFrame parent, Color defaultVal, ButtonListener buttonListener) {
		valueChangeListeners = new ArrayList<ValueChangeListener>();
		this.buttonListener = buttonListener;
		
		setTitle("Color picker");
		
		hsbSliderTab = new HSBSliderTab(this, 0, 0, 0);
		rgbSliderTab = new RGBSliderTab(this, 0, 0, 0);
		
		tabs = new JTabbedPane();
		tabs.addTab("RGB", rgbSliderTab);
		tabs.addTab("HSB", hsbSliderTab);
		tabs.setPreferredSize(new Dimension(320, 180));
		
		panel = new JGridPanel(0);
		preview = new JPanel();
		preview.add(new ColorPreviewPanel(this, 64, 64));

		okButton = newJButton("Ok", this);
		cancelButton = newJButton("Cancel", this);

		panel.add(preview, 0, 0, 1, 1, 0.0, 0.0);
		panel.add(tabs, 1, 0);
		panel.add(newJPanel(cancelButton, okButton), 0, 1, 2, 1);
		
		add(panel);
		setResizable(false);
		pack();
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		
		setColor(defaultVal.getRed(), defaultVal.getGreen(), defaultVal.getBlue());
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("JColorPicker Button pressed: "+e.getSource());
		if(buttonListener != null && e.getSource() instanceof JButton) {
			buttonListener.buttonPressed(e.getSource());
		}
	}
	
	public Color getColor() {
		return new Color(red, green, blue);
	}
	
	public void setColor(Color color) {
		updateColor(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public void setColor(int red, int green, int blue) {
		updateColor(red, green, blue);
	}

	public void windowClosing(WindowEvent e) {
		close();
	}
	
	public void close() {
		setVisible(false);
		dispose();
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
	
	public JButton getOkButton() {
		return okButton;
	}
	
	public JButton getCancelButton() {
		return cancelButton;
	}
	
	public void updateColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		preview.repaint();
		rgbSliderTab.setValue(red, green, blue);
		//TODO RGB => HSB
		for(ValueChangeListener valueChangeListener : valueChangeListeners) {
			valueChangeListener.valueChanged(this);
		}
	}
	
	public List<ValueChangeListener> getValueChangeListeners() {
		return valueChangeListeners;
	}
	
	public ButtonListener getButtonListener() {
		return buttonListener;
	}
	
	public void setButtonListener(ButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}
	
	public void windowOpened(WindowEvent e) {}

	public void windowClosed(WindowEvent e) {}

	public void windowIconified(WindowEvent e) {}

	public void windowDeiconified(WindowEvent e) {}

	public void windowActivated(WindowEvent e) {}

	public void windowDeactivated(WindowEvent e) {}
	
}
