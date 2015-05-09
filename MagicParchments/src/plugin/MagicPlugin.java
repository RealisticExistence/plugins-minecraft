package plugin;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class MagicPlugin extends JavaPlugin implements Listener{



	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}
	@EventHandler
	public void onEntityDie(EntityDeathEvent e){
		if(e.getEntityType() == EntityType.WITHER){

			e.getDrops().add(airParchment(ChatColor.BLUE + "Air Parchment"));


		}
		if(e.getEntityType() == EntityType.SILVERFISH){

			e.getDrops().add(MoisesParchment(ChatColor.DARK_PURPLE + "Moises Stick"));


		}
		if(e.getEntityType() == EntityType.OCELOT){
			e.getDrops().add(speedParchment(org.bukkit.ChatColor.YELLOW + "Speed Essence"));

		}


	}
	boolean activo2 = false;
	boolean activo3 = false;
	double health = 0;
	boolean hasLanded = false;
	boolean activo = false;
	ItemStack emerald = null;
	ItemStack stick = null;

	@EventHandler 
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(p.getItemInHand() != null){
			if(p.getItemInHand().getItemMeta() != null){
				if(p.getItemInHand().getItemMeta().getDisplayName() != null){
					if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
						if(p.getItemInHand().getItemMeta().getDisplayName().contains("Air")){
							if((p.getWorld().getBlockAt(p.getLocation().getBlockX(), p.getLocation().getBlockY() -1, p.getLocation().getBlockZ()).getType() == Material.AIR)){
								Vector vector = p.getVelocity();

								double rotX = p.getLocation().getYaw();
								double rotY = p.getLocation().getPitch();

								vector.setY(-Math.sin(Math.toRadians(0)));

								double h = Math.cos(Math.toRadians(rotY));

								vector.setX(-h * Math.sin(Math.toRadians(rotX)));
								vector.setZ(h * Math.cos(Math.toRadians(rotX)));

								p.setVelocity(vector.multiply(10));
								p.playEffect(p.getLocation(), Effect.EXTINGUISH, 2);
								p.setHealth(p.getHealth() - 0.5);
								health = p.getHealth();

							}else{
								hasLanded = true;
								Vector vector = p.getVelocity();
								double rotX = p.getLocation().getYaw();
								double rotY = p.getLocation().getPitch();

								vector.setY(-Math.sin(Math.toRadians(700)));

								double h = Math.cos(Math.toRadians(rotY));

								vector.setX(-h * Math.sin(Math.toRadians(rotX)));
								vector.setZ(h * Math.cos(Math.toRadians(rotX)));

								p.setVelocity(vector.multiply(10));
								p.playEffect(p.getLocation(), Effect.EXTINGUISH, 2);
								p.setHealth(p.getHealth() - 0.5);
								health = p.getHealth();


							}



						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().contains("Haste")){
							if(activo){

								p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,6000,5));
								ItemMeta im  = p.getItemInHand().getItemMeta();
								im.addEnchant(Enchantment.DURABILITY, 1, true);
								p.getItemInHand().setItemMeta(im);
								emerald = p.getItemInHand();
							}
							else{
								p.removePotionEffect(PotionEffectType.FAST_DIGGING);
								ItemMeta im  = p.getItemInHand().getItemMeta();
								im.removeEnchant(Enchantment.DURABILITY);
								p.getItemInHand().setItemMeta(im);
								emerald = p.getItemInHand();

							}
							activo = !activo;

						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().contains("Moises")){
							if(activo2){

								p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,6000,5));
								ItemMeta im  = p.getItemInHand().getItemMeta();
								im.addEnchant(Enchantment.DURABILITY, 1, true);
								p.getItemInHand().setItemMeta(im);
								stick = p.getItemInHand();
							}
							else{
								p.removePotionEffect(PotionEffectType.WATER_BREATHING);
								ItemMeta im  = p.getItemInHand().getItemMeta();
								im.removeEnchant(Enchantment.DURABILITY);
								p.getItemInHand().setItemMeta(im);
								stick = p.getItemInHand();

							}
							activo2 = !activo2;

						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().contains("Speed")){
							if(activo3){

								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,6000,5));
								ItemMeta im  = p.getItemInHand().getItemMeta();
								im.addEnchant(Enchantment.DURABILITY, 1, true);
								p.getItemInHand().setItemMeta(im);							}
							else{
								p.removePotionEffect(PotionEffectType.SPEED);
								ItemMeta im  = p.getItemInHand().getItemMeta();
								im.removeEnchant(Enchantment.DURABILITY);
								p.getItemInHand().setItemMeta(im);

							}
							activo3 = !activo3;

						}


					}
				}
			}
		}



	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if(stick != null){
			if(e.getPlayer().getInventory().contains(stick)){
				if(stick.containsEnchantment(Enchantment.DURABILITY)){
					Location loc = e.getPlayer().getLocation();
					int radio = 5;
					for(int x = -radio ; x < radio; x++){
						for(int y = -radio ; y < radio; y++){
							for(int z = -radio ; z < radio; z++){
								Block b = e.getPlayer().getWorld().getBlockAt(loc.getBlockX() + x, loc.getBlockY()+ y, loc.getBlockZ() + z);
								if(b.getType() == Material.STATIONARY_WATER || b.getType() == Material.WATER){
									b.setType(Material.AIR);
								}
							}
						}
					}
				}
			}

		}


	}

	int cuenta = 0;
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		if(e.getBlock().getType() == Material.DIAMOND_ORE){
			e.getPlayer().getWorld().dropItem(e.getBlock().getLocation(), hasteParchment(ChatColor.YELLOW+"Haste Amulet"));



		}
		if(emerald != null){
			if(e.getPlayer().getInventory().contains(emerald)){
				cuenta++;
				//e.getPlayer().sendMessage("Active");
				if(cuenta == 20){
					e.getPlayer().setHealth(e.getPlayer().getHealth() - 1.0);
					cuenta = 0;
				}
			}
		}


	}
	@EventHandler
	public void onPlayerDie(PlayerDeathEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(p.getItemInHand() != null){
				if(p.getItemInHand().getItemMeta() != null){
					if(p.getItemInHand().getItemMeta().getDisplayName() != null){
						if(p.getItemInHand().getItemMeta().getDisplayName().contains("Air")){
							if(hasLanded)
								e.setDeathMessage("");
							p.setHealth(health);
							hasLanded = false;


						}
					}


				}
			}

		}


	}
	private ItemStack MoisesParchment(String s) {
		ItemStack moisesParchment = new ItemStack(Material.STICK);
		ItemMeta moisesParchmentMeta = moisesParchment.getItemMeta();
		moisesParchmentMeta.setDisplayName(s);
		moisesParchment.setItemMeta(moisesParchmentMeta);
		return moisesParchment;
	}
	private ItemStack airParchment(String s) {
		ItemStack airParchment = new ItemStack(Material.PAPER);
		ItemMeta airParchmentMeta = airParchment.getItemMeta();
		airParchmentMeta.setDisplayName(s);
		airParchment.setItemMeta(airParchmentMeta);
		return airParchment;
	}
	private ItemStack hasteParchment(String s) {
		ItemStack hasteParchment = new ItemStack(Material.EMERALD);
		ItemMeta hasteParchmentMeta = hasteParchment.getItemMeta();
		hasteParchmentMeta.setDisplayName(s);
		hasteParchment.setItemMeta(hasteParchmentMeta);
		return hasteParchment;
	}
	private ItemStack speedParchment(String s) {
		ItemStack speedParchment = new ItemStack(Material.SUGAR);
		ItemMeta speedParchmentMeta = speedParchment.getItemMeta();
		speedParchmentMeta.setDisplayName(s);
		speedParchment.setItemMeta(speedParchmentMeta);
		return speedParchment;
	}

}


