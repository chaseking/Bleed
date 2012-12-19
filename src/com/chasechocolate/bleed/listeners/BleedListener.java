package com.chasechocolate.bleed.listeners;

import net.minecraft.server.Packet61WorldEvent;

import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class BleedListener implements Listener{
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event){
		Entity entity = event.getEntity();
		if(entity instanceof Player){
			Player player = (Player) event.getEntity();
			Location playerLoc = player.getLocation();
			
			int blockX = playerLoc.getBlockX();
			int blockY = playerLoc.getBlockY();
			int blockZ = playerLoc.getBlockZ();
			
			//double locX = playerLoc.getX();
			//double locY = playerLoc.getY();
			//double locZ = playerLoc.getZ();
			
			Packet61WorldEvent packet61 = new Packet61WorldEvent(2001, blockX, blockY, blockZ, 11, false);
			
			((CraftPlayer)player).getHandle().netServerHandler.sendPacket(packet61);
			
			for(Entity nearbyEntity : player.getNearbyEntities(50, 50, 50)){
				if(nearbyEntity instanceof Player){
					Player nearbyPlayer = (Player) nearbyEntity;
					((CraftPlayer)nearbyPlayer).getHandle().netServerHandler.sendPacket(packet61);
				}
			}
			//((CraftServer)Bukkit.getServer()).getHandle().getServerConfigurationManager().sendPacketNearby(x, y, z, radius, dimension, packet61);
		}
	}
}