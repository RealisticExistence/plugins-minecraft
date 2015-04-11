package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class EndPlugin extends JavaPlugin implements Listener {
	boolean isPlaced = false;
	Material luckyblockM = null;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("techtalents")){
			int radioExterior = 22;
			final int radioInterior = 19;
			final int radioAro = 17;
			
			final Player p = (Player) sender;
			
			Location centro = p.getLocation();
			
			final ItemStack c = new ItemStack(Material.STAINED_CLAY,1,(short)1);
			final Material m = Material.AIR;
			
			final int x = centro.getBlockX();
		    final int y = centro.getBlockY();
			final int z = centro.getBlockZ();
			
			for(int i = -radioExterior; i < radioExterior; i++){
				for(int j = 0; j < 1; j++){
					for(int k = -radioExterior; k < radioExterior; k++){
						if(Math.sqrt((j*j)+(i*i)+(k*k)) <= radioExterior){
							final int k2 = k;
							final int j2 = j;
							final int i2 = i;
							
								
								
									p.sendMessage("X: "+i2+", Y: "+j2+", Z: "+k2);
									p.getWorld().getBlockAt(x+i2,j2+y,k2+z).setType(c.getType());
										if(Math.sqrt((j2*j2)+(i2*i2)+(k2*k2)) <= radioInterior){
											p.getWorld().getBlockAt(x+i2,j2+y,k2+z).setType(m);
											if(Math.sqrt((j2*j2)+(i2*i2)+(k2*k2)) <= radioAro){
												p.getWorld().getBlockAt(x+i2,j2+y,k2+z).setType(c.getType());
											
											
											
										}
									
								}
							
							
								
							
								
								
							
					}
							
						
						
					}
				}
			}
			
			
			
			
			
		}
		
		
		return false;
		
	}

	


	









}
