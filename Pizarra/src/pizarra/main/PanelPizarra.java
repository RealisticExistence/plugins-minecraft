package pizarra.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelPizarra extends JPanel implements MouseMotionListener{
/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1277180204919749308L;
	private Pizarra p;
	boolean repaint = false;
	BufferedImage img = null;
	int[][] pixels = null;
	BufferedImage icono = null;
	BufferedImage tiza = null;
	String path = "C:\\Users\\JorgeHT\\Pictures\\";
	boolean keyPressed = false;
	int lastx = 0;
	int lasty= 0;
	public PanelPizarra(Pizarra p){
		super.setVisible(true);
		super.setSize(p.getWidth(), p.getHeight());
		this.p = p;
		this.addMouseMotionListener((MouseMotionListener) this);	       
	    pixels = new int[p.getWidth()][p.getHeight()];

	  
		p.setFocusable(true);
		p.requestFocusInWindow();
		p.addKeyListener(new KeyAdapter() {
			 
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_P){
					repaint = true;
					PointerInfo c = MouseInfo.getPointerInfo();
					 lastx =  c.getLocation().x;
					 lasty=  c.getLocation().y - 20;
					System.out.println("pressed");
					repaint();
				
				}
					if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					System.exit(0);
					
				}
				super.keyPressed(e);
			}
		
		    		
		    


		});
		
		try {
			img = (ImageIO.read(new File(path+"pizarra.jpg")));
			tiza = ImageIO.read(new File(path+"tiza.png"));
			icono = ImageIO.read(new File(path+"pizarra.png"));} catch (IOException e) {e.printStackTrace();}
			
		for(int x = 0; x < icono.getWidth(null); x++){
			for(int y = 0; y < icono.getHeight(null); y++){
				if(icono.getRGB(x, y) == Color.WHITE.getRGB()){
					icono.setRGB(x, y, Color.TRANSLUCENT);
				}
					
			}
		}
		for(int x = 0; x < img.getWidth(null); x++){
			for(int y = 0; y < img.getHeight(null); y++){
				if(img.getRGB(x, y) == Color.WHITE.getRGB()){
					img.setRGB(x, y, Color.TRANSLUCENT);
				}
					
			}
		}
		p.setIconImage(icono);
		
		repaint();
		
	}
	
	
	  public void mouseMoved(MouseEvent e) {
	       if(repaint){
	   		PointerInfo c = MouseInfo.getPointerInfo();
			int x = c.getLocation().x;
			int y = c.getLocation().y - 20;
			if(x > 50 && x < p.getWidth()-1 && y > 50 && y < p.getHeight()-1){
				for(int i = x-5; i <= x+5; i++){
					for(int j = y-5; j <= y+5; j++){
						if(Math.sqrt(j*j+i*i) == 10){
							pixels[i][j] = 1;
						}
						
					}
				}
				
			
			}
			lastx =  c.getLocation().x;
			lasty=  c.getLocation().y - 20;
			
			
	       }
	    }
	
	
	  Linea l = new Linea();
	@Override
	public void paint(Graphics g) {
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(tiza, new Point(0, 0), "blank cursor");
		p.getContentPane().setCursor(blankCursor);
		
		super.paint(g);
		g.drawImage(img, 0, 0, p.getWidth(), p.getHeight(), null);
		g.setColor(new Color(255, 255, 255));
		 
		for (int i = 0; i<pixels.length; i++){
		     for (int j = 0; j<pixels[i].length; j++){
		    	 if(pixels[i][j] == 1){
		    		 g.fillOval(i, j, 3, 3);
		    	 }
		    	 
		     }
		}
		     
			
		 Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					if (repaint){
						repaint();
						
					}
					
				}
				
			}
		
		});
		 t.start();
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





}
