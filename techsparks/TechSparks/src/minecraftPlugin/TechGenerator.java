package minecraftPlugin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.bukkit.plugin.java.JavaPlugin;

public class TechGenerator {

	public static ArrayList<Vec3> getTech(JavaPlugin plugin, int i, int j, String fileName) {
		
		ArrayList<Vec3> result = new ArrayList<Vec3>();
		File archivo = new File(fileName);
		
		BufferedImage imagen;
		
        try{
            imagen = ImageIO.read(archivo);
        }
        catch(Exception e){
        	throw new RuntimeException(""+e.getMessage()+"   " + archivo.getAbsolutePath());
        }
        
        
        if(imagen == null){
        	return result;
        }

        procesar(imagen, result);
        
        return result;
		
	}

	private static void procesar(BufferedImage imagen, ArrayList<Vec3> result) {
		int ofsetX = -imagen.getWidth()/2;
		int ofsetY = -imagen.getHeight()/2;
        for (int x = 0; x < imagen.getWidth(); x++) {
            for (int y = 0; y < imagen.getHeight(); y++) {
                if (imagen.getRGB(x, y) != -16777216) {
					result.add(new Vec3(x+ofsetX,y+ofsetY,0));
                }
            }
        }
	}

}
