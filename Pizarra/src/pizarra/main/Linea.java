package pizarra.main;

import java.awt.Color;
import java.awt.Graphics;

public class Linea {
	public void paint(Graphics g, int x, int y, int radio) {
		Color c = new Color(255,255,255);
		g.setColor(c);
		g.fillOval(x, y, radio, radio);
	}
	
	
	
}
