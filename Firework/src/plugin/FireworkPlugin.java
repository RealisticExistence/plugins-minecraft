package plugin;

import java.util.Random;

import javax.swing.text.html.parser.Entity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FireworkPlugin extends JavaPlugin implements Listener{
	
	BukkitRunnable br;
	
	//Variable Chatcolor para el chat
	ChatColor rojo = ChatColor.RED;
	Type type = null;
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, final String[] args) {
		final Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("firework")) {
			if(args[0] != null) {
				if(args[0].equalsIgnoreCase("start")) {

					br = new BukkitRunnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub



							
								
								if(args[1] != null) {
									Firework f =  (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
									FireworkMeta imf = f.getFireworkMeta();
									switch(args[1]) {
									case "creeper":
										type = Type.CREEPER;
										break;
									case "ball":
										type = Type.BALL;
										break;
									case "burst":
										type = Type.BURST;
										break;
									case "largeball":
										type = Type.BALL_LARGE;
										break;
									case "star":
										type = Type.STAR;
										break;
									}

									Random r = new Random();  
									int r1i = r.nextInt(17) + 1;
									int r2i = r.nextInt(17) + 1;
									Color c1 = getColor(r1i);
									Color c2 = getColor(r2i);

									FireworkEffect effect = FireworkEffect.builder().flicker(true).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
									imf.addEffect(effect);
									int rp = r.nextInt(2) + 1;
									imf.setPower(rp);
									f.setFireworkMeta(imf);


								}


							
							
						}
					};
					br.runTaskTimer(this, 20, 10);
					
					
					
				}
				else if(args[0].equals("stop")) {
					if(br!=null){
						br.cancel();
					}
					return true;

				}
			}

			else{
				p.sendMessage(ChatColor.RED + "Add an action for firework /firework [action]");

			}


		}
		return false;
	}
	private Color getColor(int i) {
		Color c = null;
		if(i==1){
			c=Color.AQUA;
		}
		if(i==2){
			c=Color.BLACK;
		}
		if(i==3){
			c=Color.BLUE;
		}
		if(i==4){
			c=Color.FUCHSIA;
		}
		if(i==5){
			c=Color.GRAY;
		}
		if(i==6){
			c=Color.GREEN;
		}
		if(i==7){
			c=Color.LIME;
		}
		if(i==8){
			c=Color.MAROON;
		}
		if(i==9){
			c=Color.NAVY;
		}
		if(i==10){
			c=Color.OLIVE;
		}
		if(i==11){
			c=Color.ORANGE;
		}
		if(i==12){
			c=Color.PURPLE;
		}
		if(i==13){
			c=Color.RED;
		}
		if(i==14){
			c=Color.SILVER;
		}
		if(i==15){
			c=Color.TEAL;
		}
		if(i==16){
			c=Color.WHITE;
		}
		if(i==17){
			c=Color.YELLOW;
		}

		return c;
	}



}  