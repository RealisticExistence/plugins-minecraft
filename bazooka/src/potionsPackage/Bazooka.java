package potionsPackage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Bazooka extends JavaPlugin implements Listener{


	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}


	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
			Block b = p.getTargetBlock(null,255);
			Location l = b.getLocation();
			l.setY(l.getY()+1);
			
			
			p.getWorld().strikeLightning(l);
			


		}
		if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
			
		}
	
}
	
	public static void sendFireballsFromPlayer(Player player,int amt){
		  Location loc=player.getEyeLocation();
		  final double tau=2 * Math.PI;
		  double arc=tau / amt;
		  for (double a=0; a < tau; a+=arc) {
		    Vector dir=new Vector(Math.cos(a),0,Math.sin(a));
		    Location spawn=loc.toVector().add(dir.multiply(2)).toLocation(loc.getWorld(),0.0F,0.0F);
		    Fireball fball=player.getWorld().spawn(spawn,Fireball.class);
		    fball.setShooter(player);
		    fball.setDirection(dir.multiply(10));
		  }
		}
	
}