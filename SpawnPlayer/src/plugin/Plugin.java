package plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.v1_8_R1.PacketPlayInLook;
import net.minecraft.server.v1_8_R1.PacketPlayOutCamera;
import net.minecraft.server.v1_8_R1.PacketPlayOutSpawnEntityLiving;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin implements Listener{
	Material m = null;
	private boolean activo = false;


	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}



	@EventHandler
	public void Playerjoin(PlayerInteractEntityEvent e){
			CraftEntity ent = (CraftEntity) e.getRightClicked();
			PacketPlayOutCamera camera = new PacketPlayOutCamera( ent.getHandle());
			((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(camera);
			
		
	
		
		
		
		 
	}
	@EventHandler
	public void SneakEvent(PlayerToggleSneakEvent e){
		CraftEntity ent = (CraftEntity) e.getPlayer();
		PacketPlayOutCamera camera = new PacketPlayOutCamera( ent.getHandle());
		((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(camera);
	}
	@EventHandler
	public void EntityDie(){
		
	}
		
		








}
