package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class FireStaffJorge extends JavaPlugin implements Listener{

	@Override
	public boolean onCommand(CommandSender sender,
			org.bukkit.command.Command command, String label, String[] args) {
		Player p = (Player)sender;
		if (command.getName().equalsIgnoreCase("firestaff")) {
			ItemStack varafuego = new ItemStack(Material.BLAZE_ROD);
			ItemMeta varaMeta = varafuego.getItemMeta();
			varaMeta.setDisplayName(ChatColor.GOLD + "Vara de fuego de " + p.getName());
			varafuego.setItemMeta(varaMeta);
			p.setItemInHand(varafuego);

		}
		if (command.getName().equalsIgnoreCase("TotalRay")) {
			ItemStack varatotal = new ItemStack(Material.STICK);
			ItemMeta varatotalMeta = varatotal.getItemMeta();
			varatotalMeta.setDisplayName(ChatColor.GOLD + "vara TOTAL de " + p.getName());
			varatotal.setItemMeta(varatotalMeta);
			p.setItemInHand(varatotal);


		}


		return false;
	}

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);



	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {


		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK){
			Player p2 = event.getPlayer();
			ItemStack itemenmano2 = p2.getItemInHand();

			if (itemenmano2 != null) {
				ItemMeta im2 = itemenmano2.getItemMeta();
				if(im2 != null && im2.getDisplayName() != null && im2.getDisplayName().contains("fuego")){

					p2.launchProjectile(LargeFireball.class,p2.getEyeLocation().getDirection());
				}

			}
		}
		else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = event.getPlayer();
			ItemStack itemenmano = p.getItemInHand();

			if (itemenmano != null) {
				ItemMeta im = itemenmano.getItemMeta();
				if(im != null && im.getDisplayName() != null && im.getDisplayName().contains("fuego")){
					Player p3 = event.getPlayer(); 
					int radio = 20;
					
					 
					
					for(int j = 0; j < 10; j++) {
						
						Location locRayo = p3.getTargetBlock(null,255).getLocation();
						double rand = Math.random();
						double rand2 = Math.random();
						locRayo.add(radio*rand, 0, radio*rand2);
						p3.getWorld().strikeLightning(locRayo);

					}



				}
				else if(im != null && im.getDisplayName() != null && im.getDisplayName().contains("TOTAL")) {

					Player p3 = event.getPlayer(); 
					int radio = 20;
					for(int i = 0; i < radio ; i++) {
						for(int j = 0; j < radio; j++) {
							Location locRayo = p3.getTargetBlock(null,255).getLocation();
							locRayo.add(j, 0, i);
							p3.getWorld().strikeLightning(locRayo);

						}

					}
				}

			}

		}
	}


}


