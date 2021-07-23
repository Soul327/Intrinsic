package Depreciated;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import main.Main;
import main.Util;

public class DogTotem implements Listener {
	
	ArrayList<Wolf> protectedWolfs = new ArrayList<Wolf>();
	Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void onRightClick(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if(event.getRightClicked().getType() == EntityType.WOLF) {
			Wolf wolf = (Wolf) event.getRightClicked();
			load(wolf.getUniqueId());
			if( wolf.isTamed() )
				if( wolf.getOwner() == player) {
					if(protectedWolfs.indexOf(wolf) == -1) { //Wolf is not protected
						if(player.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
							protectedWolfs.add(wolf);
							if(player.getGameMode() != GameMode.CREATIVE)
								player.getInventory().setItemInMainHand(null);
							player.sendMessage(wolf.getName()+" is now wearing the Totem of Undying.");
						}
					} else {//Wolf is protected
						if(player.getInventory().getItemInMainHand().getType() == Material.AIR & player.isSneaking()) {
							protectedWolfs.remove(wolf);
							if(player.getGameMode() != GameMode.CREATIVE)
								player.getInventory().setItemInMainHand( new ItemStack(Material.TOTEM_OF_UNDYING) );
							player.sendMessage(wolf.getName()+" is no longer wearing the Totem of Undying.");
						}
					}
					save(wolf);
					player.sendMessage( wolf.getUniqueId()+"" );
				}
					
		}
	}
	@EventHandler
	public void onDamageEvent(EntityDamageEvent event) {
		if(event.getEntity().getType() == EntityType.WOLF) {
			Wolf wolf = (Wolf) event.getEntity();
			load(wolf.getUniqueId());
			
			for(Wolf w:protectedWolfs)
				if(wolf == w) 
					if(wolf.getHealth() - event.getDamage()<=1) {
						Player owner = (Player) wolf.getOwner();
						owner.sendMessage(wolf.getName()+" was saved!");
						
						event.setDamage(0);
						wolf.getWorld().playSound(wolf.getLocation(), Sound.ITEM_TOTEM_USE, 6F, 1F);
						wolf.getWorld().playEffect(wolf.getLocation(), Effect.BOW_FIRE, 5);
						
						wolf.setSitting(true);
						if(owner.getBedSpawnLocation() != null)
							wolf.teleport(owner.getBedSpawnLocation());
						else {
							Location loc = Bukkit.getWorld("world").getSpawnLocation();
							loc.setY(1);
							while(loc.getBlockY() < 256) {
								if(loc.getBlock().getType() != Material.AIR) {
									loc.setY( loc.getY() + 1 );
									continue;
								}
								break;
							}
							wolf.teleport( Util.getCenter( loc ) );
						}
						event.setCancelled(true);
					}
		}
	}
	public void save(Wolf wolf) {
		plugin.getConfig().set("PetTotems." + wolf.getUniqueId() + ".wearingTotem", wolf);
		plugin.saveConfig();
		Bukkit.broadcastMessage("Saved "+wolf.getName());
	}
	public void load(UUID uuid) {
		plugin.reloadConfig();
		Object obj = plugin.getConfig().get("PetTotems." + uuid + ".wearingTotem");
		if(obj != null) {
			Wolf wolf = (Wolf)obj;
			protectedWolfs.add( wolf );
			Bukkit.broadcastMessage("Loaded "+wolf.getName());
		}
	}
}
