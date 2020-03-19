package de.b100.swing;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class TextInputWindow implements ActionListener{
	
	private ArrayList<Input> textFields;
	private ArrayList<JButton> buttons;
	
	private JFrame frame;
	private JGridPanel panel;
	private JGridPanel textFieldPanel;
	
	private JPanel buttonPanel;
	
	public TextInputWindow() {
		textFields = new ArrayList<>();
		buttons = new ArrayList<>();
	}
	
	protected void addButton(JButton button) {
		buttons.add(button);
	}
	
	protected void addTextField(String name, JTextField textField) {
		textFields.add(new Input(name, textField));
	}
	
	public void create() {
		if(frame != null) {
			close();
		}
		frame = new JFrame();
		panel = new JGridPanel(10);
		textFieldPanel = new JGridPanel(4);
		buttonPanel = new JPanel();
		
		textFieldPanel.setFillMode(2);
		int i=0;
		while(i < textFields.size()) {
			textFieldPanel.add(new JLabel(textFields.get(i).name), 0, i, 1, 1, 0, 1);
			textFieldPanel.add(textFields.get(i).textField, 1, i, 1, 1, 1, 1);
			i++;
		}
		
		int j=0;
		while(j < buttons.size()) {
			buttonPanel.add(buttons.get(j));
			j++;
		}

		panel.add(textFieldPanel, 0, 0, 1, 1, 1, 1);
		panel.add(buttonPanel, 0, 1, 1, 1, 1, 1);
		
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void close() {
		frame.setVisible(false);
		frame.dispose();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public static class Input{
		
		public String name;
		public JTextField textField;
		
		public Input(String name, JTextField textField) {
			this.name = name;
			this.textField = textField;
		}
		
	}
	
}