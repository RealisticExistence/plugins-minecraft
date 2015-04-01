package plugin;

import javax.swing.text.html.parser.Entity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ClonePlugin extends JavaPlugin implements Listener{

//Variable Chatcolor para el chat
		ChatColor rojo = ChatColor.RED;
		
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}
    private NPC npc;
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("clone")) {
			if(sender instanceof Player) {
			Player p = (Player) sender;
			EntityType en = npc.getType();
			p.getWorld().spawnEntity(p.getLocation(), en);
			}
			
		}
		return false;
	}

	
	
}  