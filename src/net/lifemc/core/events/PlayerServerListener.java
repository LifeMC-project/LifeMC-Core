package net.lifemc.core.events;

import net.lifemc.core.configurations.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerServerListener implements Listener {

	public PlayerServerListener() {

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		ConfigManager.get().loadPlayer(e.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		ConfigManager.get().savePlayer(e.getPlayer().getUniqueId());
		ConfigManager.get().unLoadPlayer(e.getPlayer().getUniqueId());

	}


}
