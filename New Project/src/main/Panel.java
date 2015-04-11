package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private static final long serialVersionUID = 3350710760250147620L;
	private JFrame ventana;

	int rand1 = 0;
	int rand2 = 0;
	int rand3 = 0;

	boolean redOK = true;
	boolean greenOK = true;
	boolean blueOK = true;
	public Panel(final Ventana ventana) {
		this.ventana = ventana;
		super.setFocusable(true);
		this.setSize(ventana.getSize());
		super.requestFocusInWindow();

		super.addKeyListener(new KeyAdapter() {



			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					if(redOK && blueOK && greenOK){
						CambiarNumeros();
						redOK = false;
						greenOK = false;
						blueOK = false;
					}



					CambiarFondo();

				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					ventana.dispose();
				}
				super.keyPressed(e);
			}




		});

	}

	private void CambiarNumeros() {
		rand1 = (int) (Math.floor(Math.random()*200))+1;
		rand2 = (int) (Math.floor(Math.random()*200))+1;
		rand3 = (int) (Math.floor(Math.random()*200))+1;

	}

	int cuenta1 = 0;
	int cuenta2 = 0;
	int cuenta3 = 0;

	private void CambiarFondo() {
		Thread t =new Thread(new Runnable() {
			
			@Override
			public void run() {
				
					 if(cuenta1 != rand1){
						 if(cuenta1 < rand1){
								cuenta1+=1;
							}
							else if(cuenta1 > rand1){
								cuenta1-=1;
							}
							redOK = false;
						}
						else{
							redOK = true;
						}
					 
						if(cuenta2 != rand2){
							if(cuenta2 < rand2){
								cuenta2+=1;
							}
							else if(cuenta2 > rand2){
								cuenta2-=1;
							}
							greenOK = false;
						}
						else{
							greenOK = true;

						}
						if(cuenta3 != rand3){
							if(cuenta3 < rand3){
								cuenta3+=1;
							}
							else if(cuenta3 > rand3){
								cuenta3-=1;
							}
							
							blueOK = false;
						}
						else{
							blueOK = true;

						}

						ventana.getContentPane().setBackground(new Color(cuenta1,cuenta2,cuenta3));
						System.out.println(cuenta1+", "+cuenta2 + ", " + cuenta3);
						repaint();
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(redOK && greenOK && blueOK){
						 cuenta1 = rand1;
						 cuenta2 = rand2;
						 cuenta3 = rand3;
						 if(redOK && blueOK && greenOK){
								CambiarNumeros();
								redOK = false;
								greenOK = false;
								blueOK = false;
							}
						}
					
				 }
				



					
				
				
			
		});
		t.start();
		
			
		
	}
	

	@Override
	public void paint(Graphics g){
		super.paint(g);
		
	}
	
}

