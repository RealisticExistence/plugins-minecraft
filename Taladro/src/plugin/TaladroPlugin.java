package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class TaladroPlugin extends JavaPlugin implements Listener {
	boolean isPlaced = false;
	Material luckyblockM = null;
	Player p = null;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(sender instanceof Player) {
			p = (Player) sender;
			/*
			
			 */

			if (command.getName().equalsIgnoreCase("drill")) {
				if(args.length > 0 && args[0] != null) {
					int i = 0;
					try {
						i = Integer.parseInt(args[0]);
					} catch (NumberFormatException e) {
						p.sendMessage(ChatColor.RED + "La altura debe ser un numero entero ");
					}

					final int hh = i;

					final Location l = p.getLocation();
					int delay = 100;
					if(args.length > 1 && args[1] != null) {
						try {
							delay = Integer.parseInt(args[1])*20;
						} catch (NumberFormatException e) {
							p.sendMessage(ChatColor.RED + "El tiempo de espera antes de empezar debe ser un numero entero");
						}
					}
					else{
						p.sendMessage(ChatColor.RED + "Debes poner un tiempo de espera /drill [altura] [tiempo de espera]");
						
					}
					
					new BukkitRunnable() {

						@Override
						public void run() {
							
							if(l.getBlockY() > hh) {
								l.getBlock().setType(Material.IRON_BLOCK);
								p.getWorld().createExplosion(l, 5);
								l.add(0, -1, 0);
								p.sendMessage("El taladro esta en la altura "+l.getBlockY());
							}else{
								this.cancel();
							}


						}

					}.runTaskTimer(this, delay, 10);
					p.sendMessage("El taladro va a comenzar en " + delay/20.0);
				}
				else{
					p.sendMessage(ChatColor.RED + "Debes poner una altura /drill [altura] [tiempo de espera]");

				}

			}	
		}


		return false;
	}

}

