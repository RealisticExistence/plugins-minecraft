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
	public int n1;
	public int n2;
	private static final long serialVersionUID = -1843052712238245198L;
	public int calculator( final int num1, final int num2, final int action,Ventana ventana){
		
	
		super.setDoubleBuffered(true);
		ventana.setFocusable(true);
		ventana.requestFocusInWindow();
		
		ventana.addKeyListener(new KeyAdapter() {
         n2 = num2;
         n1 = num1;
		public void keyPressed(KeyEvent e) {
			try{
		n1 = e.getKeyChar();
		n2 = e.getKeyChar();
			
		if(action == 0){
        	esMultiplicacion = true;
        }
			}
			catch(NumberFormatException ev){
				
			}
        
					
				
				super.keyPressed(e);
		}
        
			
		});
	}
	@Override
	public void paint(Graphics g) {
		if(esMultiplicacion){
			
			g.drawString("respuesta: "+n1*n2,200,200);
			
		}
	}
			


		
		
	
	
	
}
	

