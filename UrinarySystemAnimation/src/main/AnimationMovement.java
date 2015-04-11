package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.w3c.dom.css.RGBColor;

public class AnimationMovement extends JPanel {
	int cuenta = 0;
	int paso = 0;
	int maxK = 200;
	int maxF = 180;
		String path =  "C:\\Users\\JorgeHT\\Desktop\\UrinarySystemwork\\";
	BufferedImage UrinarySystem = null;
	BufferedImage Bladder = null;
	BufferedImage Kidney = null;
	BufferedImage Filtration = null;
	BufferedImage Ureters = null;
	public AnimationMovement(Animation a) {
		super.setVisible(true);


		try {
			
			UrinarySystem = ImageIO.read(new File(path+"UrinarySystem.jpg"));
			Kidney = ImageIO.read(new File(path+"Kidney.png"));
			Filtration = ImageIO.read(new File(path+"BloodFiltration.png"));
			Ureters = ImageIO.read(new File(path+"Ureters.png"));
			Bladder = ImageIO.read(new File(path+"Bladder.png"));
		} catch (IOException e) {
			e.printStackTrace();}
		a.setSize(610+10, 720+30);
		super.setSize(UrinarySystem.getWidth(), UrinarySystem.getHeight());
		super.setDoubleBuffered(true);
		for(int y = 0; y < Kidney.getHeight(); y++){
			for(int x = 0; x < Kidney.getWidth(); x++){
				if(Kidney.getRGB(x, y) == Color.WHITE.getRGB()){

					Kidney.setRGB(x, y, Color.TRANSLUCENT);

				}


			}

		}
		for(int y = 0; y < Filtration.getHeight(); y++){
			for(int x = 0; x < Filtration.getWidth(); x++){
				if(Filtration.getRGB(x, y) == Color.YELLOW.getRGB()){

					Filtration.setRGB(x, y, Color.TRANSLUCENT);

				}


			}

		}
		for(int y = 0; y < Ureters.getHeight(); y++){
			for(int x = 0; x < Ureters.getWidth(); x++){
				if(Ureters.getRGB(x, y) == Color.WHITE.getRGB()){

					Ureters.setRGB(x, y, Color.TRANSLUCENT);

				}


			}

		}
		for(int y = 0; y < Bladder.getHeight(); y++){
			for(int x = 0; x < Bladder.getWidth(); x++){
				if(Bladder.getRGB(x, y) == Color.WHITE.getRGB()){

					Bladder.setRGB(x, y, Color.TRANSLUCENT);

				}


			}

		}


		a.setFocusable(true);
		a.requestFocusInWindow();
		a.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					if (paso==5) paso=1; else paso++;
					if(paso==3){
						repaint();
					}
					if (paso!=3){
					cuenta=0;
					animar();
					}	
					
				}
				if(e.getKeyCode() == KeyEvent.VK_UP){
					
					
				}
				super.keyPressed(e);
			}



		});
	}
	private void animar() {
		Thread t = new Thread(new Runnable() {

			@Override             
			public void run() {
				while ((paso == 1 && cuenta < maxK) || (paso == 2 && cuenta < maxF) || (paso == 4 && cuenta < 101) || (paso==5 && cuenta < 150)){ 
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(paso == 4) 
						cuenta++;
					else {cuenta++;
					cuenta++;
					cuenta++;
					}
					repaint();	
					

				}


			}
 
		});
		t.start();

	}
	@Override
	public void paint(Graphics g) {
		int posX = 0;
		int posY = 0;
		super.paint(g);
		g.drawImage(UrinarySystem, 0, 0, 610,720,null);
		
		switch(paso){
		case 1:
			posX = Math.min(650-cuenta*2, UrinarySystem.getWidth()-350-cuenta);
			posY = Math.max(1,(UrinarySystem.getHeight()-700)-cuenta);
			g.drawImage(Kidney, posX, posY,cuenta*2,cuenta*2, null);
			break;
		case 2:
			posX = (UrinarySystem.getWidth()-350)-maxK;
			posY = Math.max(1,(UrinarySystem.getHeight()-700)-maxK);
			g.drawImage(Kidney, posX, posY,maxK*2,maxK*2, null);
			posX = (UrinarySystem.getWidth()-350)-cuenta;
			posY = Math.max(1,(UrinarySystem.getHeight()-830)-cuenta);
			g.drawImage(Filtration,posX , posY,cuenta*2,cuenta*2, null);
			break;
		case 4:
			if(cuenta < 51){
				g.drawImage(Ureters, -cuenta, -cuenta, 610+cuenta*2, 720+cuenta*2, null);
				
			}
			else if(cuenta < 101){
				
				g.drawImage(Ureters, -50 + (cuenta - 50) , -50 + (cuenta - 50) , 710-(cuenta-50)*2, 820-(cuenta-50)*2, null);
			}
			break;
		case 5:
			posY = Math.min(720-cuenta*2,640-cuenta);
			g.drawImage(Bladder , 330-cuenta, posY,cuenta*2,cuenta*2,null);
			
		}
		
		
			
		
	}

}



