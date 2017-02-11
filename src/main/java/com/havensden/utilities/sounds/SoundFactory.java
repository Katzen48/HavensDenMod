package com.havensden.utilities.sounds;

import com.havensden.utilities.HavensDenUtilities;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SoundFactory 
{
	public static SoundEvent striptease;
	
	
	//Enter sounds to register here
	public static void init()
	{	
		striptease = register("striptease");
	}
	
	public static SoundEvent register(String pName)
	{
		ResourceLocation lLoc = new ResourceLocation(HavensDenUtilities.MODID + ":" + pName);
		SoundEvent lEvent = new SoundEvent(lLoc).setRegistryName(lLoc);
		
		GameRegistry.register(lEvent);
		
		return lEvent;
	}
}
