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
		String staffmessage = ChatColor.translateAlternateColorCodes('&', user.getSuffix()
				.replace("%level%", String.valueOf(FarmLifeAPI.getLevel(player.getUniqueId())))
				.replace("%karma%", String.valueOf(FarmLifeAPI.getKarma(player.getUniqueId())))
				.replace("%name%", player.getName()));

		String message = ChatColor.translateAlternateColorCodes('&', user.getPrefix()
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
	
	
	/*
 	@EventHandler(priority = EventPriority.NORMAL)
	public void onChat(AsyncPlayerChatEvent e){

		Player p = e.getPlayer();
		
		if(e.getMessage().charAt(0) == '!') {
			//p.sendMessage("("+e.getMessage().replaceFirst("!", "")+")");
			e.setCancelled(true);
		}
		
		
		//Get pex user from database
		PermissionUser user = PermissionsEx.getUser(p);
		if(user.inGroup("Administrator")) {
			e.setFormat(ChatColor.RED + "Administrator" + ChatColor.DARK_GRAY + " : " + ChatColor.DARK_RED + p.getName() + ChatColor.GRAY + " > " + ChatColor.RESET + e.getMessage());
		}
		else
			if(user.inGroup("Player") && !user.inGroup("sub_FarmLife__vip") | user.inGroup("Player") && !user.inGroup("sub_FarmLife_NewFarmer")){
				e.setFormat(ChatColor.DARK_GREEN + "Farmer" + ChatColor.DARK_GRAY + " : " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " > " + ChatColor.RESET + e.getMessage());
			}
		if(user.inGroup("sub_FarmLife_NewFarmer")){
			e.setFormat(ChatColor.DARK_GREEN + "New Farmer" + ChatColor.DARK_GRAY + " : " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " > " + ChatColor.RESET + e.getMessage());
		}
		if(user.inGroup("sub_FarmLife_vip")){
			e.setFormat(ChatColor.GOLD + "VIP Farmer" + ChatColor.DARK_GRAY + " : " + ChatColor.YELLOW + p.getName() + ChatColor.GRAY + " > " + ChatColor.RESET + e.getMessage());
		}
		if(user.inGroup("TrialModerator") | user.inGroup("Moderator") | user.inGroup("LeadModerator") | user.inGroup("HeadModerator") | user.inGroup("TrialGamemaster") | user.inGroup("s_Gamemaster") | user.inGroup("LeadGamemaster") | user.inGroup("HeadGamemaster") | user.inGroup("TrialBuilder") | user.inGroup("Builder") | user.inGroup("LeadBuilder") | user.inGroup("HeadBuilder") | user.inGroup("Promoter") | user.inGroup("NewDeveloper") | user.inGroup("Developer") | user.inGroup("LeadDeveloper") | user.inGroup("HeadDeveloper")){
			e.setFormat(ChatColor.DARK_PURPLE + "Staff" + ChatColor.DARK_GRAY + " : " + ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.GRAY + " > " + ChatColor.RESET + e.getMessage());

		}
	}
	 */
}
