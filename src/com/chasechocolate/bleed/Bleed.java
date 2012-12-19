package com.chasechocolate.bleed;

import org.bukkit.plugin.java.JavaPlugin;

import com.chasechocolate.bleed.listeners.BleedListener;

public class Bleed extends JavaPlugin{
	
	public void log(String msg){
		getLogger().info(consoletitle + msg);
	}
	
	@Override
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new BleedListener(), this);
		log("Enabled!");
	}
	
	@Override
	public void onDisable(){
		log("Disabled!");
	}
	
	public final String consoletitle = "[Bleed] ";
}