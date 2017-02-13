package com.havensden.utilities.sounds;

import java.util.HashMap;

import com.havensden.utilities.HavensDenUtilities;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SoundFactory 
{
	private static HashMap<String, Music> music = new HashMap<String, Music>();
	
	
	//Enter sounds to register here
	public static void init()
	{	
		addMusic("wolfswacht_theme");
		addMusic("unterstadt_theme");
	}
	
	public static SoundEvent register(String pFileName)
	{
		ResourceLocation lLoc = new ResourceLocation(HavensDenUtilities.MODID + ":" + pFileName);
		SoundEvent lEvent = new SoundEvent(lLoc).setRegistryName(lLoc);
		
		GameRegistry.register(lEvent);
		
		return lEvent;
	}
	
	private static void addMusic(String pFileName)
	{
		Music lMusic = new Music(register(pFileName));
		
		music.put(pFileName, lMusic);
	}
	
	public static Music getMusic(String pName)
	{
		return music.get(pName);
	}
	
	public static Music[] getMusics()
	{
		return music.values().toArray(new Music[music.values().size()]);
	}
}
