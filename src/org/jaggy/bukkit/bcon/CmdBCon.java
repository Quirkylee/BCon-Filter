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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBCon implements CommandExecutor {
	private Bcon plugin = new Bcon();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player)sender;
		if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("rem") || args[0].equalsIgnoreCase("list")) {
		if( !player.hasPermission("bcon.add") ) {
			player.sendMessage(ChatColor.RED + "You are not allowed to use this command.");
			return true;
		} else {
			if(args[0].equalsIgnoreCase("add")) {
				if(args.length < 2) {
					player.sendMessage(ChatColor.YELLOW + "Usage: /bcon add <filter>.");
					player.sendMessage(ChatColor.YELLOW + "%n = All Numerical playername");
				} else {
					String str = "";
					for(int i = 1; i < args.length; i++) str += args[i];
					BconStack.addFilter(str);
					plugin.log(player.getName() + " added to filters:" + str);
					player.sendMessage(ChatColor.GREEN + "[Bcon] added to filters: " + str);
				}
			return true;		
			}
		}
		if( !player.hasPermission("bcon.rem") ) {
			player.sendMessage(ChatColor.RED + "You are not allowed to use this command.");
			return true;
		} else {
			if(args[0].equalsIgnoreCase("rem")) {
				if(args.length < 2) {
					player.sendMessage(ChatColor.YELLOW + "Usage: /bcon rem <filter>.");
				} else {
					String str = "";
					for(int i = 1; i < args.length; i++) str += args[i];
					BconStack.addFilter(str);
					plugin.log(player.getName() + " removed to filters:" + str);
					player.sendMessage(ChatColor.GREEN + "[Bcon] removed to filters: " + str);
				}
			return true;		
			}
		}
		if( !player.hasPermission("bcon.list") ) {
			player.sendMessage(ChatColor.RED + "You are not allowed to use this command.");
			return true;
		} else {
			if(args[0].equalsIgnoreCase("list")) {
				if(args.length > 2) {
					player.sendMessage(ChatColor.YELLOW + "Usage: /bcon list");
				} else {
					String str = "";
					for(int i = 1; i < args.length; i++) str += args[i];
					str = BconStack.list(str);
					
					player.sendMessage(ChatColor.GREEN + "[Bcon] " + str);
				}
			return true;		
			}
		}
		} else {
			player.sendMessage(ChatColor.YELLOW + "Usage: /<command> <add|rem|list> <filter>");
			player.sendMessage(ChatColor.YELLOW + "List does not require a filter.");
			return true;
		}
		return false;
	}

	
}
