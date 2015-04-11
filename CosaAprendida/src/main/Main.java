package main;

public class Main {

	public static void main(String[] args) {
		Ventana w = new Ventana(1280,960);
		w.setContentPane(new GamePanel(w));
	}

}
