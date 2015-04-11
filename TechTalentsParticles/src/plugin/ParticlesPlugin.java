package plugin;

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

public class ParticlesPlugin extends JavaPlugin implements Listener {
	boolean isPlaced = false;
	Material luckyblockM = null;
    Effect effect = null;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("set")){
			if(args[0]!=null && args.length > 0){
				if(args[0].equalsIgnoreCase("firespawner")){
					effect = Effect.MOBSPAWNER_FLAMES;
				}
				if(args[0].equalsIgnoreCase("waterdrip")){
					effect = Effect.WATERDRIP;
				}
				if(args[0].equalsIgnoreCase("none")){
				effect = null;	
				}
				if(args[0].equalsIgnoreCase("help")){
					p.sendMessage("1. /set firespawner : sets your particles to the fire inside a spawner");
					p.sendMessage("2. /set waterdrip : sets your particles to water dripping");
					p.sendMessage("3. /set none : removes your particles");
					
				}
			}
		}
		return false;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if(effect != null){
			e.getPlayer().playEffect(e.getPlayer().getLocation().add(0, 1, 0),effect,2);	
		}
		
			
		
		
	}

}
