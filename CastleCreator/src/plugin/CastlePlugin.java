package plugin;




import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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

public class CastlePlugin extends JavaPlugin implements Listener{
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
		if(command.getName().equalsIgnoreCase("castlecreator")) {
			ItemStack castleCreator = new ItemStack(Material.POISONOUS_POTATO);
			ItemMeta im = castleCreator.getItemMeta();
			im.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD+"Castle Creator");
			castleCreator.setItemMeta(im);
			p.getInventory().addItem(castleCreator);
		
		}
		
		return false;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR ||e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getItemInHand() != null){
				if(p.getItemInHand().getItemMeta() != null){
					if(p.getItemInHand().getItemMeta().getDisplayName() != null){
						if(p.getItemInHand().getItemMeta().getDisplayName().contains("Castle")){
							construirCastillo(p.getLocation(),50,50,5,3,3,10);
							
							
						}
						
					}
					
				}
			}
			
		}
		
		
	}

	private void construirCastillo(Location loc, int anchoCastillo, int largoCastillo, int altoCastillo, int anchoTorre, int largoTorre, int altoTorre) {
		
		
		Material castleMaterial = Material.COBBLESTONE;
		
		int xP = loc.getBlockX();
		int yP = loc.getBlockY();
		int zP = loc.getBlockZ();
		
		for(int x = 0; x <= anchoCastillo; x++){
			for(int y = 0; y <= altoCastillo; y++){
				for(int z = 0; z <= largoCastillo; z++){
					Block b = loc.getWorld().getBlockAt(x+xP, yP, z+zP);
					b.setType(castleMaterial);
					if(x <= 47 && x >= 3){
						if(!(y == altoCastillo && z%2 == 1)){
							Block b2 = loc.getWorld().getBlockAt(x+xP, yP+y, z+zP);
							b2.setType(castleMaterial);
						}
					
						
					}
					if(z <= 47 && z >= 3){
						if(!(y == altoCastillo && x%2 == 1)){
							Block b2 = loc.getWorld().getBlockAt(x+xP, yP+y, z+zP);
							b2.setType(castleMaterial);
						}
						
						
					}
					if(x <= 47 && x >= 3){
						if(!(y == altoCastillo && z%2 == 1)){
							Block b2 = loc.getWorld().getBlockAt(x+xP, yP+y, z+zP);
							b2.setType(castleMaterial);
						}
						
						
					}
					if(z <= 47 && z >= 3){
						if(!(y == altoCastillo && x%2 == 1)){
							Block b2 = loc.getWorld().getBlockAt(x+xP, yP+y, z+zP);
							b2.setType(castleMaterial);
						}
						
					}
					
				}
			}
		}
		
		
	}
	
}