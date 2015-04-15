package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin implements Listener{
	Material m = null;
	private boolean activo = false;


	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}



	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;

		}
		Player p = (Player) sender;
		if(p.isOp()) {
			if(command.getName().equalsIgnoreCase("worldsystem")){
				if(args[0] != null && args.length > 0) {
					if(args[0].equalsIgnoreCase("create")) {
						if(args[1] != null && args.length > 1) {
							Bukkit.createWorld(new WorldCreator(args[1]));
							p.sendMessage("Generando mundo...");



						}else{
							p.sendMessage(ChatColor.RED + "Debes poner un parametro /worldsystem create [nombre]");
						}
					}else if(args[0].equalsIgnoreCase("teleport")) {
						if(args[1] != null && args.length > 1) {
							if(Bukkit.getWorld(args[1]) != null) {
								World world = Bukkit.getWorld(args[1]);
								if(args[2]!=null){
									Player pOther = Bukkit.getPlayer(args[2]);
									if(pOther != null){
										pOther.teleport(world.getSpawnLocation());
									}
									else{
										p.teleport(world.getSpawnLocation());
									}
									
								}
								
							}
							else{
								p.sendMessage(ChatColor.RED + "Debes poner un mundo valido");
							}

						}else{
							p.sendMessage(ChatColor.RED + "Debes poner un parametro /worldsystem teleport [mundo]");
						}
					}


				}
				else{
					p.sendMessage(ChatColor.RED + "Tienes que elegir una opción del WorldSystem");
				}




			}





		}
		else{
			p.sendMessage(ChatColor.RED + "No puedes ejecutar este comando");
		}
		return false;
	}










}
