package main;

import java.io.Console;
import java.util.Random;

import javax.swing.text.html.parser.Entity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main{
	public static void main(String [] args) {
		Console console = System.console();
		String respuesta = "";
		int num = 0;
		int num1 = 0;
		int num2 = 0;


		System.out.println("La Calculadora se esta inizializando...");
		System.out.println("dame un numero");
		
		while(respuesta.length() < 1) {

		
		}
		respuesta = console.readLine();
   if(respuesta.length() < 2 && respuesta.length() > 0){
			
			try {
				num = Integer.parseInt(respuesta);
			}
			catch(NumberFormatException e){
				System.out.println("Dame un numero valido");

			}
			 num1 = num;
			System.out.println("dame otro numero");
		}
   else{
	   System.out.println("dame un numero 10");
	   
   }
   while(respuesta.length() < 1) {
		
	
	}
   respuesta = console.readLine();
		if(respuesta.length() < 2 && respuesta.length() > 0) {
			

			try {
				num = Integer.parseInt(respuesta);
			}
			catch(NumberFormatException e){
				System.out.println("Dame un numero valido");

			}
			 num2 = num;
			System.out.println("dame una operacion");
		}
		while(respuesta.length() < 1) {
			System.out.flush();

		}
		if(respuesta.length() < 2 && respuesta.length() > 0) {
			 respuesta = console.readLine();
			if(respuesta.equals("*")) {
				System.out.println("La multiplicacion de " + num1 + " y " + num2 + " es " + num1*num2);
			}
			else if(respuesta.equals("/")) {
				System.out.println("La division de " + num1 + " y " + num2 + " es " + num1/num2);
			}
			else if(respuesta.equals("+")) {
				System.out.println("La suma de " + num1 + " y " + num2 + " es " + (num1+num2));
			}
			else if(respuesta.equals("-")) {
				System.out.println("La resta de " + num1 + " y " + num2 + " es " + (num1-num2));
			}
		}
	}




}  