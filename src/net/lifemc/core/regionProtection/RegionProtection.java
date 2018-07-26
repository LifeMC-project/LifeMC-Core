package net.lifemc.core.regionProtection;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.kugick.FarmLife.FarmLife;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


public class RegionProtection{	



	private static final WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
	private static final RegionManager mgr = wg.getRegionManager(Bukkit.getWorld("FarmLife"));


	public static ProtectedRegion getRegionStanding(Player p){
		for(ProtectedRegion rg:mgr.getApplicableRegions(p.getLocation())) {
			if(rg.toString().contains("farm_")){
				return rg;
			}
		}
		return null;	
	}

	public static boolean exists(Player p){
		String id = getRegionID(p);

		return mgr.hasRegion(id + "_farm");
	}

	public static boolean createFarmsRegion(Player p, Location plotHomeLocation){

		String id = getRegionID(p);

		FarmLife.getInstance().log.info("Creating farms region protection for player " + p.getName() + ".");


		if (mgr.hasRegion(id)) return false;

		//To be updated.
		int regionSize = 13;
		BlockVector farmMin = new BlockVector(plotHomeLocation.getX() - regionSize, 0.0D, plotHomeLocation.getZ() - regionSize);
		BlockVector farmMax = new BlockVector(plotHomeLocation.getX() + regionSize, 75.0D, plotHomeLocation.getX() + regionSize);



		ProtectedCuboidRegion farm = new ProtectedCuboidRegion(id + "_farm", farmMin, farmMax);
		ProtectedCuboidRegion path = new ProtectedCuboidRegion(id + "_path", farmMin, farmMax);
		ProtectedCuboidRegion cave = new ProtectedCuboidRegion(id + "_cave", farmMin, farmMax);
		
		Map<Flag<?>, Object> farmFlags = new HashMap<Flag<?>, Object>();
		farmFlags.put(DefaultFlag.BLOCK_BREAK, StateFlag.State.ALLOW);
		farmFlags.put(DefaultFlag.BLOCK_PLACE, StateFlag.State.ALLOW);
		farmFlags.put(DefaultFlag.ENTITY_ITEM_FRAME_DESTROY, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.ENTITY_PAINTING_DESTROY, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.POTION_SPLASH, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.CREEPER_EXPLOSION, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.ENDERDRAGON_BLOCK_DAMAGE, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.ENDER_BUILD, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.ENDERPEARL, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.GHAST_FIREBALL, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.GRASS_SPREAD, StateFlag.State.ALLOW);
		farmFlags.put(DefaultFlag.ICE_FORM, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.ICE_MELT, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.LEAF_DECAY, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.LIGHTER, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.OTHER_EXPLOSION, StateFlag.State.DENY);
		farmFlags.put(DefaultFlag.TNT, StateFlag.State.DENY);

		farm.setFlags(farmFlags);
		path.setFlags(farmFlags);
		cave.setFlags(farmFlags);
		
		mgr.addRegion(farm);
		mgr.addRegion(path);
		mgr.addRegion(cave);

		return true;
	}
	public static boolean removeFarmsRegion(Player p){
		String id = getRegionID(p);

		if (!mgr.hasRegion(id)) return false;

		FarmLife.getInstance().log.info("Removing farms region protection for player " + p.getName() + ".");
		mgr.removeRegion(id + "_farm");
		mgr.removeRegion(id + "_path");
		mgr.removeRegion(id + "_cave");

		return true;
	}

	public static boolean hasMember(Player p, Player nick){
		String id = getRegionID(p);

		if (!mgr.hasRegion(id)) return false;

		mgr.getRegion(id).getMembers().contains(nick.getUniqueId());

		return true;
	}
	public static boolean addMember(Player p, Player nick){
		String id = getRegionID(p);

		if (!mgr.hasRegion(id)) return false;

		mgr.getRegion(id).getMembers().addPlayer(nick.getUniqueId());

		return true;
	}
	public static boolean removeMember(Player p, Player nick){
		String id = getRegionID(p);

		if (!mgr.hasRegion(id)) return false;

		mgr.getRegion(id).getMembers().removePlayer(nick.getUniqueId());

		return true;
	}

	public static String getRegionID(Player p)
	{return "MyFarm" + "_" + p.getUniqueId().toString() ;}
	
	public static DefaultDomain getMembers (Player p) {
	DefaultDomain members = mgr.getRegion(getRegionID(p)).getMembers();
	return members;
	}
	
}
