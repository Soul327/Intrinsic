package main;

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
		
	}
}