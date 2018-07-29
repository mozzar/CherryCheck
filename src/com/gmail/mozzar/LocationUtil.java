package com.gmail.mozzar;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtil {

	private static double x,y,z;

	private static String world;
	private static MainCherryCheck main = MainCherryCheck.getPlugin();
	
	public static boolean addConfigLocation(Location loc, String player){
		try{
			
			x = loc.getX();
			y = loc.getY();
			z = loc.getZ();
			world = loc.getWorld().getName();
			main.getConfig().set("checked." + player + ".x", x);
			main.getConfig().set("checked." + player + ".y", y);
			main.getConfig().set("checked." + player + ".z", z);
			main.getConfig().set("checked." + player + ".world", world);
			main.getConfig().options().copyDefaults(true);
			main.saveConfig();
			return true;
		
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
		
		
	}
	
	
	public static boolean setRoom(Location loc){
		try{
			x = loc.getX();
			y = loc.getY();
			z = loc.getZ();
			world = loc.getWorld().getName();
			main.getConfig().set("checkroom.x", x);
			main.getConfig().set("checkroom.y", y);
			main.getConfig().set("checkroom.z", z);
			main.getConfig().set("checkroom.world", world);
			main.getConfig().options().copyDefaults(true);
			main.saveConfig();
			return true;
			
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean removePlayerConfig(String player){
		try{
			main.getConfig().set("checked."+ player, null);
			main.getConfig().options().copyDefaults(true);
			main.saveConfig();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static Location getRoomLocation(){
		try{
			x = main.getConfig().getDouble("checkroom.x");
			y = main.getConfig().getDouble("checkroom.y");
			z = main.getConfig().getDouble("checkroom.z");
			world = main.getConfig().getString("checkroom.world");
			World w = Bukkit.getServer().getWorld(world);
			Location loc = new Location(w, x,y,z);
			return loc;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static Location getLocationFromConfig(String player){
		try{
			x = main.getConfig().getDouble("checked." + player + ".x");
			y = main.getConfig().getDouble("checked." + player + ".y");
			z = main.getConfig().getDouble("checked." + player + ".z");
			world = main.getConfig().getString("checked." + player + ".world");
			World w = Bukkit.getServer().getWorld(world);
			Location loc = new Location(w, x,y,z);
			return loc;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
}
