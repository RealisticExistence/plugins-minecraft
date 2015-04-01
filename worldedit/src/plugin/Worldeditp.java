package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Worldeditp extends JavaPlugin implements Listener{

	//Variable Chatcolor para el chat
	ChatColor rojo = ChatColor.RED;
	int radio = 0;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(!(sender instanceof Player)){
			return false;		
		}

		if (command.getName().equalsIgnoreCase("construir")) {
			if(args != null && args.length > 0 && args[0].equalsIgnoreCase("cupula")) {
				Player p = (Player) sender;
				if(args.length > 1) {
					radio = Integer.parseInt(args[1]);
				}
				else{
					radio = 10;
							
				}
                
				construirCupula(p);
			}
			if(args != null && args.length > 0 && args[0].equalsIgnoreCase("cupulacompleta")) {
				Player p = (Player) sender;

				construirCupulacompleta(p);
			}


			return true;
		}



		return false;
	}

	

	private void construirCupula(Player p) {
		final Location l = p.getLocation();
		final int lX = l.getBlockX();
		final int lY = l.getBlockY();
		final int lZ = l.getBlockZ();
		for(int i = -radio; i <= radio; i++) {
			for(int j = 0; j <= radio; j++) {
				for(int k = -radio; k <= radio; k++) {
					if((int)Math.sqrt((i*i)+(j*j)+(k*k)) == radio) {
						final int iF = i;
						final int jF = j;
						final int kF = k;
						 new BukkitRunnable() {
							
							@Override
							public void run() {
								l.getWorld().getBlockAt(iF + lX, jF + lY, kF + lZ).setType(Material.GLASS);
								
							}
						}.runTaskTimer(this, 10L, 10L);
						

					}
          

				}


			}
			
					}

	}

private void construirCupulacompleta(Player p) {
	Location l = p.getLocation();
	int radio = 100;
	int lX = l.getBlockX();
	int lY = l.getBlockY();
	int lZ = l.getBlockZ();
	for(int i = -radio; i <= radio; i++) {
		for(int j = -radio; j <= radio; j++) {
			for(int k = -radio; k <= radio; k++) {
				if((int)Math.sqrt((i*i)+(j*j)+(k*k)) == radio) {
					
					l.getWorld().getBlockAt(i + lX, j + lY, k + lZ).setType(Material.GLASS);;

				}
      

			}


		}
		
				}
		
	}








}  