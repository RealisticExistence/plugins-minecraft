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
	Dibujable aliado;
	
	private Ventana ventana;
	int cuenta = 0;
	public GamePanel(final Ventana ventana) {
		this.ventana = ventana;
		crearPartida();


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
						dibujables.clear();
						crearPartida();
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					aliado.setSpeed(-500.0,0);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					aliado.setSpeed(500.0,0);
				}
				if(e.getKeyCode() == KeyEvent.VK_UP){
					aliado.setSpeed(0,-500.0);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					aliado.setSpeed(0,500.0);
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					System.exit(0);
				}
				super.keyPressed(e);
			}

			



		});


	}
	
	private void crearPartida() {
		aliado = new Dibujable(100,200);
		Dibujable aliado2 = new Dibujable(600,600);
		ventana.setBackground(new Color(200,0,255));

		

		aliado.setLimiteDerecho(ventana.getWidth()-15);
		aliado.setLimiteArriba(0);
		aliado.setLimiteAbajo(ventana.getHeight()-36);
		aliado.setColor(new Color(1, 0, 0.5f));
		dibujables.add(aliado);


		aliado2.setLimiteDerecho(ventana.getWidth()-15);
		aliado2.setLimiteArriba(0);
		aliado2.setSpeed(500, 500);
		aliado2.setLimiteAbajo(ventana.getHeight()-36);
		aliado2.setColor(new Color(0, 1, 0.5f));
		dibujables.add(aliado2);
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
				d.mover(30);


				for(Dibujable d2 : dibujables){
					if(d != d2 && d == aliado){
						if(d.colision(d2)){


							if(!(gameOver)){
								gameOver  = true; 
								ventana.setTitle("Game Over");
							}

						}
					}
				}
			}
			Dibujable anadir = null;
			for(Dibujable d : dibujables){
				g.setColor(Color.RED);
				g.setFont(myFont);
			
				
				cuenta++;
				if(dibujables.size() < cuenta/1000){
					Dibujable aliado3 = new Dibujable(100,200);
					aliado3.setLimiteDerecho(ventana.getWidth()-15);
					aliado3.setLimiteArriba(0);
					aliado3.setSpeed(Math.random()*500+250, Math.random()*600+250);
					aliado3.setLimiteAbajo(ventana.getHeight()-36);
					float transparencia = (float) Math.min(1, Math.max(0, 1-(cuenta/10000.0)));
					System.out.println(transparencia);
					aliado3.setColor(new Color((float) Math.random(), (float) Math.random()/2.0f+0.5f, 1f , transparencia));
					anadir = aliado3;
					
					
				}
				g.drawString("Score: " + cuenta,50,50);
				d.dibujar(g);
			}
			
			
			if(anadir != null){
				dibujables.add(anadir);
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
