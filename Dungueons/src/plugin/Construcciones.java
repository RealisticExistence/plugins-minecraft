package plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class Construcciones extends JavaPlugin{
	
	public static void habitacion(int x, int y, int z, Location loc, Material material){
		int tamaño = 10;
		int altura = 5;
		
		for(int xh = 0; xh <= tamaño; xh++){
			for(int yh = 0; yh <= altura; yh++){
				for(int zh = 0; zh <= tamaño; zh++){
					Block b = loc.getWorld().getBlockAt(x+xh, y+yh, z+zh);
					b.setType(material);
					if(xh == 0 || xh == tamaño || zh == 0 || zh == tamaño || yh == 0 || yh == altura){
						b.setType(Material.getMaterial(98));
					}
					
				}
			}
		}
		
		
		
		
	}
	public static void pasillo(int x, int y, int z, Location loc, Material material){
		int altura = 4;
		int largo = 20;
		int ancho = 4;
		for(int xp = 0; xp <= ancho  ; xp++){
			for(int yp = 0; yp <= altura; yp++){
				for(int zp = 0; zp <= largo; zp++){
					Block b = loc.getWorld().getBlockAt(x+xp, y+yp, z+zp);
					b.setType(material);
					if(xp == 0 || xp == ancho || yp == 0 || yp == altura){
						b.setType(Material.getMaterial(98));
					}
				}
			}
		}
		
		
		
	}
	
	
	
	
}
