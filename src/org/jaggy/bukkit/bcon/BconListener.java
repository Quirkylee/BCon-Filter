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

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jaggy.bukkit.bcon.config.Config;


public class BconListener implements Listener {
	@EventHandler()
	public void onConnect(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("bcon.bypass")) return;
		if(BconStack.compare(player.getName())) {
			event.disallow(org.bukkit.event.player.PlayerLoginEvent.Result.KICK_OTHER, 
					Config.defaultKickMsg);
		}
		
	}

}
