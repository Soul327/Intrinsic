package main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class CustomRecipies {
	
	Material[] wools = new Material[16];
	Material[] beds = new Material[16];
	Material[] flowers = new Material[12];
	
	public void delArrays() {
		wools = null;
		beds = null;
		flowers = null;
	}
	
	public void setArrays() {
	//Add items to list
			wools[ 0] = Material.WHITE_WOOL;
			wools[ 1] = Material.ORANGE_WOOL;
			wools[ 2] = Material.MAGENTA_WOOL;
			wools[ 3] = Material.LIGHT_BLUE_WOOL;
			wools[ 4] = Material.YELLOW_WOOL;
			wools[ 5] = Material.LIME_WOOL;
			wools[ 6] = Material.PINK_WOOL;
			wools[ 7] = Material.GRAY_WOOL;
			wools[ 8] = Material.LIGHT_GRAY_WOOL;
			wools[ 9] = Material.CYAN_WOOL;
			wools[10] = Material.PURPLE_WOOL;
			wools[11] = Material.BLUE_WOOL;
			wools[12] = Material.BROWN_WOOL;
			wools[13] = Material.GREEN_WOOL;
			wools[14] = Material.RED_WOOL;
			wools[15] = Material.BLACK_WOOL;
			
			beds[ 0] = Material.WHITE_BED;
			beds[ 1] = Material.ORANGE_BED;
			beds[ 2] = Material.MAGENTA_BED;
			beds[ 3] = Material.BLACK_BED;
			beds[ 4] = Material.BLACK_BED;
			beds[ 5] = Material.BLACK_BED;
			beds[ 6] = Material.BLACK_BED;
			beds[ 7] = Material.BLACK_BED;
			beds[ 8] = Material.BLACK_BED;
			beds[ 9] = Material.BLACK_BED;
			beds[10] = Material.BLACK_BED;
			beds[11] = Material.BLACK_BED;
			beds[12] = Material.BLACK_BED;
			beds[13] = Material.BLACK_BED;
			beds[14] = Material.BLACK_BED;
			beds[15] = Material.BLACK_BED;
			
			flowers[ 0] = Material.DANDELION;
			flowers[ 1] = Material.POPPY;
			flowers[ 2] = Material.BLUE_ORCHID;
			flowers[ 3] = Material.ALLIUM;
			flowers[ 4] = Material.AZURE_BLUET;
			flowers[ 5] = Material.RED_TULIP;
			flowers[ 6] = Material.ORANGE_TULIP;
			flowers[ 7] = Material.WHITE_TULIP;
			flowers[ 8] = Material.PINK_TULIP;
			flowers[ 9] = Material.OXEYE_DAISY;
			flowers[10] = Material.CORNFLOWER;
			flowers[11] = Material.LILY_OF_THE_VALLEY;
	}
	
	public void init(Server server) {
		setArrays();
		
		ShapelessRecipe shapelessRecipe;
		ShapedRecipe shapedRecipe;
		//Wool -> String
		for(int z=0;z<wools.length;z++) {
			shapelessRecipe = new ShapelessRecipe(
					new NamespacedKey(Main.getPlugin(Main.class), "Int"+z+"ToString"),
					new ItemStack(Material.STRING, 4) );
			shapelessRecipe.addIngredient(wools[z]);
			server.addRecipe(shapelessRecipe);
		}
		
		shapelessRecipe = new ShapelessRecipe(
				new NamespacedKey(Main.getPlugin(Main.class), "BlueIceToPackedIce"),
				new ItemStack(Material.PACKED_ICE, 9) );
		shapelessRecipe.addIngredient(Material.BLUE_ICE);
		server.addRecipe(shapelessRecipe);
		
		shapelessRecipe = new ShapelessRecipe(
				new NamespacedKey(Main.getPlugin(Main.class), "PackedIceToIce"),
				new ItemStack(Material.ICE, 9) );
		shapelessRecipe.addIngredient(Material.PACKED_ICE);
		server.addRecipe(shapelessRecipe);
		
		shapedRecipe = new ShapedRecipe(
				new NamespacedKey(Main.getPlugin(Main.class), "DropperToDispenser"),
				new ItemStack(Material.DISPENSER, 1) );
		shapedRecipe.shape("TS ", "TDS", "TS ");
		shapedRecipe.setIngredient('S', Material.STICK);
		shapedRecipe.setIngredient('D', Material.DROPPER);
		shapedRecipe.setIngredient('T', Material.STRING);
		server.addRecipe(shapedRecipe);
		
		shapedRecipe = new ShapedRecipe(
				new NamespacedKey(Main.getPlugin(Main.class), "DropperToDispenserF"),
				new ItemStack(Material.DISPENSER, 1) );
		shapedRecipe.shape(" ST", "SDT", " ST");
		shapedRecipe.setIngredient('S', Material.STICK);
		shapedRecipe.setIngredient('D', Material.DROPPER);
		shapedRecipe.setIngredient('T', Material.STRING);
		server.addRecipe(shapedRecipe);
		
		shapedRecipe = new ShapedRecipe(
				new NamespacedKey(Main.getPlugin(Main.class), "EndPortal"),
				new ItemStack(Material.END_PORTAL_FRAME, 4) );
		shapedRecipe.shape("EEE", "SDS", "SSS");
		shapedRecipe.setIngredient('D', Material.DRAGON_EGG);
		shapedRecipe.setIngredient('E', Material.EMERALD);
		shapedRecipe.setIngredient('S', Material.END_STONE);
		server.addRecipe(shapedRecipe);
		
		shapelessRecipe = new ShapelessRecipe(
				new NamespacedKey(Main.getPlugin(Main.class), "CoalToBlackDye"),
				new ItemStack(Material.BLACK_DYE, 4) );
		shapelessRecipe.addIngredient( Material.COAL );
		server.addRecipe(shapelessRecipe);
		
		delArrays();
		Bukkit.broadcastMessage("ADDED RECIPIES");
	}
}