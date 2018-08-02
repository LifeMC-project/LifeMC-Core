package net.lifemc.core.configurations;

import net.lifemc.core.Core;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConfigManager {


	private static ConfigManager instance = new ConfigManager();
	private Map<UUID, YamlConfiguration> playerfiles = new HashMap<>();

	private ConfigManager() {
	}

	public static ConfigManager get() {
		return instance;
	}

	public void loadPlayer(UUID id) {

		if (playerfiles.containsKey(id)) return;

		File playerCon = new File(Core.getInstance().getDataFolder(), id + ".yml");

		if (!playerCon.exists()) {
			playerCon.getParentFile().mkdirs();
			try {
				playerCon.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(playerCon);
		playerfiles.put(id, playerFile);
	}

	public void setPlayerValue(UUID id, String path, Object value) {
		loadPlayer(id);
		playerfiles.get(id).set(path, value);
	}

	//Instead of creating a function for every type, just cast the object to what you want.
	public Object getPlayerObject(UUID id, String path) {
		loadPlayer(id);

		return playerfiles.get(id).get(path);
	}

	//Try to run this as least as possible, very CPU intensive. If possible, run Async.
	public void savePlayer(UUID id) {
		if (playerfiles.containsKey(id)) {
			try {
				playerfiles.get(id).save(new File(Core.getInstance().getDataFolder(), id + ".yml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void unLoadPlayer(UUID id) {
		if (playerfiles.containsKey(id)) {
			playerfiles.remove(id);
		}
	}
}
