package org.jaggy.bukkit.bcon.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class YMLFile implements Config {

	private JavaPlugin plugin;
	private FileConfiguration bukkitConfig;
	
	@Override
	public void load(final JavaPlugin plugin) {
		this.plugin = plugin;
		
		// this forces Bukkit to load the config
		bukkitConfig = plugin.getConfig();
	}
	
	@Override
	public void save() throws Exception {
		plugin.saveConfig();
	}
	
	@Override
	public String getKickMsg() {
		return bukkitConfig.getString("KickMessage", defaultKickMsg);
	}

	
}
