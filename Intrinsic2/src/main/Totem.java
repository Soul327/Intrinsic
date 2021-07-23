package main;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class Totem implements Listener {
	List<Entity> protectedEntities = new ArrayList<Entity>();
//	Plugin plugin = Main.getPlugin(Main.class);
	Plugin plugin = null;
	public Totem(Plugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if(event.getRightClicked().getType() == EntityType.WOLF) protect((Wolf)event.getRightClicked(), player);
		if(event.getRightClicked().getType() == EntityType.CAT) protect((Cat)event.getRightClicked(), player);
		if(event.getRightClicked().getType() == EntityType.PARROT) protect((Parrot)event.getRightClicked(), player);
	}
	public <T extends Animals & Tameable> void protect(T e, Player player) {
		if( e.isTamed() )
			if( e.getOwner() == player) {
				if(protectedEntities.indexOf(e) == -1) { //Wolf is not protected
					if(player.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
						protectedEntities.add(e);
						if(player.getGameMode() != GameMode.CREATIVE) player.getInventory().setItemInMainHand(null);
						player.sendMessage(e.getName()+" is now wearing the " + ChatColor.YELLOW + "Totem of Undying.");
						save();
					}
				} else if(player.getInventory().getItemInMainHand().getType() == Material.AIR & player.isSneaking()) {//Wolf is protected
					protectedEntities.remove(e);
					if(player.getGameMode() != GameMode.CREATIVE) player.getInventory().setItemInMainHand( new ItemStack(Material.TOTEM_OF_UNDYING) );
					player.sendMessage(e.getName()+" is no longer wearing the " + ChatColor.YELLOW + "Totem of Undying.");
					save();
				}
			}
	}
	
	
	public <T extends Animals & Tameable> void saveEntity(EntityDamageEvent event, T e) {
		if(e.getHealth() - event.getDamage()<=1) {
			Player owner = (Player) e.getOwner();
			owner.sendMessage(e.getName()+" was saved!");
			
			event.setDamage(0);
			e.getWorld().playSound(e.getLocation(), Sound.ITEM_TOTEM_USE, 6F, 1F);
			e.getWorld().playEffect(e.getLocation(), Effect.BOW_FIRE, 5);
			
			setSitting(e, 1);
			
			if(owner.getBedSpawnLocation() != null)
				e.teleport(owner.getBedSpawnLocation());
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
				e.teleport( Util.getCenter( loc ) );
			}
			event.setCancelled(true);
		}
	}
	
	public void setSitting(Entity e, int value) {
		boolean v = false;
		if(value == 1) v = true;
		if(value == -1) {
			if(e.getType() == EntityType.WOLF) v = !((Wolf)e).isSitting();
			if(e.getType() == EntityType.CAT) v = !((Cat)e).isSitting();
			if(e.getType() == EntityType.PARROT) v = !((Parrot)e).isSitting();
					 
		}
		if(e.getType() == EntityType.WOLF) ((Wolf)e).setSitting(v);
		if(e.getType() == EntityType.CAT) ((Cat)e).setSitting(v);
		if(e.getType() == EntityType.PARROT) ((Parrot)e).setSitting(v);
	}
	
	@EventHandler
	public void onDamageEvent(EntityDamageEvent event) {
		Entity e = event.getEntity();
		
		for(Entity w:protectedEntities)
			if(e == w) {
				if(e.getType() == EntityType.CAT) saveEntity(event,(Cat)e);
				if(e.getType() == EntityType.WOLF) saveEntity(event,(Wolf)e);
				if(e.getType() == EntityType.PARROT) saveEntity(event,(Parrot)e);
			}
		
		//Use totems from player's inventory to prevent death
		if(event.getEntity().getType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			PlayerInventory inv = player.getInventory();
			if(player.getHealth() - event.getDamage() < 1) //Check if player would die
				//Check if they have a totem
				//Swap second hand item with totem if they have one
				if( player.getInventory().contains( new ItemStack(Material.TOTEM_OF_UNDYING) ) )
					//Check if player already has totem in second hand
					if( player.getInventory().getItemInOffHand().getType() != Material.TOTEM_OF_UNDYING ) {
						int locOfTotem = inv.first(Material.TOTEM_OF_UNDYING);
						ItemStack temp = inv.getItemInOffHand();
						inv.setItemInOffHand( inv.getItem(locOfTotem) );
						inv.setItem(locOfTotem, temp);
					}
		}
	}
	
	public void check(Entity e) {
		if(e==null) Bukkit.broadcastMessage("NULL");
		else {
			Bukkit.broadcastMessage(e.getName());
			Bukkit.broadcastMessage(e.getUniqueId()+"");
			Bukkit.broadcastMessage(e.getUniqueId().toString()+"");
		}
	}
	public void save() {
		List<String> list = new ArrayList<String>();
		
		for(Entity e:protectedEntities)
			list.add(e.getUniqueId().toString());
		
		plugin.getConfig().set("PetTotems.wearingTotem", list);
		plugin.saveConfig();
	}
	public void load() {
		protectedEntities = new ArrayList<Entity>();
		plugin.reloadConfig();
		List<String> list =  plugin.getConfig().getStringList("PetTotems.wearingTotem");
		for(String i:list) {
			Bukkit.broadcastMessage(i);
			protectedEntities.add( getEntityByUniqueId( UUID.fromString(i) ) );
		}
	}
	public Entity getEntityByUniqueId(UUID uniqueId) {
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (entity.getUniqueId().equals(uniqueId))
					return entity;
			}
		}

		return null;
	}
}
