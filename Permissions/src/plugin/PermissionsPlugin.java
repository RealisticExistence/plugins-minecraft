package plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
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
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class PermissionsPlugin extends JavaPlugin implements Listener {
	boolean isPlaced = false;
	Material luckyblockM = null;
	Effect effect = null;
	HashMap<UUID,PermissionAttachment> a = new HashMap<UUID,PermissionAttachment>();
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)){
			return false;
		}
		Player p = (Player) sender;

		if(command.getName().equalsIgnoreCase("permission")){
			if(args[0] != null && args[1] != null && args.length > 1){
				File file = new File("plugins/logs",p.getName()+"'s chat.txt");
				
				
				if(!file.exists()){
					try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
					}
					BufferedWriter w = getBufferedWriter(file);
				InputStreamReader inputStreamReader = null;
				try {
					 inputStreamReader = new InputStreamReader(new FileInputStream (file));
				} catch (FileNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				BufferedReader re = new BufferedReader(inputStreamReader);
				try {
					p.sendMessage(re.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		}
		return false;
	}
	
	public static HashMap<File, BufferedWriter> writers = new HashMap<File, BufferedWriter>();
	public BufferedWriter getBufferedWriter(File f) {
	    try {
	        if (writers.containsKey(f)) {
	           
	            return writers.get(f);
	        } else {
	           
	            BufferedWriter returns = new BufferedWriter(new FileWriter(f, true));
	            writers.put(f, returns);
	            return returns;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) throws IOException {
	    
		File file = new File("plugins/logs",e.getPlayer().getName()+"'s chat.txt");
		BufferedWriter w = new BufferedWriter(new FileWriter(file, true));
		
		
		if(!file.exists()){
			try {
				file.createNewFile();
				e.getPlayer().sendMessage("Archivo de log creado");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
			
			
		}
		else{
			try {
				w.write(e.getMessage());
				e.getPlayer().sendMessage("Escribiendo log...");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		
		
		
	}
	
	
}
