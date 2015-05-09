package plugin;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FlechaPlugin extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}
	
	
	@EventHandler
	public void onPlayerShoot(ProjectileLaunchEvent ev) {
		if(ev.getEntityType() == EntityType.ARROW) {
			if(ev.getEntity().getShooter() instanceof Player) {
				ev.getEntity().setPassenger((Entity) ev.getEntity().getShooter());
				

			}
		}
	}
	
	
	@EventHandler
	public void onArrowHit(ProjectileHitEvent e){
		if(e.getEntity() instanceof Arrow){
			if(e.getEntity().getPassenger() instanceof Player){
				if(e.getEntity().getShooter() instanceof Player){
					Player p = (Player) e.getEntity().getShooter();
					Vector v = p.getVelocity();
					v.multiply(10);
					p.setVelocity(v);
					
				
				}
				
				
			}
			
			
		}
	}

}  