package util;

import org.bukkit.entity.Player;

public class Utils {

	
	public static void borrarInventario(Player e) {
		e.getInventory().clear();
		e.getInventory().setBoots(null);
		e.getInventory().setChestplate(null);
		e.getInventory().setLeggings(null);
		e.getInventory().setHelmet(null);
		

	}
	
	
}
