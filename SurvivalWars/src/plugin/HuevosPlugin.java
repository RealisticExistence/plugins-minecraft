package plugin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
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

public class HuevosPlugin extends JavaPlugin implements Listener{
	int esUnoUOtro = 0;
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}

		Player p = (Player)sender;
		if (command.getName().equalsIgnoreCase("survival")) {
			World w = Bukkit.createWorld(new WorldCreator("SurvivalMap"));
			ArrayList<Player> jugadores = (ArrayList<Player>) p.getWorld().getPlayers();
			for(int i = 0; i < jugadores.size(); i++) {
				jugadores.get(i).teleport(w.getSpawnLocation());
			}
		}


		return false;
	}

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);



	}



}


