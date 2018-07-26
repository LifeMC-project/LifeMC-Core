package net.lifemc.core.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;


public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();

		//TODO Add Karma and Level API
		PermissionUser user = PermissionsEx.getUser(player);
		String staffmessage = ChatColor.translateAlternateColorCodes('&', user.getSuffix()
				.replace("%level%", "0")
				.replace("%karma%", "0")
				.replace("%name%", player.getName()));

		String message = ChatColor.translateAlternateColorCodes('&', user.getPrefix()
				.replace("%level%", "0")
				.replace("%name%", player.getName()));

		for (Player pl : Bukkit.getServer().getOnlinePlayers()) {

			if (pl.hasPermission("staff.chat")) {
				pl.sendMessage(staffmessage.replace("%msg%", e.getMessage()));
			} else {
				pl.sendMessage(message.replace("%msg%", e.getMessage()));
			}

		}
	}
}
