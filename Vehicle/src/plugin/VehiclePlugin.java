package plugin;

import java.sql.Array;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class VehiclePlugin extends JavaPlugin implements Listener{

	//Variable Chatcolor para el chat
	ChatColor rojo = ChatColor.RED;
	Entity e = null;
    Player player = null;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(command.getName().equalsIgnoreCase("ride")) {
			Player p = (Player) sender;
			if(!(sender instanceof Player)) {
				return false;

			}
			String parameter = args[0];
			if(parameter == null) {
				return false;
			}

			if(parameter.equals("PIG")){
				Entity e = p.getWorld().spawn(p.getLocation(), Pig.class);
			}
			if(parameter.equals("BLAZE")){
				e = p.getWorld().spawn(p.getLocation(), Blaze.class);
			}
			if(parameter.equals("ENDERDRAGON")){
				e = p.getWorld().spawn(p.getLocation(), EnderDragon.class);
			}
			if(parameter.equals("CHICKEN")){
				e = p.getWorld().spawn(p.getLocation(), Chicken.class);
			}
			if(parameter.equals("SQUID")){
				e = p.getWorld().spawn(p.getLocation(), Squid.class);
			}
			if(parameter.equals("WITHER")){
				e = p.getWorld().spawn(p.getLocation(), Wither.class);
			}
			if(parameter.equals("IRONGOLEM")){
				e = p.getWorld().spawn(p.getLocation(), IronGolem.class);
			}
			if(parameter.equals("SPIDER")){
				
				e = p.getWorld().spawn(p.getLocation(), Spider.class);
				
				
				return true;
			}



			if (!(p.isSneaking())) {
				if(e.getPassenger() == null){

					e.setPassenger(p);
					
					
				}

			}
			else {

				e.eject();
			}
		}
		if(command.getName().equalsIgnoreCase("chain")) {
			Player p = (Player) sender;
			if(!(sender instanceof Player)) {
				return false;

			}
			String parameter = args[0];
			if(parameter == null) {
				return false;
			}

			if(parameter.equals("PIG")){
			Entity e1 = p.getWorld().spawn(p.getLocation(), Pig.class);
			Entity e2;
			for(int i = 0; i < 10; i++){
				
				e2 = p.getWorld().spawn(p.getLocation(), Pig.class);
				e1.setPassenger(e2);
				e1=e2;
				
			}
			
			}
			if(parameter.equals("BLAZE")){
				Entity e1 = p.getWorld().spawn(p.getLocation(), Blaze.class);
				Entity e2;
				for(int i = 0; i < 10; i++){
					
					e2 = p.getWorld().spawn(p.getLocation(), Blaze.class);
					e1.setPassenger(e2);
					e1=e2;
					
				}
			}
			if(parameter.equals("ENDERDRAGON")){
				Entity e1 = p.getWorld().spawn(p.getLocation(), EnderDragon.class);
				Entity e2;
				for(int i = 0; i < 10; i++){
					
					e2 = p.getWorld().spawn(p.getLocation(), EnderDragon.class);
					e1.setPassenger(e2);
					e1=e2;
					
				}
			}
			if(parameter.equals("SKELETON")){
				Entity e1 = p.getWorld().spawn(p.getLocation(), Skeleton.class);
				Entity e2;
				for(int i = 0; i < 10; i++){
					
					e2 = p.getWorld().spawn(p.getLocation(), Skeleton.class);
					e1.setPassenger(e2);
					e1=e2;
					
				}
			}
			if(parameter.equals("CHICKEN")){
				Entity e1 = p.getWorld().spawn(p.getLocation(), Chicken.class);
				Entity e2;
				for(int i = 0; i < 10; i++){
					
					e2 = p.getWorld().spawn(p.getLocation(), EnderDragon.class);
					e1.setPassenger(e2);
					e1=e2;
					
				}
			}
			if(parameter.equals("SQUID")){
				Entity e1 = p.getWorld().spawn(p.getLocation(), Squid.class);
				Entity e2;
				for(int i = 0; i < 10; i++){
					
					e2 = p.getWorld().spawn(p.getLocation(), Squid.class);
					e1.setPassenger(e2);
					e1=e2;
					
				}
			}
			if(parameter.equals("WITHER")){
				Entity e1 = p.getWorld().spawn(p.getLocation(), Wither.class);
				Entity e2;
				for(int i = 0; i < 10; i++){
					
					e2 = p.getWorld().spawn(p.getLocation(), Wither.class);
					e1.setPassenger(e2);
					e1=e2;
					
				}
			}
			if(parameter.equals("IRONGOLEM")){
				
				Entity e1 = p.getWorld().spawn(p.getLocation(), IronGolem.class);
				Entity e2;
				for(int i = 0; i < 10; i++){
					
					e2 = p.getWorld().spawn(p.getLocation(), IronGolem.class);
					e1.setPassenger(e2);
					e1=e2;
					
				}
			}
			if(parameter.equals("SPIDER")){
				Entity e1 = p.getWorld().spawn(p.getLocation(), Spider.class);
				Entity e2;
				for(int i = 0; i < 10; i++){
					
					e2 = p.getWorld().spawn(p.getLocation(), Spider.class);
					e1.setPassenger(e2);
					e1=e2;
					
				}
				
				return true;
			}



			if (!(p.isSneaking())) {
				if(e.getPassenger() == null){

					e.setPassenger(p);
					
					
				}

			} 
			else {

				e.eject();
			}
		}
		if(command.getName().equalsIgnoreCase("hack")) {
			Player p = (Player) sender;
			if(!(sender instanceof Player)) {
				return false;
				
			}
			if (!(p.isSneaking())) {
				

			}
		}

		return false;

	}
	@EventHandler
	public void onPlayerRun(PlayerMoveEvent ev) {
		if(e != null) {
			if(ev.getPlayer().isSprinting()) {
				
			Vector v = ev.getPlayer().getEyeLocation().getDirection();
			e.setVelocity(v);
		}
			else{
				e.setVelocity(null);
			}
		
	}
		
			
			
			
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
}  