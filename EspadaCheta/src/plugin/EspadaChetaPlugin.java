package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class EspadaChetaPlugin extends JavaPlugin implements Listener {
	private boolean activo = false;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	final ItemStack espadaCheta = new ItemStack(Material.DIAMOND_SWORD);
	ItemMeta im = espadaCheta.getItemMeta();
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(!(sender instanceof Player))return false;
		final Player p = (Player) sender;
		
		 
		
		if(command.getName().equalsIgnoreCase("espadacheta")){
			 
			
			 im.addEnchant(Enchantment.DAMAGE_ALL, 100, false);
			 im.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Ultimate Sword");
			 espadaCheta.setItemMeta(im);
			 final ArmorStand a = p.getWorld().spawn(p.getLocation(), ArmorStand.class);
			for(int i = 20; i > 0; i--){
				
				final Location loc = p.getLocation();
				final int j = i;
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.getEyeLocation().getDirection().getX();
						
						
						loc.add(p.getEyeLocation().getDirection());
						loc.add(0, -(j/10), 0);
						a.teleport(loc);
						a.setGravity(false);
							a.setVisible(false);
							a.setItemInHand(espadaCheta);
							
					}
				}.runTaskTimer(this,0, 5+(20-i));
				
			}
		
			
			
			p.getWorld().dropItem(p.getLocation(), espadaCheta);
			
			p.playSound(p.getLocation(), Sound.ENDERDRAGON_DEATH, 1f, 0);
			
		}
		return false;
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		if(espadaCheta.getItemMeta().hasEnchant(Enchantment.DAMAGE_ALL)){
			if(e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand() == espadaCheta){
				Location loc = e.getPlayer().getLocation();
				crearEfecto(loc,e.getPlayer());
				
				
				
			}
			
			
			
		}
		
		
		
		
	}
	private void crearEfecto(final Location loc, final Player p) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				loc.add(0, 0, 2.5);
				for(int i = loc.getBlockX();i < 5;i++){
					loc.add(1, 0, 0);
					p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 2);
					
				}

				for(int i = loc.getBlockZ();i < 5;i++){
					loc.add(0, 0, 1);
					p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 2);
					
				}
				for(int i = loc.getBlockX();i < 5;i++){
					loc.add(-1, 0, 0);
					p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 2);
					
				}

				for(int i = loc.getBlockZ();i < 5;i++){
					loc.add(0, 0, -1);
					p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 2);
					
				}
				
				
			}
		}.runTaskLater(this, 10);
		
		
	}
	
	
	
	
	

}






