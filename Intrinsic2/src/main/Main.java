package main;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

//import Depreciated.DogTotem;

public final class Main extends JavaPlugin implements Listener{
	
	public static ArrayList<PlayerSave> saves = new ArrayList<PlayerSave>();
	Chat chat;
	MOTD motd = new MOTD();
	CustomRecipies customRecipies;
	Totem totem = new Totem(this);
//	AutoTrash autoTrash = new AutoTrash();
	
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		config();
//		new AFK(this);
		saves = new ArrayList<PlayerSave>();
		Bukkit.getPluginManager().registerEvents(this, this);
		//Bukkit.getPluginManager().registerEvents(new CustomComposter(), this);
		Bukkit.getPluginManager().registerEvents(new Flower(), this);
		Bukkit.getPluginManager().registerEvents(new AutoTrash(), this);
		if(getConfig().getBoolean("Compass.enable")) Bukkit.getPluginManager().registerEvents(new Compass(), this);
		Bukkit.getPluginManager().registerEvents(new RePlant(), this);
		if(getConfig().getBoolean("CustomChat.enable")) {
			chat = new Chat();
			Bukkit.getPluginManager().registerEvents(chat, this);
		}
		if(getConfig().getBoolean("CustomTrades.enable")) Bukkit.getPluginManager().registerEvents(new CustomTrades(), this);
		
		Bukkit.getPluginManager().registerEvents(new Repair(),this);
		totem.load();
		Bukkit.getPluginManager().registerEvents(totem,this);
		
		getLogger().info("Plugin enabled");
		for(Player p : Bukkit.getOnlinePlayers()){
			PlayerSave save = new PlayerSave(p);
			saves.add(save);
		}
		
		customRecipies = new CustomRecipies();
		customRecipies.init(Bukkit.getServer());
	}
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
		totem.save();
	}
	
	public static PlayerSave getSave(Player player) {
		for(int x=0;x<saves.size();x++) 
			if(saves.get(x).player==player)
				return saves.get(x);
		return null;
	}
	
	public void config() {
		//AFK
		getConfig().addDefault("AFK.enable", true);
		getConfig().addDefault("AFK.timerInMins", 5);
		//Compass
		getConfig().addDefault("Compass.enable", true);
		//getConfig().addDefault("Compass.pointToPlayer", true);
		//getConfig().addDefault("Compass.pointToWorldSpawn", true);
		//getConfig().addDefault("Compass.pointToBed", true);
		//getConfig().addDefault("Compass.otherPlayers", true);
		//CustomTrades
		getConfig().addDefault("CustomTrades.enable", true);
		getConfig().addDefault("CustomTrades.WanderingTrader.enable", true);
		//getConfig().addDefault("CustomTrades.WanderingTrader.colorTrader", true);
		//getConfig().addDefault("CustomTrades.WanderingTrader.horseTackTrader", true);
		//getConfig().addDefault("CustomTrades.WanderingTrader.normalTrades", true);
		//CustomChat
		getConfig().addDefault("CustomChat.enable", true);
		getConfig().addDefault("CustomChat.customName", true);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		PlayerSave save = new PlayerSave( player );
		saves.add(save);
		motd.onPlayerJoin(event);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		for(int x=0;x<saves.size();x++) 
			if(saves.get(x).player==event.getPlayer()) {
				saves.remove(x);
				return;
			}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Player player = event.getEntity();
		Bukkit.getServer().broadcastMessage(player.getDisplayName()+" has died at X:"+player.getLocation().getBlockX()+" Y:"+player.getLocation().getBlockY()+" Z:"+player.getLocation().getBlockZ());
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onEntityDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (!(entity instanceof EnderDragon)) return;
		if (event.getDroppedExp() == 1) return;
		event.getDrops().add( new ItemStack(Material.DRAGON_EGG, 1) );
		event.getDrops().add( new ItemStack(Material.DRAGON_HEAD, 1) );
		event.getDrops().add( new ItemStack(Material.ELYTRA, 1) );
	}
	
	
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		Block block = event.getClickedBlock();
		
		if(player.getGameMode() != GameMode.CREATIVE)
			if (action.equals(Action.LEFT_CLICK_BLOCK))
				if(player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE)
					if (block.getType().equals(Material.END_PORTAL_FRAME)) {
						//Removes block
						block.breakNaturally();
						//Drops block item
						block.getWorld().dropItem(Util.getCenter(block.getLocation()), new ItemStack(Material.END_PORTAL_FRAME));
						//Destroy
						World world = block.getWorld();
						int x = block.getX();
						int y = block.getY();
						int z = block.getZ();
						for(int a=-3;a<=3;a++)
							for(int b=-3;b<=3;b++) {
								Block bl = world.getBlockAt(x+a, y, z+b);
								if(bl.getType() == Material.END_PORTAL)
									bl.breakNaturally();
							}
					}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(getConfig().getBoolean("Compass.enable"))
			if(new Compass().onCommand(sender, cmd, label, args)) return true;
		if(new PlayerCommands().onCommand(sender, cmd, label, args))
			return true;
		if(getConfig().getBoolean("CustomChat.enable"))
			if(chat.onCommand(sender, cmd, label, args)) return true;
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("hello"))
				return true;
		}
		
//		autoTrash.onCommand(sender, cmd, label, args);
//		if( new DiamondBank().onCommand(sender, cmd, label, args) ) return true;
		return false;
	}
}