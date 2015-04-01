package plugin;

import java.sql.Array;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class InventoryHackPlugin extends JavaPlugin implements Listener{

	//Variable Chatcolor para el chat
	ChatColor rojo = ChatColor.RED;
	Entity e = null;
	Player player = null;
	Player hackedPlayer=null;
	String parameter = null;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}
	/*
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (!(event.getPlayer().isSneaking())) {
			if ((event.getRightClicked() instanceof LivingEntity)) {
				Player player = event.getPlayer();
				Entity vehicle = event.getRightClicked();
				if(vehicle.getType().equals(EntityType.ENDER_DRAGON)){
					if (vehicle.getPassenger() == null) {
						vehicle = event.getRightClicked();
						vehicle.setPassenger(player);
					} else {
						vehicle.eject();
					}
				}
			}
		}
	}
	 */

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(command.getName().equalsIgnoreCase("hackinventory")) {
			Player p = (Player) sender;
			if(!(sender instanceof Player)) {
				return false;

			}

			if(args.length < 1) {
				return false;	
			}

			parameter = args[0];
			if(parameter == null) {
				return false;
			}

			hackedPlayer = Bukkit.getServer().getPlayer(parameter);
			if(hackedPlayer == null) {
				return false;
			}
			Inventory i = hackedPlayer.getInventory();
			p.openInventory(i);
		}
      
		return false;

	}
	
}  