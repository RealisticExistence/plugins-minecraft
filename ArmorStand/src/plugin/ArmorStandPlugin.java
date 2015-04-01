package plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.EulerAngle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ArmorStandPlugin extends JavaPlugin implements Listener{

	
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(command.getName().equalsIgnoreCase("armor")) {
			Player p = ((Player)sender);
			Location l = p.getLocation();
			l.add(0, -1, 0);
			ArmorStand a = p.getWorld().spawn(l, ArmorStand.class);
			a.setGravity(false);
			a.setBodyPose(new EulerAngle(1,0,0));
			a.setArms(true);
			a.setVisible(false);
			a.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
			ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short)3);
			SkullMeta im = (SkullMeta) skull.getItemMeta();
			im.setOwner(p.getName());
			skull.setItemMeta(im);
			a.setHelmet(skull);
	
			
			
			
			
			return true;
		}
      
		return false;

	}
}  