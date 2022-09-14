package me.thecamzone.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.thecamzone.main.Data;
import net.md_5.bungee.api.ChatColor;

public class OnPlayerDeath implements Listener {

	public void onPlayerDeath (PlayerDeathEvent e) {
		Player player = e.getEntity();
		
		if(Data.getHardcorePlayers().contains(player.getUniqueId().toString())) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage(ChatColor.RED + "Game Over!");
		}
	}
	
}
