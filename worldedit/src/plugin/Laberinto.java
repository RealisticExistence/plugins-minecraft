package plugin;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class Laberinto {
	static Construccion c;
	
	
	public static Construccion generate(Plugin plugin, int largo, int ancho, Location loc){
		c = new Construccion(plugin);
		generar(1,1);
		
		return null;
		
	}

	private static void generar(int i, int j) {
		
		
		
		
	}
	
	
	
	
}
