package me.thecamzone.events;

import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.jeff_media.customblockdata.CustomBlockData;

import me.thecamzone.main.Data;
import me.thecamzone.main.Main;
import net.md_5.bungee.api.ChatColor;

public class OnBlockPlace implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Block block = e.getBlock();
		ItemStack item = e.getItemInHand();
		
		if(!Data.BEDS.contains(block.getType())) return;
		
		ItemMeta itemMeta = item.getItemMeta();
		PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
		
		if(pdc.get(new NamespacedKey(Main.plugin, "hardcorebeds"), PersistentDataType.STRING) == null) return;
		
		String uuid =  pdc.get(new NamespacedKey(Main.plugin, "hardcorebeds"), PersistentDataType.STRING);
		Player player = e.getPlayer();
		
		if(Data.getPlacedBeds().contains(uuid)) {
			if(player.getUniqueId().equals(UUID.fromString(uuid))) {
				player.sendMessage(ChatColor.RED + "You have already placed your bed.");
			} else {
				player.sendMessage(ChatColor.RED + "This user has already been assigned a bed.");
			}
			
			e.setCancelled(true);
			return;
		}
		
		PersistentDataContainer customBlockData = new CustomBlockData(block, Main.plugin);
		
		customBlockData.set(new NamespacedKey(Main.plugin, "hardcorebeds"), PersistentDataType.STRING, uuid);
		
		if(!Data.getPlacedBeds().contains(uuid)) {
			player.sendMessage(ChatColor.GRAY + "You have placed your hardcore bed. If it is destroyed, you cannot respawn.");
		}
		
		Data.addPlacedBed(UUID.fromString(uuid));
	}
	
}
