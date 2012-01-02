package com.twosquaredstudios.PlayerPressure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import org.bukkit.Location;

public class PlayerPressureConfig {
	private final PlayerPressure plugin;
	private ArrayList<PlayerPressureLocation> platesList = new ArrayList<PlayerPressureLocation>();
	
	public PlayerPressureConfig(PlayerPressure instance) {
		plugin = instance;
	}
	
	public ArrayList<PlayerPressureLocation> getPlatesList() {
		return platesList;
	}
	
	public boolean isInPlatesList(PlayerPressureLocation location) {
		if (location==null) {
			return false;
		}
		return platesList.contains(location);
	}
	
	public boolean isInPlatesList(Location location) {
		if (location==null) {
			return false;
		}
		PlayerPressureLocation loc = new PlayerPressureLocation(location);
		return platesList.contains(loc);
	}
	
	public void addToPlatesList(PlayerPressureLocation location) {
		platesList.add(location);
		saveConfig();
	}
	
	public void addToPlatesList(Location location) {
		PlayerPressureLocation loc = new PlayerPressureLocation(location);
		platesList.add(loc);
		saveConfig();
	}
	
	public void removeFromPlatesList(PlayerPressureLocation location) {
		while (platesList.contains(location)) {
			platesList.remove(location);
		}
		saveConfig();
	}
	
	public void removeFromPlatesList(Location location) {
		PlayerPressureLocation loc = new PlayerPressureLocation(location);
		while (platesList.contains(loc)) {
			platesList.remove(loc);
		}
		saveConfig();
	}
	
	public void loadConfig() {
	try {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(plugin.getDataFolder() + "/plates.save"), "UTF-8"));
		String s = "";
		Integer line = 0;
		while ((s = in.readLine()) != null) {
			line++;
			try {
				s = s.trim();
				if (s.equals("")) {}
				else if (s.endsWith(";")) {
					s = s.substring(0, s.length()-1);
				} else {
					throw new IllegalArgumentException("Missing semi-colon at end of line.");
				}
				PlayerPressureLocation loc = new PlayerPressureLocation(s);
				platesList.add(loc);
			} catch (IllegalArgumentException e) {
				plugin.logWarning("Unable to parse line " + line + " of 'plates.save'. The error was:\n\"" + e.getMessage() + "\"");
			}
		}
		in.close();
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		File directory = plugin.getDataFolder();
		if (!directory.isDirectory()) {
			if (!directory.mkdir()) {
				plugin.logError("Unable to create plugin data directory!");
				return;
			}
		}
		try {
			Writer out = new OutputStreamWriter(new FileOutputStream(plugin.getDataFolder() + "/plates.save"), "UTF-8");
			out.write("");
			out.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	public void saveConfig() {
		String outStr = "";
		int size = platesList.size();
		for (int i = 0; i < size; i++) {
			outStr = outStr + platesList.get(i).toString();
			if (i < size-1) {
				outStr = outStr + "\n";
			}
		}
		try {
			Writer out = new OutputStreamWriter(new FileOutputStream(plugin.getDataFolder() + "/plates.save.tmp"), "UTF-8");
			out.write(outStr);
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			plugin.logError("The save file could not be accessed!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File tempFile = new File(plugin.getDataFolder() + "/plates.save.tmp");
		File targetFile = new File(plugin.getDataFolder() + "/plates.save");
		if (!tempFile.renameTo(targetFile)) {
			if (!targetFile.delete()) {
				
			}
			if (!tempFile.renameTo(targetFile)) {
				plugin.logError("Unable to rename the temporary file!");
			}
		}
	}
}
