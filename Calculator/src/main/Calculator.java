package main;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Calculator extends JPanel {
	 public boolean esMultiplicacion;

	public int num1;

	public int num2;
	
	private static final long serialVersionUID = -1843052712238245198L;
	public void calculator(final int num1,final int num2, final int action,Ventana ventana){
		
	
		super.setDoubleBuffered(true);
		ventana.setFocusable(true);
		ventana.requestFocusInWindow();
		ventana.addKeyListener(new KeyAdapter() {
       

		public void keyPressed(KeyEvent e) {
			try{
		num1 = e.getKeyChar();
		num2 = e.getKeyChar();
			}
			catch(NumberFormatException ev){
				
			}
        if(action == 0){
        	esMultiplicacion = true;
        }
					
				
				super.keyPressed(e);
        }
			
		});
	}
	@Override
	public void paint(Graphics g) {
		if(esMultiplicacion){
			
			g.drawString(""+num1*num2,200,200);
		}
	}
			


		
		
	
	
	
}
	

