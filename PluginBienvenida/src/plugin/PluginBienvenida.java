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
		//5. crea una variable de tipo jugador y mete al jugador que entre al servidor
		Player p = event.getPlayer();
		String nombreJugador = p.getName();
		//Enviar el mensaje al jugador cuando entre al servidor
		p.getWorld().setPVP(true);
		    ItemStack flecha = new ItemStack(Material.ARROW);
			ItemStack palaOp = new ItemStack(Material.DIAMOND_SPADE);
			ItemStack arcoOp = new ItemStack(Material.BOW);
			ItemStack botasOp = new ItemStack(Material.DIAMOND_BOOTS);
			ItemStack legginsOp = new ItemStack(Material.DIAMOND_LEGGINGS);
			ItemStack cascoOp = new ItemStack(Material.DIAMOND_HELMET);
			ItemStack petoOp = new ItemStack(Material.DIAMOND_CHESTPLATE);
			ItemStack EspadaOp = new ItemStack(Material.DIAMOND_SWORD);
			ItemStack picoOp = new ItemStack(Material.DIAMOND_PICKAXE);
			ItemStack Arco = new ItemStack(Material.BOW);
			ItemStack ArcoT = new ItemStack(Material.BOW);
			ItemStack ArcoC = new ItemStack(Material.BOW);
			
			ItemMeta ArcoImC = ArcoC.getItemMeta();
			ItemMeta ArcoIm = Arco.getItemMeta();
			ItemMeta ArcoImT = ArcoT.getItemMeta();
			ItemMeta picoOpIm = picoOp.getItemMeta();
			ItemMeta palaOpIm = palaOp.getItemMeta();
			ItemMeta arcoOpIm = arcoOp.getItemMeta();
			ItemMeta espadaOpIm = EspadaOp.getItemMeta();
			ItemMeta cascoOpIm = cascoOp.getItemMeta();
			ItemMeta petoOpIm = petoOp.getItemMeta();
			ItemMeta leggingsOpIm = legginsOp.getItemMeta();
			ItemMeta botasOpIm = botasOp.getItemMeta();

			p.setGameMode(GameMode.SURVIVAL);

			p.sendMessage(verde + " Hola, amo " + nombreJugador);
			Inventory inventario = p.getInventory();

			//borra los items que no contienen prop
			ItemStack[] items = inventario.getContents();
			for(int j=0;j<items.length;j++){
				if(items[j]!= null && items[j].getItemMeta().getDisplayName()!=null && items[j].getItemMeta().getDisplayName().contains("prop")){

					items[j] = null;
				}
			}






			inventario.setContents(items);

			inventario.addItem(flecha);
			ArcoImC.setDisplayName(rojo +"Arco Constructor" + verde + " - propiedad de " + nombreJugador);
			ArcoC.setItemMeta(ArcoImC);
			ArcoC.addEnchantment(Enchantment.ARROW_INFINITE,1);
			inventario.addItem(ArcoC);

			ArcoImT.setDisplayName(rojo +"Arco Teletransportador" + verde + " - propiedad de " + nombreJugador);
			ArcoT.setItemMeta(ArcoImT);
			ArcoT.addEnchantment(Enchantment.ARROW_INFINITE,1);
			inventario.addItem(ArcoT);

			ArcoIm.setDisplayName(rojo +"Arco Destructor" + verde + " - propiedad de " + nombreJugador);
			Arco.setItemMeta(ArcoIm);
			Arco.addEnchantment(Enchantment.ARROW_INFINITE,1);
			inventario.addItem(Arco);

			picoOpIm.setDisplayName(rojo + "Pico Op " + verde + "- propiedad de " + nombreJugador);
			picoOp.setItemMeta(picoOpIm);
			picoOp.addEnchantment(Enchantment.SILK_TOUCH,1);
			picoOp.addEnchantment(Enchantment.DIG_SPEED,3);
			picoOp.addEnchantment(Enchantment.DURABILITY,3);
			inventario.addItem(picoOp);


			palaOpIm.setDisplayName(rojo + "Pala Op " + verde + "- propiedad de " + nombreJugador);
			palaOp.setItemMeta(palaOpIm);
			palaOp.addEnchantment(Enchantment.SILK_TOUCH,1);
			palaOp.addEnchantment(Enchantment.DIG_SPEED,3);
			palaOp.addEnchantment(Enchantment.DURABILITY,3);
			inventario.addItem(palaOp);

			arcoOpIm.setDisplayName(rojo + "Arco Op " + verde + "- propiedad de " + nombreJugador);
			arcoOp.setItemMeta(arcoOpIm);
			arcoOp.addEnchantment(Enchantment.ARROW_DAMAGE, 4);
			arcoOp.addEnchantment(Enchantment.ARROW_FIRE,1);
			arcoOp.addEnchantment(Enchantment.ARROW_KNOCKBACK,2);
			arcoOp.addEnchantment(Enchantment.ARROW_INFINITE,1);
			arcoOp.addEnchantment(Enchantment.DURABILITY,3);
			inventario.addItem(arcoOp);

			espadaOpIm.setDisplayName(rojo + "Espada Op " + verde + "- propiedad de " + nombreJugador);
			EspadaOp.setItemMeta(espadaOpIm);
			EspadaOp.addEnchantment(Enchantment.FIRE_ASPECT, 2);
			EspadaOp.addEnchantment(Enchantment.DAMAGE_ALL,5);
			EspadaOp.addEnchantment(Enchantment.KNOCKBACK,2);
			EspadaOp.addEnchantment(Enchantment.LOOT_BONUS_MOBS,3);
			EspadaOp.addEnchantment(Enchantment.DURABILITY,3);
			inventario.addItem(EspadaOp);


			cascoOpIm.setDisplayName(rojo + "Casco de Iron Man " + verde + "- propiedad de " + nombreJugador);
			cascoOp.setItemMeta(cascoOpIm);
			cascoOp.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			cascoOp.addEnchantment(Enchantment.WATER_WORKER,1);
			cascoOp.addEnchantment(Enchantment.THORNS,2);
			cascoOp.addEnchantment(Enchantment.OXYGEN,1);
			cascoOp.addEnchantment(Enchantment.DURABILITY,3);
			inventario.addItem(cascoOp);


			petoOpIm.setDisplayName(rojo + "Peto de Iron Man " + verde + "- propiedad de " + nombreJugador);
			petoOp.setItemMeta(petoOpIm);
			petoOp.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			petoOp.addEnchantment(Enchantment.PROTECTION_PROJECTILE,1);
			petoOp.addEnchantment(Enchantment.THORNS,2);
			petoOp.addEnchantment(Enchantment.PROTECTION_FIRE,1);
			petoOp.addEnchantment(Enchantment.DURABILITY,3);
			inventario.addItem(petoOp);


			leggingsOpIm.setDisplayName(rojo + "Pantalon de Iron Man " + verde + "- propiedad de " + nombreJugador);
			legginsOp.setItemMeta(leggingsOpIm);
			legginsOp.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			legginsOp.addEnchantment(Enchantment.PROTECTION_PROJECTILE,1);
			legginsOp.addEnchantment(Enchantment.THORNS,2);
			legginsOp.addEnchantment(Enchantment.PROTECTION_FIRE,1);
			legginsOp.addEnchantment(Enchantment.DURABILITY,3);
			inventario.addItem(legginsOp);


			botasOpIm.setDisplayName(rojo + "Zapatos de Iron Man " + verde + "- propiedad de " + nombreJugador);
			botasOp.setItemMeta(botasOpIm);
			botasOp.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			botasOp.addEnchantment(Enchantment.PROTECTION_FALL,1);
			botasOp.addEnchantment(Enchantment.THORNS,2);
			botasOp.addEnchantment(Enchantment.PROTECTION_FIRE,1);
			botasOp.addEnchantment(Enchantment.DURABILITY,3);
			inventario.addItem(botasOp);

			




		

		
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
