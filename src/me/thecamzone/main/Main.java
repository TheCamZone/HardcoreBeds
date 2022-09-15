package me.thecamzone.main;

import org.bukkit.plugin.java.JavaPlugin;

import com.jeff_media.customblockdata.CustomBlockData;

import me.thecamzone.commands.HardcoreBeds;
import me.thecamzone.commands.HardcoreBedsTC;
import me.thecamzone.events.OnBlockPlace;
import me.thecamzone.events.OnCustomBlockBreak;
import me.thecamzone.events.OnPlayerDeath;
import me.thecamzone.files.DataFile;

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
		
		getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
		getServer().getPluginManager().registerEvents(new OnCustomBlockBreak(), this);
		getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
	}
	
	private Boolean makeDir() {
		if(!getDataFolder().exists()) {
			getDataFolder().mkdir();
			return true;
		}
		return false;
	}
	
}
