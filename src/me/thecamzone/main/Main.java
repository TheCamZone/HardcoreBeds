package me.thecamzone.main;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import com.jeff_media.customblockdata.CustomBlockData;

import me.thecamzone.commands.HardcoreBeds;
import me.thecamzone.commands.HardcoreBedsTC;
import me.thecamzone.events.OnBlockPlace;
import me.thecamzone.events.OnCustomBlockBreak;
import me.thecamzone.events.OnPlayerDeath;
import me.thecamzone.events.OnPlayerJoin;
import me.thecamzone.files.DataFile;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

	public static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		makeDir();
		
		CustomBlockData.registerListener(this);
		
		DataFile.setup();
		Data.load();
		
		getCommand("hardcorebeds").setExecutor(new HardcoreBeds());
		
		getCommand("hardcorebeds").setTabCompleter(new HardcoreBedsTC());
		
		getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
		getServer().getPluginManager().registerEvents(new OnCustomBlockBreak(), this);
		getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
	}
	
	public void giveItem(Player player, ItemStack item) {
		player.getInventory().addItem(item);
	}
	
	public void tryGiveItem(CommandSender sender, Player player, ItemStack item) { 
		if(player.getInventory().addItem(item).isEmpty()) {
			sender.sendMessage(ChatColor.GREEN + "Successfully gave " + player.getName() + " a bed.");
		} else {
			sender.sendMessage(ChatColor.RED + "Tried to give " + player.getName() + " a bed, but their inventory was full.");
		}
	}
	
	public ItemStack createBed(Player player) {
		ItemStack bed = new ItemStack(Material.RED_BED);
		ItemMeta bedMeta = bed.getItemMeta();
		PersistentDataContainer pdc = bedMeta.getPersistentDataContainer();
		
		pdc.set(new NamespacedKey(Main.plugin, "hardcorebeds"), PersistentDataType.STRING, player.getUniqueId().toString());
		
		bedMeta.setDisplayName(ChatColor.WHITE + "Hardcore Bed");
		bedMeta.setLore(Arrays.asList(
				ChatColor.GRAY + "Place the bed to be able to respawn.",
				ChatColor.GRAY + "If your bed is destroyed,",
				ChatColor.GRAY + "you will be stuck in hardcore mode."
				));
		
		bed.setItemMeta(bedMeta);
		return bed;
	}
	
	public ItemStack createBed(Player player, Material material) {
		ItemStack bed = new ItemStack(material);
		ItemMeta bedMeta = bed.getItemMeta();
		PersistentDataContainer pdc = bedMeta.getPersistentDataContainer();
		
		pdc.set(new NamespacedKey(Main.plugin, "hardcorebeds"), PersistentDataType.STRING, player.getUniqueId().toString());
		
		bedMeta.setDisplayName(ChatColor.WHITE + "Hardcore Bed");
		bedMeta.setLore(Arrays.asList(
				ChatColor.GRAY + "Place the bed to be able to respawn.",
				ChatColor.GRAY + "If your bed is destroyed,",
				ChatColor.GRAY + "you will be stuck in hardcore mode."
				));
		
		bed.setItemMeta(bedMeta);
		return bed;
	}
	
	private Boolean makeDir() {
		if(!getDataFolder().exists()) {
			getDataFolder().mkdir();
			return true;
		}
		return false;
	}
	
}
