package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import util.FakePlayer;

public class HuevosPlugin extends JavaPlugin implements Listener{
	int esUnoUOtro = 0;
	@Override
	public boolean onCommand(CommandSender sender,
			org.bukkit.command.Command command, String label, String[] args) {
		Player p = (Player)sender;
		if (command.getName().equalsIgnoreCase("huevo")) {
			ItemStack huevo = new ItemStack(Material.EGG);
			ItemMeta huevoMeta = huevo.getItemMeta();
			huevoMeta.setDisplayName(ChatColor.GOLD + "Huevo del poder de " + p.getName());
			huevoMeta.addEnchant(Enchantment.DAMAGE_ALL, 10000, false);
			
			huevo.setItemMeta(huevoMeta);
			p.setItemInHand(huevo);
			FakePlayer.SpawnNPC(p.getLocation(), "NPC");

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


			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player p = event.getPlayer();
				ItemStack itemenmano = p.getItemInHand();

				if (itemenmano != null) {
					ItemMeta im = itemenmano.getItemMeta();
					if(im != null && im.getDisplayName() != null && im.getDisplayName().contains("poder")){

						esUnoUOtro = 1; 
					}


				}
			}
		}

	}
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		if(esUnoUOtro == 1) {
			if(event.getEntity() instanceof Player) {
				Location l = event.getEntity().getLocation();
				l.setY(l.getY() + 20);
				esUnoUOtro = 0;
			}


		}

	}

}


