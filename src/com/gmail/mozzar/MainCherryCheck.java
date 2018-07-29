package com.gmail.mozzar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MainCherryCheck extends JavaPlugin{

	public static MainCherryCheck plugin;
	public static List<String> checkedPlayers = new ArrayList<>();
	public static String commandsblocked,logoutcmd,playerkickduringcheck,roomchat,setroom, setroomerror, perm,broadcastclear, nopermission, alreadycheck,checkyes, wrongusage, playeroffline,broadcastcheck, playerchecked, nocheck;
	
	public void onEnable(){
		plugin = this;
		this.saveDefaultConfig();
		this.reloadMessages();
		this.getCommand("cherrycheck").setExecutor(new CherryCheckCmds());
		this.getCommand("cherrysetroom").setExecutor(new CherryCheckCmds());
		this.getCommand("cherryclear").setExecutor(new CherryCheckCmds());
		this.getCommand("cherryreload").setExecutor(new CherryCheckCmds());
		Bukkit.getPluginManager().registerEvents(new CheckRoomEvents(), this);
		
		
	}
	
	public void reloadMessages(){
		try{
			perm = getConfig().getString("permissions.usage");
			nopermission = getConfig().getString("messages.nopermission").replace("&", "§");
			alreadycheck = getConfig().getString("messages.playeralreadycheck").replace("&", "§");
			checkyes = getConfig().getString("messages.playercheck").replace("&", "§");
			wrongusage = getConfig().getString("messages.wrongusage").replace("&", "§");
			playeroffline = getConfig().getString("messages.playeroffline").replace("&", "§");
			broadcastcheck = getConfig().getString("messages.broadcasts.check").replace("&", "§");
			playerchecked = getConfig().getString("messages.playerischecked").replace("&", "§");
			nocheck = getConfig().getString("messages.playernocheck").replace("&", "§");
			broadcastclear = getConfig().getString("messages.broadcasts.clear").replace("&", "§");
			setroom = getConfig().getString("messages.setroom").replace("&", "§");
			setroomerror = getConfig().getString("messages.errorsetroom").replace("&", "§");
			roomchat = getConfig().getString("messages.roomchat").replace("&", "§");
			logoutcmd = getConfig().getString("commands.logout");
			playerkickduringcheck = getConfig().getString("messages.kickduringcheck").replace("&", "§");
			commandsblocked = getConfig().getString("messages.commandsblocked").replace("&", "§");
			}catch(Exception exc){
				//System.out.println("CherryCheck » Check Config , there's a few bugs!");
				
				exc.printStackTrace();
			}
	}
	
	public static boolean hasperm(String pl, String permission){
		PermissionUser user = PermissionsEx.getUser(pl);
		if(user.has(permission)){
			return true;
		}
		return false;
	}
	public static boolean isOnline(String pl){
		if(Bukkit.getPlayer(pl).isOnline() == true){
			return true;
		}
		
		return false;
	}
	
	
	public static MainCherryCheck getPlugin(){
		return plugin;
	}
	
	
	
	
}
