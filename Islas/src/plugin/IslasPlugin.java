package plugin;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import util.Utils;

public class IslasPlugin extends JavaPlugin implements Listener {

	Location l = null;

	int xisla =  100;
	int yisla = 0;
	int zisla = 0;
	int rotacion = 36;
	double xx = 0;
	double zz = 0;
	Material piedra = null;
	Material tierra = null;
	Material cesped = null;
	int alturaTierra = 0;
	Player p = null;
	World w = null;
	boolean IsActivated = false;
	List<Player> equipo1 = null;
	List<Player> equipo2 = null;
	final ArrayList<String> playerNames = new ArrayList<String>();
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);

	}
	List<Player> jugadores= null;
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String label, String[] args) {
		piedra = Material.STONE;
		tierra = Material.DIRT;
		cesped = Material.GRASS;
		alturaTierra = 3;

		if(!(sender instanceof Player)) {
			return false;
		}
		p = (Player) sender;
		jugadores = p.getWorld().getPlayers();
		
		if(command.getName().equalsIgnoreCase("islas")) {


			p = (Player)sender;
			w = Bukkit.createWorld(new WorldCreator(Math.random()*10000 + ""));
			for(int i = 0;i<jugadores.size(); i++){
				jugadores.get(i).teleport(w.getSpawnLocation());
			}
			l = p.getLocation();

			xisla =  50;
			yisla = 0;
			zisla = 0;
			rotacion = 36;
			xx = l.getBlockX();
			zz = l.getBlockZ();
			final int yy = l.getBlockY();


			BukkitRunnable r = new BukkitRunnable() {
				int i = 0;
				@Override
				public void run() {
					if(i < 11) {
						double xp =  ((xisla*Math.cos(rotacion*i))- (zisla*Math.sin(rotacion*i)));
						double zp =  ((zisla*Math.cos(rotacion*i))- (xisla*Math.sin(rotacion*i)));

						l.setX(xp+xx );

						l.setZ(zp+zz );
						l.setY(yy+50);
						crearIsla(piedra,tierra,cesped,alturaTierra,p,l);
						if(i<jugadores.size()){
							playerNames.add(jugadores.get(i).getDisplayName());
								
							
							jugadores.get(i).teleport(l);
						}

						i++;
					}else{
						cancel();
					}


				}
			};
			r.runTaskTimer(this, 100, 120);














		}
		return false;
		
	}



	@EventHandler
	public void onPlayerDie(PlayerDeathEvent ev) {
		if(playerNames.contains(ev.getEntity().getDisplayName())) {
			playerNames.remove(ev.getEntity().getDisplayName());
			if(playerNames.size() == 1) {
				Bukkit.getServer().unloadWorld(w, true);
			}
		}

	}


	private void crearIsla(Material piedra, Material tierra, Material cesped, int alturaTierra, Player p, Location l) {




		World w = p.getWorld();
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();


		int radio = 5;
		for(int i=-radio;i<=radio;i++){
			for(int j=-radio;j<=0;j++){
				for(int k=-radio;k<=radio;k++){
					for(int o = -radio; o < radio; o++) {


					}

					int distanciaAlCentro = (int) Math.sqrt((i*i)+(j*j)+(k*k));
					if(distanciaAlCentro <= radio){

						if(j > -alturaTierra) {
							w.getBlockAt(i+x, j+y, k+z).setType(tierra);
						}else{
							w.getBlockAt(i+x, j+y, k+z).setType(piedra);
						}
						if(j == 0) {
							w.getBlockAt(i+x, j+y, k+z).setType(cesped);
						}
						if(j == -alturaTierra) {
							if(Math.random() > 0.5){
								w.getBlockAt(i+x, j+y, k+z).setType(tierra);
							}
						}

						if(Math.random() < (Math.pow(distanciaAlCentro, 5))/(float)(Math.pow(radio,5)) ){
							w.getBlockAt(i+x, j+y, k+z).setType(Material.AIR);
						}

					}
				}
			}
		}
		for(int i=-radio;i<=radio;i++){
			for(int j=-radio;j<=0;j++){
				for(int k=-radio;k<=radio;k++){
					if(estaSeparado(w,i+x, j+y, k+z)){
						w.getBlockAt(i+x, j+y, k+z).setType(Material.AIR);
					}
				}
			}
		}



		//crear el cofre
		int rand = (int)(Math.random()*(10)-5);

		int rand2 = (int)(Math.random()*(10)-5);

		w.getBlockAt(x+rand, y, z+rand2).setType(Material.CHEST);

		Chest c = (Chest) w.getBlockAt(x+rand, y, z+rand2).getState();
		Inventory i = c.getInventory();
		List<Material> materials = Arrays.asList(Material.DIAMOND_HELMET,Material.DIAMOND_CHESTPLATE,Material.DIAMOND_LEGGINGS,Material.DIAMOND_BOOTS,Material.IRON_HELMET,Material.IRON_CHESTPLATE,Material.IRON_LEGGINGS,Material.IRON_BOOTS,Material.GOLD_HELMET,Material.GOLD_CHESTPLATE,Material.GOLD_LEGGINGS,Material.GOLD_BOOTS,Material.CHAINMAIL_HELMET,Material.CHAINMAIL_CHESTPLATE,Material.CHAINMAIL_LEGGINGS,Material.CHAINMAIL_BOOTS,Material.LEATHER_HELMET,Material.LEATHER_CHESTPLATE,Material.LEATHER_LEGGINGS,Material.LEATHER_BOOTS,Material.DIAMOND_SWORD,Material.IRON_SWORD,Material.GOLD_SWORD,Material.STONE_SWORD,Material.WOOD_SWORD,Material.COOKED_BEEF,Material.APPLE,Material.DIAMOND_SWORD,Material.IRON_SWORD,Material.GOLD_SWORD,Material.STONE_SWORD,Material.WOOD_SWORD,Material.COOKED_BEEF,Material.APPLE,Material.DIAMOND_SWORD,Material.IRON_SWORD,Material.GOLD_SWORD,Material.STONE_SWORD,Material.WOOD_SWORD,Material.COOKED_BEEF,Material.APPLE,Material.DIAMOND_SWORD,Material.IRON_SWORD,Material.GOLD_SWORD,Material.STONE_SWORD,Material.WOOD_SWORD,Material.COOKED_BEEF,Material.APPLE, Material.BOW, Material.ARROW);


		int quantity = (int) (Math.random()*(5-10)+10);
		for(int n = 0; n <= quantity; n++) {
			int randMaterial = (int) (Math.random()*43);
			i.addItem(new ItemStack(materials.get(randMaterial)));
			for(int q = 0; q < 20; q++) {
				i.addItem(new ItemStack(Material.WOOD));

			}
			for(int q = 0; q < 16; q++) {
				i.addItem(new ItemStack(Material.EGG));

			}
			if(materials.get(randMaterial) == Material.ARROW) {
				for(int g = 0; g < 20; g++) {
					i.addItem(new ItemStack(Material.ARROW));

				}

			}


		}


	}






	private boolean estaSeparado(World w, int i, int j, int k) {
		Block b = w.getBlockAt(i, j, k);
		for(int l=-1;l<=1;l++){
			for(int m=-1;m<=1;m++){
				for(int n=-1;n<=1;n++){
					b = w.getBlockAt(i+l, j+m, k+n);
					if(b.getType() != Material.AIR)
						return false;
				}
			}
		}


		return true;
	}








}
