package bombaP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class LaberintoPlugin extends JavaPlugin implements Listener{

	ChatColor rojo = ChatColor.RED;
	ChatColor negrita = ChatColor.BOLD;
	protected float FUERZA_EXPLOSION = 20;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);	
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if (command.getName().equalsIgnoreCase("laberinto")) {
			if(sender instanceof Player) {
				construirLaberinto(((Player) sender).getLocation());
			}
		}
		return false;

	}

	private void construirLaberinto(Location location) {
		Laberinto l = new Laberinto(51, 51);
		int x=location.getBlockX();
		int z=location.getBlockZ();
		int y=location.getBlockY();
		
		for(int i=0;i<l.ladrillos.length;i++){
			for(int j=0;j<l.ladrillos[0].length;j++){
				if(l.ladrillos[i][j]){
					location.getWorld().getBlockAt(x+i, y+1, z+j).setType(Material.DIAMOND_BLOCK);
					location.getWorld().getBlockAt(x+i, y, z+j).setType(Material.DIAMOND_BLOCK);
				}else{
					location.getWorld().getBlockAt(x+i, y, z+j).setType(Material.AIR);
				}
			}
		}
	}
}
