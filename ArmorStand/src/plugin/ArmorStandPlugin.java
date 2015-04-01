package plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.EulerAngle;
import org.bukkit.entity.ArmorStand;

public class ArmorStandPlugin extends JavaPlugin implements Listener{

	
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(command.getName().equalsIgnoreCase("armor")) {
			Player p = ((Player)sender);
			ArmorStand a = p.getWorld().spawn(p.getLocation(), ArmorStand.class);
			a.setCustomName("Conejillo de Indias");
			a.setCustomNameVisible(true);
			a.setLeftLegPose(new EulerAngle(0.4, 0.3, 0.5));
			//a.setVisible(false);
			a.setGravity(false);
			a.setVisible(false);
			ArmorStand a1 = p.getWorld().spawn(p.getLocation().add(0, 0.2, 0), ArmorStand.class);
			a1.setCustomName("Conejillo de Indias");
			a1.setCustomNameVisible(true);
			//a1.setVisible(false);
			
			a1.setGravity(false);

			
			ArmorStand a2 = p.getWorld().spawn(p.getLocation().add(0, 0.4, 0), ArmorStand.class);
			a2.setCustomName("Conejillo de Indias");
			a2.setCustomNameVisible(true);
			//a2.setVisible(false);
			a2.setGravity(false);
	
			
			
			
			
			return true;
		}
      
		return false;

	}
}  