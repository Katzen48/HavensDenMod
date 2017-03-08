package com.havensden.utilities.sounds;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MusicPlayer 
{
	public static void playMusic(String pMusic, boolean pLoop)
	{
		if(pMusic != null)
		{
			Music lMusic = SoundFactory.getMusic(pMusic);
				
			if(lMusic != null)
			{
				lMusic.setPlayer(Minecraft.getMinecraft().player);
		
				lMusic.setRepeat(pLoop);
		
				Minecraft.getMinecraft().getSoundHandler().playSound(lMusic);
			}
		}
	}
	
	public static void stopMusic(String pMusic)
	{
		if(!pMusic.equals("none"))
		{
			Music lMusic = SoundFactory.getMusic(pMusic);
			
			if(lMusic != null)
			{
				Minecraft.getMinecraft().getSoundHandler().stopSound(lMusic);
			}	
		}
		else
		{			
			Music[] lMusics = SoundFactory.getMusics();
			
			for(Music lMusic : lMusics)
			{
				Minecraft.getMinecraft().getSoundHandler().stopSound(lMusic);
			}
		}
	}
}
