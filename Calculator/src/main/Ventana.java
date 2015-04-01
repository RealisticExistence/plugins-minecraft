package main;

import javax.swing.JFrame;

public class Ventana extends JFrame{
	private static final long serialVersionUID = 5532908151984083500L;
	public Ventana(int ancho, int alto){
		super.setSize(ancho, alto);
		super.setVisible(true);
		super.setContentPane(new Calculator());
	}

}
