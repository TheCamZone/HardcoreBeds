package me.thecamzone.events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.jeff_media.customblockdata.CustomBlockData;
import com.jeff_media.customblockdata.events.CustomBlockDataRemoveEvent;

import me.thecamzone.main.Data;
import me.thecamzone.main.Main;
import net.md_5.bungee.api.ChatColor;

public class OnCustomBlockBreak implements Listener {

	@EventHandler
	public void onCustomBlockBreak(CustomBlockDataRemoveEvent e) {
		if (!Data.BEDS.contains(e.getBlock().getType())) return;
		
		Block block = e.getBlock();
		PersistentDataContainer customBlockData = new CustomBlockData(block, Main.plugin);
		
		String uuid = customBlockData.get(new NamespacedKey(Main.plugin, "hardcorebeds"), PersistentDataType.STRING);
		
		Data.addHardcorePlayer(UUID.fromString(uuid));
		Data.removePlacedBeds(UUID.fromString(uuid));
		
		if(Bukkit.getPlayer(UUID.fromString(uuid)) == null) return;
			
		Player bedOwner = Bukkit.getPlayer(UUID.fromString(uuid));
			
		bedOwner.sendMessage(ChatColor.RED + "Your bed has been destroyed! You will not respawn from this point forward.");
	}

}
