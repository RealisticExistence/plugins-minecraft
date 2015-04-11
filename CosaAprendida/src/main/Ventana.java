package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicGraphicsUtils;

import java.applet.*;
import java.net.*;

public class Ventana extends JFrame{
	
	private static final long serialVersionUID = -3859897502750206690L;
	private static AudioClip clip;

	public Ventana(int ancho, int alto) {
		super.setSize(new Dimension(ancho, alto));
		super.setExtendedState(MAXIMIZED_BOTH);
		
		super.setUndecorated(true);
		super.setVisible(true);
		
		super.setTitle("Bola");
		super.getContentPane().setBackground(new Color(200,0,255));
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setContentPane(new GamePanel(this));
		playSound();
	}
		
		

	public static void playSound() {
		try {
			java.applet.AudioClip clip =
			java.applet.Applet.newAudioClip(
			new java.net.URL("file:///C:/Users/JorgeHT/Downloads/nyanCatWav.wav"));
			clip.play();
			} catch (java.net.MalformedURLException murle) {
			System.out.println(murle);
			}
		
	}
}
	
	

