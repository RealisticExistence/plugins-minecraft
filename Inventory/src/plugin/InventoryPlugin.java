Fbopackage plugin;

import javax.swing.text.html.parser.Entity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.DoubleChest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InventoryPlugin extends JavaPlugin implements Listener{

	//Variable Chatcolor para el chat
	Location pvpLoc = null;
	ChatColor rojo = ChatColor.RED;
	ChatColor bold = ChatColor.BOLD;
	ItemStack libro = null;
	boolean IsOpen = false;
	boolean IsInLobby = false;
	boolean IsOpenKit = false;
	boolean IsInPvP = false;
	ItemStack diam = null;
	ItemStack nether = null;
	ItemStack kitD = null;
	ItemStack damKit = null;
	ItemStack kit = null;
	Inventory i = null;
	Inventory i2 = null;
	Location lobbyLoc = null;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@EventHandler
	public void onPlayerLogIn(PlayerJoinEvent event){

		//variable Player para represetntar al player que se meta en el server
		Player p = event.getPlayer();
		
	
		//Variable del Nombre del jugador o la variable p /\
		String NombreJugador = p.getName();
		lobbyLoc = p.getLocation();

		libro = new ItemStack(Material.BOOK);
		ItemMeta imLibro = libro.getItemMeta();
		imLibro.setDisplayName(rojo + "Travel");	
		libro.setItemMeta(imLibro);
		p.getInventory().addItem(libro);
		kit = new ItemStack(Material.PAPER);
		ItemMeta imKit  = kit.getItemMeta();
		imKit.setDisplayName(ChatColor.GREEN + "Kit Selection");	
		kit.setItemMeta(imKit);
		p.getInventory().addItem(libro);
		lobbyLoc.setY(14);
		lobbyLoc.setX(597.500);
		lobbyLoc.setZ(-194.500);
		p.teleport(lobbyLoc);
		IsInLobby = true;
		IsInPvP = false;
		//p.getWorld().spawnEntity(arg0, arg1)
	}

	@EventHandler
	public void onPlayerLogIn(PlayerInteractEvent event){
		
		//variable Player para represetntar al player que se meta en el server
		Player p = event.getPlayer();
		//Variable del Nombre del jugador o la variable p /\
		String NombreJugador = p.getName();
		int Espacios = 9;//tiene que ser multiplo de nueve (9,18,27,36,45,...)
		diam = new ItemStack(Material.DIAMOND_SWORD);
		kitD = new ItemStack(Material.DIAMOND_HELMET);
		damKit = new ItemStack(Material.DIAMOND_SWORD);
		kitD.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		damKit.addEnchantment(Enchantment.DAMAGE_ALL, 4);
		ItemMeta diamIm = diam.getItemMeta();
		diamIm.setDisplayName(rojo + "" + bold + "PvP");
		diam.setItemMeta(diamIm);

		nether = new ItemStack(Material.NETHER_STAR);
		ItemMeta netherIm = diam.getItemMeta();
		netherIm.setDisplayName(rojo + "" + bold + "Lobby");
		nether.setItemMeta(netherIm);

		if(p.getItemInHand().getType() == Material.BOOK && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {

			IsOpen = true;
			i = Bukkit.createInventory(p, Espacios, ChatColor.GOLD + "Travel");
			ItemStack[] contenido = new ItemStack[Espacios];
			contenido[0] = new ItemStack(diam);
			contenido[1] = new ItemStack(nether);
			i.setContents(contenido);
			p.openInventory(i);
			p.sendMessage("Travel Abierto");	

		}
		if(p.getItemInHand().getType() == Material.PAPER && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {


			i2 = Bukkit.createInventory(p, Espacios, ChatColor.GOLD + "Kit");
			IsOpenKit = true;
			ItemStack[] contenidoKit = new ItemStack[Espacios];
			contenidoKit[0] = new ItemStack(kitD);
			contenidoKit[1] = new ItemStack(damKit);
			i2.setContents(contenidoKit);
			p.openInventory(i2);
			p.sendMessage("Kit Selection Abierto");	


		}

		//p.getWorld().spawnEntity(arg0, arg1)



	}
	@EventHandler
	public void onPlayerInventory(InventoryClickEvent e) {

		pvpLoc = e.getWhoClicked().getLocation();
		e.getWhoClicked().getWorld().setPVP(true);
		pvpLoc.setY(78);
		pvpLoc.setX(339.500);
		pvpLoc.setZ(-178.500);
		if(e.getInventory().getName() == i.getName() && IsOpen && e.getCurrentItem().getType() == diam.getType()) {	
			e.getWhoClicked().teleport(pvpLoc); 
			borrarInventario(e.getWhoClicked());
			e.getWhoClicked().getInventory().addItem(libro);
			e.getWhoClicked().getInventory().addItem(kit);
			IsInLobby = false;
			IsInPvP = true;

			e.setCancelled(true);


		}if(i2 != null) {
			if(e.getInventory().getName() == i2.getName() && IsOpenKit && e.getCurrentItem().getType() == damKit.getType()) {
				borrarInventario(e.getWhoClicked());
				dardamKit(e.getWhoClicked());
				e.getWhoClicked().getInventory().addItem(libro);
				e.getWhoClicked().getInventory().addItem(kit);



				e.setCancelled(true);
			}
			else if(e.getInventory().getName() == i2.getName() && IsOpenKit && e.getCurrentItem().getType() == kitD.getType()) {
				borrarInventario(e.getWhoClicked());
				e.getWhoClicked().getInventory().addItem(libro);
				e.getWhoClicked().getInventory().addItem(kit);
				darKitD(e.getWhoClicked());

				e.setCancelled(true);
			}
		}
		if(e.getInventory().getName() == i.getName() && IsOpen && e.getCurrentItem().getType() == nether.getType()) {
			Location lobbyLoca = e.getWhoClicked().getLocation();
			lobbyLoca.setY(14);
			lobbyLoca.setX(597.500);
			lobbyLoca.setZ(-194.500);
			e.getWhoClicked().teleport(lobbyLoca);
			borrarInventario(e.getWhoClicked());
			e.getWhoClicked().getInventory().remove(Material.DIAMOND_SWORD);
			e.getWhoClicked().getInventory().remove(Material.IRON_SWORD);
			e.getWhoClicked().getInventory().remove(Material.BOOK);
			e.getWhoClicked().getInventory().remove(kit);
			e.getWhoClicked().getInventory().addItem(libro);
			e.setCancelled(true);
			IsInLobby = true;
			IsInPvP = false;
		}


	}


	@EventHandler
	public void onPlayerDie(PlayerDeathEvent e) {
		if(IsInLobby) {
			e.getEntity().setHealth(20);
		}
		
		

	}
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		

		if(IsInPvP) {
			
			pvpLoc = e.getPlayer().getLocation();
			pvpLoc.setY(78);
			pvpLoc.setX(339.500);
			pvpLoc.setZ(-178.500);
			e.getPlayer().teleport(pvpLoc);
			e.getPlayer().getInventory().addItem(libro);
			e.getPlayer().getInventory().addItem(kit);
		}
		
		

	}

	

	private void borrarInventario(HumanEntity e) {
		e.getInventory().clear();
		e.getInventory().setBoots(null);
		e.getInventory().setChestplate(null);
		e.getInventory().setLeggings(null);
		e.getInventory().setHelmet(null);


	}
	private void darKitD(HumanEntity e) {

		e.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET));
		e.getInventory().addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
		e.getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
		e.getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS));
		e.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
		for(int h = 0; h<63; h++) {
			e.getInventory().addItem(new ItemStack(Material.COOKED_BEEF));
		}

	}
	private void dardamKit(HumanEntity e) {

		e.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
		e.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
		e.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
		e.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
		ItemStack swordDam = new ItemStack(Material.DIAMOND_SWORD);
		swordDam.addEnchantment(Enchantment.DAMAGE_ALL, 4);
		e.getInventory().addItem(swordDam);
		for(int h = 0; h<63; h++) {
			e.getInventory().addItem(new ItemStack(Material.COOKED_BEEF));
		}

	}
}  