package plugin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Skull;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ImagePlugin extends JavaPlugin implements Listener{

	private Location localizacion;
	ChatColor rojo = ChatColor.RED;
	ChatColor negrita = ChatColor.BOLD;
	ItemStack cabeza = null;
	protected float FUERZA_EXPLOSION = 20;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);	
	}
	BufferedImage img = null;
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("cabeza")) {
			try {
				img = ImageIO.read(new File("plugins/imagen/hola.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int x = 0; x < img.getWidth(); x++){
				for(int y = 0; y < img.getHeight(); y++){
					if(img.getRGB(x, y) == Color.WHITE.getRGB()){
						img.setRGB(x, y, Color.TRANSLUCENT);
						
					}
					else{
						org.bukkit.Color color = org.bukkit.Color.fromRGB(img.getRGB(x, y));
						
						DyeColor dyeColor = DyeColor.getByColor(color);
						ItemStack lana = new ItemStack(Material.WOOL, 1, (byte) dyeColor.getData());
						
						Location center = p.getLocation();
						center.add(x, y, 0);
						center.getBlock().setType(lana.getType());
						
					}
					
					
				}
				
			}
			
			
		}
		
		
		return false;
		
	}


}
