package plugin;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
				if(args[0].equalsIgnoreCase("vip")){
					List<Player> jugadores = p.getWorld().getPlayers();
					Player player = Bukkit.getPlayer(args[1]);
					if(jugadores.contains(player)){
						PermissionAttachment attachment = player.addAttachment(this);
						attachment.setPermission("vip", true);
						attachment.unsetPermission("god");
						attachment.unsetPermission("mvip");
						a.put(player.getUniqueId(), attachment);


					}

				}
				else if(args[0].equalsIgnoreCase("mvip")){
					List<Player> jugadores = p.getWorld().getPlayers();
					Player player = Bukkit.getPlayer(args[1]);
					if(jugadores.contains(player)){
						PermissionAttachment attachment = player.addAttachment(this);
						attachment.setPermission("mvip", true);
						a.put(player.getUniqueId(), attachment);
					}
				}
				else if(args[0].equalsIgnoreCase("god")){
					List<Player> jugadores = p.getWorld().getPlayers();
					Player player = Bukkit.getPlayer(args[1]);
					if(jugadores.contains(player)){	
						PermissionAttachment attachment = player.addAttachment(this);
						attachment.setPermission("god", true);
						a.put(player.getUniqueId(), attachment);
					}
				}
			}
		}
		return false;
	}
}
