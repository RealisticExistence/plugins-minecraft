package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginBienvenida extends JavaPlugin implements Listener{

	//1. Crea una variable de tipo ChatColor con nombre: colorRojo que sea de color rojo
	ChatColor rojo = ChatColor.RED;
	//2. Crea una variable de tipo ChatColor con nombre: colorVerde que sea de color verde
	ChatColor verde = ChatColor.GREEN;
	//3. Crea una variable de tipo String con nombre: dueñoServidor con contenido: Tu nombre


	//4. Crea una variable de tipo String con nombre: mensaje con contenido: El mensaje de bienvenida


	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);

	}



	@EventHandler
	public void onInventoryDrag(PlayerMoveEvent event){



		Player p = event.getPlayer();
		if (p.getItemInHand().equals(Material.SKULL))p.kickPlayer("Expulsed for being so ugly!!!");
		if(p.getEquipment().getBoots() != null){
			boolean tengoBotas = false;
			if(p.getEquipment().getBoots().getItemMeta().getDisplayName() != null){
				tengoBotas = p.getEquipment().getBoots().getItemMeta().getDisplayName().contains("Iron Man");
			}
			if (tengoBotas) {
				if(!p.getAllowFlight()){
					p.setAllowFlight(true);
					p.sendMessage(rojo + "Fly activado!");
				}
			}
			if(!tengoBotas && p.getAllowFlight()){
				p.setAllowFlight(false);
				p.sendMessage(rojo + "Fly desactivado!");
			}


		}else if(p.getAllowFlight()){
			p.setAllowFlight(false);
			p.sendMessage(rojo + "Fly desactivado!");
		}


	}

	@EventHandler
	public void onPlayerLogIn(PlayerJoinEvent event){

if(event.getPlayer().isOp()) {
	util.Utils.DarKitAdmin(event.getPlayer());
}
else{
	event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Bienvenido "+ event.getPlayer().getName() + "!");
	event.getPlayer().setGameMode(GameMode.ADVENTURE);
}
	}
	@EventHandler
	public void onPlayerMove(PlayerDeathEvent e) {
		e.getEntity().setHealth(20);
	
	}
}
