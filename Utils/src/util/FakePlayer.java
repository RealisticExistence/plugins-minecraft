package util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.NPC;

public class FakePlayer{

	
	public static void SpawnNPC(Location l, String name) {
		PlayerEntity s = 
		NPC entity = l.getWorld().spawnEntity(l, NPC.class);
		entity.setCustomName(name);
		entity.setCustomNameVisible(true);
	}
	

}
