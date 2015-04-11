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
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ArmasPlugin extends JavaPlugin implements Listener{

	BukkitRunnable br;

	//Variable Chatcolor para el chat
	ChatColor rojo = ChatColor.RED;
	Type type = null;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}
	ItemStack granada = new ItemStack(Material.TNT);
	ItemStack pistola = new ItemStack(Material.WOOD_SPADE);
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		ItemMeta granadaIm = granada.getItemMeta();
		granadaIm.addEnchant(Enchantment.DURABILITY, 1, false);
		granadaIm.setDisplayName(ChatColor.RED + "Granade");
		granada.setItemMeta(granadaIm);
		e.getPlayer().getInventory().addItem(granada);
	
		ItemMeta pistolaIm = pistola.getItemMeta();
		pistolaIm.setDisplayName(ChatColor.GOLD + "Desert Eagle");
		pistolaIm.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
		pistola.setItemMeta(pistolaIm);
		e.getPlayer().getInventory().addItem(pistola);
	}
	Projectile en = null;
	Player p = null;
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
	
		Action rA = Action.RIGHT_CLICK_AIR;
		Action rB = Action.RIGHT_CLICK_BLOCK;
		Action lB = Action.LEFT_CLICK_BLOCK;
		Action lA = Action.LEFT_CLICK_AIR;
		boolean esNullmano = e.getPlayer().getItemInHand() == null;
		if(!(esNullmano)) {
			if((e.getAction() == rA || e.getAction() == rB) && e.getPlayer().getItemInHand().getType() == pistola.getType()) {
				p = e.getPlayer();
				Projectile en = e.getPlayer().launchProjectile(Arrow.class,e.getPlayer().getEyeLocation().getDirection().multiply(10));
				
			}
			if((e.getAction() == rA || e.getAction() == rB) && e.getPlayer().getItemInHand().getType() == granada.getType()) {
				p = e.getPlayer();
				Projectile en = e.getPlayer().launchProjectile(Arrow.class,e.getPlayer().getEyeLocation().getDirection());
				Entity granada = e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.PRIMED_TNT);
				granada.setVelocity(en.getVelocity());

				en.remove();

			}


		}

	}
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e){
		if(en != null && e.getEntity().getType() == en.getType()){
			if(p != null){
				p.sendMessage("Projectile Launched!");
				p.playEffect(en.getLocation(), Effect.MOBSPAWNER_FLAMES, 2);
			}
			
			
		}
		
		
		
		
		
	}




}  