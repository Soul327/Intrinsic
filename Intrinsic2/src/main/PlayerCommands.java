package main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommands {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			//
			if(cmd.getName().equalsIgnoreCase("openender") & args.length>0) {
				if(player.isOp()){
					try {
						player.openInventory(Bukkit.getPlayer(args[0]).getEnderChest());
					}catch(Exception e) {
						player.sendMessage("Player \""+args[0]+"\" not found.");
					}
					return true;
				}else {
					player.sendMessage("You do not have the required permission.");
					return true;
				}
			}
			//
			if(cmd.getName().equalsIgnoreCase("openinv") & args.length>0) {
				if(player.isOp()){
					try {
						if(Bukkit.getPlayer(args[0])!=player) player.openInventory(Bukkit.getPlayer(args[0]).getInventory());
						else player.sendMessage("You can not open your own inventory.");
					}catch(Exception e) {
						player.sendMessage("Player \""+args[0]+"\" not found.");
					}
					return true;
				}else {
					player.sendMessage("You do not have the required permission.");
					return true;
				}
			}
			//
			if(cmd.getName().equalsIgnoreCase("locatePlayer") & args.length>0) {
				try { 
					Player p = Bukkit.getPlayer(args[0]);
					player.sendMessage(p.getDisplayName()+" X:"+(int)p.getLocation().getX()+" Y:"+(int)p.getLocation().getY()+" Z:"+(int)p.getLocation().getZ());
				}catch(Exception e) {
					player.sendMessage("Player \""+args[0]+"\" not found.");
				}
				return true;
			}
		}
		return false;
	}
}
