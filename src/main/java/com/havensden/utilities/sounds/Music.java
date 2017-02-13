package com.havensden.utilities.sounds;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

public class Music extends MovingSound 
{
	private EntityPlayer player;
	
	public Music(SoundEvent pEvent) 
	{
		super(pEvent, SoundCategory.VOICE);
		
		this.volume = 0.1F;
		this.category = SoundCategory.VOICE;
	}
	
	public void setPlayer(EntityPlayer pPlayer)
	{
		this.player = pPlayer;
	}
	
	public void setRepeat(boolean lRepeat)
	{
		this.repeat = lRepeat;
	}

	@Override
	public boolean canRepeat() 
	{
		return this.repeat;
	}

	@Override
	public float getVolume() 
	{
		return this.volume;
	}

	@Override
	public void update() 
	{
        this.xPosF = (float)this.player.posX;
        this.yPosF = (float)this.player.posY;
        this.zPosF = (float)this.player.posZ;
	}
	
}
