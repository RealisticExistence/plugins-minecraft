package plugin;




import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class EntityChangePlugin extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	EntityType ent = null;
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {

		if(!(sender instanceof Player)) {
			return false;
		}
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("egg")) {
			ItemStack huevo = new ItemStack(Material.EGG);
			ItemMeta huevoIm = huevo.getItemMeta();
			huevoIm.setDisplayName(ChatColor.GOLD  + "Huevos Del Poder");
			huevoIm.addEnchant(Enchantment.ARROW_KNOCKBACK,3,true);
			huevo.setItemMeta(huevoIm);
			for(int i = 0; i<64;i++) {
				p.getInventory().addItem(huevo);
			}


		}


		return false;
	}
	@EventHandler
	public void onPlayerthrow(PlayerEggThrowEvent e) {
		if(e.getEgg().getShooter() instanceof Player) {
			Player player = e.getPlayer();
			Location l = player.getLocation();
			l.add(0, 10, 0);
			player.teleport(l);
			l.add(0, -10, 0);

		}




	}

}