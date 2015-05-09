package plugin;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class WaypointsPlugin extends JavaPlugin implements Listener {
	private boolean activo = false;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	HashMap<String,Location> waypoints = new  HashMap<String,Location>();
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("waypoints")){
			if(args[0] != null && args[1] != null && args.length > 1){
				if(args[0].equalsIgnoreCase("help")){
					p.sendMessage("command: /waypoints [option] [parameter]");
					p.sendMessage("Options: ");
					p.sendMessage("create ");
					p.sendMessage("teleport");
					p.sendMessage("Parameters:");
					p.sendMessage("/waypoint create [name]");
					p.sendMessage("/waypoint teleport [name]");



				}
				if(args[0].equalsIgnoreCase("create")){
					waypoints.put(args[1], p.getLocation());
				}
				if(args[0].equalsIgnoreCase("teleport")){
					if(waypoints.containsKey(args[1])){
						Location l = waypoints.get(args[1]);
						p.teleport(l);
					}
				}
			}


		}
		return false;
	}



}








