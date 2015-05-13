package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DungeonsPlugin extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player))return false;
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("dungeon")){
			if(args[0] != null && args.length > 0){
				if(args[1] != null && args.length > 1){
					if(args[0].equalsIgnoreCase("create")){
						crearDungeon(p.getLocation());
						p.sendMessage(ChatColor.BLUE+"Tu dungeon "+ args[1] + " ha sido creada!");
						p.sendMessage(ChatColor.YELLOW+"Pon /dungeon teleport " + args[1] + " para teletransportarte a ella!");

					}
				}else{
				p.sendMessage(ChatColor.RED + "Debes poner un nombre a tu dungeon /dungeon create [nombre]");
				}

			}else {
			p.sendMessage(ChatColor.RED + "Debes poner una opcion /dungeon [opcion] [nombre] Opciones: ");
			p.sendMessage(ChatColor.RED + "-Teleport");
			p.sendMessage(ChatColor.RED + "-Create");
			}
		}
		return false;
	}

	private void crearDungeon(Location location) {
		Location loc = location;
		Construcciones.habitacion(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location, Material.AIR);
		loc.add(3, 0, 10);
		Construcciones.pasillo(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc, Material.AIR);
		
	}





}  