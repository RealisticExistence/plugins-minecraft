package plugin;




import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class EntityChangePlugin extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	String ent = null;
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {

		if(!(sender instanceof Player)) {
			return false;
		}
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("set")) {
			if(args[0] != null && args.length > 0){
				ent = args[0];
				


			}

		}


		return false;
	}
	@EventHandler
	public void onPLayerInteractentity(PlayerInteractEntityEvent e) {
			Player player = e.getPlayer();
			Location l = player.getLocation();
		if(ent != null){
			
			
			
			
		}



	}

}