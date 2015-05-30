package plugin;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Construccion {

	ArrayList<Vec3> vectores = new ArrayList<Vec3>();
	private Plugin plugin;


	public Construccion(Plugin p){
		this.plugin = p;
	}

	public void addVec3(Vec3 v){
		vectores.add(v);
	}
	
	public void addVec3(int x, int y, int z, Material m){
		vectores.add(new Vec3(x,y,z,m));
	}
	
	
	
	public void construir(final Location loc){
		BukkitRunnable br = new BukkitRunnable() {
			int i = 0;
			@Override
			public void run() {
				if(i<vectores.size()){
					Vec3 v = vectores.get(i);
					loc.getWorld().getBlockAt(v.x, v.y, v.z).setType(v.m);
					i++;
				}else{
					cancel();
				}
			}
		};
		br.runTaskTimer(plugin, 0L, 1L);

	}


}
