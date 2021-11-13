package main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CustomComposter implements Listener {
	
	ArrayList<ComposterRecipe> recipes = new ArrayList<ComposterRecipe>();
	
	public CustomComposter() {
		//5%
		recipes.add(new ComposterRecipe(Material.STRING,.05));
		//30%
		recipes.add(new ComposterRecipe(Material.BAMBOO,.3));
		recipes.add(new ComposterRecipe(Material.ROTTEN_FLESH,.3));
		//recipes.add(new ComposterRecipe(Material.EGG,.3));
		recipes.add(new ComposterRecipe(Material.SPIDER_EYE,.3));
		//50%
		recipes.add(new ComposterRecipe(Material.TROPICAL_FISH,.5));
		recipes.add(new ComposterRecipe(Material.PUFFERFISH,.5));
		recipes.add(new ComposterRecipe(Material.SALMON,.5));
		recipes.add(new ComposterRecipe(Material.COD,.5));
		recipes.add(new ComposterRecipe(Material.BEEF,.5));
		recipes.add(new ComposterRecipe(Material.CHICKEN,.5));
		recipes.add(new ComposterRecipe(Material.MUTTON,.5));
		recipes.add(new ComposterRecipe(Material.PORKCHOP,.5));
		recipes.add(new ComposterRecipe(Material.RABBIT,.5));
		//65%
		recipes.add(new ComposterRecipe(Material.COOKED_BEEF,.65));
		recipes.add(new ComposterRecipe(Material.COOKED_CHICKEN,.65));
		recipes.add(new ComposterRecipe(Material.COOKED_COD,.65));
		recipes.add(new ComposterRecipe(Material.COOKED_MUTTON,.65));
		recipes.add(new ComposterRecipe(Material.COOKED_PORKCHOP,.65));
		recipes.add(new ComposterRecipe(Material.COOKED_RABBIT,.65));
		recipes.add(new ComposterRecipe(Material.COOKED_SALMON,.65));
		recipes.add(new ComposterRecipe(Material.HONEYCOMB,.65));
		//100%
		recipes.add(new ComposterRecipe(Material.POISONOUS_POTATO,1));
	}
	
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = event.getItem();
		Block block = event.getClickedBlock();
		
		if (action.equals(Action.RIGHT_CLICK_BLOCK))
			if (block.getType().equals(Material.COMPOSTER))
				for(int r=0;r<recipes.size();r++) {
					if ( item != null && item.getType() == recipes.get(r).input ) {
						if(Math.random()<recipes.get(r).chance)
							block.getWorld().dropItem(Util.getCenter(block.getLocation()), new ItemStack(recipes.get(r).output));
						if(player.getGameMode() != GameMode.CREATIVE) {
							item.setAmount(item.getAmount()-1);
							Material mat = block.getType();
							mat.createBlockData();
						}
					}
				}
	}
}

class ComposterRecipe {
	public Material input, output;
	public double chance = 1;
	
	public ComposterRecipe(Material input) {
		this.input = input;
		this.output = Material.BONE_MEAL;
	}
	public ComposterRecipe(Material input, double chance) {
		this.input = input;
		this.chance = chance;
		this.output = Material.BONE_MEAL;
	}
	public ComposterRecipe(Material input, Material output) {
		this.input = input;
		this.output = output;
	}
}