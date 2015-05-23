package plugin;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

import me.cybermaxke.materialapi.material.CustomMaterial;

public class LaEspada extends CustomMaterial{

	public LaEspada(String id, Material material) {
		super(id, material);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onBlockBreak(BlockBreakEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockDamage(BlockDamageEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockInteract(PlayerInteractEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockPlaced(BlockPlaceEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHit(EntityDamageByEntityEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHold(PlayerItemHeldEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInteract(PlayerInteractEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInteractEntity(PlayerInteractEntityEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
