package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class KitsPlugin extends JavaPlugin implements Listener {
 boolean SensorGiven = false;
 ItemStack sensor = new ItemStack(Material.DISPENSER);
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		//if(p.getName().equals("jorgejorge99")){
			//util.Utils.DarKitAdmin(p);
			
			
		//}
		//else{
		if(p.hasPermission("permissions.god")){
			ItemStack seleccion = new ItemStack(Material.BOOK);
			ItemMeta seleccionIm = seleccion.getItemMeta();
			seleccionIm.addEnchant(Enchantment.DURABILITY, 1, false);
			seleccionIm.setDisplayName(ChatColor.GREEN + "Kits Selection");
			seleccion.setItemMeta(seleccionIm);
			p.getInventory().addItem(seleccion);
		}
			
			
		//}
		
		
	}
	Inventory i = null;
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(p.getItemInHand() != null && p.getItemInHand().getType() == Material.BOOK && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
			i = Bukkit.createInventory(p, 9);
			p.sendMessage("Opening kit selection...");
			ItemStack snowMan = CambiarNombre(Material.SNOW_BALL,"Olaf The Snowman");
			i.addItem(snowMan);
			ItemStack tank = CambiarNombre(Material.OBSIDIAN, "Tank");
			i.addItem(tank);
			ItemStack healer = CambiarNombre(Material.RED_ROSE, "Healer");
			i.addItem(healer);
			p.openInventory(i);
			
			
			
			
			
			
		}
	}
	boolean hasOlafsArmor = false;
	@EventHandler
	public void onPlayerInteractInventory(InventoryInteractEvent e){
		if(e.getInventory() == i){
			Player p = (Player) e.getWhoClicked();
			if(e.getWhoClicked().getItemOnCursor() != null && e.getWhoClicked().getItemOnCursor().getType() == Material.SNOW_BALL){
				for(int i = 0; i< 5; i++){
					p.getInventory().addItem(new ItemStack(Material.SNOW_BALL));
				}
				
				hasOlafsArmor = true;
				p.getInventory().setHelmet(OlafArmorHelmet(Color.WHITE));
				p.getInventory().setLeggings(OlafArmorLeggings(Color.WHITE));
				p.getInventory().setChestplate(OlafArmorChestplate(Color.WHITE));
				p.getInventory().setBoots(OlafArmorBoots(Color.WHITE));
				e.setCancelled(true);
				
			}
		}
		
	}
	boolean cantMove = false;
	Player hit = null;
	@EventHandler
	public void onPlayerShoot(EntityDamageEvent e){
		if(hasOlafsArmor && e.getCause() == DamageCause.PROJECTILE && e.getEntity() instanceof Player){
			cantMove = true;
			hit = (Player) e.getEntity();
		}
		else{
			cantMove = false;
		}
	}
	@EventHandler
	public void onplayerMove(PlayerMoveEvent e){
		if(cantMove && e.getPlayer() == hit){
			e.setCancelled(true);
		}
		
		
	}
	@EventHandler
	public void PlayerDie(PlayerDeathEvent e){
		if(e.getEntity() == hit && e.getEntity() instanceof Player){
			cantMove = false;
		}
	}
	private ItemStack OlafArmorHelmet(Color white) {
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta lAMHelmet = (LeatherArmorMeta) helmet.getItemMeta();
		lAMHelmet.setColor(white);
		lAMHelmet.setDisplayName(ChatColor.WHITE + "Olaf`s head");
		helmet.setItemMeta(lAMHelmet);
		return helmet;
		
	}
	private ItemStack OlafArmorChestplate(Color white) {
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta lAMHelmet = (LeatherArmorMeta) chestplate.getItemMeta();
		lAMHelmet.setColor(white);
		lAMHelmet.setDisplayName(ChatColor.WHITE + "Olaf`s body");
		chestplate.setItemMeta(lAMHelmet);
		return chestplate;
		
	}
	private ItemStack OlafArmorLeggings(Color white) {
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lAMHelmet = (LeatherArmorMeta) leggings.getItemMeta();
		lAMHelmet.setColor(white);
		lAMHelmet.setDisplayName(ChatColor.WHITE + "Olaf`s Legs");
		leggings.setItemMeta(lAMHelmet);
		return leggings;
		
	}
	private ItemStack OlafArmorBoots(Color white) {
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta lAMHelmet = (LeatherArmorMeta) boots.getItemMeta();
		lAMHelmet.setColor(white);
		lAMHelmet.setDisplayName(ChatColor.WHITE + "Olaf`s head");
		boots.setItemMeta(lAMHelmet);
		return boots;
		
	}
	private ItemStack CambiarNombre(Material m, String string) {
		ItemStack cosa = new ItemStack(m);
		ItemMeta im = cosa.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + string);
		im.addEnchant(Enchantment.DURABILITY, 1, false);
		cosa.setItemMeta(im);
		return cosa;
		
	}
	

}
