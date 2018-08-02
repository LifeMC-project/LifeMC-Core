package net.lifemc.core.events;

import net.lifemc.core.FarmLifeAPI;
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

		PermissionUser user = PermissionsEx.getUser(player);

		String staffmessage = ChatColor.translateAlternateColorCodes('&', user.getOption("staffchat")
				.replace("%level%", String.valueOf(FarmLifeAPI.getLevel(player.getUniqueId())))
				.replace("%karma%", String.valueOf(FarmLifeAPI.getKarma(player.getUniqueId())))
				.replace("%name%", player.getName()));

		String message = ChatColor.translateAlternateColorCodes('&', user.getOption("playerchat")
				.replace("%level%", String.valueOf(FarmLifeAPI.getLevel(player.getUniqueId())))
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
