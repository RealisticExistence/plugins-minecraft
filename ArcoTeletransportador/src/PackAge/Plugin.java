package PackAge;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Plugin extends JavaPlugin implements Listener {
	private boolean activo = false;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(command.getName().equalsIgnoreCase("ArcoTeletransportador")){
			ChatColor rojo = ChatColor.RED;
			ChatColor magia = ChatColor.MAGIC;
			if (activo) {
				activo = false;
				sender.sendMessage(rojo + "Arco Teletransportador" + " desactivado");
			}
			else if (!activo) {

				activo = true;
				sender.sendMessage(rojo + "Arco Teletransportador" + " activado");
			}
			return true;
		}
		return false;
	}
	@EventHandler
	public void onPlayerShoot(ProjectileLaunchEvent ev) {
		if(ev.getEntityType() == EntityType.ARROW) {
			if(ev.getEntity().getShooter() instanceof Player) {

				/*
				Player p = (Player) ev.getEntity().getShooter();
				
				ItemMeta im = p.getItemInHand().getItemMeta();
				im.setDisplayName(ChatColor.RED + "El propulsa cerdos");
				im.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
				im.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				p.getItemInHand().setItemMeta(im);
				Vector vector = p.getEyeLocation().getDirection().multiply(5);//ev.getEntity().getLocation().getDirection().multiply(2);
				Pig pig = p.getWorld().spawn(ev.getEntity().getLocation(), Pig.class);
				ev.getEntity().remove();
				
				
				pig.setVelocity(vector);
				*/
				ev.getEntity().setPassenger((Entity) ev.getEntity().getShooter());
				

			}
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent ev) {
		if(ev instanceof EntityDamageByEntityEvent) {
			if(ev.getEntity().getType() == EntityType.PIG) {
				if(ev.getEntity().isInsideVehicle()) {
					ev.setCancelled(true);
				}


			}

		}
	}
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent ev) {
		if(ev.getEntity().getType() == EntityType.ARROW) {
			Arrow a = (Arrow) ev.getEntity();
			Pig e = (Pig) a.getPassenger();
			ev.getEntity().eject();
			e.setHealth(0);
		}

	}
	@EventHandler
	public void onPlayerProjectile(ProjectileHitEvent a) {
		if (!activo) return;
		Location	l = a.getEntity().getLocation();
		if(!(a.getEntity().getShooter() instanceof Player)){
			return;
		}

		Player p = (Player) a.getEntity().getShooter();
		if(p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null) {
			if(p.getItemInHand().getItemMeta().getDisplayName().contains("Constructor")){
				construirCasa(l);
				p.teleport(l);

			}
			else if(p.getItemInHand().getItemMeta().getDisplayName().contains("Teletransportador")) {
				p.teleport(l);


			}
			else if(p.getItemInHand().getItemMeta().getDisplayName().contains("Destructor")) {

				p.getWorld().createExplosion(l,5);
			}

		}				

	}

	private void construirCasa(Location centro) {

		int x = centro.getBlockX();
		int y = centro.getBlockY();
		int z = centro.getBlockZ();



		int ancho = 10;
		int alto = 3;
		int largo = 10;
		Material pared = Material.WOOD;
		Material suelo = Material.GLOWSTONE;

		for(int i=-ancho/2;i<=ancho/2;i++){
			for(int j=-1;j<=alto;j++){
				for(int k=-largo/2;k<=largo/2;k++){
					Block b = centro.getWorld().getBlockAt(x+i, y+j, z+k);
					boolean esPared = (i == -ancho/2 || i == ancho/2) || (k == -largo/2 || k == largo/2);
					boolean esTechoOSuelo = (j == -1 || j == alto);

					if(esPared){
						b.setType(pared);
					}
					else if(esTechoOSuelo){
						b.setType(suelo);
					}

				}
			}
		}

	}

}






