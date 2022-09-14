package me.thecamzone.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.thecamzone.main.Main;
import net.md_5.bungee.api.ChatColor;

public class HardcoreBeds implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("hardcorebeds")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.GRAY + "HardcoreBeds created by TheCamZone for u/Weekly-Deer4161");
				sender.sendMessage(ChatColor.GRAY + "Type " + ChatColor.GREEN + "/hb help" + ChatColor.GRAY + " for help.");
				return true;
			}
			
			if(args.length == 1) {
				
				if(args[0].equalsIgnoreCase("give")) {
					sender.sendMessage(ChatColor.RED + "Incorrect Syntax: /hb give <player>");
					return true;
				}
				
			}
			
			if(args.length == 2) {
				
				if(args[0].equalsIgnoreCase("give")) {
					if(Bukkit.getPlayer(args[1]) == null) {
						sender.sendMessage(ChatColor.RED + "The player " + args[1] + " does not exist.");
						return true;
					}
					
					Player player = Bukkit.getPlayer(args[1]);
					
					ItemStack bed = new ItemStack(Material.RED_BED);
					ItemMeta bedMeta = bed.getItemMeta();
					PersistentDataContainer pdc = bedMeta.getPersistentDataContainer();
					
					pdc.set(new NamespacedKey(Main.plugin, "hardcorebeds"), PersistentDataType.STRING, player.getUniqueId().toString());
					
					bed.setItemMeta(bedMeta);
					
					if(player.getInventory().addItem(bed).isEmpty()) {
						sender.sendMessage(ChatColor.GREEN + "Successfully gave " + player.getName() + " a bed.");
					} else {
						sender.sendMessage(ChatColor.RED + "Tried to give " + player.getName() + " a bed, but their inventory was full.");
					}
				}
				
			}
			
		}
		
		return true;
	}

	
	
}
