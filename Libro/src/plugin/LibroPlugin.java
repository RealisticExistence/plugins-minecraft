package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class LibroPlugin extends JavaPlugin implements Listener{
	boolean esUnoUOtro = false;
	ChatColor rojo = ChatColor.RED;  
	ChatColor bold = ChatColor.BOLD;  
	Player p = null;
	Inventory i = null; 
	ItemStack libro = null;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);



	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		 p = e.getPlayer();
		 i = p.getInventory(); 
		 libro = new ItemStack(Material.BOOK);
		libro.getItemMeta().setDisplayName(rojo + "" + bold +"Travel");
		i.addItem(libro);


	}
	
	public interface DoubleChestInventory extends Inventory {
		

			
	
	}
}


