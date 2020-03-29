package de.b100.swing;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class TextInputWindow implements ActionListener{
	
	private ArrayList<Input> inputs;
	private ArrayList<JButton> buttons;
	
	private String title;
	
	private JFrame frame;
	private JGridPanel panel;
	private JGridPanel textFieldPanel;
	
	private JPanel buttonPanel;
	
	public TextInputWindow() {
		inputs = new ArrayList<>();
		buttons = new ArrayList<>();
	}
	
	protected void addButton(JButton button) {
		buttons.add(button);
	}
	
	protected void addTextField(String name, JTextField textField) {
		inputs.add(new InputString(name, textField));
	}
	
	protected void addCheckBox(JCheckBox checkBox) {
		inputs.add(new InputBoolean("", checkBox));
	}
	
	protected void setTitle(String title) {
		this.title = title;
	}
	
	public void create() {
		if(frame != null) {
			close();
		}
		frame = new JFrame();
		panel = new JGridPanel(10);
		textFieldPanel = new JGridPanel(6);
		buttonPanel = new JPanel();
		
		textFieldPanel.setFillMode(2);
		textFieldPanel.setBackground(Color.white);
		textFieldPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		panel.getGridBagConstraints().ipadx = 100;
		
		int i=0;
		while(i < inputs.size()) {
			textFieldPanel.add(new JLabel(inputs.get(i).name), 0, i, 1, 1, 0, 1);
			textFieldPanel.add(inputs.get(i).component, 1, i, 1, 1, 1, 1);
			i++;
		}
		
		int j=0;
		while(j < buttons.size()) {
			buttonPanel.add(buttons.get(j));
			j++;
		}

		panel.getGridBagConstraints().insets = new Insets(5, 0, 5, 0);
		if(title != null) {
			JPanel panel2 = new JPanel();
			panel2.add(new JLabel(title));
			panel.add(panel2, 0, 0, 1, 1, 1, 0);
		}
		panel.add(textFieldPanel, 0, 1, 1, 1, 1, 1);
		panel.add(buttonPanel, 0, 2, 1, 1, 1, 1);
		
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void close() {
		frame.setVisible(false);
		frame.dispose();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public abstract static class Input{
		
		private String name;
		private JComponent component;
		
		public Input(String name, JComponent component) {
			this.name = name;
			this.component = component;
		}
		
		public String getName() {
			return name;
		}
		
		public JComponent getComponent() {
			return component;
		}
		
	}
	
	public static class InputString extends Input{
		
		public InputString(String name, JTextField textField) {
			super(name, textField);
		}
		
	}
	
	public static class InputBoolean extends Input{
		
		public InputBoolean(String name, JCheckBox checkBox) {
			super(name, checkBox);
		}
	}
	
}
