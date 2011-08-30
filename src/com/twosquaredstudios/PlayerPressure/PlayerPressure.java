package com.twosquaredstudios.PlayerPressure;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class PlayerPressure extends JavaPlugin {
	private PluginDescriptionFile pdfFile;
	private final PlayerPressurePlayerListener ppPlayerListener = new PlayerPressurePlayerListener(this);
	private final PlayerPressureEntityListener ppEntityListener = new PlayerPressureEntityListener(this);
	private final PlayerPressureConfig ppConfig = new PlayerPressureConfig(this);
	Logger log = Logger.getLogger("Minecraft");

	@Override
	public void onEnable() {
		pdfFile = this.getDescription();
		ppConfig.loadConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, ppPlayerListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.ENTITY_INTERACT, ppEntityListener, Event.Priority.Highest, this);
		log.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}
	
	@Override
	public void onDisable() {
		ppConfig.saveConfig();
		log.info(pdfFile.getName() + " is disabled!");
	}
	
	public PlayerPressureConfig getPlayerPressureConfig() {
		return ppConfig;
	}
	
	public void logInfo(String string) {
		log.info("[" + pdfFile.getName() + "] " + string);
	}
	
	public void logWarning(String string) {
		log.warning("[" + pdfFile.getName() + "] " + string);
	}
	
	public void logError(String string) {
		log.severe("[" + pdfFile.getName() + "] " + string);
	}
}
