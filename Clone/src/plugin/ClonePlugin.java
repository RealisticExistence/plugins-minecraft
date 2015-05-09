package plugin;

import javax.swing.text.html.parser.Entity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ClonePlugin extends JavaPlugin implements Listener{
	Location loc1 = null;
	Location loc2 = null;
	Material materiales[][][];
//Variable Chatcolor para el chat
		ChatColor rojo = ChatColor.RED;
		int locx = 0;
		int locy = 0;
		int locz = 0;
		
		

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}
	@EventHandler
	public void onPLayerInteract(PlayerInteractEvent e){

		if(e.getAction() == Action.LEFT_CLICK_BLOCK){
			Player p = e.getPlayer();
			loc1 = p.getTargetBlock(null, 255).getLocation();
			p.sendMessage(ChatColor.BLUE + "Localizacion 1 cogida: " + loc1.getBlockX() + ", " + loc1.getBlockY() + ", " + loc1.getBlockZ());

		}
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			Player p = e.getPlayer();
			loc2 = p.getTargetBlock(null, 255).getLocation();
			p.sendMessage(ChatColor.BLUE + "Localizacion 2 cogida: " + loc2.getBlockX() + ", " + loc2.getBlockY() + ", " + loc2.getBlockZ());
		}
		if(loc1 != null && loc2 != null){
			
			
			
			int x2 = Math.max(loc1.getBlockX(), loc2.getBlockX());
			int x1 = Math.min(loc1.getBlockX(), loc2.getBlockX());
			int z1 = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
			int z2 = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
			int y1 = Math.min(loc1.getBlockY(), loc2.getBlockY());
			int y2 = Math.max(loc1.getBlockY(), loc2.getBlockY());
			
			for(int x = x1; x <= x2; x++){

				for(int y = y1; y <= y2; y++){

					for(int z = z1; z <= z2; z++){
						Block b = e.getPlayer().getWorld().getBlockAt(x, y, z);
						materiales[x][y][z] = b.getType();
						locz++;
						locy++;
						locx++;


					}
				}
				
			}
		}
		

	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)){
			return false;
			
		}
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("clone")){
			Location l = p.getLocation();
			int xp = p.getLocation().getBlockX();
			int yp = p.getLocation().getBlockY();
			int zp = p.getLocation().getBlockZ();
		
			
			
			for(int x = 0; x < locx; x++){
				for(int y = 0; y < locy; y++){
					for(int z = 0; z < locz; z++){
						Block b = p.getWorld().getBlockAt(x+xp, y+yp, z+zp);
						b.setType(materiales[x][y][z]);
					}
				}
			}
			
			
		}
		
		return false;
	}
	


}