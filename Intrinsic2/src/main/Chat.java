package main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {
	
	@EventHandler
	public void chatFormat(AsyncPlayerChatEvent event){
		Player p = event.getPlayer();
		event.setFormat(p.getCustomName()+": " + format(event.getMessage()) );
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			//
			if(cmd.getName().equalsIgnoreCase("setName")) {
				if(args.length==0) { showFormat(player); return true;}
				if(args.length==1) {
					String name = args[0];
					name = format(name);
					player.setCustomName(name+ChatColor.GRAY);
					Bukkit.broadcastMessage(player.getCustomName()+" has changed their name.");
					Main.getSave(player).updateSave();
					return true;
				}
			}
			//
			if(cmd.getName().equalsIgnoreCase("getName")) {
				if(args.length==0) {
					for(Player p:Bukkit.getOnlinePlayers())
						player.sendMessage(p.getName() + " " +ChatColor.GRAY + p.getDisplayName());
					return true;
				}
				if(args.length==1) {
					for(Player p:Bukkit.getOnlinePlayers()) {
						if(removeFormat(p.getCustomName()).equalsIgnoreCase(args[0]))
							player.sendMessage(ChatColor.GRAY+p.getName());
						else
							player.sendMessage(p.getName() + " " +p.getCustomName());
							//player.sendMessage(p.getName() + " " + p.getDisplayName()+" "+removeFormat(p.getDisplayName()) +" "+args[0]);
					}
					return true;
				}
			}
		}
		return false;
	}
	public void showFormat(Player player) {
		player.sendMessage("#0 "+ChatColor.BLACK+"Black");
		player.sendMessage("#1 "+ChatColor.DARK_BLUE+"Dark Blue");
		player.sendMessage("#2 "+ChatColor.DARK_GREEN+"Dark Green");
		player.sendMessage("#3 "+ChatColor.DARK_AQUA+"Dark Aqua");
		player.sendMessage("#4 "+ChatColor.DARK_RED+"Dark Red");
		player.sendMessage("#5 "+ChatColor.DARK_PURPLE+"Dark Purple");
		player.sendMessage("#6 "+ChatColor.GOLD+"Gold");
		player.sendMessage("#7 "+ChatColor.GRAY+"Gray");
		player.sendMessage("#8 "+ChatColor.DARK_GRAY+"Dark Gray");
		player.sendMessage("#9 "+ChatColor.BLUE+"Blue");
		player.sendMessage("#a "+ChatColor.GREEN+"Green");
		player.sendMessage("#b "+ChatColor.AQUA+"Aqua");
		player.sendMessage("#c "+ChatColor.RED+"Red");
		player.sendMessage("#d "+ChatColor.LIGHT_PURPLE+"Light Purple");
		player.sendMessage("#e "+ChatColor.YELLOW+"Yellow");
		player.sendMessage("#f "+ChatColor.WHITE+"White");
		
		player.sendMessage("#l "+ChatColor.BOLD+"Bold");
		player.sendMessage("#m "+ChatColor.STRIKETHROUGH+"Strikethrough");
		player.sendMessage("#n "+ChatColor.UNDERLINE+"Underline");
		player.sendMessage("#o "+ChatColor.ITALIC+"Italic");
		player.sendMessage("#r "+ChatColor.RESET+"Reset");
	}
	public String format(String s) {
		//Colors
		s = s.replace("#0", ""+ChatColor.BLACK);
		s = s.replace("#1", ""+ChatColor.DARK_BLUE);
		s = s.replace("#2", ""+ChatColor.DARK_GREEN);
		s = s.replace("#3", ""+ChatColor.DARK_AQUA);
		s = s.replace("#4", ""+ChatColor.DARK_RED);
		s = s.replace("#5", ""+ChatColor.DARK_PURPLE);
		s = s.replace("#6", ""+ChatColor.GOLD);
		s = s.replace("#7", ""+ChatColor.GRAY);
		s = s.replace("#8", ""+ChatColor.DARK_GRAY);
		s = s.replace("#9", ""+ChatColor.BLUE);
		s = s.replace("#a", ""+ChatColor.GREEN);
		s = s.replace("#b", ""+ChatColor.AQUA);
		s = s.replace("#c", ""+ChatColor.RED);
		s = s.replace("#d", ""+ChatColor.LIGHT_PURPLE);
		s = s.replace("#e", ""+ChatColor.YELLOW);
		s = s.replace("#f", ""+ChatColor.WHITE);
		//Formatting
		s = s.replace("#k", ""+ChatColor.WHITE);
		s = s.replace("#l", ""+ChatColor.BOLD);
		s = s.replace("#m", ""+ChatColor.STRIKETHROUGH);
		s = s.replace("#n", ""+ChatColor.UNDERLINE);
		s = s.replace("#o", ""+ChatColor.ITALIC);
		s = s.replace("#r", ""+ChatColor.RESET);
		//s = s.replace("#s", ""+ChatColor.MAGIC);
		return s;
	}
	public String removeFormat(String s) {
		//Colors
		s = s.replace("#0", "");
		s = s.replace("#1", "");
		s = s.replace("#2", "");
		s = s.replace("#3", "");
		s = s.replace("#4", "");
		s = s.replace("#5", "");
		s = s.replace("#6", "");
		s = s.replace("#7", "");
		s = s.replace("#8", "");
		s = s.replace("#9", "");
		s = s.replace("#a", "");
		s = s.replace("#b", "");
		s = s.replace("#c", "");
		s = s.replace("#d", "");
		s = s.replace("#e", "");
		s = s.replace("#f", "");
		//Formatting
		s = s.replace("#k", "");
		s = s.replace("#l", "");
		s = s.replace("#m", "");
		s = s.replace("#n", "");
		s = s.replace("#o", "");
		s = s.replace("#r", "");
		s = s.replace("#s", "");
		return s;
	}
}
