package net.lifemc.core;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
	
	public Logger log = Bukkit.getLogger();
	
	public void onEnable() {
		log.info("LifeMC-Core enabled.ß");
		
	}
	public void onDisable() {
		log.info("LifeMC-Core disabled.");
	}
	
	public static Core getInstance() {
		if ((Bukkit.getServer().getPluginManager().getPlugin("LifeMC-Core") instanceof Core)) {
			return (Core) Bukkit.getServer().getPluginManager().getPlugin("LifeMC-Core");
		}
		return null;
	}

}