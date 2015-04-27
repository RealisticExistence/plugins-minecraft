package plugin;




import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class CastlePlugin extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	String ent = null;
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {

		if(!(sender instanceof Player)) {
			return false;
		}
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("castlecreator")) {
			ItemStack castleCreator = new ItemStack(Material.POISONOUS_POTATO);
			ItemMeta im = castleCreator.getItemMeta();
			im.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD+"Castle Creator");
			castleCreator.setItemMeta(im);
			p.getInventory().addItem(castleCreator);

		}

		return false;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR ||e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getItemInHand() != null){
				if(p.getItemInHand().getItemMeta() != null){
					if(p.getItemInHand().getItemMeta().getDisplayName() != null){
						if(p.getItemInHand().getItemMeta().getDisplayName().contains("Castle")){
							Location loc = construirCastillo(p.getLocation(),50,50,5,3,3,10);
							p.teleport(loc);

						}

					}

				}
			}

		}


	}

	private Location construirCastillo(Location loc, int anchoCastillo, int largoCastillo, int altoCastillo, int anchoTorre, int largoTorre, int altoTorre) {


		Material castleMaterial = Material.getMaterial(98);

		int xP = loc.getBlockX();
		int yP = loc.getBlockY();
		int zP = loc.getBlockZ();

		for(int x = 0; x <= anchoCastillo; x++){
			for(int y = 0; y <= altoCastillo; y++){
				for(int z = 0; z <= largoCastillo; z++){
					Block b = loc.getWorld().getBlockAt(x+xP, yP, z+zP);
					b.setType(castleMaterial);

					if(z == 48 || z == 2 || z == 0 || z == 50){
						if(!(y == altoCastillo && x%2 == 1)){
							Block b2 = loc.getWorld().getBlockAt(x+xP, yP+y, z+zP);
							b2.setType(castleMaterial);
						}
					}else if((z == 49 || z == 1) && y < altoCastillo){
						Block b2 = loc.getWorld().getBlockAt(x+xP, yP+y, z+zP);
						b2.setType(castleMaterial);
					}

					if(x == 48 || x == 2 || x == 0 || x == 50){
						if(!(y == altoCastillo && (z%2 == 1))){
							Block b2 = loc.getWorld().getBlockAt(x+xP, yP+y, z+zP);
							b2.setType(castleMaterial);
						}
					}else if((x == 49 || x == 1) && y < altoCastillo){
						Block b2 = loc.getWorld().getBlockAt(x+xP, yP+y, z+zP);
						b2.setType(castleMaterial);
					}


				}
			}
		}




		//foso
		for(int x = -3; x <= anchoCastillo+3; x++){
			for(int z = -3; z <= largoCastillo+3; z++){

				if(!(x >0 && x < anchoCastillo && z > 0 && z < largoCastillo)){
					Block b2 = loc.getWorld().getBlockAt(x+xP, yP-1, z+zP);
					b2.setType(Material.LAVA);
				}

			}
		}

		//torre 0,0
		construirTorre(loc);

		//torre ac, 0
		loc.setX(loc.getX()+anchoCastillo);
		construirTorre(loc);

		//torre ac, lc
		loc.setZ(loc.getZ()+largoCastillo);
		construirTorre(loc);

		//torre 0, lc
		loc.setX(loc.getX()-anchoCastillo);
		construirTorre(loc);



		loc.add(anchoCastillo/2, 1, -largoCastillo/2);

		return loc;

	}

	private void construirTorre(Location loc) {
		int radio = 4;
		int radio2 = 5;
		int altura = 12;
		Material castleMaterial = Material.getMaterial(98);
		int xP = loc.getBlockX();
		int yP = loc.getBlockY();
		int zP = loc.getBlockZ();

		for(int x = -radio; x <= radio; x++){

			for(int y = 0; y <= altura; y++){
				for(int z = -radio; z <= radio; z++){
					if((int)Math.sqrt(x*x+z*z) < radio){
						Block b = loc.getWorld().getBlockAt(x+xP, y+yP, z+zP);

						b.setType(castleMaterial);

					}
				}	
			}	
		}


		int cuenta = 0;
		boolean almena = true;
		for(int x = -radio2; x <= radio2; x++){

			for(int y = altura; y <= altura+3; y++){
				for(int z = -radio2; z <= radio2; z++){
					if((int)Math.sqrt(x*x+z*z) <= radio2){
						if(y < altura+3) {
							Block b2 = loc.getWorld().getBlockAt(x+xP, y+yP, z+zP);

							b2.setType(castleMaterial);


						}
						else if((int)Math.sqrt(x*x+z*z) == radio2){
							
							if(almena){
								Block b2 = loc.getWorld().getBlockAt(x+xP, y+yP, z+zP);
								b2.setType(castleMaterial);
							
							}
							if(almena){
								almena = false;
							}
							else{
								almena = true;
							}
						}




					}
				}
			}	
		}	
	}

}



