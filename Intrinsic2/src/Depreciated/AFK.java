package Depreciated;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import main.Main;
import main.PlayerSave;

public class AFK {
	private Plugin plugin = Main.getPlugin(Main.class);
	public static int afkInMins = 5;
	
	public AFK(Main main) {
		afkInMins = plugin.getConfig().getInt("AFK.timerInMins");
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			@Override
			public void run() {
				for(PlayerSave playerSave:Main.saves) {
					Player player = playerSave.player;
					
					
					if(playerSave.player.getLocation()==null) {
						playerSave.x = playerSave.player.getLocation().getBlockX();
						playerSave.y = playerSave.player.getLocation().getBlockY();
						playerSave.z = playerSave.player.getLocation().getBlockZ();
					}else {
						if(playerSave.x != playerSave.player.getLocation().getBlockX() | 
							playerSave.y != playerSave.player.getLocation().getBlockY() | 
							playerSave.z != playerSave.player.getLocation().getBlockZ()) {
							
							playerSave.x = playerSave.player.getLocation().getBlockX();
							playerSave.y = playerSave.player.getLocation().getBlockY();
							playerSave.z = playerSave.player.getLocation().getBlockZ();
							playerSave.afkMins = 0;
							
							if(playerSave.afk) 
								Bukkit.broadcastMessage(player.getDisplayName()+" has returned!");
							playerSave.afk = false;
							player.setPlayerListName(ChatColor.WHITE + player.getName());
							
						}else {
							if(playerSave.afkMins<Integer.MAX_VALUE-1) {
								playerSave.afkMins+= 1;
							}
							if(playerSave.afkMins==AFK.afkInMins) {
								Bukkit.broadcastMessage(player.getDisplayName()+" has gone afk.");
								playerSave.afk = true;
								player.setPlayerListName(ChatColor.GRAY + player.getName());
							}
						}
					}
				}
			}
		}, 1200, 1200);//1200 1200
	}

}