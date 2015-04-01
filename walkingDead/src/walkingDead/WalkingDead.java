package walkingDead;

import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WalkingDead extends JavaPlugin implements Listener{


	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@EventHandler
	public void onPlayerLogIn(PlayerJoinEvent event) {
		event.getPlayer().getWorld().setMonsterSpawnLimit(1000000);
		ChatColor rojo = ChatColor.RED;
		ChatColor verde = ChatColor.GREEN;
		Player p = event.getPlayer();
		Bukkit.getServer().broadcastMessage(rojo + p.getName() + verde + " a entrado al server!");
	}


	@EventHandler
	public void onPlayerMoves(PlayerMoveEvent event){

		List<Entity> Entidades = event.getPlayer().getWorld().getEntities();
		for (Entity e : Entidades){


			if (e.getLocation().distance(event.getPlayer().getLocation()) < 10) {

				if(e.getType() == EntityType.ZOMBIE){
					((Zombie)e).setHealth(0);
				}
				if(e.getType() == EntityType.SKELETON){
					((Skeleton)e).setHealth(0);
				}
				if(e.getType() == EntityType.CREEPER){
					((Creeper)e).setHealth(0);
				}
				if(e.getType() == EntityType.SPIDER){
					((Spider)e).setHealth(0);
				}
				if(e.getType() == EntityType.ENDERMAN){
					((Enderman)e).setHealth(0);
				}
				if(e.getType() == EntityType.WITCH){
					((Witch)e).setHealth(0);
				}






			}
		}
	}

}