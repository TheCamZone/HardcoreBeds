package me.thecamzone.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.thecamzone.main.Data;

public class HardcoreBedsTC implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("hardcorebeds")) {
			//if(!sender.hasPermission("hardcorebeds.admin")) return null;
			
			if(args.length == 1) {
				return Arrays.asList("help", "give", "list", "remove");
			}
			
			else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("give")) return null;
				if(args[0].equalsIgnoreCase("remove")) return null;
				return Arrays.asList("");
			}
			
			else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("give")) return Data.COLORS;
				return Arrays.asList("");
			}
			
			else {
				return Arrays.asList("");
			}
		}
		return null;
	}

}
