package main;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import util.Utils;

public class MinecraftPanel extends JPanel {

	public MinecraftPanel(Window w) {
		
		w.setFocusable(true);
		w.requestFocusInWindow();
		w.addKeyListener(new KeyAdapter() {


			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_D){
					Utils.mandarMensaje("hola", "jorgejorge99");
					
					
				}
				
				super.keyPressed(e);
			}

			



		});

		
	}
	
	
	
	
	
	
}
