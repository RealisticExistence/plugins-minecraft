package main;

import java.awt.Graphics;

import javax.swing.JPanel;

public class print extends JPanel{


	private static final long serialVersionUID = 2287441246983862804L;

	public void printText(String s,Graphics g) {
		g.drawString(s, 50, 50);
	}
	
	
}
