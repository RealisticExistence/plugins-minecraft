package plugin;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Plugin extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(command.getName().equalsIgnoreCase("virus")){
		ItemStack virusBow = new ItemStack(Material.BOW);
		ItemMeta im = virusBow.getItemMeta();
		im.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
		im.setDisplayName(ChatColor.GREEN + "Virus Bow");
		virusBow.setItemMeta(im);
		Player p = (Player) sender;
		p.getInventory().addItem(virusBow);
		ItemStack skull = setName(new ItemStack(Material.SKULL_ITEM,1,(byte)SkullType.WITHER.ordinal()),ChatColor.BLACK+"Wither");
		
		Inventory i = Bukkit.createInventory(p, 9, "Virus Selection");
		i.addItem(skull);
		i.addItem(setName(new ItemStack(Material.SPIDER_EYE),ChatColor.GREEN+"Poison"));
		i.addItem(setName(new ItemStack(Material.ROTTEN_FLESH),ChatColor.DARK_GREEN+"Hunger"));
		i.addItem(setName(new ItemStack(Material.EYE_OF_ENDER),ChatColor.GRAY+"Blindness"));
		p.openInventory(i);
		
		}
		return false;
	}
	boolean isWither = false;
	boolean isPoison = false;
	boolean isHunger = false;
	boolean isBlindness = false;
	@EventHandler
	public void onPlayerInteractInventory(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getName().contains("Virus")){
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Wither")){
				e.setCancelled(true);
				isWither = true;
				p.sendMessage(ChatColor.BLACK+"Your virus has now Wither!!");
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Poison")){
				e.setCancelled(true);
				isPoison = true;
				p.sendMessage(ChatColor.GREEN+"Your virus has now Poison!!");
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Hunger")){
				e.setCancelled(true);
				isHunger = true;
				p.sendMessage(ChatColor.DARK_GREEN+"Your virus has now Hunger!!");
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Blindness")){
				e.setCancelled(true);
				isBlindness = true;
				p.sendMessage(ChatColor.GRAY+"Your virus has now Blindness!!");
			}
		}
		
		
	}
	private ItemStack setName(ItemStack itemStack, String string) {
		ItemMeta im = itemStack.getItemMeta();
		im.setDisplayName(string);
		itemStack.setItemMeta(im);
		return itemStack;
	}
	boolean virusSended = false;
	@EventHandler
	public void onPlayerShoot(ProjectileLaunchEvent ev) {
		if(ev.getEntityType() == EntityType.ARROW) {
			if(ev.getEntity().getShooter() instanceof Player) {
				Player p = (Player) ev.getEntity().getShooter();
				if(p.getItemInHand().getItemMeta().getDisplayName().contains("Virus")){
					p.sendMessage(ChatColor.GREEN + "Virus sended!");
					p.getItemInHand().setDurability((short) 0);
					virusSended = true;
					

				
				}
			}
		}
	}

	
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent ev) {
		if(ev.getEntity().getType() == EntityType.ARROW) {
			Player p = (Player) ev.getEntity().getShooter();
			if(virusSended){
				virusSended = false;
			}
		}

	}
	Player infected = null;
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e){
		if(e.getCause() == DamageCause.PROJECTILE){
			if(e.getEntity() instanceof Player){
				Player p = (Player) e.getEntity();
				infected = p;
				if(isWither){
					p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60000000,1));
				}
					if(isPoison){
						p.addPotionEffect(new PotionEffect(PotionEffectType.POISON,60000000,1));
					}
						 if(isHunger){
							 p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,60000000,1));
						 }
							 if(isBlindness){
								 p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,60000000,1));
							 }
					
			}
			
			
			
		}
		
		
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if(infected != null){
			if(infected == e.getPlayer()){
				List<Entity> entities = e.getPlayer().getWorld().getEntities();
				for(int j = 0; j <= entities.size(); j++){
					Entity en = entities.get(j);
					if(e.getPlayer().getLocation().distance(en.getLocation()) < 2){
						if(en instanceof Player){
							Player p = (Player) en;
							if(p != infected){
								if(isWither){
									p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60000000,1));
								}
									if(isPoison){
										p.addPotionEffect(new PotionEffect(PotionEffectType.POISON,60000000,1));
									}
										 if(isHunger){
											 p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,60000000,1));
										 }
											 if(isBlindness){
												 p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,60000000,1));
											 }
							}
							
							
							
						}
						
						
					}
					
					
				}
				
				
				
				
				
				
				
			}
		}
	}
	@EventHandler
	public void onPlayerDie(PlayerDeathEvent e){
		isBlindness = false;
		isHunger = false;
		isPoison = false;
		isWither = false;
		
	}
	

}






