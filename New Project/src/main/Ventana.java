package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Ventana extends JFrame{


	private static final long serialVersionUID = 5532908151984083500L;
    

	public Ventana(int ancho, int largo, Color color) {
		// TODO Auto-generated constructor stub
		super.setSize(new Dimension(ancho,largo));
    	super.getContentPane().setBackground(color);
    	super.setExtendedState(MAXIMIZED_BOTH);
		super.setUndecorated(true);
		super.setVisible(true);    
		super.setTitle("Pruebas");
		super.setContentPane(new Panel(this));
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
