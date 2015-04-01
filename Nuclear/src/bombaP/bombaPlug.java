package bombaP;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class bombaPlug extends JavaPlugin implements Listener{

	private Location localizacion;
	ChatColor rojo = ChatColor.RED;
	ChatColor negrita = ChatColor.BOLD;
	protected float FUERZA_EXPLOSION = 20;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);	
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if (command.getName().equalsIgnoreCase("bomba_nuclear")) {
			if(sender instanceof Player) {
				if(args.length>0){
					try{
						FUERZA_EXPLOSION = Integer.parseInt(args[0]);
						if(FUERZA_EXPLOSION > 100 || FUERZA_EXPLOSION < 1){
							((Player) sender).sendMessage("debes poner un numero entre 1 y 100");
						}
						
					}catch(Exception e){
						((Player) sender).sendMessage("Debes poner un numero de potencia");
					};
				}
				else  {
					FUERZA_EXPLOSION = 5;
				}




				ItemStack bomba = new ItemStack(Material.TNT);
				ItemMeta imBomba = bomba.getItemMeta();

				imBomba.setDisplayName(rojo + "" + negrita + "Bomba Nuclear-Mucho Danger-Potencia: " + FUERZA_EXPLOSION);
				bomba.setItemMeta(imBomba);
				((Player) sender).getInventory().addItem(bomba);
			}
		}
		return false;

	}

	@EventHandler
	public void onBlockPlace(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {

			Location l = e.getPlayer().getTargetBlock(null, 255).getLocation();
			final Player p = e.getPlayer();
			if(e.getPlayer().getItemInHand() == null || e.getPlayer().getItemInHand().getItemMeta() == null ||
					e.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null){
				return;
			}
			if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("Nuclear")){
				e.getPlayer().setItemInHand(null);
				localizacion = l;

				new BukkitRunnable() {


					@Override
					public void run() {
						if(localizacion != null){
							localizacion.getWorld().createExplosion(localizacion, FUERZA_EXPLOSION);
							localizacion = null;
						}
					}
				}.runTaskLater(this, 1000);


				new BukkitRunnable() {

					int cuenta=0;
					@Override
					public void run() {
						if(localizacion != null){
							if (p.getLocation().distance(localizacion) < FUERZA_EXPLOSION) {
								String mensaje = rojo + "" + negrita + " Quedan " + ((1000.0-(cuenta*100.0))*(6.0/100.0)) + " segundos para la explosion!! Huye!!!";
								p.sendMessage(mensaje);
							}

							cuenta++;
							if (cuenta == 10) {
								cuenta = 0;
							}

						}
					}
				}.runTaskTimer(this, 0, 100);


			}
		}

	}



}
