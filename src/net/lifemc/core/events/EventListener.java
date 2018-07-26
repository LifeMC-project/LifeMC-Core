package net.lifemc.core.events;

import org.bukkit.event.Listener;

import net.lifemc.core.Core;

public class EventListener implements Listener{
	@SuppressWarnings("unused")
	private Core plugin;

	public EventListener(Core instance) {
		this.plugin = instance;
	}
	
	//Heyyy,this is one clean file to write on! ;) -kugick
}
