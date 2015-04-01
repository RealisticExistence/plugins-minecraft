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
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("examen")){
			teacher = p.getWorld().spawn(p.getLocation(), Villager.class);
			teacher.setCustomName("Teacher");
			teacher.setTarget(p);
			teacher.setHealth(20);
			teacher.setProfession(Profession.PRIEST);
			
		}
		return false;
		
	}
	int rand = (int) (Math.random()*15);
	int rand2 = (int) (Math.random()*15);
	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent ev) {
		if(teacher == null) {
			return;
		}
		if(ev.getRightClicked() == teacher){
			
			ev.getPlayer().sendMessage("Fast! Tell me the sum of " + rand + " and " + rand2);
			Responded = false;
			HasStarted = true;
		}
	
	}
	@EventHandler
	public void onPlayerChat(PlayerChatEvent ev) {
		if(!(Responded)) {
			ev.getPlayer().sendMessage("Answer!!");
			if(ev.getMessage().equals(rand+rand2)) {
				Responded = true;
			}
		}
		
		
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent ev) {
		if(HasStarted) {
			if(!(Responded)) {
				ev.getPlayer().sendMessage("ANSWER!!!");
				ev.setCancelled(true);
			}
			else{
				ev.getPlayer().sendMessage("Good work!");
				ev.setCancelled(false);
			}
		}
		
		
	}
}
