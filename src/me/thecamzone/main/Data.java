package me.thecamzone.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;

import me.thecamzone.files.DataFile;

public class Data {

	private static List<String> hardcorePlayers = new ArrayList<>();
	private static List<String> placedHardcoreBeds = new ArrayList<>();
	private static List<String> receivedBed = new ArrayList<>();
	
	public static final List<Material> BEDS = Arrays.asList(Material.BLACK_BED, Material.BLUE_BED, Material.BROWN_BED,
			Material.CYAN_BED, Material.GRAY_BED, Material.GREEN_BED, Material.LIGHT_BLUE_BED, Material.LIGHT_GRAY_BED,
			Material.LIME_BED, Material.MAGENTA_BED, Material.ORANGE_BED, Material.PINK_BED, Material.PURPLE_BED,
			Material.RED_BED, Material.WHITE_BED, Material.YELLOW_BED);

	public static final List<String> COLORS = Arrays.asList("BLACK", "BLUE", "BROWN", "CYAN", "GRAY", "GREEN",
			"LIGHT_BLUE", "LIGHT_GRAY", "LIME", "MAGENTA", "ORANGE", "PINK", "PURPLE", "RED", "YELLOW");

	public static void load() {
		hardcorePlayers = DataFile.get().getStringList("HardcorePlayers");
		placedHardcoreBeds = DataFile.get().getStringList("PlacedHardcoreBeds");
		receivedBed = DataFile.get().getStringList("ReceivedBeds");
	}

	public static void save() {
		DataFile.get().set("HardcorePlayers", hardcorePlayers);
		DataFile.get().set("PlacedHardcoreBeds", placedHardcoreBeds);
		DataFile.get().set("ReceivedBeds", receivedBed);
		DataFile.save();
	}

	public static List<String> getReceivedBeds() {
		return receivedBed;
		
	}
	
	public static void addReceivedBed(UUID uuid) {
		if(receivedBed.contains(uuid.toString())) return;
		
		receivedBed.add(uuid.toString());
		save();
	}
	
	public static Boolean removeReceivedBed(UUID uuid) {
		if (receivedBed.remove(uuid.toString())) {
			save();
			return true;
		}

		return false;
	}
	
	public static void addPlacedBed(UUID uuid) {
		if(placedHardcoreBeds.contains(uuid.toString())) return;
		
		placedHardcoreBeds.add(uuid.toString());
		save();
	}
	
	public static Boolean removePlacedBeds(UUID uuid) {
		if (placedHardcoreBeds.remove(uuid.toString())) {
			save();
			return true;
		}

		return false;
	}
	
	public static List<String> getPlacedBeds() {
		return placedHardcoreBeds;
	}
	
	public static Boolean removeHardcorePlayer(UUID uuid) {
		if (hardcorePlayers.remove(uuid.toString())) {
			save();
			return true;
		}

		return false;
	}

	public static void addHardcorePlayer(UUID uuid) {
		if(hardcorePlayers.contains(uuid.toString())) return;
		
		hardcorePlayers.add(uuid.toString());
		save();
	}

	public static List<String> getHardcorePlayers() {
		return hardcorePlayers;
	}
}
