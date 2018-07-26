package net.lifemc.core.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.plotsquared.bukkit.events.PlayerTeleportToPlotEvent;

import net.kugick.FarmLife.FarmLife;
import net.kugick.FarmLife.Utils;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EventListener implements Listener{
	@SuppressWarnings("unused")
	private FarmLife plugin;

	public EventListener(FarmLife instance) {
		this.plugin = instance;
	}


	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		//Enum all blocks used at right state like wheat fully grown
		//Wheat, Carrots, Potatos, Melons, Pumpkins, Cocoa Beans
		//Give money/reward?

		Block b =  e.getBlock();
		Player breaker = e.getPlayer();

		//Wheat
		if(b.getType() == Material.WHEAT){
			if(Utils.isFullyGrown(b)){

			}
		}
		//Carrots
		if(b.getType() == Material.CARROT){
			if(Utils.isFullyGrown(b)){

			}
		}
		//Potatos
		if(b.getType() == Material.POTATO){
			if(Utils.isFullyGrown(b)){

			}
		}
		//Cocoa beans
		if(b.getType() == Material.COCOA){
			if(Utils.isFullyGrown(b)){

			}
		}
		//Melons
		if(b.getType() == Material.MELON_BLOCK){

		}
		//Pumpkins
		if(b.getType() == Material.PUMPKIN){
			if(Utils.isFullyGrown(b)){

			}
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){

		//Entity list
		Sheep s = (Sheep)e.getEntity();
		Pig p = (Pig)e.getEntity();
		Cow c = (Cow)e.getEntity();


		if (e.getEntity() instanceof Sheep && e.getEntity() instanceof Pig && e.getEntity() instanceof Cow){

			//Sheep
			if(e.getEntity() == s){
				Player pl = s.getKiller();
			}
			//Pig
			if(e.getEntity() == p){
				Player pl = p.getKiller();
			}
			//Cow
			if(e.getEntity() == c){
				Player pl = c.getKiller();
			}
		}
	}

	//Create region protection check each time.
	@EventHandler
	public void onTeleportToFarm(PlayerTeleportToPlotEvent e){
		Player p = e.getPlayer();
		if(!RegionProtection.exists(p)) {
			
			Location plotHomeLocation = new Location(Bukkit.getWorld(e.getPlot().getHome().getWorld()), e.getPlot().getHome().getX(), e.getPlot().getHome().getY(), e.getPlot().getHome().getZ());
			RegionProtection.createFarmsRegion(p, plotHomeLocation);
		}
	}

	//Since I can't simply format the chat across plugins like I want, decided to just take over it.

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
}
