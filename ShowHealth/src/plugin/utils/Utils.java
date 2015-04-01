package plugin.utils;

public class Utils {

	/**
	 * 
	 * @param x1 
	 * @param y1 
	 * @param z1 
	 * @param x2 
	 * @param y2 
	 * @param z2 
	 * @return La distancia entre los dos puntos.
	 */
	public static double distancia(double x1, double y1, double z1, double x2, double y2, double z2){
		return Math.sqrt((x2-x1)*(x2-x1) +
				(y2-y1)*(y2-y1) +
				(z2-z1)*(z2-z1));
	}

}
