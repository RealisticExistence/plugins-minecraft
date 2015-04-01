package packAge;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin implements Listener{
	Material m = null;
	private boolean activo = false;


	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}



	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(command.getName().equalsIgnoreCase("BloqueTeletransportador")){
			ChatColor rojo = ChatColor.RED;
			ChatColor magia = ChatColor.MAGIC;
			if (activo) {
				activo = false;
				sender.sendMessage(rojo + "Bloque Teletransportador" + " desactivado");
			}
			else if (!activo) {

				activo = true;
				sender.sendMessage(rojo + "Bloque Teletransportador" + " activado");
			}
			return true;
		}
		return false;
	}




	@EventHandler
	public void playerInteract(PlayerInteractEvent e) {
		if(!activo) return;
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			Location l = p.getTargetBlock(null, 255).getLocation();
			m = l.getBlock().getType();
			l.getBlock().breakNaturally();
		}


		if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {

			Player p = e.getPlayer();
			Location l = p.getTargetBlock(null, 255).getLocation();
			if (l.getBlock().getType() != Material.AIR) {
				if (m != null) { 
					l.getBlock().setType(m);
					m = null;
				}

			}

		}
	}










}
