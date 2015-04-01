package minecraftPlugin;

import java.util.ArrayList;

import net.minecraft.server.v1_8_R1.EnumParticle;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TechSparks extends JavaPlugin{
	private boolean activo;

	@Override
	public void onEnable(){

	}


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("techtalents")){
			if (sender instanceof Player) {
				Player player = (Player) sender;
				final Location location = player.getWorld().getSpawnLocation();

				createFireworks(location);


				return true;
			}
		}
		return false;
	}

	private void createFireworks(final Location location) {
		final ArrayList<ArrayList<Vec3>> fireworks = new ArrayList<ArrayList<Vec3>>();
		fireworks.add(TechGenerator.getTech(this,0,0, "plugins/techsparks/BeCoderText.png"));
		fireworks.add(TechGenerator.getTech(this,0,0, "plugins/techsparks/BeMakerText.png"));
		fireworks.add(TechGenerator.getTech(this,0,0, "plugins/techsparks/BeGamerText.png"));
		fireworks.add(TechGenerator.getTech(this,0,0, "plugins/techsparks/TechLetras.png"));

		randomize(fireworks);

		for(int i = 0; i<fireworks.size() ;i++){
			final ArrayList<Vec3> techLogo = fireworks.get(i);
			final int xIni = location.getBlockX();
			final int yIni = location.getBlockY();
			final int zIni = location.getBlockZ();

			new BukkitRunnable() {
				@Override
				public void run() {
					for(int i=0;i<techLogo.size();i++){

						location.setX(xIni + techLogo.get(i).x);
						//location.setZ(zIni + 50);
						location.setY(yIni + 60+ techLogo.get(i).y);

						ParticleEffects.sendToLocation(EnumParticle.FIREWORKS_SPARK , location, 0, 0, 0, 0, 2, 0);

					}
					location.setX(xIni);
					location.setZ(zIni);
					location.setY(yIni);
				}
			}.runTaskLater(this, 20L+90*i);//runTaskTimer(this, 20L+90*i, 10L);


		}
	}


	private void randomize(ArrayList<ArrayList<Vec3>> fireworks) {
		int cantidad = fireworks.size();

		for(int i=0; i<10*cantidad;i++){
			int ran = (int) (cantidad*Math.random());
			fireworks.add(fireworks.remove(ran));

		}
	}
}