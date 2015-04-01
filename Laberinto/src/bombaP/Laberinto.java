package bombaP;

import java.util.ArrayList;
import java.util.Random;

public class Laberinto {
	private static final int ABAJO=0;
    private static final int ARRIBA=1;
    private static final int DERECHA=2;
    private static final int IZQUIERDA=3;
    
    public boolean[][] ladrillos;
    public boolean[][] visitado;
    public ArrayList<Vec2> laberinto;
    private int ancho;
    private int alto;
    /**
     * Constructor for objects of class Mundo.
     * @param numAlto 
     * @param numAncho 
     * 
     */
    public Laberinto(int ancho, int alto)
    {
    	this.ancho=ancho;
    	this.alto=alto;
        ladrillos = new boolean[ancho][alto];
        visitado = new boolean[ancho][alto];
        this.laberinto= new ArrayList<Vec2>();
        rellenarMuros();
        generarLaberinto(1, 1);
        
        
    }
    
    

	private void rellenarMuros(){
        for(int i=0; i<ancho; i++){
            for(int j=0; j<alto ;j++){
                ladrillos[i][j]=true;
                
            }
        }
    }
    
    private void generarLaberinto(int i, int j){
        visitado[i][j]=true;
        
        annadir(i,j);
        //sortear celdas adyacentes
        int[] adyacentes = getAdyacentesSorteadas(i, j);
        
        for(int k = 0; k < adyacentes.length; k++){
            //para cada una de las adyacentes
            switch(adyacentes[k]){
                case DERECHA:
                    if(!visitado[i+2][j]){
                        annadir(i+1, j);
                        generarLaberinto(i+2, j);
                    }
                    break;
                case ARRIBA:
                    if(!visitado[i][j-2]){
                        annadir(i, j-1);
                        generarLaberinto(i, j-2);
                    }
                    break;
                case ABAJO:
                    if(!visitado[i][j+2]){
                        annadir(i, j+1);
                        generarLaberinto(i, j+2);
                    }
                    break;
                case IZQUIERDA:
                    if(!visitado[i-2][j]){
                        annadir(i-1, j);
                        generarLaberinto(i-2, j);
                    }
                    break;
            }
                //si no visitada
                
                    //eliminar muro
                    //marcar visitada
                    //proceder recursivamente con la celda
        }
    }
    
    private void annadir(int i, int j) {
    	
		ladrillos[i][j]=false;
        laberinto.add(new Vec2(i, j));
	}

	private int[] getAdyacentesSorteadas(int i, int j){
        int[] adyacentes = null;
        
        boolean bordeIzquierdo = i < 3;
        boolean bordeDerecho = i > ancho-3;
        
        boolean bordeArriba = j < 3;
        boolean bordeAbajo = j > alto-3;
        
        if(bordeIzquierdo){
            if(bordeArriba) 
                adyacentes = new int[]{ABAJO, DERECHA};
            else if(bordeAbajo) 
                adyacentes = new int[]{ARRIBA, DERECHA};
            else  
                adyacentes = new int[]{ABAJO, ARRIBA, DERECHA};
        }
        else if(bordeDerecho){
            if(bordeArriba) 
                adyacentes = new int[]{ABAJO, IZQUIERDA};
            else if(bordeAbajo) 
                adyacentes = new int[]{ARRIBA, IZQUIERDA};
            else  
                adyacentes = new int[]{ABAJO, IZQUIERDA, ARRIBA};
        }
        else{
            if(bordeArriba) 
                adyacentes = new int[]{ABAJO, IZQUIERDA, DERECHA};
            else if(bordeAbajo) 
                adyacentes = new int[]{ARRIBA, IZQUIERDA, DERECHA};
            else  
                adyacentes = new int[]{ABAJO, IZQUIERDA, ARRIBA, DERECHA};
        }
        
        //sorteo
        Random r = new Random();
        
        for(int k = (int)(Math.random()*20); k>0; k--){
            int pos = r.nextInt(adyacentes.length);
            int aux = adyacentes[pos];
            int pos2 = r.nextInt(adyacentes.length);
            adyacentes[pos] = adyacentes[pos2];
            adyacentes[pos2] = aux;
        }
        return adyacentes;
    }
}
