package plugin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Skull;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CabezasPlugin extends JavaPlugin implements Listener{

	private Location localizacion;
	ChatColor rojo = ChatColor.RED;
	ChatColor negrita = ChatColor.BOLD;
	ItemStack cabeza = null;
	protected float FUERZA_EXPLOSION = 20;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);	
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		
	
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("cabeza")) {
			if(p.getItemInHand() != new ItemStack(Material.SKULL)) {
				ItemStack itemEnMano = p.getItemInHand();
				if(itemEnMano != null) {
					p.getEquipment().setHelmet(itemEnMano);

					
				}
				else{
					p.sendMessage(ChatColor.RED + "Tienes que tener algo en la mano que ponerte");
				}
				
				
			}
			else{
				if(args[0] != null && args.length > 0) {
					 cabeza = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta meta = (SkullMeta) cabeza.getItemMeta();
						meta.setOwner("jorgejorge99");
						meta.setDisplayName(p.getName() + "'s head");
						cabeza.setItemMeta(meta);
					p.getEquipment().setHelmet(cabeza);
				}	
				p.sendMessage("Pon un nombre para la cabeza");
			}
			
		}
		
		
		return false;
		
	}


}
