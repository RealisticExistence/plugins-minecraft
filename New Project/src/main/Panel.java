package main;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.html.HTML.Tag;

public class Panel extends JPanel {
	private static final long serialVersionUID = 3350710760250147620L;
	private JFrame ventana;

	int rand1 = 0;
	int rand2 = 0;
	int rand3 = 0;

	boolean redOK = true;
	boolean greenOK = true;
	boolean blueOK = true;
	private volatile Thread blinker;
	public Panel(final Ventana ventana) {
		redOK = true;
		greenOK = true;
		blueOK = true;
		this.ventana = ventana;
		super.setFocusable(true);
		super.requestFocusInWindow();
		ventana.addKeyListener(new KeyAdapter() {



			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					if(redOK && blueOK && greenOK){
						CambiarNumeros();
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
		rand1 = (int) (Math.floor(Math.random()*200));
		rand2 = (int) (Math.floor(Math.random()*200));
		rand3 = (int) (Math.floor(Math.random()*200));

	}

	int cuenta1 = 0;
	int cuenta2 = 0;
	int cuenta3 = 0;

	private void CambiarFondo() {
		final Runnable r = new Runnable() {
			
			@Override
			public void run() {
				 Thread thisThread = Thread.currentThread();
			        while (blinker == thisThread) {
			            try {
			                thisThread.sleep(100);
			            } catch (InterruptedException e){
			            }
			            repaint();
			        }
				if(cuenta1 < rand1){
					cuenta1++;
					redOK = false;
				}
				else{
					redOK = true;
				}
				if(cuenta2 < rand2){
					cuenta2++;
					greenOK = false;
				}
				else{
					greenOK = true;

				}
				if(cuenta3 < rand3){
					cuenta3++;
					blueOK = false;
				}
				else{
					blueOK = true;

				}

				ventana.getContentPane().setBackground(new Color(cuenta1,cuenta2,cuenta3));
				
			if(blueOK && redOK && greenOK){
				blinker = null;
			}
				
			}
		};
		r.run();
		
			
		
	}
}

