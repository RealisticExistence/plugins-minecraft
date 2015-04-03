package plugin;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.RedstoneWire;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class SecurityPlugin extends JavaPlugin implements Listener {
 boolean SensorGiven = false;
 ItemStack sensor = new ItemStack(Material.DISPENSER);
 Player sender = null;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)){
			return false;
		}
		Player p = (Player) sender;
		sender = p;
		List<Player> playersWhitelisted = p.getWorld().getPlayers();
		boolean args0disponible = args.length > 0 && args[0] != null;
		if(command.getName().equalsIgnoreCase("security")){
			if(args0disponible){
				SensorGiven = args[0].equalsIgnoreCase("sensor");
				if(args[0].equalsIgnoreCase("help")){
					p.sendMessage(ChatColor.BLUE + "Security Help: ");
					p.sendMessage(ChatColor.GOLD + "/security sensor : if a player goes through, then it sends a redstone signal");
					p.sendMessage(ChatColor.GOLD + "/security trap : when it gets a redstone signal, it kills the intruder");
					p.sendMessage(ChatColor.GOLD + "/security config [PlayerName] : it lets the player go through the security system. WARNING: The security traps and sensors will kill you too, so configure it well!");
				}
				else if(args[0].equalsIgnoreCase("sensor")){
					
					ItemMeta sensorIm = sensor.getItemMeta();
					sensorIm.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Sensor - Property of: " + p.getName());
					sensor.setItemMeta(sensorIm);
					p.getInventory().addItem(sensor);
					
				}
			}
		}
		return false;
		
	}
	Dispenser d = null;
	Location l = null;
	@EventHandler
	public void onPlayerPlaceBlock(BlockPlaceEvent e){
		if(SensorGiven && e.getBlockPlaced() == sensor){
			 d = (Dispenser) e.getBlock();
			l = e.getBlock().getLocation();
		}
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Vector v = d.getLocation().getDirection();
		Player p = e.getPlayer();
	
			
		
		if(v.getMidpoint(v).distance(p.getVelocity()) < 5 && p != sender){
			if(l.getBlock().getType() != Material.AIR){
				if(l.add(0, 1, 0).getBlock().getType() == Material.REDSTONE_WIRE){
					RedstoneWire r = (RedstoneWire) l.add(0,1,0).getBlock();
					BlockState state = l.add(0, 1, 0).getBlock().getState();
				if(l.add(1, 0, 0).getBlock().getType() == Material.REDSTONE_TORCH_ON){
					state.setRawData(l.add(1, 0, 0).getBlock().getData());
				}
				else if(l.add(0, 0, 1).getBlock().getType() == Material.REDSTONE_TORCH_ON) {
					state.setRawData(l.add(0, 0, 1).getBlock().getData());
				}
				else{
					p.sendMessage(ChatColor.RED + "Place a redstone torch next to the sensor to make it work");
				}
					
				}
			}else{
				l = null;
			}
			
			
		}
	}

	

}
