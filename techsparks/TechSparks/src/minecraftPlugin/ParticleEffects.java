package minecraftPlugin;

import net.minecraft.server.v1_8_R1.EnumParticle;
import net.minecraft.server.v1_8_R1.PacketPlayOutWorldParticles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ParticleEffects {
	
	/**
	 * @param effect         If true, particle distance increases from 256 to 65536.
	 * @param location       Position of the particle
	 * @param offsetX        This is added to the X position after being multiplied by random.nextGaussian()
	 * @param offsetY        This is added to the Y position after being multiplied by random.nextGaussian()
	 * @param offsetZ        This is added to the Z position after being multiplied by random.nextGaussian()
	 * @param particleData   The data of each particle
	 * @param count          Number of particles (The number of particles to create)
	 * @param data           Length depends on particle. ICON_CRACK, BLOCK_CRACK, and BLOCK_DUST have lengths of 2, the rest have 0.
	 */
	public static void sendToLocation(EnumParticle effect, Location location, float offsetX, float offsetY, float offsetZ, float particleData, int count, int data) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(effect, true, (float)location.getX(), (float)location.getY(), (float)location.getZ(), offsetX, offsetY, offsetZ, particleData, count, data);
		for(Player p : Bukkit.getOnlinePlayers()) {
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		}
	}
	
	/**
	 * @param effect         If true, particle distance increases from 256 to 65536.
	 * @param player         The player who sees the particle
	 * @param location       Position of the particle
	 * @param offsetX        This is added to the X position after being multiplied by random.nextGaussian()
	 * @param offsetY        This is added to the Y position after being multiplied by random.nextGaussian()
	 * @param offsetZ        This is added to the Z position after being multiplied by random.nextGaussian()
	 * @param particleData   The data of each particle
	 * @param count          Number of particles (The number of particles to create)
	 * @param data           Length depends on particle. ICON_CRACK, BLOCK_CRACK, and BLOCK_DUST have lengths of 2, the rest have 0.
	 */
	public static void sendToPlayer(EnumParticle effect, Player player, Location location, float offsetX, float offsetY, float offsetZ, float particleData, int count, int data) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(effect, true, (float)location.getX(), (float)location.getY(), (float)location.getZ(), offsetX, offsetY, offsetZ, particleData, count, data);
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static void generateCircleAroundPlayer(Player player, EnumParticle particle, int size, int points) {
		for (int i = 0; i < 360; i += 360/points) {
            double angle = (i * Math.PI / 180);
            double x = size * Math.cos(angle);
            double z = size * Math.sin(angle);
            Location loc = player.getLocation().add(x, 1, z);
            ParticleEffects.sendToLocation(EnumParticle.CRIT, loc, 0, 0, 0, 0, 1, 0);	        
		}
	}
	
	public static void generateCircleAroundPlayersHead(Player player, EnumParticle particle, int points) {
		double size = 0.3;
		for (int i = 0; i < 360; i += 360/points) {
            double angle = (i * Math.PI / 180);
            double x = size * Math.cos(angle);
            double z = size * Math.sin(angle);
            Location loc = player.getLocation().add(x, 2, z);
            ParticleEffects.sendToLocation(EnumParticle.CRIT, loc, 0, 0, 0, 0, 1, 0);	        
		}
	}
}