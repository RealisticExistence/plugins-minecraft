package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GalletaPlugin extends JavaPlugin implements Listener {
	boolean isPlaced = false;
	Material luckyblockM = null;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	


	@EventHandler
	public void onChestCreat(PrepareItemCraftEvent event){
		ItemStack galleta = new ItemStack(Material.COOKIE);
		ItemMeta galletaIm = galleta.getItemMeta();
		galletaIm.addEnchant(Enchantment.DAMAGE_ALL, 25, true);
		galletaIm.addEnchant(Enchantment.KNOCKBACK, 100, true);
		galletaIm.setDisplayName(ChatColor.GOLD + "Santa's Cookie");
		galleta.setItemMeta(galletaIm);
	
		ShapedRecipe galletaR = new ShapedRecipe(galleta);
		galletaR.shape(new String[] { "SSS", "SIS", "SSS" });
		galletaR.setIngredient('S', Material.DIAMOND);
		galletaR.setIngredient('I', Material.COOKIE ); 
		getServer().addRecipe(galletaR);

		((HumanEntity) event.getViewers()).getInventory().addItem(galleta);
		
	}









}
