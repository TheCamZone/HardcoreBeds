package me.thecamzone.events;

import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.thecamzone.main.Data;
import net.md_5.bungee.api.ChatColor;

public class OnPlayerDeath implements Listener {

	@EventHandler
	public void onPlayerDeath (PlayerDeathEvent e) {
		Player player = e.getEntity();
		UUID uuid = player.getUniqueId();
		String uuidString = uuid.toString();
		
		if(Data.getHardcorePlayers().contains(uuidString) || !Data.getPlacedBeds().contains(uuidString)) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage(ChatColor.RED + "Game Over!");
		}
	}
	
}
