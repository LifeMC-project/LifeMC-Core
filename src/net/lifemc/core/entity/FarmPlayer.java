package net.lifemc.core.entity;


import net.lifemc.core.FarmLifeAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class FarmPlayer {

	private int level;
	private int karma;
	private int experience;
	private Player player;

	public FarmPlayer(Player player) {
		this.experience = FarmLifeAPI.getExperience(player.getUniqueId());
		this.level = FarmLifeAPI.getLevel(player.getUniqueId());
		this.karma = FarmLifeAPI.getKarma(player.getUniqueId());
		this.player = player;
	}

	public UUID getUUID() {
		return player.getUniqueId();
	}

	public Location getLocation() {
		return player.getLocation();
	}

	public void updateInventory() {
		player.updateInventory();
	}

	public void openInventory(Inventory inventory) {
		player.openInventory(inventory);
	}

	public void teleport(Location location) {
		player.teleport(location);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getKarma() {
		return karma;
	}

	public void setKarma(int karma) {
		this.karma = karma;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Player getPlayer() {
		return player;
	}
}
