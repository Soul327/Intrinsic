package main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import net.md_5.bungee.api.ChatColor;

public class CustomTrades implements Listener {
	
	@EventHandler
	public void onSpawn(CreatureSpawnEvent event) {
		if (event.getEntity() instanceof WanderingTrader) {
			WanderingTrader villager = (WanderingTrader) event.getEntity();
			setTrades(villager);
		}
	}
	
	public void setTrades(WanderingTrader villager) {
		String text = "The color trader has arrived!";
		switch((int)(Math.random()*3)) {
			case 0:
				text = "The color trader has arrived!";
				villager.setRecipes( getColorTrades() );
				break;
			
			case 1:
				text = "The horse tack trader has arrived!";
				villager.setRecipes( getHorseTrades() );
				break;
			
			case 2:
				villager.setRecipes( getHorseTrades() );
				break;
		
			case 3:
				villager.setRecipes( getHorseTrades() );
				break;
			
			default:
				text = "The wandering trader has arrived at "+villager.getLocation().getBlockX()+","+villager.getLocation().getBlockY()+"!";
				break;
		}
		Bukkit.getServer().broadcastMessage(ChatColor.BLUE + text);
	}

	public MerchantRecipe createRecipe(ItemStack item1, ItemStack item2, ItemStack reward, int disabled) {
		if(disabled == -1) disabled = Integer.MAX_VALUE;
		MerchantRecipe trade = new MerchantRecipe(reward, disabled);
		if(item1!=null) trade.addIngredient(item1);
		if(item2!=null) trade.addIngredient(item2);
		return trade;
	}

	public List<MerchantRecipe> getColorTrades() {
		List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.WHITE_DYE,16),      8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.RED_DYE,16),        8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.ORANGE_DYE,16),     8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.PINK_DYE,16),       8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.YELLOW_DYE,16),     8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.LIME_DYE,16),       8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.GREEN_DYE,16),      8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.LIGHT_BLUE_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.CYAN_DYE,16),       8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.BLUE_DYE,16),       8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.MAGENTA_DYE,16),    8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.PURPLE_DYE,16),     8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.BROWN_DYE,16),      8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.GRAY_DYE,16),       8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.LIGHT_GRAY_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.BLACK_DYE,16),      8 ));
		return recipes;
	}

	public List<MerchantRecipe> getHorseTrades() {
		List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,10), null, new ItemStack(Material.SADDLE,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 5), null, new ItemStack(Material.LEAD,2), 12 ));
		
		//Always armor
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 15), null, new ItemStack(Material.LEATHER_HORSE_ARMOR,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 25), new ItemStack(Material.IRON_INGOT, 7), new ItemStack(Material.IRON_HORSE_ARMOR,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 35), new ItemStack(Material.GOLD_INGOT, 7), new ItemStack(Material.GOLDEN_HORSE_ARMOR,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 55), new ItemStack(Material.DIAMOND, 7), new ItemStack(Material.DIAMOND_HORSE_ARMOR,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1),  null, new ItemStack(Material.WHEAT,10), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 10), null, new ItemStack(Material.GOLDEN_CARROT,4), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 40), null, new ItemStack(Material.NAME_TAG,1), 5 ));
		return recipes;
	}

	public List<MerchantRecipe> getFishMongerTrades() {
		List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();
		recipes.add( createRecipe( new ItemStack(Material.COD,10), null, new ItemStack(Material.EMERALD,1), -1 ));
		recipes.add( createRecipe( new ItemStack(Material.SALMON,10), null, new ItemStack(Material.EMERALD,1), -1 ));
		recipes.add( createRecipe( new ItemStack(Material.PUFFERFISH,5), null, new ItemStack(Material.EMERALD,1), -1 ));
		recipes.add( createRecipe( new ItemStack(Material.TROPICAL_FISH,1), null, new ItemStack(Material.EMERALD,1), -1 ));
		
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.COOKED_COD,4), 32 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,10), null, new ItemStack(Material.FISHING_ROD,1), 32 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,2), null, new ItemStack(Material.BUCKET,1), 32 ));
		return recipes;
	}

	public List<MerchantRecipe> getMinerTrades() {
		// Pick a random depth for the miner
		int depth = (int)(Math.random() * 128)-64+1;
		List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();
		//Items to sell to player, change price due to depth of miner?
		recipes.add( createRecipe( new ItemStack(Material.COAL,15), null, new ItemStack(Material.EMERALD,1), -1 ));
		recipes.add( createRecipe( new ItemStack(Material.COPPER_ORE,10), null, new ItemStack(Material.EMERALD,1), -1 ));
		if(depth < 64)
			recipes.add( createRecipe( new ItemStack(Material.LAPIS_LAZULI,2), null, new ItemStack(Material.EMERALD,1), -1 ));
		else
			recipes.add( createRecipe( new ItemStack(Material.LAPIS_LAZULI,4), null, new ItemStack(Material.EMERALD,1), -1 ));
		recipes.add( createRecipe( new ItemStack(Material.IRON_ORE,4), null, new ItemStack(Material.EMERALD,1), -1 ));
		if(depth < 30)
			recipes.add( createRecipe( new ItemStack(Material.GOLD_ORE,5), null, new ItemStack(Material.EMERALD,1), -1 ));
		else
			recipes.add( createRecipe( new ItemStack(Material.GOLD_ORE,10), null, new ItemStack(Material.EMERALD,1), -1 ));
		if(depth < 16)
			recipes.add( createRecipe( new ItemStack(Material.DIAMOND,1), null, new ItemStack(Material.EMERALD,2), -1 ));
		else
			recipes.add( createRecipe( new ItemStack(Material.DIAMOND,1), null, new ItemStack(Material.EMERALD,4), -1 ));
			
		recipes.add( createRecipe( new ItemStack(Material.FLINT,30), null, new ItemStack(Material.EMERALD,20), -1 ));
		
		//Basic Materials
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.COBBLESTONE,16), -1 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.GRANITE,16), -1 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.DIORITE,16), -1 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.ANDESITE,16), -1 ));
		
		if(depth < 16) {
			//Deep miner
			recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.DEEPSLATE,8), -1 ));
			recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.TUFF,4), -1 ));
			recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.CALCITE,2), -1 ));
			recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.POINTED_DRIPSTONE,2), -1 ));
		}
		if( Math.random() < .1 ) recipes.add( createRecipe( new ItemStack(Material.EMERALD,10), null, new ItemStack(Material.AXOLOTL_BUCKET,2), 8 ));
		if( Math.random() < .1 ) recipes.add( createRecipe( new ItemStack(Material.EMERALD,4), null, new ItemStack(Material.BIG_DRIPLEAF,2), 8 ));
		if( Math.random() < .1 ) recipes.add( createRecipe( new ItemStack(Material.EMERALD,5), null, new ItemStack(Material.AMETHYST_SHARD,1), 8 ));
		
		return recipes;
	}

	/* UNUSED / WORK IN PROGRESS */

	public void beeKeeper(WanderingTrader villager) {
		List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,10), null, new ItemStack(Material.BEE_NEST,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,10), null, new ItemStack(Material.BEEHIVE,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,10), null, new ItemStack(Material.HONEY_BLOCK,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,10), null, new ItemStack(Material.HONEY_BOTTLE,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,10), null, new ItemStack(Material.HONEYCOMB,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,10), null, new ItemStack(Material.HONEYCOMB_BLOCK,1), 5 ));
		villager.setRecipes(recipes);
	}

	public void potionTrader(WanderingTrader villager) {}
	public void farmerTrader(WanderingTrader villager) {}
}
	
