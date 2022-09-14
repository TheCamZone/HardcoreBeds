package me.thecamzone.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;

import me.thecamzone.files.DataFile;

public class Data {

	private static List<String> hardcorePlayers = new ArrayList<>();
	public static final List<Material> BEDS = Arrays.asList(Material.BLACK_BED, Material.BLUE_BED, Material.BROWN_BED,
			Material.CYAN_BED, Material.GRAY_BED, Material.GREEN_BED, Material.LIGHT_BLUE_BED, Material.LIGHT_GRAY_BED,
			Material.LIME_BED, Material.MAGENTA_BED, Material.ORANGE_BED, Material.PINK_BED, Material.PURPLE_BED,
			Material.RED_BED, Material.WHITE_BED, Material.YELLOW_BED);
	
	public static void load() {
		hardcorePlayers = DataFile.get().getStringList("HardcorePlayers");
	}
	
	public static void save() {
		DataFile.get().set("HardcorePlayers", hardcorePlayers);
		DataFile.save();
	}
	
	public static Boolean removeHardcorePlayer(UUID uuid) {
		if(hardcorePlayers.remove(uuid.toString())) {
			return true;
		}
		
		return false;
	}
	
	public static void addHardcorePlayer(UUID uuid) {
		hardcorePlayers.add(uuid.toString());
	}
	
	public static List<String> getHardcorePlayers() {
		return hardcorePlayers;
	}
}
