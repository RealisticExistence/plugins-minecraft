package plugin;

import java.util.ArrayList;
import java.util.List;

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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import util.Utils;

public class HuevosPlugin extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);



	}
	List<Player> jugadores= null;
	boolean IsActivated = false;
	List<Player> equipo1 = null;
	List<Player> equipo2 = null;
	int esUnoUOtro = 0;
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}

		Player p = (Player)sender;
		jugadores = p.getWorld().getPlayers();

		if (command.getName().equalsIgnoreCase("survival")) {
			 IsActivated = false;
			final World w = Bukkit.createWorld(new WorldCreator("Survival"));
			for(int i = 0; i < jugadores.size(); i++) {
				Player p2 = jugadores.get(i);
				p2.teleport(w.getSpawnLocation());
				Utils.borrarInventario(p2);
				p2.sendMessage("Teneis 5 minutos para equiparos");
			}
			w.setPVP(false);
			IsActivated = false;
			equipo1 = CrearEquipo1(jugadores);
			equipo2 = CrearEquipo2(jugadores);
			StringBuilder sb1 = new StringBuilder();

			for(Player player : equipo1){
				sb1.append(player.getDisplayName() + " ");
			}
			
			StringBuilder sb2 = new StringBuilder();
			for(Player player : equipo2){
				sb2.append(player.getDisplayName() + " ");
			}

			for(int i = 0; i < jugadores.size(); i++){
				Player p3 = jugadores.get(i);
				p3.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Equipos: Equipo Vegetta: " + sb1.toString() + "Equipo Willy: " + sb2.toString());

			}
new BukkitRunnable() {
	
	@Override
	public void run() {
		w.setPVP(true);
		for(int i = 0; i < jugadores.size(); i++){
			Player p = jugadores.get(i);
			p.sendMessage(ChatColor.RED+"EL PvP esta activado! Luchad!");
             IsActivated = true;
		}
		
		
	}
}.runTaskLater(this, 6000);



		}


		return false;
	}
@EventHandler
public void onPlayerDamage(EntityDamageByEntityEvent e){
	if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
		if(equipo1.contains(e.getDamager()) && equipo1.contains(e.getEntity())) {
			e.getDamager().sendMessage(ChatColor.RED + "No puedes pegar a tus colegas");
			e.setCancelled(true);
		}
		else if(equipo2.contains(e.getDamager()) && equipo2.contains(e.getEntity())) {
			e.getDamager().sendMessage(ChatColor.RED + "No puedes pegar a tus colegas");
			e.setCancelled(true);
		}
	}
}
	private List<Player> CrearEquipo1(List<Player> players) {
		List<Player> toReturn = new ArrayList<Player>();

		if(players.size() > 1) {
			for(int i = 0; i < players.size()/2; i++){
				Player p = players.get(i);
				ItemStack vegetta = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta imVegetta = (SkullMeta) vegetta.getItemMeta();
				imVegetta.setOwner("VegettaGaymer");
				imVegetta.setDisplayName(ChatColor.BLUE + "Team Vegetta");
				vegetta.setItemMeta(imVegetta);
				p.getInventory().setHelmet(vegetta);
				toReturn.add(p);
			}
		}
		return toReturn;
	}

	
		
	

public List<Player> CrearEquipo2(List<Player> players) {
	List<Player> toReturn = new ArrayList<Player>();
	if(players.size() > 1) {
		for(int i = players.size()/2; i < players.size(); i++){
			Player p = players.get(i);
			ItemStack willy = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta imWilly = (SkullMeta) willy.getItemMeta();
			imWilly.setOwner("TheWillyrex");
			imWilly.setDisplayName(ChatColor.BLUE + "Team Willy");
			willy.setItemMeta(imWilly);
			p.getInventory().setHelmet(willy);
			toReturn.add(p);
		}
	}
	return toReturn;
}
	@EventHandler
	public void onPlayerBreakBlock(BlockBreakEvent e) {
		if(jugadores.contains(e.getPlayer()) && e.getBlock().getType() == Material.GLASS){
			e.setCancelled(true);
			
			
		}

}
}


