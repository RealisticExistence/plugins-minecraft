package plugin;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Creature;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RegisterPlugin extends JavaPlugin implements Listener{
	Logger logger = Logger.getLogger("Minecraft");

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
				
	}
	Player p = null;
@EventHandler
public void onPlayerJoin(PlayerJoinEvent e){
	p = e.getPlayer();
}
	public interface NPC extends Creature{set
	@Override
	public void setCustomName(String arg0);
			
			
	

	
	}



}
