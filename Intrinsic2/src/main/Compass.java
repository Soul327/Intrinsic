package main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Compass implements Listener{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			//
			if(cmd.getName().equalsIgnoreCase("pointCompass")) {
				//
				if(args.length==1) {
					if(args[0].equalsIgnoreCase("bed")) pointToBed(player);
					if(args[0].equalsIgnoreCase("worldSpawn")) pointToWorldSpawn(player);
					return true;
				}
				//
				if(args.length==2) {
					if(args[0].equalsIgnoreCase("player")) {
						try {
						Player p = Bukkit.getPlayer(args[1]);
						player.setCompassTarget(p.getLocation());
						player.sendMessage("Compass is now pointing to "+p.getDisplayName());
						}catch(Exception e) {
							player.sendMessage("Player not found");
						}
						return true;
					}
				}
				//
				if(args.length==3) {
					try {
						pointToCords(player,
								new Location(
										Bukkit.getWorld("world"),
										Double.parseDouble(args[0]),
										Double.parseDouble(args[1]),
										Double.parseDouble(args[2])
										));
					}catch(Exception e) {
						player.sendMessage(e.getMessage());
					}
					return true;
				}
				//
				player.sendMessage("Missing args");
				
				return true;
			}
		}
		return false;
	}
	
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = event.getItem();
		if ( item != null && item.getType() == Material.COMPASS ) {
			if ( action.equals( Action.RIGHT_CLICK_AIR ) || action.equals( Action.RIGHT_CLICK_BLOCK ) ) {
					PlayerSave save = Main.getSave(player);
					if(save==null) {
						player.sendMessage("Player save not found, please relog");
					}else {
						int options = 2;
						
						save.compassNum++;
						if(save.compassNum>options+(Main.saves.size()-1)) save.compassNum = 0;
						update(save);
				}
			}
			
			if ( action.equals( Action.LEFT_CLICK_AIR ) || action.equals( Action.LEFT_CLICK_BLOCK ) ) {
					PlayerSave save = Main.getSave(player);
					if(save==null) {
						player.sendMessage("Player save not found, please relog");
					}else {
						int options = 2;
						if(save.compassNum>options+(Main.saves.size()-1)) save.compassNum = 0;
						update(save);
						
					}
				}
		}
	}
	public void update(PlayerSave save) {

		switch(save.compassNum) {
			case 0: pointToWorldSpawn(save.player); break;
			case 1: pointToBed(save.player); break;
			default: 
				//player.sendMessage("Getting player #"+ (save.compassNum-(options)) ); 
				pointToPlayer(save.player, Main.saves.get(save.compassNum-(2)).player); break;
		}
	}
	public void pointToCords(Player player, Location loc) {
		if(loc==null) return;
		player.setCompassTarget(loc);
		player.sendMessage("Compass is now pointing to X:"+loc.getBlockX()+" Y:"+loc.getBlockY()+" Z:"+loc.getBlockZ());
	}
	public void pointToBed(Player player) {
		try {
			player.setCompassTarget(player.getBedSpawnLocation());
			player.sendMessage("Compass is now pointing to your bed");
		}catch(Exception e) {
			player.sendMessage("No bed location found");
		}
	}
	public void pointToPlayer(Player player, Player otherPlayer) {
		if(player==null) { return; }
		if(otherPlayer==null) { player.sendMessage("Other player can not be null"); return; }
		player.setCompassTarget(otherPlayer.getLocation());
		player.sendMessage("Compass is now pointing to "+otherPlayer.getDisplayName());
	}
	public void pointToWorldSpawn(Player player) {
		player.setCompassTarget(Bukkit.getServer().getWorld("world").getSpawnLocation());
		player.sendMessage("Compass is now pointing to the world's spawn point");
	}
	public Player randPlayer() { return Bukkit.getOnlinePlayers().stream().skip((int) (Bukkit.getOnlinePlayers().size() * Math.random())).findFirst().orElse(null); }
	public Player randOtherPlayer(Player player) { 
		Player rand;
		do {
			rand = Bukkit.getOnlinePlayers().stream().skip((int) (Bukkit.getOnlinePlayers().size() * Math.random())).findFirst().orElse(null);
		}while(rand==player & Bukkit.getOnlinePlayers().size()>1);
		return rand;
	}
}