package packagePlot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PlotClass extends JavaPlugin implements Listener {

	private HashMap<String, Location> plotPlayer = new HashMap<String, Location>();
	private Location[] plots;

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			return false;
		}
		
		if(command.getName().equalsIgnoreCase("plotgenerator")) {
			generatePlots(sender, args);
		}




		return false;
	}

	private void generatePlots(CommandSender sender, String[] args) {
		int tamaño = Integer.parseInt(args[0]);
		int cantidad = Integer.parseInt(args[1]);
		int cantidad2 = Integer.parseInt(args[2]);


		


		Player p  = (Player) sender;
		Location l = p.getLocation();

		int numPlotsX = cantidad;
		int numPlotsZ = cantidad2;
		int numDePlots = numPlotsX*numPlotsZ;

		plots = new Location[numDePlots];


		int x = l.getBlockX();

		int y = l.getBlockY()-1;
		int z = l.getBlockZ();
		int zinicial = z;

		//variables tamaños
		int anchoC = 3;
		int anchoB = 1;
		int anchoP = tamaño;
		int anchoTotalPlot = 2*(anchoC + anchoB * 2) + anchoP;
		int anchot = anchoC + anchoB*2 + anchoP;

		//Variables materiales
		Material materialPlot = Material.GRASS;
		Material materialCamino = Material.STONE;
		Material materialBordillo = Material.STONE_SLAB2;
		int xfin = anchot * numPlotsX + anchoB*2 + anchoC;
		int zfin = anchot * numPlotsZ + anchoB*2 + anchoC;

		//camino y bordillo final
		for(int i = 0; i<xfin; i++) {
			l.getWorld().getBlockAt(x-1+i,y+1,z-1).setType(materialBordillo);
			l.getWorld().getBlockAt(x-1+i,y+1,z-2+zfin).setType(materialBordillo);
			for(int j = 0; j < anchoC; j++){
				if(i == 0 || i == xfin -1) {
					l.getWorld().getBlockAt(x-1+i,y+1,z-2+zfin -1 -j).setType(materialBordillo);
				}
				else {
					l.getWorld().getBlockAt(x-1+i,y,z-2+zfin -1 -j).setType(materialCamino);
				}
			}


		}

		for(int i = 0; i<zfin-anchoB*2 - anchoC; i++) {
			l.getWorld().getBlockAt(x-1,y+1,z+i).setType(materialBordillo);
			l.getWorld().getBlockAt(x-2+xfin,y+1,z+i).setType(materialBordillo);
			for(int j = 0; j < anchoC; j++){

				l.getWorld().getBlockAt(x-2+xfin -1 -j,y,z+i).setType(materialCamino);

			}


		}

		int plotPointer=0;
		// Generar plots
		for(int Px = 0; Px < numPlotsX; Px++) {

			if(Px > 0) {
				x = x + anchot;
			}

			for(int Pz = 0; Pz < numPlotsZ; Pz++) {
				if(Pz > 0) {
					z = z + anchot;
				}else{
					z = zinicial;
				}
				
				



				for(int j = 0;j < anchot; j++) {

					for(int i = 0;i < anchot; i++)  {


						boolean esCamino = (j>=0 && j < anchoC) || (i >= 0 && i < anchoC);

						boolean esBordillo = (j >= anchoC && (i == anchoC || i == anchot - 1)) ||
								(i >= anchoC && (j == anchoC || j == anchot - 1));
						boolean esPlot = (i >= anchoC + anchoB && i < anchot - 1 && j >= anchoC + anchoB && j < anchot - 1);

						if(esBordillo) {
							l.getWorld().getBlockAt(x+i,y+1,z+j).setType(materialBordillo);
						}

						if(esCamino) {
							l.getWorld().getBlockAt(x+i,y,z+j).setType(materialCamino);
						}
						if(esPlot) {
							if(plots[plotPointer] == null){
								plots[plotPointer] = l.getWorld().getBlockAt(x+i,y,z+j).getLocation();
								
							}
							l.getWorld().getBlockAt(x+i,y,z+j).setType(materialPlot);
							
						}
					}

				}
				plotPointer++;
				
			}
		}

		for(int i =0;i<plots.length;i++){
			if(plots[i] != null){
				p.sendMessage("" + plots[i].getX() + ", " + plots[i].getY() + ", " + plots[i].getZ());
			}
		}
	}



}
