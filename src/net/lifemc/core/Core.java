package net.lifemc.core;

import net.lifemc.core.events.ChatListener;
import net.lifemc.core.events.PlayerServerListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Core extends JavaPlugin {
	
	public Logger log = Bukkit.getLogger();
	
	public static Core getInstance() {
		if ((Bukkit.getServer().getPluginManager().getPlugin("LifeMC-Core") instanceof Core)) {
			return (Core) Bukkit.getServer().getPluginManager().getPlugin("LifeMC-Core");
		}
		return null;
	}

	public void onEnable() {
		log.info("LifeMC-Core enabled.ß");

		register(new ChatListener(),
				new PlayerServerListener());
	}

	private void register(Listener... listeners) {
		for (Listener l : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(l, this);
		}
	}

	public void onDisable() {
		log.info("LifeMC-Core disabled.");
	}

}