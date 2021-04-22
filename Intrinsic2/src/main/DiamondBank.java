package main;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class DiamondBank implements Listener{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			PlayerSave save = Main.getSave(player);
			//
			if(cmd.getName().equalsIgnoreCase("deposit")) {
				ItemStack item = player.getInventory().getItemInMainHand();
				if(item.getType() == Material.DIAMOND) 
					save.bankAmount += item.getAmount();
				player.getInventory().setItemInMainHand( new ItemStack(Material.AIR) );
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("withdraw")) {
				if(args.length == 1) {
					int amount = Integer.parseInt(args[0]);
					if( amount < save.bankAmount ) {
					save.bankAmount -= amount;
					player.getWorld().dropItem( Util.getCenter(player.getLocation()) , new ItemStack(Material.DIAMOND, amount));
					}
				}
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("balance")) {
				player.sendMessage("Balance: "+save.bankAmount);
				return true;
			}
		}
		return false;
	}
}
