package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Worldeditp extends JavaPlugin implements Listener{

	//Variable Chatcolor para el chat
	ChatColor rojo = ChatColor.RED;
	int radio = 0;
	private Material material;
	private int radioP;
	private int altura;
	private Construccion c = new Construccion(this);

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(!(sender instanceof Player)){
			return false;		
		}
		Player p = (Player) sender;

		if (command.getName().equalsIgnoreCase("construir")) {
			if(args != null && args.length > 0 && args[0].equalsIgnoreCase("cupula")) {
				if(args.length > 1) {
					radio = Integer.parseInt(args[1]);
				}
				else{
					radio = 10;

				}

				construirCupula(p);
			}
			if(args != null && args.length > 0 && args[0].equalsIgnoreCase("cupulacompleta")) {

				construirCupulacompleta(p);
			}


			if(args != null && args.length > 0 && args[0].equalsIgnoreCase("piramide")){
				Location loc = p.getLocation();

				if(args.length > 1){
					radioP = Integer.parseInt(args[1]);

				}
				else{
					radioP = 10;
				}

				if(args.length > 2){
					material = Material.getMaterial(args[2].toUpperCase());
				}
				else{
					material = Material.SANDSTONE;
				}
				if(args.length > 3){
					altura = Integer.parseInt(args[3]);
				}
				else{
					altura = 10;
				}
				if(material != null){
					construirPiramide(material, radioP, p.getWorld(),p, altura, loc);
					c.construir(loc);
				}
			}



			return true;

		}

		return false;
	}



	private void construirPiramide(Material material2,  int radioP2, final World world, final Player p, int altura, final Location loc) {
		radioP2*=2;
		for( int i = 0; i <= radioP2; i = i+2){
			final int radiofinal = radioP2-i;
			
					construirAro(radiofinal,world,p, loc);
					loc.add(1, 1, 0);
					
		}

	}

	private void construirAro(int i,World w, Player p, Location loc) {
		for(int j = 0;j < i; j++){
			Vec3 v = new Vec3(loc.getBlockX()+j, loc.getBlockY(), loc.getBlockZ(), material);
			c.addVec3(v);
			if(j %8 == 0){
				Vec3 v2 = new Vec3(loc.getBlockX()+j, loc.getBlockY(), loc.getBlockZ(), Material.SEA_LANTERN);
				c.addVec3(v2);
			}
		}
		loc.add(i, 0, 0);
		for(int j = 0;j < i; j++){
			Vec3 v = new Vec3(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()+j, material);
			c.addVec3(v);
			if(j %8 == 0){
				Vec3 v2 = new Vec3(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()+j, Material.SEA_LANTERN);
				c.addVec3(v2);
			}
		}
		loc.add(0, 0, i);

		for(int j = 0;j < i; j++){
			Vec3 v = new Vec3(loc.getBlockX()-j, loc.getBlockY(), loc.getBlockZ(), material);
			c.addVec3(v);
			if(j %8 == 0){
				Vec3 v2 = new Vec3(loc.getBlockX()-j, loc.getBlockY(), loc.getBlockZ(), Material.SEA_LANTERN);
				c.addVec3(v2);
			}
		}
		loc.add(-i, 0, 0);

		for(int j = 0;j < i; j++){

			Vec3 v = new Vec3(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()-j, material);
			c.addVec3(v);			
			if(j %8 == 0){
				Vec3 v2 = new Vec3(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()-j, Material.SEA_LANTERN);
				c.addVec3(v2);
			}

		}
		loc.add(0, 0, -(i-1));

	}
	
	private void construirCupula(Player p) {
		final Location l = p.getLocation();
		final int lX = l.getBlockX();
		final int lY = l.getBlockY();
		final int lZ = l.getBlockZ();
		for(int i = -radio; i <= radio; i++) {
			for(int j = 0; j <= radio; j++) {
				for(int k = -radio; k <= radio; k++) {
					if((int)Math.sqrt((i*i)+(j*j)+(k*k)) == radio) {
						final int iF = i;
						final int jF = j;
						final int kF = k;
						new BukkitRunnable() {

							@Override
							public void run() {
								l.getWorld().getBlockAt(iF + lX, jF + lY, kF + lZ).setType(Material.GLASS);

							}
						}.runTaskTimer(this, 10L, 10L);


					}


				}


			}

		}

	}

	private void construirCupulacompleta(Player p) {
		Location l = p.getLocation();
		int radio = 100;
		int lX = l.getBlockX();
		int lY = l.getBlockY();
		int lZ = l.getBlockZ();
		for(int i = -radio; i <= radio; i++) {
			for(int j = -radio; j <= radio; j++) {
				for(int k = -radio; k <= radio; k++) {
					if((int)Math.sqrt((i*i)+(j*j)+(k*k)) == radio) {

						l.getWorld().getBlockAt(i + lX, j + lY, k + lZ).setType(Material.GLASS);;

					}


				}


			}

		}

	}








}  