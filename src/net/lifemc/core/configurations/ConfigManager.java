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

	private void loadPlayer(UUID id) {

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
	//////////////////////////
	//PLAYER CONFIG MANAGER//
	///////////////////////// 
	
	
	
	/* public static void loadPlayer(Player p){
		File playerCon = new File(Core.getInstance().getDataFolder(), p.getPlayer().getUniqueId().toString() + ".yml");

		if(!playerCon.exists()){
			Core.getInstance().log.info("[FarmLife] Creating a new config for player " + p.getName() + ".");
			playerCon.getParentFile().mkdirs();
			try {
				playerCon.createNewFile();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(playerCon);
		try{
			playerFile.load(playerCon);
			playerFile.save(playerCon);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InvalidConfigurationException e){	
			e.printStackTrace();
		}
	}

/*	public static void loadPlayer(UUID id){
		File playerCon = new File(Core.getInstance().getDataFolder(), id + ".yml");

		if(!playerCon.exists()){
			Core.getInstance().log.info("[FarmLife] Creating a new config for UUID " + id + ".");
			playerCon.getParentFile().mkdirs();
			try {
				playerCon.createNewFile();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(playerCon);
		try{
			playerFile.load(playerCon);
			playerFile.save(playerCon);
		}
		catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void setPlayerValue(Player p, String path, Object value) {
		Core.getInstance().log.info("[FarmLife] Setting '" + path + "' to '" + value + "' for " + p.getName() + ".");
		File playerCon = new File(Core.getInstance().getDataFolder(), p.getPlayer().getUniqueId().toString() + ".yml");
		YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(playerCon);
		if(!playerCon.exists()){
			loadPlayer(p);
		}
		playerFile.set(path, value);
		try {
			playerFile.save(playerCon);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPlayerString(Player p, String path){
		File playerCon = new File(Core.getInstance().getDataFolder(), p.getPlayer().getUniqueId().toString() + ".yml");

		if(!playerCon.exists()){
			loadPlayer(p);
		}
		YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(playerCon);
		return playerFile.getString(path);
	}

	public static int getPlayerInt(UUID id, String path) {
		File playerCon = new File(Core.getInstance().getDataFolder(), id + ".yml");

		if(!playerCon.exists()){
			//loadPlayer(id);
		}
		YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(playerCon);
		return playerFile.getInt(path);

	}

	public static int getPlayerInt(Player p, String path){
		File playerCon = new File(Core.getInstance().getDataFolder(), p.getPlayer().getUniqueId().toString() + ".yml");

		if(!playerCon.exists()){
			loadPlayer(p);
		}
		YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(playerCon);
		return playerFile.getInt(path);
	}
	public static Double getPlayerDouble(Player p, String path){
		File playerCon = new File(Core.getInstance().getDataFolder(), p.getPlayer().getUniqueId().toString() + ".yml");

		if(!playerCon.exists()){
			loadPlayer(p);
		}
		YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(playerCon);
		return playerFile.getDouble(path);
	}
	public static List<String> getPlayerList(Player p, String path){
		File playerCon = new File(Core.getInstance().getDataFolder(), p.getPlayer().getUniqueId().toString() + ".yml");

		if(!playerCon.exists()){
			loadPlayer(p);
		}
		YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(playerCon);
		return playerFile.getStringList(path);
	}

	public static void savePlayer(Player p){
		File playerCon = new File(Core.getInstance().getDataFolder(), p.getPlayer().getUniqueId().toString() + ".yml");
		YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(playerCon);
		try {
			playerFile.save(playerCon);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	} */


	//REWRITING EVERY THING
	/* Step 1: Use a Singleton, not static
	   Step 2: Stop loading all files over and over.
	   Step 3: Don't save unless a player leaves the server.
	 */

}
