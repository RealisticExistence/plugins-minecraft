package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import org.w3c.dom.css.RGBColor;

public class GamePanel extends JPanel {

	List<Dibujable> dibujables = new ArrayList<Dibujable>();

	Font myFont = new Font("Arial",Font.BOLD,20);
	Font myFontGameOver = new Font("Arial",Font.BOLD,80);
	
	private Ventana ventana;
	int cuenta = 0;
	public GamePanel(final Ventana ventana) {
		final Dibujable bolita = new Dibujable(100,200);
		Dibujable bolita2 = new Dibujable(600,600);
		ventana.setBackground(new Color(200,0,255));

		this.ventana = ventana;

		bolita.setLimiteDerecho(ventana.getWidth()-15);
		bolita.setLimiteArriba(0);
		bolita.setLimiteAbajo(ventana.getHeight()-36);
		bolita.setColor(new Color(1, 0, 0.5f));
		dibujables.add(bolita);


		bolita2.setLimiteDerecho(ventana.getWidth()-15);
		bolita2.setLimiteArriba(0);
		bolita2.setSpeed(500, 500);
		bolita2.setLimiteAbajo(ventana.getHeight()-36);
		bolita2.setColor(new Color(0, 1, 0.5f));
		dibujables.add(bolita2);


		super.setDoubleBuffered(true);
		Graphics g = super.getGraphics();
		crearHiloMovimiento(g);
		ventana.setFocusable(true);
		ventana.requestFocusInWindow();
		ventana.addKeyListener(new KeyAdapter() {


			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					if(gameOver){
						gameOver = false;
						cuenta = 0;
						ventana.setTitle("Bola");
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					bolita.setSpeed(-500.0,0);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					bolita.setSpeed(500.0,0);
				}
				if(e.getKeyCode() == KeyEvent.VK_UP){
					bolita.setSpeed(0,-500.0);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					bolita.setSpeed(0,500.0);
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					ventana.dispose();
					
				}
				super.keyPressed(e);
			}



		});


	}
	private boolean gameOver = false;
	private void crearHiloMovimiento(final Graphics g) {
		Thread t = new Thread(new Runnable() {





			@Override
			public void run() {
				while(true){

					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					for(Dibujable d : dibujables){
						d.mover(30);


						for(Dibujable d2 : dibujables){
							if(d != d2){
								if(d.colision(d2)){


									if(!(gameOver)){
										gameOver  = true; 
										ventana.setTitle("Game Over");
									}

								}
							}
						}
					}
					repaint();
				}
			}
		});

		t.start();
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		if(!(gameOver)){
			for(Dibujable d : dibujables){
				g.setColor(Color.RED);
				g.setFont(myFont);
			
				
				cuenta++;
				g.drawString("Score: " + cuenta,50,50);
				d.dibujar(g);
			}
		}
		else{
			g.setFont(myFontGameOver);
			int anchoTextoScore = g.getFontMetrics().stringWidth("Score: "+ cuenta);
			int anchoTextoGameOver = g.getFontMetrics().stringWidth("Game Over");
			g.setColor(Color.BLUE);
			g.drawString("Score: "+ cuenta, ventana.getWidth()/2-anchoTextoScore/2, ventana.getHeight()/2-75);

			g.drawString("Game Over", ventana.getWidth()/2-anchoTextoGameOver/2, ventana.getHeight()/2);

		}
	}



}
