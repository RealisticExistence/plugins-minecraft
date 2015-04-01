package plugin;




import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class UltraZombieHardcorePluginClass extends JavaPlugin implements Listener{

//Variable Chatcolor para el chat
		ChatColor rojo = ChatColor.RED;
		
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent event){
		//variable Player para represetntar al player que se meta en el server
		LivingEntity e  = event.getEntity();
		if(e.getType() == EntityType.ZOMBIE) {
			int Rand = (int)Math.floor(Math.random()*(11-1)+1);
			if(Rand == 1) {
				e.getEquipment().setItemInHand(new ItemStack(Material.WOOD_SWORD));
				e.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));	
			e.setCustomName("Zombie Lvl: "+Rand);
			}
			else if(Rand == 2) {
				e.getEquipment().setItemInHand(new ItemStack(Material.WOOD_SWORD));
				e.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				e.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
			e.setCustomName("Zombie Lvl: "+Rand);
			}
			else if(Rand == 3) {
				e.getEquipment().setItemInHand(new ItemStack(Material.WOOD_SWORD));
				e.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				e.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
				e.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
				e.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
			e.setCustomName("Zombie Lvl: "+Rand);
			}
			else if(Rand == 4) {
				e.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
				e.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				e.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				e.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
				e.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
			e.setCustomName("Zombie Lvl: "+Rand);
			}
			else if(Rand == 5) {
				e.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
				e.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				e.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				e.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
				e.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
			e.setCustomName("Zombie Lvl: "+Rand);
			}
			else if(Rand == 6) {
				e.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
				e.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				e.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				e.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				e.getEquipment().setBoots(new ItemStack(Material.GOLD_HELMET));
			e.setCustomName("Zombie Lvl: "+Rand);
			}
			else if(Rand == 7) {
				e.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD));
				e.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
				e.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				e.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				e.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
			e.setCustomName("Zombie Lvl: "+Rand);
			}
			else if(Rand == 8) {
				e.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD));
				e.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
				e.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
				e.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				e.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
			e.setCustomName("Zombie Lvl: "+Rand);
			}
			else if(Rand == 9) {
				e.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD));
				e.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
				e.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
				e.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
				e.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
			e.setCustomName("Zombie Lvl: "+Rand);
			}
			else if(Rand == 10) {
				e.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
				e.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
				e.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
				e.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
				e.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
			e.setCustomName("Zombie Lvl: "+Rand);
			}
			
			
			
		
		}
	}
}  