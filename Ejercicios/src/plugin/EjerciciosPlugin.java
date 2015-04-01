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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class EjerciciosPlugin extends JavaPlugin implements Listener {
	boolean isPlaced = false;
	Material luckyblockM = null;
	Player p = null;
	String jorge = "";
	String saul = "";
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)  {
		 p = e.getPlayer();
		if(p.getName().equals("JefeGeneral")) {
			 jorge = "<jorgejorge99>";
			 saul = "<77creeper77>";
			
			new BukkitRunnable() {


				@Override
				public void run() {
					p.sendMessage(ChatColor.YELLOW + "jorgejorge99 joined the game");
					
					
				}
			}.runTaskLater(this, 30);
			new BukkitRunnable() {


				@Override
				public void run() {
					p.sendMessage(ChatColor.YELLOW + "77creeper77 joined the game");
					
					
				}
			}.runTaskLater(this, 60);
			new BukkitRunnable() {


				@Override
				public void run() {
					p.sendMessage(jorge + " saul?");
					
					
				}
			}.runTaskLater(this, 120);
			new BukkitRunnable() {


				@Override
				public void run() {
					p.sendMessage(saul + " jorje?");
					
					
				}
			}.runTaskLater(this, 180);
			new BukkitRunnable() {


				@Override
				public void run() {
					p.sendMessage(jorge + " boy a cerrarle el servidor con el putty");
					
					
				}
			}.runTaskLater(this, 240);
			new BukkitRunnable() {


				@Override
				public void run() {
					p.sendMessage(saul + " PUTTY jajajajjajajajajja XD");
					
					
				}
			}.runTaskLater(this, 300);
			new BukkitRunnable() {


				@Override
				public void run() {
					p.sendMessage(jorge + " tu enserio boy a cerrarselo trollololol");
					
					
				}
			}.runTaskLater(this, 360);
			p.getServer().shutdown();
		}
	


		
		
}
}
