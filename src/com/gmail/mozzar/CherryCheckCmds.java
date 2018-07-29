package com.gmail.mozzar;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.gmail.mozzar.LocationUtil;
public class CherryCheckCmds implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String l,
			String[] args) {
		
		MainCherryCheck main = MainCherryCheck.getPlugin();
		String player = s.getName();
		if(cmd.getName().equalsIgnoreCase("cherrycheck")){
			if(main.hasperm(player, main.perm) == true){
				if(args.length == 1){
					String playercheck = args[0].toLowerCase();
					if(main.isOnline(playercheck) == true){
						if(!main.checkedPlayers.contains(playercheck)){
							Location locfirst = s.getServer().getPlayer(playercheck).getLocation();
							LocationUtil.addConfigLocation(locfirst, playercheck);
							
							Bukkit.getServer().getPlayer(playercheck).teleport(LocationUtil.getRoomLocation());
							s.getServer().getPlayer(s.getName()).teleport(LocationUtil.getRoomLocation());
							
							s.sendMessage(main.checkyes.replace("%player%", playercheck));
							s.getServer().getPlayer(playercheck).sendMessage(main.playerchecked.replace("%admin%", player));
							main.getServer().broadcastMessage(main.broadcastcheck.replace("%admin%", player).replace("%player%",playercheck));
							main.checkedPlayers.add(playercheck);
							return true;
							
							
						}else{
							s.sendMessage(main.alreadycheck.replace("%player%", playercheck));
							return true;
						}
					}else{
						s.sendMessage(main.playeroffline);
						return true;
					}
				}else{
					s.sendMessage(main.wrongusage);
					return true;
				}
				
			}else{
				s.sendMessage(main.nopermission);
				return true;
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("cherrysetroom")){
			if(main.hasperm(player, main.perm) == true){
				Location loc = s.getServer().getPlayer(s.getName()).getLocation();
				if(LocationUtil.setRoom(loc) == true){
					s.sendMessage(main.setroom.replace("%position%", loc.toString()));
					return true;
				}else{
					s.sendMessage(main.setroomerror);
					return true;
				}
				
			}else{
				s.sendMessage(main.nopermission);
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("cherryreload")){
			if(main.hasperm(player, main.perm) == true){
				if(args.length == 0){
					main.reloadMessages();
					s.sendMessage("Config Reloaded!");
					return true;
				}else{
					s.sendMessage(main.wrongusage);
					return true;
				}
			}else{
				s.sendMessage(main.nopermission);
				return true;
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("cherryclear")){
			if(main.hasperm(player, main.perm) == true){
				if(args.length == 1){
					String playercheck = args[0].toLowerCase();
					if(main.isOnline(playercheck) == true){
						if(main.checkedPlayers.contains(playercheck)){
						
							main.getServer().broadcastMessage(main.broadcastclear.replace("%player%",playercheck));
							main.checkedPlayers.remove(playercheck);
							main.getServer().getPlayer(playercheck).teleport(LocationUtil.getLocationFromConfig(playercheck));
							LocationUtil.removePlayerConfig(playercheck);
							
							
							
							return true;
							
							
						}else{
							s.sendMessage(main.nocheck.replace("%player%", playercheck));
							return true;
						}
					}else{
						s.sendMessage(main.playeroffline);
						return true;
					}
				}else{
					s.sendMessage(main.wrongusage);
					return true;
				}
			}else{
				s.sendMessage(main.nopermission);
				return true;
			}
		}
		
		return false;
	}

	
	
	
	
}
