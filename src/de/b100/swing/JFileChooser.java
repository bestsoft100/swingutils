package de.b100.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class JFileChooser implements ActionListener, WindowListener{

	public static final int BUTTON_ACTION_OK = 0;
	public static final int BUTTON_ACTION_CANCEL = 1;
	public static final int BUTTON_ACTION_CLOSE = 2;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new JFileChooser(null, Type.OPEN);
	}
	
	private JFrame frame;
	private ActionListener actionListener;
	
	private JGridPanel panel;
	private JGridPanel panel2;

	private JTextField textField;
	private JButton buttonOk;
	private JButton buttonCancel;
	private JComboBox<String> drives;
	private JList<File> fileList;
	
	public JFileChooser(ActionListener actionListener, Type type) {
		this.actionListener = actionListener;
		
		frame = new JFrame();
		panel = new JGridPanel(2);
		panel2 = new JGridPanel(2);
		panel2.defaultVerticalWeight = 0;
		drives = SwingUtils.newJComboBox(getDrivesStrings());
		fileList = new JList<>(getFileListModel("src"));
		
		textField = new JTextField(30);
		buttonOk = new JButton(type.getOkButtonText());
		buttonCancel = new JButton("Cancel");

		panel2.add(textField,0,0);
		panel2.add(buttonOk, 1, 0, 1, 1, 0, 0);
		panel2.add(buttonCancel, 1, 1, 1, 1, 0, 0);
		
		panel.add(drives, 0, 0, 1, 1, 1, 0);
		panel.add(fileList, 0, 1);
		panel.add(panel2, 0, 2, 1, 1, 1, 0);
		
		frame.add(panel);
		
		frame.setSize(560, 360);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static DefaultListModel<File> getFileListModel(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		
		DefaultListModel<File> def = new DefaultListModel<File>();
		
		for(File f : files) {
			def.addElement(f);
		}
		
		return def;
	}
	
	public static ArrayList<File> getDrives(){
		ArrayList<File> drives = new ArrayList<>();
		char c = 'A';
		while(c <= 'Z') {
			String str = "" + c + ":/";
			File f = new File(str);
			if(f.exists()) {
				drives.add(f);
			}
			c++;
		}
		
		return drives;
	}
	
	public static ArrayList<String> getDrivesStrings(){
		ArrayList<String> drives = new ArrayList<>();
		char c = 'A';
		while(c <= 'Z') {
			String str = "" + c + ":/";
			File f = new File(str);
			if(f.exists()) {
				drives.add(str);
			}
			c++;
		}
		
		return drives;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonOk) {
			actionListener.actionPerformed(new ActionEvent(this, BUTTON_ACTION_OK, "ok"));
		}
		if(e.getSource() == buttonCancel) {
			actionListener.actionPerformed(new ActionEvent(this, BUTTON_ACTION_CANCEL, "cancel"));
		}
	}
	
	public static enum Type{
		OPEN("Open"), SAVE("Save");
		
		private String okButtonText;
		
		private Type(String okButtonText) {
			this.okButtonText = okButtonText;
		}
		
		public String getOkButtonText() {
			return okButtonText;
		}
	}
	
	public void windowClosing(WindowEvent e) {
		frame.setVisible(false);
		frame.dispose();
		actionListener.actionPerformed(new ActionEvent(this, BUTTON_ACTION_CLOSE, "close"));
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}
	
}
