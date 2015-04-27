package main;

import javax.swing.JFrame;

public class Window extends JFrame{

	public Window(int i, int j) {
		super.setVisible(true);
		super.setSize(i, j);
		super.setContentPane(new MinecraftPanel(this));
	}

	
	
}
