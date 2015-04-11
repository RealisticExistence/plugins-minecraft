package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Animation extends JFrame{
	private static final long serialVersionUID = -1766805168585438884L;
	
	public Animation(int i, int j) {
		super.setSize(610, 720);
		super.setVisible(true);
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setContentPane(new AnimationMovement(this));
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	
	
}
