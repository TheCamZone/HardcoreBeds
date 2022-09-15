package me.thecamzone.events;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import me.thecamzone.main.Data;
import me.thecamzone.main.Main;

public class OnPlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		UUID uuid = player.getUniqueId();
		String uuidString = uuid.toString();
		
		if(Data.getReceivedBeds().contains(uuidString)) return;
		
		Random random = new Random();
		Integer i = random.nextInt(Data.COLORS.size());
		Data.COLORS.get(i);
		
		Material material = Material.valueOf(Data.COLORS.get(i) + "_BED");
		
		ItemStack bed = Main.plugin.createBed(player, material);
		
		Main.plugin.giveItem(player, bed);
		
		Data.addReceivedBed(uuid);
	}
	
}
