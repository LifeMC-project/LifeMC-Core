

public class Core extends JavaPlugin {
	
	public static Core getInstance() {
		if ((Bukkit.getServer().getPluginManager().getPlugin("LifeMC-Core") instanceof LifeMC-Core)) {
			return (LifeMC-Core) Bukkit.getServer().getPluginManager().getPlugin("LifeMC-Core");
		}
		return null;
	}

}
