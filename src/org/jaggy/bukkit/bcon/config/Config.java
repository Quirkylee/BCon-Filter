/** Basic config for a plugin.
 * 
 */
package org.jaggy.bukkit.bcon.config;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author matthewl
 *
 */
public interface Config {
	public String defaultKickMsg = "[BCon] Your not allowed to join the server.";
	
	public String getKickMsg();
	
	public void save() throws Exception;
	public void load(JavaPlugin plugin);

}
