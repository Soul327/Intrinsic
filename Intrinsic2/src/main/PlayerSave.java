package main;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerSave {
	
	public int compassNum = 0;
	public double bankAmount = 0;
	public Player player;
	private Plugin plugin = Main.getPlugin(Main.class);
	
	//AFK Vars
	public boolean afk = false;
	public int afkMins = 0, x,y,z;
	
	public PlayerSave(Player player) {
		this.player = player;
		player.setCustomName(player.getName());
		loadConfig();
		updateSave();
	}
	public void loadConfig() {
		plugin.reloadConfig();
		if(plugin.getConfig().getBoolean("CustomChat.customName")) 
			player.setCustomName( plugin.getConfig().getString("Users." + this.player.getUniqueId() + ".customName") );
		
	}
	public void updateSave() {
		if(plugin.getConfig().getBoolean("CustomChat.customName")) 
			plugin.getConfig().set("Users." + player.getUniqueId() + ".customName", player.getCustomName());
		plugin.saveConfig();
	}
}
