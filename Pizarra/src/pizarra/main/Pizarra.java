package pizarra.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Pizarra extends JFrame{
	public Pizarra(){
		super.setVisible(true);
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setTitle("Pizarra - TechPro Creations");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setContentPane(new PanelPizarra(this));
		
		
	}
	
	
	
	
}
