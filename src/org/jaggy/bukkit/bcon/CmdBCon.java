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
		if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("rem") 
				|| args[0].equalsIgnoreCase("list") || args.length >= 1) {
			
			if(args[0].equalsIgnoreCase("add")) {
				if(!(sender instanceof Player)){
					//console command support
					add(sender, args);
				} else {
					//in game support
				
					Player player = (Player) sender;
					if( !player.hasPermission("bcon.add") ) {
						player.sendMessage(ChatColor.RED + "You are not allowed to use this command.");
						
					} else {
						add(sender, args);
					}
				}
			}
			
			
			if(args[0].equalsIgnoreCase("rem")) {
				if(!(sender instanceof Player)){
					//console command support
					rem(sender, args);
				} else {
					//in game support
					Player player = (Player) sender;
					if( !player.hasPermission("bcon.rem") ) {
						player.sendMessage(ChatColor.RED + "You are not allowed to use this command.");	
					} else {
						rem(sender, args);
					}
				}
			}
			
			if(args[0].equalsIgnoreCase("list")) {
				if(!(sender instanceof Player)){
					//console command support
					list(sender, args);
				} else {
					//in game support
					Player player = (Player) sender;
					if( !player.hasPermission("bcon.list") ) {
						player.sendMessage(ChatColor.RED + "You are not allowed to use this command.");	
					} else {
						list(sender, args);
					}
				}
				
			}
			
			if(args[0].equalsIgnoreCase("load")) {
				if(!(sender instanceof Player)){
					//console command support
					load(sender);
				} else {
					//in game support
					Player player = (Player) sender;
					if( !player.hasPermission("bcon.load") ) {
						player.sendMessage(ChatColor.RED + "You are not allowed to use this command.");	
					} else {
						load(sender);
					}
				}
				
			}
			
			if(args[0].equalsIgnoreCase("save")) {
				if(!(sender instanceof Player)){
					//console command support
					save(sender);
				} else {
					//in game support
					Player player = (Player) sender;
					if( !player.hasPermission("bcon.save") ) {
						player.sendMessage(ChatColor.RED + "You are not allowed to use this command.");	
					} else {
						save(sender);
					}
				}
				
			}
			return true;
		} else {
			sender.sendMessage(ChatColor.YELLOW + "Usage: /<command> <add|rem|list> <filter>");
			sender.sendMessage(ChatColor.YELLOW + "List does not require a filter.");
			return true;
		}
	}
	
	
	/**
	 * Adds filter rule into memory
	 * @param sender
	 * @param args
	 */
	public void add(CommandSender sender, String[] args) {
		if(args.length < 2) {
			sender.sendMessage(ChatColor.YELLOW + "Usage: /bcon add <filter>.");
			sender.sendMessage(ChatColor.YELLOW + "%n = All Numerical playername");
		} else {
			String str = "";
			for(int i = 1; i < args.length; i++) str += args[i];
			BconStack.addFilter(str);
			if(sender instanceof Player) plugin.log(sender.getName() + " added to filters:" + str);
			sender.sendMessage(ChatColor.GREEN + "[Bcon] added to filters: " + str);
		}
	}
	
	
	/**
	 * Removes filter rule from memory.
	 * @param sender
	 * @param args
	 */
	public void rem(CommandSender sender, String[] args) {
		if(args.length < 2) {
			sender.sendMessage(ChatColor.YELLOW + "Usage: /bcon rem <filter>.");
		} else {
			String str = "";
			for(int i = 1; i < args.length; i++) str += args[i];
			BconStack.remFilter(str);
			if(sender instanceof Player) plugin.log(sender.getName() + " removed to filters:" + str);
			sender.sendMessage(ChatColor.GREEN + "[Bcon] removed to filters: " + str);
		}
	}
	
	public void save(CommandSender sender) {
		try {
			BconStack.save();
			if(sender instanceof Player) plugin.log(sender.getName() + " saved filters.");
			sender.sendMessage(ChatColor.GREEN + "[Bcon] saved filters.");
		} catch (IOException e) {
			if(sender instanceof Player) plugin.log("Failed to save filters.");
			sender.sendMessage(ChatColor.GREEN + "[Bcon] Failed to save filters.");
			e.printStackTrace();
		}
	}
	
	public void load(CommandSender sender) {
		try {
			BconStack.load();
			if(sender instanceof Player) plugin.log(sender.getName() + " loaded filters.");
			sender.sendMessage(ChatColor.GREEN + "[Bcon] Loaded filters.");
		} catch (IOException e) {
			if(sender instanceof Player) plugin.log("Failed to load filters.");
			sender.sendMessage(ChatColor.GREEN + "[Bcon] Failed to load filters.");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Lists filter Rules in memory
	 * @param sender
	 * @param args
	 */
	public void list(CommandSender sender, String[] args) {
		if(args.length > 2) {
			sender.sendMessage(ChatColor.YELLOW + "Usage: /bcon list");
		} else {
			String str = "";
			for(int i = 1; i < args.length; i++) str += args[i];
			str = BconStack.list(str);
			sender.sendMessage(ChatColor.GREEN + "[Bcon] list: " + str);
		}
	}
}
