package Prices;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;

import plugin.ShowHealthPlugin;

public class TurretPlugin extends JavaPlugin implements Listener{

	//Variable Chatcolor para el chat
	ChatColor rojo = ChatColor.RED;
	int radio = 0;
	Player player = null;
	Location loc = null;
	boolean LoHaComprado = false;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(!(sender instanceof Player)){
			return false;		
		}
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("turret") && ShowHealthPlugin.board.getObjective(ShowHealthPlugin.boardName).getScore(ChatColor.GREEN + "Money" + ChatColor.GRAY + ":").getScore() > 49) {
			LoHaComprado = true;
			
           
           
		}
		else{
			p.sendMessage("You can`t afford this! Price: 50");
		}
		if(LoHaComprado) {
			player = p;
			Bukkit.getServer().getScoreboardManager().getMainScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(player).setScore(Bukkit.getServer().getScoreboardManager().getMainScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(player).getScore() - 50);
			Location l = p.getLocation();
			l.add(0, 2, 0);
			construirAro(l);
			l.add(0, -1, 0);
			construirAro(l);
			l.add(0,-1,0);
			construirAro(l);
			l.add(0,1,0);
			loc = construirPalo(l);
		}
				
				
			
			
		
		return false;
}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(loc != null) {
			if(loc.distance(e.getPlayer().getLocation()) < 7 && e.getPlayer() != player) {

				e.getPlayer().getWorld().strikeLightning(e.getPlayer().getLocation());
			}
		}

	}

	private Location construirPalo(Location l) {
		l.add(1, 1, 1);
		l.getBlock().setType(Material.NETHER_FENCE);		
		l.add(0, 1, 0);
		l.getBlock().setType(Material.NETHER_FENCE);		
		return l;
	}

	private void construirAro(Location l) {
		for(int i = 0; i<2; i++) {
			l.getBlock().setType(Material.IRON_FENCE);
			l.add(1, 0, 0);	
		}
		for(int i = 0; i<2; i++) {
			l.getBlock().setType(Material.IRON_FENCE);
			l.add(0, 0, 1);	
		}
		for(int i = 0; i<2; i++) {
			l.getBlock().setType(Material.IRON_FENCE);
			l.add(-1, 0, 0);	
		}
		for(int i = 0; i<2; i++) {
			l.getBlock().setType(Material.IRON_FENCE);
			l.add(0, 0, -1);	
		}





	}

}








