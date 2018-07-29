package net.lifemc.core;

import net.lifemc.core.configurations.ConfigManager;

import java.util.UUID;

public class FarmLifeAPI {

	public static int getLevel(UUID id) {
		try {
			return (int) ConfigManager.get().getPlayerObject(id, id + ".level");
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getKarma(UUID id) {
		try {
			return (int) ConfigManager.get().getPlayerObject(id, id + ".karma");
		} catch (Exception e) {
			return 0;
		}
	}

	//TODO Add other data from the Core needed.
}
