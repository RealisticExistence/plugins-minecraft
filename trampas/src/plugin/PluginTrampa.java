package plugin;

import java.util.List;











import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class PluginTrampa extends JavaPlugin implements Listener{
	Player p = null;
	Location l = null;
	String s = null;
	boolean trampaActive = false;
	//Variable Chatcolor para el chat
	ChatColor rojo = ChatColor.RED;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(command.getName().equalsIgnoreCase("trampa")) {
			p = (Player) sender;
			if(!(sender instanceof Player)) {
				return false;
			}
			l = p.getLocation();
			trampaActive = true;
			s = args[0];
		}
		return false;


	}
	@EventHandler
	public void onPlayerMoves(PlayerMoveEvent ev) {
		if(ev.getPlayer() != p) {
			if(trampaActive) {
				int radio = 5;
				List<Player> Entidades = p.getWorld().getPlayers();
				if(s.equalsIgnoreCase("explosion")) {
					for (Player e : Entidades){

						if (e.getLocation().distance(l) < radio) {

							p.getWorld().createExplosion(l, radio);
							trampaActive = false;
						}


					}
				}
				if(ev.getPlayer() != p) {
					if(s.equalsIgnoreCase("monstruos")) {
						for (Player e : Entidades){

							if (e.getLocation().distance(l) < radio) {
								for(int j = 0; j < radio*2; j++) {
									e.getWorld().spawn(e.getLocation(), Creeper.class);	
								}
								e.getWorld().setTime(18000);
								e.getInventory().clear();
								trampaActive = false;
							}



						}

					}

					if(ev.getPlayer() != p) {
						if(s.equalsIgnoreCase("ban")) {
							for (Player e : Entidades){

								if (e.getLocation().distance(l) < radio) {
									e.setBanned(true);
									e.kickPlayer(rojo + "Kicked from a trap! noober!!!");
									trampaActive = false;


								}

							}

						}

						if(ev.getPlayer() != p) {
							if(s.equalsIgnoreCase("esqueletos")) {
								for (Player e : Entidades){
									Location l = e.getLocation();
									int radio2 = 5;
									int lX = l.getBlockX();
									int lY = l.getBlockY();
									int lZ = l.getBlockZ();
									for(int i = -radio2; i <= radio2; i++) {	
											for(int k = -radio2; k <= radio2; k++) {
												if((int)Math.sqrt((i*i)+(k*k)) == radio2) {
													Entity e1 = l.getWorld().spawn(l, Skeleton.class);
													Entity e2;
														e2 = l.getWorld().spawn(l, Skeleton.class);
														e1.setPassenger(e2);
														e1=e2;

												


											


										}

									}


								}

							}

						}


					}
				}
			}

		}
	}
}
}

