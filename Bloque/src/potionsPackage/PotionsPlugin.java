package potionsPackage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PotionsPlugin extends JavaPlugin implements Listener{


	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = null;
		if(sender instanceof Player){
			p = (Player)sender;
		}
		if(p == null)return false;
		
		if (command.getName().equalsIgnoreCase("Explosion")){
			if(args.length > 0){
				try{
					int potencia = Integer.parseInt(args[0]);
					int x = Integer.parseInt(args[1]);
					int y = Integer.parseInt(args[2]);
					int z = Integer.parseInt(args[3]); 
					
					p.getWorld().createExplosion(x,y,z, potencia);
				}catch(NumberFormatException e){
					p.sendMessage(ChatColor.RED + "Debes meter un numero entre 1 y 100, y despues mete unas coordenadas [x][y][z]");
				}
				
				
			}
		}
		
		
		if(command.getName().equalsIgnoreCase("casa")){
			
			
			Location centro = p.getLocation();
			
			int x = centro.getBlockX();
			int y = centro.getBlockY();
			int z = centro.getBlockZ();
			
			
			
			int ancho = 50;
			int alto = 10;
			int largo = 50;
			Material pared = Material.SEA_LANTERN;
			Material suelo = Material.PRISMARINE;
			
			for(int i=-ancho/2;i<=ancho/2;i++){
				for(int j=-1;j<=alto;j++){
					for(int k=-largo/2;k<=largo/2;k++){
						Block b = p.getWorld().getBlockAt(x+i, y+j, z+k);
						boolean esPared = (i == -ancho/2 || i == ancho/2) || (k == -largo/2 || k == largo/2);
						boolean esTechoOSuelo = (j == -1 || j == alto);
						
						if(esPared){
							b.setType(pared);
						}
						else if(esTechoOSuelo){
							b.setType(suelo);
						}
						
					}
				}
			}
			
			
			
			return true;
		}
		return false;
	}

	
}