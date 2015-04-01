package plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
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
			final ArmorStand a = p.getWorld().spawn(l, ArmorStand.class);
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
			final Zombie z = p.getWorld().spawn(p.getLocation(), Zombie.class);
			
			z.setBaby(true);
			z.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,600000,1));
			z.getEquipment().setHelmet(null);
			z.getEquipment().setChestplate(null);
			z.getEquipment().setLeggings(null);
			z.getEquipment().setBoots(null);
			z.getEquipment().setItemInHand(null);
			new BukkitRunnable() {
				
				@Override
				public void run() {
				a.setRightArmPose(new EulerAngle(0,0,0));
				a.setLeftArmPose(new EulerAngle(0,0,0));
				Location loc = z.getLocation();
				loc.add(1, -1, 0);
				a.setVelocity(z.getEyeLocation().getDirection());
				a.teleport(loc);
				a.setRightArmPose(new EulerAngle(1,0,0));
				a.setLeftArmPose(new EulerAngle(1,0,0));
					
				}
			}.runTaskTimer(this, 2, 2);
	
			
			
			
			
			return true;
		}
      
		return false;

	}
	
	
}  