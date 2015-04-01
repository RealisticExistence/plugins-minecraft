package main;

import java.awt.Color;
import java.awt.Graphics;

public class Dibujable {

	
	private double y;
	private double x;
	private double vx=100;
	private double limiteIzquierdo;
	private double limiteDerecho;
	private Color color;
	private double vy = 100;
	private int limiteAbajo;
	private int limiteSuperior;
	private int radio=50;

	public Dibujable(double x, double y) {
		this.y = y;
		this.x = x;
	}

	public void dibujar(Graphics g) {
		g.setColor(color);
		g.fillOval((int)x-radio, (int)y-radio, radio*2, radio*2);
	}

	public void mover(int tiempo) {
		

		x = x + (tiempo/1000.0)*vx;
		y = y + (tiempo/1000.0)*vy;

		if(x < limiteIzquierdo+radio) {
			x = radio;
			vx = -vx;
		}
		else if(x>limiteDerecho-radio){
			x = limiteDerecho-radio;
			vx = -vx;
		}


		if(y < limiteSuperior+radio) {
			y = radio;
			vy = -vy;
		}
		else if(y>limiteAbajo-radio){
			y = limiteAbajo-radio;
			vy = -vy;
		}
	}
	public void setLimiteAbajo(int i) {
		limiteAbajo = i;





	}
	public void setLimiteArriba(int i) {
		limiteSuperior = i;





	}

	public void setLimiteIzquierdo(int i) {
		limiteIzquierdo = i;





	}
	public void setLimiteDerecho(int i) {
		limiteDerecho = i;





	}

	public void setColor(Color color) {
		this.color=color;
	}

	public void setSpeed(double x, double y) {
		vx = x;
		vy = y;
	}

	public boolean colision(Dibujable d2) {
		double distancia = Math.sqrt((x-d2.getX())*(x-d2.getX())+((y-d2.getY())*(y-d2.getY())));
		if(distancia  < radio+d2.getRadio()){
			return true;
		}
		else{
			return false;
		}
		
	}

	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public int getRadio() {
		return radio;
	}

}
