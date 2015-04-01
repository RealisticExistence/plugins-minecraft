package plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class luckyBlocksPlugin extends JavaPlugin implements Listener {
	boolean isPlaced = false;
	Material luckyblockM = null;
	Player p = null;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		p = (Player) sender;
		if(command.getName().equalsIgnoreCase("luckyblocks")) {

			luckyblockM = Material.IRON_BLOCK;
			int length = 10;
			Location l = p.getLocation();
			for(int i = 0; i < length ; i++) {
				
				l.add(5, 0, 0);
				p.getWorld().getBlockAt(l).setType(luckyblockM);
				isPlaced = true;
			}

		}

		return false;
	}
	@EventHandler
	public void onPlayerBlock(BlockBreakEvent e) {
		if(isPlaced) {
			p.sendMessage("IsPlaced");
			if(e.getBlock().getType() == luckyblockM) {
				p.sendMessage("BLock Broken");
				int rand = (int)Math.floor(Math.random()*(8-0)+0);
				if(rand == 0) {
					p.getWorld().spawnEntity(p.getLocation(), EntityType.GIANT);
					
                    p.sendMessage("0");
				}
				else if(rand == 1) {
					p.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
					p.sendMessage("1");
				}
				else if(rand == 2) {
					Location l = p.getLocation();
					l.setY(0);
					p.teleport(l);
					p.sendMessage("2");
				}
				else if(rand == 3) {
					p.getInventory().addItem(new ItemStack(Material.GOLD_CHESTPLATE));
					p.getInventory().addItem(new ItemStack(Material.GOLD_LEGGINGS));
					p.getInventory().addItem(new ItemStack(Material.GOLD_BOOTS));
					p.getInventory().addItem(new ItemStack(Material.GOLD_HELMET));
					p.sendMessage("3");
				}
				else if(rand == 4) {
					p.setHealth(0);
					p.sendMessage("4");
				}
				else if(rand == 5) {

					p.sendMessage("5");
					p.getWorld().spawnEntity(p.getLocation(), EntityType.IRON_GOLEM);
				}
				else if(rand == 6) {
					p.getInventory().clear();
					p.getInventory().setArmorContents(null);
					p.getWorld().setTime(18000);
					for(int j = 0; j<30; j++) {
						p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
						p.sendMessage("6");
					}
				}
				else if(rand == 7) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 360, 1));
					p.sendMessage("7");
					
				}	
				else if(rand == 8) {
					p.getWorld().createExplosion(p.getLocation(), 10);
					p.sendMessage("8");

				}	

			}

		}

	}
}
