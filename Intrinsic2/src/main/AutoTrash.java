package main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AutoTrash implements Listener {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			//
			if(cmd.getName().equalsIgnoreCase("autoTrash")) {
				Main.getSave(player).autoTrashList.add( Material.matchMaterial(args[0]) );
				return true;
			}
		}
		return false;
	}
	
	@EventHandler
	public void EntityPickupItemEvent(LivingEntity entity, Item item, int remaining) {
		Bukkit.broadcastMessage("onPickup");
		if(entity.getType() == EntityType.PLAYER) {
			Player player = (Player)entity;
			PlayerSave save = Main.getSave(player);
			for(Material mat:save.autoTrashList)
				if(mat == item.getItemStack().getType()) {
					item.eject();
					player.sendMessage("ITEM DELETED");
				}
		}
	}
}
