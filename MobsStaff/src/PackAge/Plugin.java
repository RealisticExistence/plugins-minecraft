package PackAge;

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
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Plugin extends JavaPlugin implements Listener {
	private boolean activo = false;
	EntityType en = EntityType.PIG;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("setmobstaff")){
			if(args[0] != null && args.length > 0){
					en = EntityType.valueOf(args[0].toUpperCase());
			}
			else{
				p.sendMessage(ChatColor.RED + "Choose an entity type /setmobstaff [entity]");
			}
			
			
		}
		return false;
	}

	@EventHandler
	public void onChestCreat(PrepareItemCraftEvent event){
		ItemStack vara = new ItemStack(Material.HOPPER);
		ItemMeta varaim = vara.getItemMeta();
		varaim.setDisplayName(ChatColor.GOLD + "Mob Launcher");
		vara.setItemMeta(varaim);

		ShapedRecipe galletaR = new ShapedRecipe(vara);
		galletaR.shape(new String[] { "SIS", "SIS", "SPS" });
		galletaR.setIngredient('S', Material.PORK);
		galletaR.setIngredient('I', Material.EMERALD); 
		galletaR.setIngredient('P', Material.STICK); 
		getServer().addRecipe(galletaR);

		((HumanEntity) event.getViewers()).getInventory().addItem(vara);
		}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(p.getItemInHand().getItemMeta().getDisplayName().contains("Mob Launcher")){
			Entity ent = p.getWorld().spawnEntity(p.getLocation(), en);
			Vector v = p.getEyeLocation().getDirection();
			ent.setVelocity(v.multiply(5));
			
		}
	}
}








