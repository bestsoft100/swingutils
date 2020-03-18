package de.b100.swing.test;

import java.awt.Color;
import java.awt.Graphics;

import de.b100.swing.Application;

public class TestApp extends Application{

	public static void main(String[] args) {
		new TestApp().start(800, 600);
	}
	
	public void run() {
		System.out.println("TestApp is now running");
		
		while(running) {
			
			update();
		}
		
		System.out.println("TestApp is stopping");
	}

	public void update(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 64, 64);
	}

}
