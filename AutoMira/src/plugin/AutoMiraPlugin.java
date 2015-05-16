package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoMiraPlugin extends JavaPlugin implements Listener {
	private boolean activo = false;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	ItemStack lanzaCerdos = new ItemStack(Material.RAW_BEEF);
	ItemMeta ImCerdo = lanzaCerdos.getItemMeta();
	ChatColor oro = ChatColor.GOLD;
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}

		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("laarma")){

			ImCerdo.addEnchant(Enchantment.KNOCKBACK, 5, true);
			ImCerdo.setDisplayName(oro + "Arma Lanza Cerdos");
			lanzaCerdos.setItemMeta(ImCerdo);
			p.getInventory().addItem(lanzaCerdos);
		}
		return false;
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Action rightAir = Action.RIGHT_CLICK_AIR;
		Action rightBlock = Action.RIGHT_CLICK_BLOCK;
		Player p = e.getPlayer();
		if(p.getItemInHand().getItemMeta() == ImCerdo) {
			if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Location loc = p.getLocation();
				loc.add(0, 2, 0);
				p.sendMessage("Arma inizialziada!");
				Entity pig = p.getWorld().spawn(p.getLocation(), Pig.class);
				Projectile arrow = p.getWorld().spawn(loc, Arrow.class);
				arrow.setVelocity(p.getEyeLocation().getDirection());
				
			}
		}
	}

}






