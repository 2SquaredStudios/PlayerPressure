package com.twosquaredstudios.PlayerPressure;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class PlayerPressurePlayerListener extends PlayerListener {
	private final PlayerPressure plugin;
	Logger log = Logger.getLogger("Minecraft");
	
	public PlayerPressurePlayerListener(PlayerPressure instance) {
		plugin = instance;
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.isCancelled()) return;
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.STONE_PLATE)) {
			if (plugin.getPlayerPressureConfig().isInPlatesList(event.getClickedBlock().getLocation())) {
				if (event.getPlayer().hasPermission("playerpressure.create")) {
					event.getPlayer().sendMessage("Plate is already only triggered by players.");
					event.setCancelled(true);
				} else {
					event.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to make a pressure plate triggered only by players.");
				}
			} else {
				if (event.getPlayer().hasPermission("playerpressure.create")) {
					plugin.getPlayerPressureConfig().addToPlatesList(event.getClickedBlock().getLocation());
					event.getPlayer().sendMessage("Plate will now only be triggered by players.");
					event.setCancelled(true);
				} else {
					event.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to make a pressure plate triggered only by players.");
				}
			}
		}
	}
}