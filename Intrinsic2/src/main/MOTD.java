package main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class MOTD {

	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		String name = player.getCustomName();
		if(name == null)
			name = player.getDisplayName();
		
		event.setJoinMessage(player.getCustomName() + ChatColor.YELLOW + " has joined the game");
		if(event.getPlayer().getWorld().isGameRule("doPatrolSpawning")) event.getPlayer().sendMessage("Pillager patrols are enabled"); else event.getPlayer().sendMessage("Pillager patrols are disabled");
		player.sendMessage("The difficulty is "+world.getDifficulty());
		player.sendMessage("Servers are expensive, help by donating @https://paypal.me/Soul327");
		sendMessage(player, "Servers are expensive, help by donating", "https://paypal.me/Soul327");
		
	}
	public void sendMessage(Player player, String message, String url) {
		Bukkit.getServer().dispatchCommand(
				Bukkit.getConsoleSender(),
				"/tellraw " + player.getName() +
				" {text:\"" + message + "\",clickEvent:{action:open_url,value:\"" +
						url + "\"}}");
}
}