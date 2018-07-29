package com.gmail.mozzar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CheckRoomEvents implements Listener{
	
	private static MainCherryCheck main = MainCherryCheck.getPlugin();
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
	public void ChatEvent(AsyncPlayerChatEvent e){
		Player player = e.getPlayer();
		if(main.checkedPlayers.contains(player.getName().toLowerCase())){
			e.setFormat(main.roomchat.replace("%player%", player.getName()).replace("%message%", e.getMessage()));
			return;
		}
		
		
	}
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
	public void CheckLeave(PlayerQuitEvent e){
		Player player = e.getPlayer();
		if(main.checkedPlayers.contains(player.getName().toLowerCase())){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), main.logoutcmd.replace("%player%", player.getName()));
			main.checkedPlayers.remove(player.getName().toLowerCase());
			
			LocationUtil.removePlayerConfig(player.getName().toLowerCase());
		}
	}
	
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void checkedkick(PlayerKickEvent e){
		Player player = e.getPlayer();
		if(main.checkedPlayers.contains(player.getName().toLowerCase())){
			Bukkit.broadcastMessage(main.playerkickduringcheck.replace("%player%", player.getName()));
			main.checkedPlayers.remove(player.getName().toLowerCase());
			
			LocationUtil.removePlayerConfig(player.getName().toLowerCase());
		}
	}
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void checkCommands(PlayerCommandPreprocessEvent e){
		Player player = e.getPlayer();
		if(main.checkedPlayers.contains(player.getName().toLowerCase())){
			e.setCancelled(true);
			player.sendMessage(main.commandsblocked);
		}
	}
	
	
	
}
