package com.twosquaredstudios.PlayerPressure;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityListener;

public class PlayerPressureEntityListener extends EntityListener {
	private final PlayerPressure plugin;
	Logger log = Logger.getLogger("Minecraft");
	
	public PlayerPressureEntityListener(PlayerPressure instance) {
		plugin = instance;
	}
	
	public void onEntityInteract(EntityInteractEvent event) {
		if (event.getBlock().getType().equals(Material.STONE_PLATE)) {
			if (plugin.getPlayerPressureConfig().isInPlatesList(event.getBlock().getLocation())) {
				event.setCancelled(true);
			}
		}
	}
}
