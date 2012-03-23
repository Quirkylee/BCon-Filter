/*
BCon Filter: Blocks players from connecting to a bukkit server.
Copyright (C) 2012  matthewl6970 (Matthew Lindsey)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jaggy.bukkit.bcon;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/*
BCon Filter: Blocks players from connecting to a bukkit server.
Copyright (C) 2012  matthewl6970 (Matthew Lindsey)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
public class Bcon extends JavaPlugin {
	private static final Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		try {
			BconStack.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new BconListener(), this);
		
		getCommand("bcon").setExecutor(new CmdBCon());
		
	}
	public void onDisable() {
		try {
			BconStack.save();
		} catch (IOException e) {
			log(e.toString());
			e.printStackTrace();
		}
	}
	public void log(String msg) {
		log.info("[BCon] "+msg);
	}
}
