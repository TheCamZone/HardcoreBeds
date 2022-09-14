package me.thecamzone.events;

import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.jeff_media.customblockdata.CustomBlockData;
import com.jeff_media.customblockdata.events.CustomBlockDataRemoveEvent;

import me.thecamzone.main.Data;
import me.thecamzone.main.Main;

public class OnCustomBlockBreak implements Listener {

	@EventHandler
	public void onCustomBlockBreak(CustomBlockDataRemoveEvent e) {
		if (!Data.BEDS.contains(e.getBlock().getType())) return;
		
		Block block = e.getBlock();
		PersistentDataContainer customBlockData = new CustomBlockData(block, Main.plugin);
		
		String uuid = customBlockData.get(new NamespacedKey(Main.plugin, "hardcorebeds"), PersistentDataType.STRING);
		
		Data.addHardcorePlayer(UUID.fromString(uuid));
		Data.save();
	}

}
