package permheads;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.SkullType;

import java.lang.String;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;


public class Main extends JavaPlugin implements Listener {
    
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        
    }

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("clone")) {
		if(args[0] != null) {
			Player cloned = Bukkit.getPlayer(args[0]);
	        Zombie e = cloned.getWorld().spawn(cloned.getLocation(), Zombie.class);
		 

	        
		}
		else{
			p.sendMessage("Too few arguments /clone [player]");
			
		}
		
	}
		return false;	
	}
    
   
}