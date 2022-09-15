package me.thecamzone.commands;

import java.util.UUID;

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

import me.thecamzone.main.Data;
import me.thecamzone.main.Main;
import net.md_5.bungee.api.ChatColor;

public class HardcoreBeds implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("hardcorebeds")) {
			if(!sender.hasPermission("hardcorebeds.admin")) return true;
			
			if(args.length == 0) {
				sender.sendMessage(ChatColor.GRAY + "HardcoreBeds created by TheCamZone for u/Weekly-Deer4161");
				sender.sendMessage(ChatColor.GRAY + "Type " + ChatColor.GREEN + "/hb help" + ChatColor.GRAY + " for help.");
				return true;
			}
			
			else if(args.length == 1) {
				
				if(args[0].equalsIgnoreCase("help")) {
					sender.sendMessage(ChatColor.GRAY + "Commands:");
					sender.sendMessage(ChatColor.WHITE + "  - " + ChatColor.GREEN + "/hb help " + ChatColor.GRAY + "displays this message.");
					sender.sendMessage(ChatColor.WHITE + "  - " + ChatColor.GREEN + "/hb give <player> " + ChatColor.GRAY + "gives a hardcore bed.");
					return true;
				}
				
				if(args[0].equalsIgnoreCase("give")) {
					sender.sendMessage(ChatColor.RED + "Incorrect Syntax: /hb give <player>");
					return true;
				}
				
				if(args[0].equalsIgnoreCase("remove")) {
					sender.sendMessage(ChatColor.RED + "Incorrect Syntax: /hb remove <player>");
					return true;
				}
				
				if(args[0].equalsIgnoreCase("list")) {
					if(Data.getHardcorePlayers().isEmpty()) {
						sender.sendMessage(ChatColor.GRAY + "There are no online hardcore players.");
						return true;
					}
					
					sender.sendMessage(ChatColor.GRAY + "List of online hardcore players:");
					
					for(String string : Data.getHardcorePlayers()) {
						UUID uuid = UUID.fromString(string);
						if(Bukkit.getPlayer(uuid) == null) return true;
						
						sender.sendMessage(ChatColor.WHITE + "  - " + ChatColor.GREEN + Bukkit.getPlayer(uuid).getName());
					}
					return true;
				}
				
			}
			
			else if(args.length == 2) {
				
				if(args[0].equalsIgnoreCase("remove")) {
					if(Bukkit.getPlayer(args[1]) == null) {
						sender.sendMessage(ChatColor.RED + "The player " + args[1] + " is not online right now.");
						return true;
					}
					
					Player player = Bukkit.getPlayer(args[1]);
					
					if(Data.removeHardcorePlayer(player.getUniqueId())) {
						sender.sendMessage(ChatColor.GREEN + "Successfully removed " + args[1] + " from the hardcore list.");
					} else {
						sender.sendMessage(ChatColor.RED + "Player " + args[1] + " is not in hardcore mode.");
					}
					
					return true;
				}
				
				if(args[0].equalsIgnoreCase("give")) {
					if(Bukkit.getPlayer(args[1]) == null) {
						sender.sendMessage(ChatColor.RED + "The player " + args[1] + " is not online right now.");
						return true;
					}
					
					Player player = Bukkit.getPlayer(args[1]);
					
					ItemStack bed = createBed(player);
					
					tryGiveItem(sender, player, bed);
					return true;
				}
				
			}
			
			else if(args.length == 3) {
				
				if(args[0].equalsIgnoreCase("give")) {
					if(Bukkit.getPlayer(args[1]) == null) {
						sender.sendMessage(ChatColor.RED + "The player " + args[1] + " does not exist.");
						return true;
					}
					
					if(!Data.COLORS.contains(args[2].toUpperCase())) {
						sender.sendMessage(ChatColor.RED + "The color " + args[2].toUpperCase() + " does not exist for beds.");
						return true;
					}
					
					Player player = Bukkit.getPlayer(args[1]);
					
					Material material = Material.valueOf(args[2].toUpperCase() + "_BED");
					
					ItemStack bed = createBed(player, material);
					
					tryGiveItem(sender, player, bed);
					return true;
				}
				
			}
			else {
				
				sender.sendMessage(ChatColor.GRAY + "Commands:");
				sender.sendMessage(ChatColor.WHITE + "  - " + ChatColor.GREEN + "/hb help " + ChatColor.GRAY + "displays this message.");
				sender.sendMessage(ChatColor.WHITE + "  - " + ChatColor.GREEN + "/hb give <player> " + ChatColor.GRAY + "gives a hardcore bed.");
				return true;
				
			}
			
		}
		
		return true;
	}

	private void tryGiveItem(CommandSender sender, Player player, ItemStack item) { 
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
		
		bed.setItemMeta(bedMeta);
		return bed;
	}
	
	public ItemStack createBed(Player player, Material material) {
		ItemStack bed = new ItemStack(material);
		ItemMeta bedMeta = bed.getItemMeta();
		PersistentDataContainer pdc = bedMeta.getPersistentDataContainer();
		
		pdc.set(new NamespacedKey(Main.plugin, "hardcorebeds"), PersistentDataType.STRING, player.getUniqueId().toString());
		
		bed.setItemMeta(bedMeta);
		return bed;
	}
	
}
