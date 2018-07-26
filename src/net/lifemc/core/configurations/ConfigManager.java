package net.lifemc.core.configurations;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import net.lifemc.core.Core;

public class ConfigManager {
	
	//////////////////////////
	//PLAYER CONFIG MANAGER//
	///////////////////////// 
	
	
	
	public static void loadPlayer(Player p){
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
	}


}
