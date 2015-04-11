package plugin;

 



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EspiralPlugin extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	
}
	Villager teacher = null;
	boolean Responded = false;
	boolean HasStarted = false;
	
		
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	
	
	}





	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		
		}
		final Player p = (Player) sender;
		final int xJ = p.getLocation().getBlockX();
		final int yJ = p.getLocation().getBlockY();
		final int zJ = p.getLocation().getBlockZ();
		if(command.getName().equalsIgnoreCase("pitagoras")){
			final int radio = 2;
			for(int cuenta = 0; cuenta < 5; cuenta++){
				new BukkitRunnable() {
					
					@Override
					public void run() {
						for(int x = -radio; x < radio; x++){
							for(int y = 0; y < 2; y++){
								for(int z = -radio; z < radio; z++){
									p.getWorld().getBlockAt(xJ+x, yJ+y, zJ+z).setType(Material.STAINED_CLAY);
									
									
								}
							}
						}
						
					}
				}.runTaskLater(this, 20);
				int radio2 = radio;
				radio2 = radio2*2;
				
				
				
			}
			
			
			
			
		}
		return false;
		
	}
	
}
