package main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import net.md_5.bungee.api.ChatColor;

public class CustomTrades implements Listener{
	
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
				setColorTrades(villager);
				text = "The color trader has arrived!";
				break;
			case 1:
				horseTrades(villager);
				text = "The horse tack trader has arrived!";
				break;
			default:
				normalTrades(villager);
				text = "The wandering trader has arrived!";
				break;
		}
		Bukkit.getServer().broadcastMessage(ChatColor.BLUE + text);
	}
	
	public void normalTrades(WanderingTrader villager) {
		List<MerchantRecipe> recipes = new ArrayList();
		for(int x=0;x<5;x++) normalTrades1(villager, recipes);
		for(int x=0;x<1;x++) normalTrades2(villager, recipes);
		villager.setRecipes(recipes);
	}
	public void normalTrades1(WanderingTrader villager,List<MerchantRecipe> recipes) {
		switch((int)(Math.random()*38)) {
			case  0:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.ALLIUM,1), 12 ));break;
			case  1:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.AZURE_BLUET,1), 12 ));break;
			case  2:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.CORNFLOWER,1), 12 ));break;
			case  3:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.DANDELION,1), 12 ));break;
			case  4:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.FERN,1), 12 ));break;
			case  5:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.OXEYE_DAISY,1), 12 ));break;
			case  6:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.POPPY,1), 12 ));break;
			case  7:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.RED_TULIP,1), 12 ));break;
			case  8:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.ORANGE_TULIP,1), 12 ));break;
			case  9:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.PINK_TULIP,1), 12 ));break;
			case 10:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.WHITE_TULIP,1), 12 ));break;
			case 11:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.WHEAT_SEEDS,1), 12 ));break;
			case 12:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.BEETROOT_SEEDS,1), 12 ));break;
			case 13:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.PUMPKIN_SEEDS,1), 12 ));break;
			case 14:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.MELON_SEEDS,1), 12 ));break;
			case 15:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.BROWN_MUSHROOM,1), 12 ));break;
			case 16:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.RED_MUSHROOM,1), 12 ));break;
			case 17:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.VINE,1), 12 ));break;
			case 18:
				int amount = 16;
				switch((int)(Math.random()*16)) {
					case  0: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.WHITE_DYE,amount), 12 )); break;
					case  1: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.RED_DYE,amount), 12 )); break;
					case  2: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.ORANGE_DYE,amount), 12 )); break;
					case  3: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.PINK_DYE,amount), 12 )); break;
					case  4: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.YELLOW_DYE,amount), 12 )); break;
					case  5: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.LIME_DYE,amount), 12 )); break;
					case  6: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.GREEN_DYE,amount), 12 )); break;
					case  7: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.LIGHT_BLUE_DYE,amount), 12 )); break;
					case  8: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.CYAN_DYE,amount), 12 )); break;
					case  9: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.BLUE_DYE,amount), 12 )); break;
					case 10: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.MAGENTA_DYE,amount), 12 )); break;
					case 11: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.PURPLE_DYE,amount), 12 )); break;
					case 12: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.BROWN_DYE,amount), 12 )); break;
					case 13: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.GRAY_DYE,amount), 12 )); break;
					case 14: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.LIGHT_GRAY_DYE,amount), 12 )); break;
					case 15: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.BLACK_DYE,amount), 12 )); break;
				}
				break;
			case 19:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.BLUE_ORCHID,1), 8 ));break;
			case 20:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.SAND,8), 8 ));break;
			case 21:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.SUGAR_CANE,1), 8 ));break;
			case 22:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.LILY_OF_THE_VALLEY,1), 7 ));break;
			case 23:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.RED_SAND,4), 6 ));break;
			case 24:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.LILY_PAD,2), 5 ));break;
			case 25:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.PUMPKIN,1), 4 ));break;
			case 26:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 2), null, new ItemStack(Material.SEA_PICKLE,1), 5 ));break;
			case 27:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 2), null, new ItemStack(Material.GLOWSTONE,1), 5 ));break;
			case 28:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 3), null, new ItemStack(Material.KELP,1), 12 ));break;
			case 29:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 3), null, new ItemStack(Material.CACTUS,1), 8 ));break;
			case 30:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 3), null, new ItemStack(Material.BRAIN_CORAL_BLOCK,1), 8 ));break;
			case 31:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 3), null, new ItemStack(Material.BUBBLE_CORAL_BLOCK,1), 8 ));break;
			case 32:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 3), null, new ItemStack(Material.FIRE_CORAL_BLOCK,1), 8 ));break;
			case 33:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 3), null, new ItemStack(Material.HORN_CORAL_BLOCK,1), 8 ));break;
			case 34:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 3), null, new ItemStack(Material.TUBE_CORAL_BLOCK,1), 8 ));break;
			case 35:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 4), null, new ItemStack(Material.SLIME_BALL,1), 5 ));break;
			case 36:
				switch((int)(Math.random()*6)) {
					case  0: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.ACACIA_SAPLING,1), 8 )); break;
					case  1: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.BIRCH_SAPLING,1), 8 )); break;
					case  2: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.DARK_OAK_SAPLING,1), 8 )); break;
					case  3: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.JUNGLE_SAPLING,1), 8 )); break;
					case  4: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.OAK_SAPLING,1), 8 )); break;
					case  5: recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.SPRUCE_SAPLING,1), 8 )); break;
				}
				recipes.add( createRecipe( new ItemStack(Material.EMERALD, 5), null, new ItemStack(Material.SLIME_BALL,1), 8 ));
				break;
			case 37:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 5), null, new ItemStack(Material.SLIME_BALL,1), 5 ));break;
		}
	}
	public void normalTrades2(WanderingTrader villager,List<MerchantRecipe> recipes) {
		switch((int)(Math.random()*5)) {
			case 0:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.GUNPOWDER,1), 8 ));break;
			case 1:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 3), null, new ItemStack(Material.PODZOL,3), 6 ));break;
			case 2:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 3), null, new ItemStack(Material.PACKED_ICE,1), 6 ));break;
			case 3:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 5), null, new ItemStack(Material.PUFFERFISH_BUCKET,1), 4 ));break;
			case 4:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 5), null, new ItemStack(Material.TROPICAL_FISH_BUCKET,1), 4 ));break;
			case 5:recipes.add( createRecipe( new ItemStack(Material.EMERALD, 6), null, new ItemStack(Material.BLUE_ICE,1), 6 ));break;
		}
	}
	public void horseTrades(WanderingTrader villager) {
		List<MerchantRecipe> recipes = new ArrayList();
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,10), null, new ItemStack(Material.SADDLE,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 5), null, new ItemStack(Material.LEAD,2), 12 ));
		//Always armor
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 15), null, new ItemStack(Material.LEATHER_HORSE_ARMOR,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 25), new ItemStack(Material.IRON_INGOT, 7), new ItemStack(Material.IRON_HORSE_ARMOR,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 35), new ItemStack(Material.GOLD_INGOT, 7), new ItemStack(Material.GOLDEN_HORSE_ARMOR,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 55), new ItemStack(Material.DIAMOND, 7), new ItemStack(Material.DIAMOND_HORSE_ARMOR,1), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 1), null, new ItemStack(Material.WHEAT,10), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 10), null, new ItemStack(Material.GOLDEN_CARROT,4), 5 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD, 40), null, new ItemStack(Material.NAME_TAG,1), 5 ));
		villager.setRecipes(recipes);
	}
	public void setColorTrades(WanderingTrader villager) {
		List<MerchantRecipe> recipes = new ArrayList();
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.WHITE_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.RED_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.ORANGE_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.PINK_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.YELLOW_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.LIME_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.GREEN_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.LIGHT_BLUE_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.CYAN_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.BLUE_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.MAGENTA_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.PURPLE_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.BROWN_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.GRAY_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.LIGHT_GRAY_DYE,16), 8 ));
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,1), null, new ItemStack(Material.BLACK_DYE,16), 8 ));
		villager.setRecipes(recipes);
	}
	public void potionTrader(WanderingTrader villager) {
		List<MerchantRecipe> recipes = new ArrayList();
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,5), null, new ItemStack(Material.BREWING_STAND,1), 8 ));
		villager.setRecipes(recipes);
	}
	public void farmerTrader(WanderingTrader villager) {
		List<MerchantRecipe> recipes = new ArrayList();
		recipes.add( createRecipe( new ItemStack(Material.EMERALD,5), null, new ItemStack(Material.BREWING_STAND,1), 8 ));
		villager.setRecipes(recipes);
	}
	public MerchantRecipe createRecipe(ItemStack item1, ItemStack item2, ItemStack reward) {
		return createRecipe(item1, item2, reward, 10);
	}
	public MerchantRecipe createRecipe(ItemStack item1, ItemStack item2, ItemStack reward, int disabled) {
		MerchantRecipe trade = new MerchantRecipe(reward, disabled);
		if(item1!=null) trade.addIngredient(item1);
		if(item2!=null) trade.addIngredient(item2);
		return trade;
	}
}
	