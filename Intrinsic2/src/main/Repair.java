package main;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class Repair implements Listener{
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = event.getItem();
		short amount = 10;
		if(player.isSneaking() & getTotalExperience(player)>0)
			if ( item != null )
				if( item.containsEnchantment(Enchantment.MENDING) )
					if ( action.equals( Action.RIGHT_CLICK_AIR ) || action.equals( Action.RIGHT_CLICK_BLOCK ) ) {
						if( getTotalExperience(player)<amount )
							amount = (short) getTotalExperience(player);
						
						Damageable pdmg = (Damageable)item.getItemMeta();
						if(pdmg.getDamage()>0) {
							pdmg.setDamage(pdmg.getDamage()-amount);
							item.setItemMeta(((ItemMeta)pdmg));
							player.giveExp(-amount);
						}
					}
	}
	public static int getTotalExperience(Player player) {
		return Math.round(player.getExp() * player.getExpToLevel()) + getTotalExperience(player.getLevel());
	}
	public static int getTotalExperience(int level) {
		int xp = 0;
		
		if (level >= 0 && level <= 15) {
			xp = (int) Math.round(Math.pow(level, 2) + 6 * level);
		} else if (level > 15 && level <= 30) {
			xp = (int) Math.round((2.5 * Math.pow(level, 2) - 40.5 * level + 360));
		} else if (level > 30) {
			xp = (int) Math.round(((4.5 * Math.pow(level, 2) - 162.5 * level + 2220)));
		}
		return xp;
}
}
