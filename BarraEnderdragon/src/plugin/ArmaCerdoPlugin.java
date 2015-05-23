package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ArmaCerdoPlugin extends JavaPlugin implements Listener {
	private boolean activo = false;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e){
		final EnderDragon en = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation().add(0, 50, 0), EnderDragon.class);
		en.setCustomName(ChatColor.RED+""+ChatColor.BOLD+"El Server De jorgejorge99");
        en.setCustomName(ChatColor.GREEN+""+ChatColor.BOLD+en.getCustomName()+"KARMA:");
        en.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,1000000,10000000));
        en.setHealth(1);
       
	}

}






