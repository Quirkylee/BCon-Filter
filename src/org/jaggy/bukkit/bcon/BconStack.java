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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class BconStack implements Runnable {
	public static ArrayList<String> stack = new ArrayList<String>();
	
	public static void addFilter(String str) {
		try {
			stack.add(str.toLowerCase());
		} 
		   catch( Exception e )
		   {
			   
		   }
	}
	public static void remFilter(String str) {
		
		stack.remove(str);
	}
	
	private static boolean isInteger( String input )
	{
	   try
	   {
	      Integer.parseInt( input );
	      return true;
	   }
	   catch( Exception e )
	   {
	      return false;
	   }
	}

	public static boolean compare(String str) {
		if(isInteger(str)) {
			if(stack.equals("%n")) return true;
		} else {
			try {
				str = str.toLowerCase();
				for(int i = 0; i < stack.size(); i++) {
					String s = stack.get(i);		
				if(str.contains(s)) return true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	public static void load() throws IOException {
		File Folder = new File("plugins/Bcon Filter");
		File file = new File("plugins/Bcon Filter/filters.txt");
		if(!Folder.exists()) Folder.mkdir();
		if(file.exists()) {
			
			FileInputStream fstream = new FileInputStream("plugins/Bcon Filter/filters.txt");
			
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in)); 
			
			String strLine;
				while((strLine = br.readLine()) != null) {
					addFilter(strLine);
				}
				in.close();
		} else
				file.createNewFile();
		
	}
	public static String list(String str) {
		str = str.toLowerCase();
		String s = "";
		boolean first = true;
		for(int i = 0; i < stack.size(); i++) {
			if(first) s = stack.get(i);
			else s += ", " + stack.get(i);
		}
		return s;
	}
	
	public static void save() throws IOException {
		File Folder = new File("plugins/Bcon Filter");
		File file = new File("plugins/Bcon Filter/filters.txt");
		if(!Folder.exists()) Folder.mkdir();
		if(!file.exists()) file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter("plugins/Bcon Filter/filters.txt"));
		for(int i = 0; i < stack.size(); i++) {
			String s = stack.get(i);
			bw.write(s);
			bw.newLine();
		}
			bw.close();
		
	}
	@Override
	public void run() {
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
