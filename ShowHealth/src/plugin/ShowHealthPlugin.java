package plugin;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import plugin.utils.Utils;

public class ShowHealthPlugin extends JavaPlugin implements Listener {
	
	
	public static HashMap<String, Scoreboard> playerScoreboards = new HashMap<String, Scoreboard>();
	
	boolean isPlaced = false;
	Material luckyblockM = null;
	public static Scoreboard board = null;
	static String pname = "";
	static Player player = null;
	public static String boardName = "";
	static Score money = null;
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	  @EventHandler
	    public void onPlayerJoin(PlayerJoinEvent ev){
	        Player p = ev.getPlayer();
	       pname = p.getName().toLowerCase();
	 
	        if (!playerScoreboards.containsKey(pname))
	            create(p, pname, ChatColor.BLUE + "Player Statistic");
	        
	        
	        ///hhhhhhhhhhhhhh
	        set(p);
	 
	    }
	
	  
	  
	  
	    public static HashMap<String, Score> moneyscore = new HashMap<String, Score>();
	 
	 
	    public static void create(Player p, String BoardName, String BoardDisplayName){
	         pname = p.getName().toLowerCase();
	        player = p;
	        boardName = BoardName;
	        ScoreboardManager manager = Bukkit.getScoreboardManager();
	        board = manager.getNewScoreboard();
	 
	        Objective objective = board.registerNewObjective(boardName, "dummy");
	 
	        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	        objective.setDisplayName(BoardDisplayName);
	 
	         money = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Money" + ChatColor.GRAY + ":"));
	 
	        money.setScore(0); // On Create just set the Integer of Money to 0
	 
	        moneyscore.put(pname, money);
	 
	        playerScoreboards.put(pname, board);
	    }
	    @EventHandler
	    public void onPlayerMove(EntityDeathEvent ev){
	    	
	    	if(ev.getEntity().getKiller() instanceof Player) {
		    	if(pname != null && board != null ) {
		    		if(board != null) {
		    			if(board.getObjective(boardName) != null) {
		    				if(board.getObjective(boardName).getScore(ChatColor.GREEN + "Money" + ChatColor.GRAY + ":") != null) {
		    					board.getObjective(boardName).getScore(ChatColor.GREEN + "Money" + ChatColor.GRAY + ":").setScore( board.getObjective(boardName).getScore(ChatColor.GREEN + "Money" + ChatColor.GRAY + ":").getScore() + 1);
		    	    
		    	    		    money.setScore(board.getObjective(boardName).getScore(ChatColor.GREEN + "Money" + ChatColor.GRAY + ":").getScore());
		    				}
		    				else{
		    	    			player.sendMessage("null score");
		    				}
		    			}
		    			else{
			    			player.sendMessage("null objetive");
		    			}
		    		}
		    		else{
		    			player.sendMessage("null board");
		    			
		    		}
		    		 
		    	}
		    	else{
		    		player.sendMessage("NullPointerExeption");
		    	}
	    	}
	    	
	   
	    }
	   
	 
	    public static void set(Player p){
	 
	        String pname = p.getName().toLowerCase();
	        p.setScoreboard(playerScoreboards.get(pname));
	 
	    }

		}
	
