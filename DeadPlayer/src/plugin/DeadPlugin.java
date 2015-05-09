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
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
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

public class DeadPlugin extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}
	ItemStack casco = null;
	ItemStack peto = null;
	ItemStack pantalones = null;
	ItemStack botas = null;
	ItemStack mano = null;
	@EventHandler
	public void onPlayerDie(PlayerDeathEvent e){
		Player p = e.getEntity();
		Zombie dead = p.getWorld().spawn(p.getLocation(), Zombie.class);
		if(p.getItemInHand() != null)
			mano = p.getItemInHand();
		if(p.getInventory().getHelmet() != null) 
			casco = p.getInventory().getHelmet();
		    if(p.getInventory().getChestplate() != null)
				peto = p.getInventory().getChestplate();
		    	if(p.getInventory().getLeggings() != null)
					pantalones = p.getInventory().getLeggings();
		    		if(p.getInventory().getBoots() != null)
			botas = p.getInventory().getBoots();
			String nombre = p.getDisplayName();
			if(mano != null)
				dead.getEquipment().setItemInHand(mano);
			if(casco != null) 
				dead.getEquipment().setHelmet(casco);

			    if(peto != null)
					dead.getEquipment().setChestplate(peto);

			    	if(pantalones != null)
						dead.getEquipment().setLeggings(pantalones);

			    		if(botas != null)
							dead.getEquipment().setBoots(botas);
			dead.setCustomName(nombre);
			dead.setCustomNameVisible(true);
			
		}
		
	}
